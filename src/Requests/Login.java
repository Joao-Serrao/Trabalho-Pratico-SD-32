package Requests;

import Communication.Communication;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Login {
    private final Communication communication;
    private ServerClientFacade facade;
    private static final Integer messageType = 1;

    protected Login(ServerClientFacade facade) {
        this.communication = new Communication();
        this.facade = facade;
    }

    protected Login() {
        this.communication = new Communication();
    }

    protected Boolean login(DataInputStream in) throws Exception{
        byte[] data = communication.receive(in);
        if (data.length == 0) {
            return false;
        }

        String[] stringData = (new String(data)).split("\\|");
        return this.facade.loginClient(stringData[0], stringData[1]);
    }

    protected Boolean send(DataOutputStream out, DataInputStream in, String Username, String Pass) throws Exception{
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
