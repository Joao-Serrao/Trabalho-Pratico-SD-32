package Server;

import Interfaces.IServerCLient;

import java.util.Map;
import java.util.Set;

public class ServerClientFacade implements IServerCLient {
    ServerMain server;
    public ServerClientFacade(ServerMain server) {
        this.server = server;
    }

    @Override
    public Boolean registerClient(String UserName, String Pass) throws Exception {
        return server.registerClient(UserName, Pass);
    }

    @Override
    public Boolean loginClient(String Username, String Pass) throws Exception {
        return server.loginClient(Username, Pass);
    }

    @Override
    public void put(String key, byte[] value) throws Exception {
        server.put(key, value);
    }

    @Override
    public void multiPut(Map<String, byte[]> pairs) throws Exception {
        server.multiPut(pairs);
    }

    @Override
    public byte[] get(String key) throws Exception {
        return server.get(key);
    }

    @Override
    public Map<String, byte[]> multiGet(Set<String> keys) throws Exception {
        return server.multiGet(keys);
    }

    @Override
    public byte[] whenGet(String key, String keyCond, byte[] value) throws Exception{
        return server.getWhen(key, keyCond, value);
    }
}
