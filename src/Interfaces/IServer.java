package Interfaces;

import java.io.IOException;
import java.net.SocketAddress;

public interface IServer {
    public SocketAddress getIp() throws Exception;
    public void listen() throws Exception;
    public void CloseServer() throws IOException;
}
