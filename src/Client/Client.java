package Client;

import Requests.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Client {
    private Socket socket;
    private int port;
    private DataOutputStream out;
    private DataInputStream in;
    private Integer maxRequests;
    private Integer requests = 0;
    private Integer paralel = 0;
    private Integer maxParalel;
    private Request request = new Request();
    public Boolean aut = false;
    public Boolean reg = false;

    public Client(int port, int mR, int mP) throws Exception{
        this.socket = new Socket("localhost",port);
        this.port = port;
        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.in = new DataInputStream(this.socket.getInputStream());
        this.maxRequests = mR;
        this.maxParalel = mP;
    }

    public Boolean register(String Username, String Pass) throws Exception{
        if (this.socket.isClosed()) {
            Socket s = new Socket("localhost", this.port);
            this.socket = s;
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(this.socket.getInputStream());
        }
        return this.request.requestRegister(this.out, this.in, Username, Pass);
    }

    public Boolean login(String Username, String Pass) throws Exception{
        if (this.socket.isClosed()) {
            Socket s = new Socket("localhost", this.port);
            this.socket = s;
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(this.socket.getInputStream());
        }
        return this.request.requestLogin(this.out, this.in, Username, Pass);
    }

    public Boolean put(String Key, byte[] value) throws Exception{
        this.requests += 1;
        Boolean result = this.request.requestPut(this.out, this.in, Key, value);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

    public Boolean multiPut(Map<String,byte[]> pairs) throws Exception{
        this.requests += 1;
        Boolean result = this.request.requestMultiPut(this.out, this.in, pairs);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

    public byte[] get(String key) throws Exception{
        this.requests += 1;
        byte[] result = this.request.requestGet(this.out, this.in, key);
        if (this.requests == this.maxRequests) {
            this.logout();
        }
        return result;

    }

    public Map<String,byte[]> multiGet(Set<String> keys) throws Exception{
        Map<String, byte[]> result = this.request.requestMultiGet(this.out, this.in, keys);
        if (this.requests == this.maxRequests) {
            this.logout();
            this.requests = 0;
        }
        return result;
    }

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

    public static void main(String[] args) throws Exception {
        Client c = new Client(12345, 3, 3);
        System.out.println( c.register("Joao", "1234"));
        System.out.println( c.login("Joao", "1234"));
        System.out.println( c.put("hello", "world".getBytes()));
        byte[] data = c.get("hello");
        String s = new String(data);
        System.out.println(s);

        Thread.sleep(10000);


    }
}
