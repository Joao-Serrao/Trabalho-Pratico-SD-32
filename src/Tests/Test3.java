package Tests;

import Client.Client;

import java.util.*;

public class Test3 {public void run(Client c, String Username, String Pass) throws Exception{

    c.setMaxRequests(7);
    c.setRequest(0);

    System.out.println("Doing a Put");
    Boolean PutResult1 = c.put("Gabriel", "Rodrigues".getBytes());
    System.out.println(PutResult1);

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map1 = new HashMap<>();
    map1.put("Claudia", "Farias".getBytes());
    map1.put("Eduardo", "Almeida".getBytes());
    map1.put("Simone", "Oliveira".getBytes());
    map1.put("Felipe", "Costa".getBytes());
    Boolean MultiPutResult1 = c.multiPut(map1);
    System.out.println(MultiPutResult1);

    System.out.println("Doing a Get");
    byte[] GetResult1 = c.get("Felipe");
    System.out.println(new String(GetResult1));

    System.out.println("Doing a MultiGet");
    List<String> keysList1 = new ArrayList<>();
    keysList1.add("Gabriel");
    keysList1.add("Claudia");
    keysList1.add("Simone");
    keysList1.add("Eduardo");
    Set<String> keys1 = new HashSet<>(keysList1);
    Map<String, byte[]> MultiGetResult1 = c.multiGet(keys1);
    MultiGetResult1.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    System.out.println("Doing a Put");
    Boolean PutResult2 = c.put("Thiago", "Ribeiro".getBytes());
    System.out.println(PutResult2);

    System.out.println("Doing a Get");
    byte[] wResult1 = c.get("Felipe");
    System.out.println(new String(wResult1));

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map2 = new HashMap<>();
    map2.put("Mariana", "Martins".getBytes());
    map2.put("Antonio", "Silva".getBytes());
    map2.put("Luiza", "Alves".getBytes());
    Boolean MultiPutResult2 = c.multiPut(map2);
    System.out.println(MultiPutResult2);

    Thread.sleep(200);
    c.login(Username,Pass);
    c.setMaxRequests(2);

    System.out.println("Doing a MultiGet");
    List<String> keysList2 = new ArrayList<>();
    keysList2.add("Thiago");
    keysList2.add("Luiza");
    keysList2.add("Mariana");
    keysList2.add("Antonio");
    Set<String> keys2 = new HashSet<>(keysList2);
    Map<String, byte[]> MultiGetResult2 = c.multiGet(keys2);
    MultiGetResult2.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    System.out.println("Doing a Get");
    byte[] GetResult2 = c.get("Luiza");
    System.out.println(new String(GetResult2));

    Thread.sleep(500);
    c.login(Username,Pass);
    c.setMaxRequests(4);

    System.out.println("Doing a Put");
    Boolean PutResult3 = c.put("Cecilia", "Duarte".getBytes());
    System.out.println(PutResult3);

    System.out.println("Doing a Get");
    byte[] wResult2 = c.get("Mariana");
    System.out.println(new String(wResult2));

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map3 = new HashMap<>();
    map3.put("Lucia", "Goncalves".getBytes());
    map3.put("Raquel", "Souza".getBytes());
    map3.put("Renato", "Teixeira".getBytes());
    Boolean MultiPutResult3 = c.multiPut(map3);
    System.out.println(MultiPutResult3);

    System.out.println("Doing a MultiGet");
    List<String> keysList3 = new ArrayList<>();
    keysList3.add("Cecilia");
    keysList3.add("Lucia");
    keysList3.add("Renato");
    keysList3.add("Raquel");
    Set<String> keys3 = new HashSet<>(keysList3);
    Map<String, byte[]> MultiGetResult3 = c.multiGet(keys3);
    MultiGetResult3.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    Thread.sleep(100);
    c.login(Username,Pass);
    c.setMaxRequests(1);

    System.out.println("Doing a Get");
    byte[] GetResult3 = c.get("Raquel");
    System.out.println(new String(GetResult3));

    Thread.sleep(200);
    c.login(Username,Pass);
    c.setMaxRequests(3);

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map4 = new HashMap<>();
    map4.put("Diana", "Pereira".getBytes());
    map4.put("Sergio", "Mendes".getBytes());
    map4.put("Gustavo", "Nunes".getBytes());
    Boolean MultiPutResult4 = c.multiPut(map4);
    System.out.println(MultiPutResult4);

    System.out.println("Doing a Get");
    byte[] wResult3 = c.get("Sergio");
    System.out.println(new String(wResult3));

    System.out.println("Doing a MultiGet");
    List<String> keysList4 = new ArrayList<>();
    keysList4.add("Diana");
    keysList4.add("Gustavo");
    keysList4.add("Sergio");
    Set<String> keys4 = new HashSet<>(keysList4);
    Map<String, byte[]> MultiGetResult4 = c.multiGet(keys4);
    MultiGetResult4.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    Thread.sleep(450);
    c.login(Username,Pass);
    c.setMaxRequests(3);

    System.out.println("Doing a Get");
    byte[] GetResult4 = c.get("Diana");
    System.out.println(new String(GetResult4));

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map5 = new HashMap<>();
    map5.put("Paula", "Oliveira".getBytes());
    map5.put("Paulo", "Almeida".getBytes());
    map5.put("Marcio", "Ferreira".getBytes());
    Boolean MultiPutResult5 = c.multiPut(map5);
    System.out.println(MultiPutResult5);

    System.out.println("Doing a MultiGet");
    List<String> keysList5 = new ArrayList<>();
    keysList5.add("Paula");
    keysList5.add("Paulo");
    keysList5.add("Marcio");
    Set<String> keys5 = new HashSet<>(keysList5);
    Map<String, byte[]> MultiGetResult5 = c.multiGet(keys5);
    MultiGetResult5.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    Thread.sleep(200);
    c.login(Username,Pass);
    c.setMaxRequests(2);

    System.out.println("Doing a Put");
    Boolean PutResult4 = c.put("Lucas", "Santos".getBytes());
    System.out.println(PutResult4);

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map6 = new HashMap<>();
    map1.put("Vera", "Pereira".getBytes());
    map1.put("Miguel", "Costa".getBytes());
    map1.put("Tiago", "Nunes".getBytes());
    map1.put("Carla", "Souza".getBytes());
    Boolean MultiPutResult6 = c.multiPut(map6);
    System.out.println(MultiPutResult6);

    Thread.sleep(400);
    c.login(Username,Pass);
    c.setMaxRequests(5);

    System.out.println("Doing a Get");
    byte[] GetResult5 = c.get("Miguel");
    System.out.println(new String(GetResult5));

    System.out.println("Doing a Get");
    byte[] gResult = c.get("Tiago");
    System.out.println(new String(gResult));

    System.out.println("Doing a MultiGet");
    List<String> keysList6 = new ArrayList<>();
    keysList1.add("Vera");
    keysList1.add("Carla");
    keysList1.add("Tiago");
    keysList1.add("Miguel");
    Set<String> keys6 = new HashSet<>(keysList1);
    Map<String, byte[]> MultiGetResult6 = c.multiGet(keys6);
    MultiGetResult6.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    System.out.println("Doing a Put");
    Boolean PutResult5 = c.put("Joana", "Figueiredo".getBytes());
    System.out.println(PutResult5);

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map7 = new HashMap<>();
    map2.put("Rita", "Lima".getBytes());
    map2.put("Gustavo", "Costa".getBytes());
    map2.put("Pedro", "Alves".getBytes());
    Boolean MultiPutResult7 = c.multiPut(map7);
    System.out.println(MultiPutResult7);

    Thread.sleep(200);
    c.login(Username,Pass);
    c.setMaxRequests(2);

    System.out.println("Doing a Get");
    byte[] GetResult6 = c.get("Rita");
    System.out.println(new String(GetResult6));

    System.out.println("Doing a MultiGet");
    List<String> keysList7 = new ArrayList<>();
    keysList2.add("Gustavo");
    keysList2.add("Joana");
    keysList2.add("Pedro");
    keysList2.add("Rita");
    Set<String> keys7 = new HashSet<>(keysList7);
    Map<String, byte[]> MultiGetResult7 = c.multiGet(keys7);
    MultiGetResult7.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    Thread.sleep(500);
    c.login(Username,Pass);
    c.setMaxRequests(5);

    System.out.println("Doing a Get");
    byte[] gResult2 = c.get("Joana");
    System.out.println(new String(gResult2));

    System.out.println("Doing a Put");
    Boolean PutResult6 = c.put("Raul", "Mendes".getBytes());
    System.out.println(PutResult6);

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map8 = new HashMap<>();
    map3.put("Beatriz", "Oliveira".getBytes());
    map3.put("Sofia", "Ferreira".getBytes());
    map3.put("Ricardo", "Almeida".getBytes());
    Boolean MultiPutResult8 = c.multiPut(map8);
    System.out.println(MultiPutResult8);

    System.out.println("Doing a Get");
    byte[] GetResult7 = c.get("Raul");
    System.out.println(new String(GetResult7));

    System.out.println("Doing a MultiGet");
    List<String> keysList8 = new ArrayList<>();
    keysList3.add("Ricardo");
    keysList3.add("Raul");
    keysList3.add("Beatriz");
    keysList3.add("Sofia");
    Set<String> keys8 = new HashSet<>(keysList8);
    Map<String, byte[]> MultiGetResult8 = c.multiGet(keys8);
    MultiGetResult8.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    Thread.sleep(1000);
    c.login(Username,Pass);
    c.setMaxRequests(2);

    System.out.println("Doing a Get");
    byte[] gResult3 = c.get("Raul");
    System.out.println(new String(gResult3));

    System.out.println("Doing a Put");
    Boolean PutResult7 = c.put("Marco", "Pinto".getBytes());
    System.out.println(PutResult7);

    Thread.sleep(300);
    c.login(Username,Pass);
    c.setMaxRequests(3);

    System.out.println("Doing a Get");
    byte[] GetResult8 = c.get("Marco");
    System.out.println(new String(GetResult8));

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map9 = new HashMap<>();
    map4.put("Francisco", "Cruz".getBytes());
    map4.put("Daniel", "Santos".getBytes());
    map4.put("Leonor", "Ribeiro".getBytes());
    Boolean MultiPutResult9 = c.multiPut(map9);
    System.out.println(MultiPutResult9);

    System.out.println("Doing a MultiGet");
    List<String> keysList9 = new ArrayList<>();
    keysList4.add("Francisco");
    keysList4.add("Leonor");
    keysList4.add("Daniel");
    Set<String> keys9 = new HashSet<>(keysList9);
    Map<String, byte[]> MultiGetResult9 = c.multiGet(keys9);
    MultiGetResult9.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    Thread.sleep(500);
    c.login(Username,Pass);
    c.setMaxRequests(5);

    System.out.println("Doing a Get");
    byte[] gResult4 = c.get("Leonor");
    System.out.println(new String(gResult4));

    System.out.println("Doing a Put");
    Boolean PutResult8 = c.put("Clara", "Gomes".getBytes());
    System.out.println(PutResult8);

    System.out.println("Doing a MultiPut");
    Map<String, byte[]> map0 = new HashMap<>();
    map5.put("Joaquim", "Martins".getBytes());
    map5.put("Patricia", "Souza".getBytes());
    map5.put("Cristina", "Alves".getBytes());
    Boolean MultiPutResult0 = c.multiPut(map0);
    System.out.println(MultiPutResult0);

    System.out.println("Doing a MultiGet");
    List<String> keysList0 = new ArrayList<>();
    keysList5.add("Clara");
    keysList5.add("Cristina");
    keysList5.add("Joaquim");
    keysList5.add("Patricia");
    Set<String> keys0 = new HashSet<>(keysList0);
    Map<String, byte[]> MultiGetResult0 = c.multiGet(keys0);
    MultiGetResult0.forEach((k, v) -> {
        System.out.println(k + "-" + new String(v));
    });

    System.out.println("Doing a Get");
    byte[] gResult5 = c.get("Patricia");
    System.out.println(new String(gResult5));


}

}
