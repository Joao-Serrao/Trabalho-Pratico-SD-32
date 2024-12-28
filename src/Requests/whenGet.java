package Requests;

import Communication.Communication;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class whenGet {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 7;

    protected whenGet(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected whenGet() {
        this.communication = new Communication();
    }

    protected byte[] whenGet(DataInputStream in, Boolean t) throws Exception{
        byte[] data = this.communication.receive(in);
        byte[] data2 = this.communication.receive(in);
        byte[] data3 = this.communication.receive(in);
        if (data.length == 0 || data2.length == 0 || data3.length == 0) {
            return data;
        }

        if (t) {
            String stringData = new String(data);
            String stringData2 = new String(data2);
            return this.facade.whenGet(stringData, stringData2, data3);
        }
        else {
            return new byte[0];
        }
    }

    protected byte[] send(DataOutputStream out, DataInputStream in, String key, String keyCond, byte[] value) throws Exception{
        List<byte[]> l = new ArrayList<>();
        l.add( key.getBytes());
        l.add(keyCond.getBytes());
        l.add(value);

        out.writeInt(messageType);
        this.communication.multiSend(l,out);
        int response = in.readInt();
        if (response == 0) {
            return new byte[0];
        }
        else {
            return this.communication.receive(in);
        }
    }
}
