package Requests;

import Communication.Communication;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

public class MultiGet {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 5;

    protected MultiGet(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected MultiGet() {
        this.communication = new Communication();
    }

    protected Map<String, byte[]> multiGet(DataInputStream in, Boolean t) throws Exception{
        int e = in.readInt();

        List<String> keys = new ArrayList<>();
        for(int j=0; j < e; j++) {
            byte[] data = this.communication.receive(in);
            if (data.length == 0) {
                return new HashMap<>();
            }

            keys.add(new String(data));
        }

        if (t) {
            Set<String> key = new HashSet<>(keys);

            return this.facade.multiGet(key);
        }
        else {
            return new HashMap<>(0);
        }
    }

    protected Map<String,byte[]> send(DataOutputStream out, DataInputStream in, Set<String> keys) throws Exception{
        List<byte[]> data = new ArrayList<>();

        keys.forEach(k -> {
            data.add(k.getBytes());
        });

        out.writeInt(messageType);
        out.writeInt(keys.size());
        this.communication.multiSend(data,out);

        int response = in.readInt();
        if (response == 0) {
            return new HashMap<>(0);
        }
        else {
            Map<String,byte[]> pairs = new HashMap<>();
            int i = in.readInt();
            for(int j=0; j < i; j++) {
                byte[] byteKey = this.communication.receive(in);
                byte[] value = this.communication.receive(in);

                pairs.put(new String(byteKey), value);
            }
            return pairs;
        }
    }

}
