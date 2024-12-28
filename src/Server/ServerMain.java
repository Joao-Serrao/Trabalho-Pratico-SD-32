package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ServerMain implements Runnable{
    private Map<String,String> registered = new HashMap<>();
    private Map<String,byte[]> storage = new HashMap<>();
    private Integer aut = 0;
    private Integer S;
    private ServerSocket ss;
    private Lock lock = new ReentrantLock();
    private Condition readCond = lock.newCondition();
    private Condition writeCond = lock.newCondition();
    private Condition regCond = lock.newCondition();
    private Condition loginCond = lock.newCondition();
    private Integer readers = 0;
    private Integer writers = 0;
    private Integer registers = 0;
    private Integer loginers = 0;
    private List<ServerClient> sc = new ArrayList<>();
    private List<Thread> t = new ArrayList<>();
    private Boolean running = true;
    private ServerFacade facade;

    protected ServerMain (ServerSocket ss, Integer S, ServerFacade facade) {
        this.ss = ss;
        this.S = S;
        this.facade = facade;
    }

    protected Boolean registerClient(String UserName, String Pass) throws Exception{
        if (!this.registered.containsKey(UserName)) {
            this.lock.lock();
            try {
                while(this.registers != 0 || this.loginers != 0) {
                    this.regCond.await();
                }
                this.registers += 1;
                this.registered.put(UserName, Pass);
                return true;

            }
            finally {
                this.registers -= 1;
                this.regCond.signal();
                this.loginCond.signal();
                this.lock.unlock();
            }
        }
        else {
            return false;
        }
    }

    protected Boolean loginClient(String Username, String Pass) throws Exception{
        this.lock.lock();
        try {
            this.loginers += 1;
            while(this.registers != 0 || this.aut >= this.S) {
                this.loginCond.await();
            }
            if (this.registered.containsKey(Username)) {
                if (this.registered.get(Username).equals(Pass)) {
                    this.aut += 1;
                    return true;
                }
            }
            return false;
        }
        finally {
            this.loginers -= 1;
            this.loginCond.signal();
            this.regCond.signal();
            this.lock.unlock();
        }
    }

    protected void logout() throws Exception{
        this.lock.lock();
        try {
            this.aut -= 1;
        }
        finally {
            this.loginCond.signal();
            this.lock.unlock();
        }
    }

    protected void put(String key, byte[] value) throws Exception{
        this.lock.lock();
        try {
            while(this.writers != 0 || this.readers != 0) {
                this.writeCond.await();
            }
            this.writers += 1;
            this.storage.put(key,value);
        }
        finally {
            this.writers = 0;
            this.writeCond.signal();
            this.readCond.signalAll();
            this.lock.unlock();
        }
    }

    protected void multiPut(Map<String,byte[]> pairs) throws Exception{
        this.lock.lock();
        try {
            while(this.writers != 0 || this.readers !=0) {
                this.writeCond.await();
            }
            this.writers += 1;
            this.storage.putAll(pairs);
        }
        finally {
            this.writers = 0;
            this.writeCond.signal();
            this.readCond.signalAll();
            this.lock.unlock();
        }
    }

    protected byte[] get(String key) throws Exception{
        this.lock.lock();
        try {
            this.readers += 1;
            while(this.writers != 0) {
                this.readCond.await();
            }
            if (this.storage.containsKey(key)) {
                return this.storage.get(key);
            }
            else {
                return new byte[0];
            }
        }
        finally {
            this.readers -= 1;
            this.readCond.signalAll();
            this.writeCond.signal();
            this.lock.unlock();
        }
    }

    protected  Map<String,byte[]> multiGet(Set<String> keys) throws Exception{
        this.lock.lock();
        try {
            this.readers += 1;
            while(this.writers != 0) {
                this.readCond.await();
            }
            Map<String,byte[]> map = new HashMap<>();
            keys.forEach(s -> {
                if (this.storage.containsKey(s)) {
                    map.put(s,this.storage.get(s));
                }
                else {
                    map.put(s, new byte[0] );
                }
            });

            return map;
        }
        finally {
            this.readers -= 1;
            this.readCond.signalAll();
            this.writeCond.signal();
            this.lock.unlock();
        }
    }

    protected byte[] getWhen(String key, String keyCond, byte[] valueCond) throws Exception{
        this.lock.lock();
        try {
            while (this.writers != 0 || !Arrays.equals(this.storage.get(keyCond), valueCond)) {
                this.readCond.await();
            }
            this.readers += 1;
            if (this.storage.containsKey(key)) {
                return this.storage.get(key);
            }
            else {
                return new byte[0];
            }
        }
        finally {
            this.readers -=1;
            this.readCond.signal();
            this.writeCond.signal();
            this.lock.unlock();
        }
    }

    protected void listen() throws Exception {
        this.running = true;
        try {
            while (this.running) {
                Socket c = this.ss.accept();
                ServerClient sc = new ServerClient(this, c);
                this.sc.add(sc);
                Thread t = new Thread(sc);
                this.t.add(t);
                t.start();
            }
        } catch (Exception e) {
            System.out.println("Server CLosing!");
        }
        this.sc.forEach(sc -> {
            try {
                sc.close();
                System.out.println("Closing Connection");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    protected void removeSC(ServerClient sc) throws Exception{
        this.sc.remove(sc);
    }

    protected void CloseServer() throws IOException {
        this.running = false;
        this.ss.close();
    }

    @Override
    public void run() {
        try {
            this.listen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected ServerSocket getSocket() {
        return this.ss;
    }

}
