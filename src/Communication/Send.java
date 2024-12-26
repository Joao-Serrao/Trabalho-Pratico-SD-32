package Communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Send {
    protected void send(byte[] data, DataOutputStream out) throws IOException {
        out.writeInt(data.length);
        out.write(data);
        out.flush();
    }

    protected void multiSend(List<byte[]> data, DataOutputStream out){
        data.forEach(d -> {
            try {
                this.send(d, out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
