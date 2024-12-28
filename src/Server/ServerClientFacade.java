package Server;

import java.util.Map;
import java.util.Set;

public class ServerClientFacade {
    ServerMain server;
    public ServerClientFacade(ServerMain server) {
        this.server = server;
    }

    public Boolean registerClient(String UserName, String Pass) throws Exception {
        return server.registerClient(UserName, Pass);
    }

    public Boolean loginClient(String Username, String Pass) throws Exception {
        return server.loginClient(Username, Pass);
    }

    public void put(String key, byte[] value) throws Exception {
        server.put(key, value);
    }

    public void multiPut(Map<String, byte[]> pairs) throws Exception {
        server.multiPut(pairs);
    }

    public byte[] get(String key) throws Exception {
        return server.get(key);
    }

    public Map<String, byte[]> multiGet(Set<String> keys) throws Exception {
        return server.multiGet(keys);
    }

    public byte[] whenGet(String key, String keyCond, byte[] value) throws Exception{
        return server.getWhen(key, keyCond, value);
    }
}
