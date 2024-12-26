package Requests;

import Communication.Communication;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Get {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 4;

    protected Get(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected Get() {
        this.communication = new Communication();
    }

    protected byte[] get(DataInputStream in) throws Exception{
        byte[] data = this.communication.receive(in);
        if (data.length == 0) {
            return data;
        }

        String stringData = new String(data);
        return this.facade.get(stringData);
    }

    protected byte[] send(DataOutputStream out, DataInputStream in, String key) throws Exception{
        byte[] byteData = key.getBytes();

        out.writeInt(messageType);
        this.communication.send(byteData,out);

        int response = in.readInt();
        if (response == 0) {
            return new byte[0];
        }
        else {
            return this.communication.receive(in);
        }
    }
}
