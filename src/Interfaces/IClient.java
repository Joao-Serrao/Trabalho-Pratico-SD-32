package Interfaces;


import java.util.Map;
import java.util.Set;

public interface IClient {
    public Boolean register(String Username, String Pass) throws Exception;
    public Boolean login(String Username, String Pass) throws Exception;
    public Boolean put(String Key, byte[] value) throws Exception;
    public Boolean multiPut(Map<String,byte[]> pairs) throws Exception;
    public byte[] get(String key) throws Exception;
    public Map<String,byte[]> multiGet(Set<String> keys) throws Exception;
    public byte[] whenGet(String key, String keyCond, byte[] value) throws Exception;
    public void logout() throws Exception;
}
