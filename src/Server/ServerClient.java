package Server;

import Requests.Response;
import Communication.Communication;

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
    private Boolean aut = false;

    protected ServerClient (ServerMain server, Socket c) throws IOException {
        this.server = server;
        this.c = c;
        this.inputStream = new DataInputStream(c.getInputStream());
        this.outputStream = new DataOutputStream(c.getOutputStream());
    }


    private void listen() throws Exception{
        try {
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
                        } else {
                            this.outputStream.writeInt(1);
                        }
                        break;
                    case 1:
                        if (this.aut) {
                            this.outputStream.writeInt(1);
                        } else {
                            Boolean logined = response.login(this.inputStream);
                            if (!logined) {
                                this.outputStream.writeInt(0);
                            } else {
                                this.aut = true;
                                this.outputStream.writeInt(1);
                            }
                        }
                        break;
                    case 2:
                        if (this.aut) {
                            Boolean saved = response.put(this.inputStream);
                            if (!saved) {
                                this.outputStream.writeInt(0);
                            } else {
                                this.outputStream.writeInt(1);
                            }
                        } else {
                            this.outputStream.writeInt(0);
                        }
                        break;
                    case 3:
                        if (this.aut) {
                            byte[] data = response.get(this.inputStream);
                            if (data.length == 0) {
                                this.outputStream.writeInt(0);
                            } else {
                                this.outputStream.writeInt(1);
                            }
                            Communication c = new Communication();
                            c.send(data, this.outputStream);
                        }
                        this.outputStream.writeInt(0);
                        Communication c = new Communication();
                        c.send(new byte[0], this.outputStream);
                        break;
                    case 4:
                        if (this.aut) {
                            boolean msaved = response.multiPut(this.inputStream);
                            if (!msaved) {
                                this.outputStream.writeInt(0);
                            } else {
                                this.outputStream.writeInt(1);
                            }
                        } else {
                            this.outputStream.writeInt(0);
                        }
                        break;
                    case 5:
                        if (this.aut) {
                            Map<String, byte[]> map = response.multiGet(this.inputStream);
                            if (map.isEmpty()) {
                                this.outputStream.writeInt(0);
                            } else {
                                this.outputStream.writeInt(1);
                            }
                            this.outputStream.flush();
                            Communication c1 = new Communication();
                            List<byte[]> data = new ArrayList<>();

                            map.forEach((key, value) -> {
                                byte[] byteData = key.getBytes();
                                data.add(byteData);
                                data.add(value);
                            });


                            this.outputStream.writeInt(map.size());
                            c1.multiSend(data, this.outputStream);

                        } else {
                            this.outputStream.writeInt(0);
                        }
                        break;
                    case 6:
                        if (this.aut) {
                            this.server.logout();
                        }
                        this.close();
                        break;

                }
            }
        } catch (Exception e) {
            if (this.aut) {
                this.server.logout();
            }
            this.close();
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
