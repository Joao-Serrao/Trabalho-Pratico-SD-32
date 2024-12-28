package Communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Send {
    protected void send(byte[] data, DataOutputStream out) throws Exception {
        out.writeInt(data.length);
        out.write(data);
        out.flush();
    }

    protected void multiSend(List<byte[]> data, DataOutputStream out) throws Exception{
        data.forEach(d -> {
            try {
                this.send(d, out);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
