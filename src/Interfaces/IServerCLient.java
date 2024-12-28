package Interfaces;

import java.util.Map;
import java.util.Set;

public interface IServerCLient {
    public Boolean registerClient(String UserName, String Pass) throws Exception;
    public Boolean loginClient(String Username, String Pass) throws Exception;
    public void put(String key, byte[] value) throws Exception;
    public void multiPut(Map<String, byte[]> pairs) throws Exception;
    public byte[] get(String key) throws Exception;
    public Map<String, byte[]> multiGet(Set<String> keys) throws Exception;
    public byte[] whenGet(String key, String keyCond, byte[] value) throws Exception;

}
