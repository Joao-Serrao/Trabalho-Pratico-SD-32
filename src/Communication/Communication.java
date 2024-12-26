package Communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Communication {
    Receive r = new Receive();
    Send s = new Send();

    public byte[] receive(DataInputStream in) throws Exception {
        return this.r.receive(in);
    }

    public void send(byte[] data, DataOutputStream out) throws Exception {
        this.s.send(data, out);
    }

    public void multiSend(List<byte[]> data, DataOutputStream out) throws Exception {
        this.s.multiSend(data,out);
    }
}
