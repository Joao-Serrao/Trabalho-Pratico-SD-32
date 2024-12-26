package Communication;

import java.io.DataInputStream;
import java.io.IOException;

public class Receive {
    protected byte[] receive(DataInputStream in) throws IOException {
        int len = in.readInt();

        if (len < 0){
            return new byte[0];
        }
        byte[] data = new byte[len];
        in.readFully(data); // Read the message content
        return data;
    }
}
