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
    private DataOutputStream out;
    private DataInputStream in;
    private Integer maxRequests;
    private Integer requests = 0;
    private Integer paralel = 0;
    private Integer maxParalel;
    private Request request = new Request();

    public Client(int address, int mR, int mP) throws Exception{
        this.socket = new Socket("localhost",address);
        System.out.println(this.socket.getLocalSocketAddress().toString());
        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.in = new DataInputStream(this.socket.getInputStream());
        this.maxRequests = mR;
        this.maxParalel = mP;
    }

    public Boolean register(String Username, String Pass) throws Exception{
        return this.request.requestRegister(this.out, this.in, Username, Pass);
    }

    public Boolean login(String Username, String Pass) throws Exception{
        return this.request.requestLogin(this.out, this.in, Username, Pass);
    }

    public Boolean put(String Key, byte[] value) throws Exception{
        return this.request.requestPut(this.out, this.in, Key, value);
    }

    public Boolean multiPut(Map<String,byte[]> pairs) throws Exception{
        return this.request.requestMultiPut(this.out, this.in, pairs);
    }

    public byte[] get(String key) throws Exception{
        return this.request.requestGet(this.out, this.in, key);
    }

    public Map<String,byte[]> multGet(Set<String> keys) throws Exception{
        return this.request.requestMultiGet(this.out, this.in, keys);
    }

    public String logout() throws Exception{
        while(this.paralel != 0) {
            Thread.sleep(10);
        }
        this.out.writeInt(6);
        this.out.flush();

        try {
            Integer a = in.readInt();
        } catch (EOFException e) {
            this.socket.close();
        }
        return "End of Connection!";
    }

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("0.0.0.0", 12345);

        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());

        out.writeInt(0);
        String data = "Joao|1234";
        byte[] b = data.getBytes();
        out.writeInt(b.length);
        out.write(b);
        out.flush();

        out.writeInt(1);
        out.writeInt(b.length);
        out.write(b);
        out.flush();

        out.writeInt(2);
        data = "Joao";
        b = data.getBytes();
        out.writeInt(b.length);
        out.write(b);
        data = "12345";
        b = data.getBytes();
        out.writeInt(b.length);
        out.write(b);
        out.flush();

        out.writeInt(3);
        data = "Joao";
        b = data.getBytes();
        out.writeInt(b.length);
        out.write(b);
        out.flush();

        Map<String,byte[]> map = new HashMap<>();
        map.put("Duarte", "password".getBytes());
        map.put("Diogo", "burro".getBytes());

        out.writeInt(4);
        out.writeInt(map.size());
        map.forEach((key,value) -> {
            byte[] b1 = key.getBytes();
            try {
                out.writeInt(b1.length);
                out.write(b1);
                out.writeInt(value.length);
                out.write(value);
                out.flush();
            } catch (IOException e){
                System.out.println("Error!");
            }
        });

        Set<String> keys = Set.of("Joao","Duarte","Diogo");

        out.writeInt(5);
        out.writeInt(keys.size());
        keys.forEach(s1 -> {
            byte[] b2 = s1.getBytes();
            try {
                out.writeInt(b2.length);
                out.write(b2);
                out.flush();
            } catch (IOException e){
                System.out.println("Error!");
            }
        });

        out.writeInt(6);
        out.flush();

        try {
            Integer a = in.readInt();
        } catch (EOFException e) {
            System.out.println("End of Connection!");
        }

        Thread.sleep(10000);


    }
}
