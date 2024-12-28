package Interfaces;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Map;
import java.util.Set;

public interface IRequest {
    public Boolean requestRegister(DataOutputStream out, DataInputStream in, String Username, String Pass) throws Exception;
    public Boolean requestLogin(DataOutputStream out, DataInputStream in, String Username, String Pass) throws Exception;
    public Boolean requestPut(DataOutputStream out, DataInputStream in, String key, byte[] value) throws Exception;
    public Boolean requestMultiPut(DataOutputStream out, DataInputStream in, Map<String,byte[]> pairs) throws Exception;
    public byte[] requestGet(DataOutputStream out, DataInputStream in, String key) throws Exception;
    public Map<String, byte[]> requestMultiGet(DataOutputStream out, DataInputStream in, Set<String> keys) throws Exception;
    public byte[] requestWhenGet(DataOutputStream out, DataInputStream in, String key, String keyCond, byte[] value) throws Exception;
}
