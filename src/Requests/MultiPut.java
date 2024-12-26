package Requests;

import Communication.Communication;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiPut {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 4;

    protected MultiPut(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected MultiPut() {
        this.communication = new Communication();
    }

    protected Boolean multiPut(DataInputStream in) throws Exception{
        int e = in.readInt();

        Map<String,byte[]> map = new HashMap<>();
        for (int j =0 ; j < e; j++){
            byte[] data = this.communication.receive(in);
            byte[] value = this.communication.receive(in);
            if (data.length == 0 || value.length == 0) {
                return false;
            }
            String key = new String(data);
            map.put(key,value);
        }

        this.facade.multiPut(map);
        return true;
    }

    protected Boolean send(DataOutputStream out, DataInputStream in, Map<String,byte[]> pairs) throws Exception{
        List<byte[]> data = new ArrayList<>();

        pairs.forEach((key,value) -> {
            byte[] byteData = key.getBytes();
            data.add(byteData);
            data.add(value);
        });

        out.writeInt(4);
        out.writeInt(pairs.size());
        this.communication.multiSend(data,out);

        int response = in.readInt();
        if (response == 0) {
            return false;
        }
        else {
            return true;
        }
    }
}
