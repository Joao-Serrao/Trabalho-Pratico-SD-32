package Requests;

import Interfaces.IRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Map;
import java.util.Set;

public class Request implements IRequest {
    private Register register = new Register();
    private Login login = new Login();
    private Put put = new Put();
    private MultiPut multiPut = new MultiPut();
    private Get get = new Get();
    private MultiGet multiGet = new MultiGet();

    @Override
    public Boolean requestRegister(DataOutputStream out, DataInputStream in, String Username, String Pass) throws Exception{
        return this.register.send(out,in,Username,Pass);
    }

    @Override
    public Boolean requestLogin(DataOutputStream out, DataInputStream in, String Username, String Pass) throws Exception{
        return this.login.send(out,in,Username,Pass);
    }

    @Override
    public Boolean requestPut(DataOutputStream out, DataInputStream in, String key, byte[] value) throws Exception{
        return this.put.send(out,in,key, value);
    }

    @Override
    public Boolean requestMultiPut(DataOutputStream out, DataInputStream in, Map<String,byte[]> pairs) throws Exception{
        return this.multiPut.send(out,in,pairs);
    }

    @Override
    public byte[] requestGet(DataOutputStream out, DataInputStream in, String key) throws Exception{
        return this.get.send(out,in,key);
    }

    @Override
    public Map<String, byte[]> requestMultiGet(DataOutputStream out, DataInputStream in, Set<String> keys) throws Exception{
        return this.multiGet.send(out,in,keys);
    }
}
