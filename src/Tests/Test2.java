package Tests;

import Client.Client;

import java.util.*;

public class Test2 {
    public void run(Client c, String Username, String Pass) throws Exception{
        c.setMaxRequests(2);
        c.setRequest(0);

        System.out.println("Doing a Put");
        Boolean PutResult1 = c.put("Lucas", "Santos".getBytes());
        System.out.println(PutResult1);

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map1 = new HashMap<>();
        map1.put("Vera", "Pereira".getBytes());
        map1.put("Miguel", "Costa".getBytes());
        map1.put("Tiago", "Nunes".getBytes());
        map1.put("Carla", "Souza".getBytes());
        Boolean MultiPutResult1 = c.multiPut(map1);
        System.out.println(MultiPutResult1);

        Thread.sleep(400);
        c.login(Username,Pass);
        c.setMaxRequests(5);

        System.out.println("Doing a Get");
        byte[] GetResult1 = c.get("Miguel");
        System.out.println(new String(GetResult1));

        System.out.println("Doing a Get");
        byte[] gResult = c.get("Tiago");
        System.out.println(new String(gResult));

        System.out.println("Doing a MultiGet");
        List<String> keysList1 = new ArrayList<>();
        keysList1.add("Vera");
        keysList1.add("Carla");
        keysList1.add("Tiago");
        keysList1.add("Miguel");
        Set<String> keys1 = new HashSet<>(keysList1);
        Map<String, byte[]> MultiGetResult1 = c.multiGet(keys1);
        MultiGetResult1.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        System.out.println("Doing a Put");
        Boolean PutResult2 = c.put("Joana", "Figueiredo".getBytes());
        System.out.println(PutResult2);

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map2 = new HashMap<>();
        map2.put("Rita", "Lima".getBytes());
        map2.put("Gustavo", "Costa".getBytes());
        map2.put("Pedro", "Alves".getBytes());
        Boolean MultiPutResult2 = c.multiPut(map2);
        System.out.println(MultiPutResult2);

        Thread.sleep(200);
        c.login(Username,Pass);
        c.setMaxRequests(2);

        System.out.println("Doing a Get");
        byte[] GetResult2 = c.get("Rita");
        System.out.println(new String(GetResult2));

        System.out.println("Doing a MultiGet");
        List<String> keysList2 = new ArrayList<>();
        keysList2.add("Gustavo");
        keysList2.add("Joana");
        keysList2.add("Pedro");
        keysList2.add("Rita");
        Set<String> keys2 = new HashSet<>(keysList2);
        Map<String, byte[]> MultiGetResult2 = c.multiGet(keys2);
        MultiGetResult2.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        Thread.sleep(500);
        c.login(Username,Pass);
        c.setMaxRequests(5);

        System.out.println("Doing a Get");
        byte[] gResult2 = c.get("Joana");
        System.out.println(new String(gResult2));

        System.out.println("Doing a Put");
        Boolean PutResult3 = c.put("Raul", "Mendes".getBytes());
        System.out.println(PutResult3);

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map3 = new HashMap<>();
        map3.put("Beatriz", "Oliveira".getBytes());
        map3.put("Sofia", "Ferreira".getBytes());
        map3.put("Ricardo", "Almeida".getBytes());
        Boolean MultiPutResult3 = c.multiPut(map3);
        System.out.println(MultiPutResult3);

        System.out.println("Doing a Get");
        byte[] GetResult3 = c.get("Raul");
        System.out.println(new String(GetResult3));

        System.out.println("Doing a MultiGet");
        List<String> keysList3 = new ArrayList<>();
        keysList3.add("Ricardo");
        keysList3.add("Raul");
        keysList3.add("Beatriz");
        keysList3.add("Sofia");
        Set<String> keys3 = new HashSet<>(keysList3);
        Map<String, byte[]> MultiGetResult3 = c.multiGet(keys3);
        MultiGetResult3.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        Thread.sleep(1000);
        c.login(Username,Pass);
        c.setMaxRequests(2);

        System.out.println("Doing a Get");
        byte[] gResult3 = c.get("Raul");
        System.out.println(new String(gResult3));

        System.out.println("Doing a Put");
        Boolean PutResult4 = c.put("Marco", "Pinto".getBytes());
        System.out.println(PutResult4);

        Thread.sleep(300);
        c.login(Username,Pass);
        c.setMaxRequests(3);

        System.out.println("Doing a Get");
        byte[] GetResult4 = c.get("Marco");
        System.out.println(new String(GetResult4));

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map4 = new HashMap<>();
        map4.put("Francisco", "Cruz".getBytes());
        map4.put("Daniel", "Santos".getBytes());
        map4.put("Leonor", "Ribeiro".getBytes());
        Boolean MultiPutResult4 = c.multiPut(map4);
        System.out.println(MultiPutResult4);

        System.out.println("Doing a MultiGet");
        List<String> keysList4 = new ArrayList<>();
        keysList4.add("Francisco");
        keysList4.add("Leonor");
        keysList4.add("Daniel");
        Set<String> keys4 = new HashSet<>(keysList4);
        Map<String, byte[]> MultiGetResult4 = c.multiGet(keys4);
        MultiGetResult4.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        Thread.sleep(500);
        c.login(Username,Pass);
        c.setMaxRequests(5);

        System.out.println("Doing a Get");
        byte[] gResult4 = c.get("Leonor");
        System.out.println(new String(gResult4));

        System.out.println("Doing a Put");
        Boolean PutResult5 = c.put("Clara", "Gomes".getBytes());
        System.out.println(PutResult5);

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map5 = new HashMap<>();
        map5.put("Joaquim", "Martins".getBytes());
        map5.put("Patricia", "Souza".getBytes());
        map5.put("Cristina", "Alves".getBytes());
        Boolean MultiPutResult5 = c.multiPut(map5);
        System.out.println(MultiPutResult5);

        System.out.println("Doing a MultiGet");
        List<String> keysList5 = new ArrayList<>();
        keysList5.add("Clara");
        keysList5.add("Cristina");
        keysList5.add("Joaquim");
        keysList5.add("Patricia");
        Set<String> keys5 = new HashSet<>(keysList5);
        Map<String, byte[]> MultiGetResult5 = c.multiGet(keys5);
        MultiGetResult5.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        System.out.println("Doing a Get");
        byte[] gResult5 = c.get("Patricia");
        System.out.println(new String(gResult5));


    }
}
