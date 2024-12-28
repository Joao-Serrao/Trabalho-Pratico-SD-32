package Requests;

import Communication.Communication;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Put {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 2;

    protected Put(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected Put() {
        this.communication = new Communication();
    }

    protected Boolean put(DataInputStream in, Boolean t) throws Exception{
        byte[] data = this.communication.receive(in);
        //int type = in.readInt();
        byte[] value = this.communication.receive(in);

        if (data.length == 0 || value.length == 0) {
            return false;
        }

        if (t) {
        String stringData = new String(data);
            this.facade.put(stringData, value);

            return true;
        }
        else {
            return false;
        }
    }

    protected Boolean send(DataOutputStream out, DataInputStream in, String Key, byte[] value) throws Exception{

        byte[] byteData = Key.getBytes();
        List<byte[]> data = new ArrayList<>();

        data.add(byteData);
        data.add(value);

        out.writeInt(messageType);
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
