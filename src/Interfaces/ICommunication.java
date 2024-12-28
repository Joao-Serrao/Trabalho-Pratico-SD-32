package Interfaces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public interface ICommunication {
    public byte[] receive(DataInputStream in) throws Exception;
    public void send(byte[] data, DataOutputStream out) throws Exception;
    public void multiSend(List<byte[]> data, DataOutputStream out) throws Exception;
}
