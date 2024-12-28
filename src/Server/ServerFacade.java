package Server;

import Interfaces.IServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class ServerFacade implements IServer {
    private ServerMain server;

    public ServerFacade(Integer port, Integer S) throws Exception{
        //InetAddress bindAddress = InetAddress.getByName("0.0.0.0");
        //InetSocketAddress socketAddress = new InetSocketAddress(bindAddress, port);

        // Create the ServerSocket and bind it to the specified address
        ServerSocket serverSocket = new ServerSocket(port);
        //serverSocket.bind(socketAddress);
        this.server = new ServerMain(serverSocket,S,this);
    }

    @Override
    public SocketAddress getIp() throws Exception{
        return this.server.getSocket().getLocalSocketAddress();
    }

    @Override
    public void listen() throws Exception {
        System.out.println("run");
        Thread t = new Thread(this.server);
        t.start();
    }

    @Override
    public void CloseServer() throws IOException {
        this.server.CloseServer();
    }

}
