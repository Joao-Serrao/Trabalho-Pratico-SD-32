package Interfaces;

import java.io.DataInputStream;
import java.util.Map;

public interface IResponse {
    public Boolean registerClient(DataInputStream in) throws Exception;
    public Boolean login(DataInputStream in) throws Exception;
    public Boolean put(DataInputStream in, Boolean t) throws Exception;
    public Boolean multiPut(DataInputStream in, Boolean t) throws Exception;
    public byte[] get(DataInputStream in, Boolean t) throws Exception;
    public Map<String, byte[]> multiGet(DataInputStream in, Boolean t) throws Exception;
    public byte[] whenGet(DataInputStream in, Boolean t) throws Exception;
}
