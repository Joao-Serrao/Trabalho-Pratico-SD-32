package Server;

import Requests.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

class ServerClient implements Runnable {
    private ServerMain server;
    private Socket c;
    private Boolean running = true;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    protected ServerClient (ServerMain server, Socket c) throws IOException {
        this.server = server;
        this.c = c;
        this.inputStream = new DataInputStream(c.getInputStream());
        this.outputStream = new DataOutputStream(c.getOutputStream());
    }


    private void listen() throws Exception{
        System.out.println("Hello");
        ServerClientFacade facade = new ServerClientFacade(this.server);
        Response response = new Response(facade);
        while (running) {
            int tag = this.inputStream.readInt();
            System.out.println(tag);
            switch (tag) {
                case 0:
                    Boolean success = response.registerClient(this.inputStream);
                    if (!success) {
                        this.outputStream.writeInt(0);
                    }
                    else {
                        this.outputStream.writeInt(1);
                    }
                    break;
                case 1:
                    Boolean logined = response.login(this.inputStream);
                    if (!logined) {
                        System.out.println("Can't login");
                    }
                    else {
                        System.out.println("Can login");
                    }
                    break;
                case 2:
                    Boolean saved = response.put(this.inputStream);
                    if (!saved) {
                        System.out.println("Not Saved!");
                    }
                    else {
                        System.out.println("Saved!");
                    }
                    break;
                case 3:
                    byte[] data = response.get(this.inputStream);
                    if (data.length == 0) {
                        System.out.println("no value!");
                    }
                    else {
                        System.out.println(new String(data));
                    }
                case 4:
                    boolean msaved = response.multiPut(this.inputStream);
                    if (!msaved) {
                        System.out.println("Not Saved!");
                    }
                    else {
                        System.out.println("Saved!");
                    }
                    break;
                case 5:
                    Map<String,byte[]> map = response.multiGet(this.inputStream);
                    if (map.isEmpty()) {
                        System.out.println("no value!");
                    }
                    else {
                        map.forEach((key,value) -> {
                            System.out.println(new String(value));
                        });
                    }
                    break;
                case 6:
                    this.server.logout();
                    this.close();

            }
        }
        this.c.close();
    }

    protected void close() throws Exception{
        this.running = false;
    }

    @Override
    public void run() {
        try {
            this.listen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
