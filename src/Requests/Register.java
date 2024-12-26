package Requests;


import Server.ServerClientFacade;
import Communication.Communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Register {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 0;

    protected Register(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected Register() {
        this.communication = new Communication();
    }

    protected Boolean registerClient(DataInputStream in) throws Exception {
        // Use the communication instance to call receive()
        byte[] data = communication.receive(in);
        if (data.length == 0) {
            return false;
        }

        String[] stringData = (new String(data)).split("\\|");
        return this.facade.registerClient(stringData[0], stringData[1]);
    }

    protected Boolean send(DataOutputStream out, DataInputStream in, String Username, String Pass) throws Exception {
        String data = Username + '|' + Pass;
        byte[] byteData = data.getBytes();

        out.writeInt(messageType);
        this.communication.send(byteData, out);

        int response = in.readInt();

        if (response == 0) {
            return false;
        }
        else {
            return true;
        }

    }
}
