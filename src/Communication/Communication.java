package Communication;

import Interfaces.ICommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Communication implements ICommunication{
    Receive r = new Receive();
    Send s = new Send();

    @Override
    public byte[] receive(DataInputStream in) throws Exception {
        return this.r.receive(in);
    }

    @Override
    public void send(byte[] data, DataOutputStream out) throws Exception {
        this.s.send(data, out);
    }

    @Override
    public void multiSend(List<byte[]> data, DataOutputStream out) throws Exception {
        this.s.multiSend(data,out);
    }
}
