package Client;

import Interfaces.IClient;
import Requests.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class Client implements IClient {
    private Socket socket;
    private final int port;
    private DataOutputStream out;
    private DataInputStream in;
    private Integer maxRequests;
    private Integer requests = 0;
    private Integer paralel = 0;
    private Integer maxParalel;
    private Request request = new Request();
    private Boolean aut = false;
    private Boolean reg = false;

    public Client(int port, int mR, int mP) throws Exception{
        this.socket = new Socket("localhost",port);
        this.port = port;
        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.in = new DataInputStream(this.socket.getInputStream());
        this.maxRequests = mR;
        this.maxParalel = mP;
    }

    @Override
    public Boolean register(String Username, String Pass) throws Exception{
        if (this.socket.isClosed()) {
            this.socket = new Socket("localhost", this.port);
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(this.socket.getInputStream());
        }
        return this.request.requestRegister(this.out, this.in, Username, Pass);
    }

    @Override
    public Boolean login(String Username, String Pass) throws Exception{
        if (this.socket.isClosed()) {
            this.socket = new Socket("localhost", this.port);
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(this.socket.getInputStream());
        }
        try {
            return this.request.requestLogin(this.out, this.in, Username, Pass);
        } catch (Exception e) {
            this.socket = new Socket("localhost", this.port);
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(this.socket.getInputStream());
            return this.request.requestLogin(this.out, this.in, Username, Pass);
        }
    }

    @Override
    public Boolean put(String Key, byte[] value) throws Exception{
        this.requests += 1;
        Boolean result = this.request.requestPut(this.out, this.in, Key, value);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

    @Override
    public Boolean multiPut(Map<String,byte[]> pairs) throws Exception{
        this.requests += 1;
        Boolean result = this.request.requestMultiPut(this.out, this.in, pairs);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

    @Override
    public byte[] get(String key) throws Exception{
        this.requests += 1;
        byte[] result = this.request.requestGet(this.out, this.in, key);
        if (this.requests == this.maxRequests) {
            this.logout();
        }
        return result;

    }

    @Override
    public Map<String,byte[]> multiGet(Set<String> keys) throws Exception{
        this.requests += 1;
        Map<String, byte[]> result = this.request.requestMultiGet(this.out, this.in, keys);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

    @Override
    public byte[] whenGet(String key, String keyCond, byte[] value) throws Exception{
        this.requests += 1;
        byte[] result = this.request.requestWhenGet(this.out, this.in, key, keyCond, value);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

    public Boolean getAut(){
        return this.aut;
    }

    public void setAut(Boolean t){
        this.aut = t;
    }

    public Boolean getReg() {
        return this.reg;
    }

    public void setReg(Boolean t) {
        this.reg = t;
    }

    public void setMaxRequests(int r){
        this.maxRequests = r;
    }

    public void setRequest(int r){
        this.requests = r;
    }

    @Override
    public void logout() throws Exception{
        while(this.paralel != 0) {
            Thread.sleep(10);
        }
        this.requests = 0;
        this.out.writeInt(6);
        this.out.flush();

        try {
            Integer a = in.readInt();
        } catch (EOFException e) {
            this.socket.close();
        }
        this.aut = false;
    }

}
