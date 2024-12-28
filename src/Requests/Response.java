package Requests;

import Interfaces.IResponse;
import Server.ServerClientFacade;

import java.io.DataInputStream;
import java.util.Map;

public class Response implements IResponse {
    private Register register;
    private Login login;
    private Put put;
    private MultiPut multiPut;
    private Get get;
    private MultiGet multiGet;
    private whenGet whenGet;

    public Response(ServerClientFacade facade) {
        this.register = new Register(facade);
        this.login = new Login(facade);
        this.put = new Put(facade);
        this.multiPut = new MultiPut(facade);
        this.get = new Get(facade);
        this.multiGet = new MultiGet(facade);
        this.whenGet = new whenGet(facade);
    }

    @Override
    public Boolean registerClient(DataInputStream in) throws Exception{
        return this.register.registerClient(in);
    }

    @Override
    public Boolean login(DataInputStream in) throws Exception{
        return this.login.login(in);
    }

    @Override
    public Boolean put(DataInputStream in, Boolean t) throws Exception{
        return this.put.put(in,t);
    }

    @Override
    public Boolean multiPut(DataInputStream in, Boolean t) throws Exception{
        return this.multiPut.multiPut(in, t);
    }

    @Override
    public byte[] get(DataInputStream in, Boolean t) throws Exception{
        return this.get.get(in, t);
    }

    @Override
    public Map<String, byte[]> multiGet(DataInputStream in, Boolean t) throws Exception{
        return this.multiGet.multiGet(in, t);
    }

    @Override
    public byte[] whenGet(DataInputStream in, Boolean t) throws Exception{
        return this.whenGet.whenGet(in, t);
    }
}
