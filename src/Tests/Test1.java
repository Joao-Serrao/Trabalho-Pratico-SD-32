package Tests;

import Client.Client;

import java.util.*;

public class Test1 {
    public void run(Client c, String Username, String Pass) throws Exception{
        c.setMaxRequests(5);
        c.setRequest(0);


        System.out.println("Doing a Put");
        Boolean PutResult1 = c.put("Carlos", "Sousa".getBytes());
        System.out.println(PutResult1);

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map1 = new HashMap<>();
        map1.put("Pedro", "Gomes".getBytes());
        map1.put("Joao", "Serrao".getBytes());
        map1.put("Ricardo", "Martins".getBytes());
        map1.put("Patricia", "Costa".getBytes());
        Boolean MultiPutResult1 = c.multiPut(map1);
        System.out.println(MultiPutResult1);

        System.out.println("Doing a Put");
        Boolean PutResult2 = c.put("Ana", "Lima".getBytes());
        System.out.println(PutResult2);

        System.out.println("Doing a Get");
        byte[] gResult1 = c.get("Ricardo");
        System.out.println(new String(gResult1));

        System.out.println("Doing a Get");
        byte[] GetResult1 = c.get("Pedro");
        System.out.println(new String(GetResult1));

        Thread.sleep(500);
        c.login(Username,Pass);
        c.setAut(true);
        c.setMaxRequests(3);

        System.out.println("Doing a MultiGet");
        List<String> keysList1 = new ArrayList<>();
        keysList1.add("Patricia");
        keysList1.add("Carlos");
        keysList1.add("Joao");
        keysList1.add("Ana");
        keysList1.add("Ricardo");
        keysList1.add("Pedro");
        Set<String> keys1 = new HashSet<>(keysList1);
        Map<String, byte[]> MultiGetResult1 = c.multiGet(keys1);
        MultiGetResult1.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });


        System.out.println("Doing a Put");
        Boolean PutResult3 = c.put("Luisa", "Moura".getBytes());
        System.out.println(PutResult3);


        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map2 = new HashMap<>();
        map2.put("Diogo", "Fortunas".getBytes());
        map2.put("André", "Ferreia".getBytes());
        map2.put("Maria", "Silva".getBytes());
        map2.put("Joao", "Serrao".getBytes());
        Boolean MultiPutResult2 = c.multiPut(map2);
        System.out.println(MultiPutResult2);


        Thread.sleep(300);
        c.login(Username,Pass);
        c.setMaxRequests(2);

        System.out.println("Doing a Get");
        byte[] gResult2 = c.get("Maria");
        System.out.println(new String(gResult2));

        System.out.println("Doing a Get");
        byte[] GetResult2 = c.get("Maria");
        System.out.println(new String(GetResult2));


        Thread.sleep(700);
        c.login(Username,Pass);
        c.setMaxRequests(5);

        System.out.println("Doing a MultiGet");
        List<String> keysList2 = new ArrayList<>();
        keysList2.add("Ana");
        keysList2.add("Diogo");
        keysList2.add("Luisa");
        keysList2.add("Ricardo");
        keysList2.add("Patricia");
        keysList2.add("Pedro");
        Set<String> keys2 = new HashSet<>(keysList2);
        Map<String, byte[]> MultiGetResult2 = c.multiGet(keys2);
        MultiGetResult2.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });


        System.out.println("Doing a Put");
        Boolean PutResult4 = c.put("Marta", "Alves".getBytes());
        System.out.println(PutResult4);

        System.out.println("Doing a Put");
        Boolean PutResult5 = c.put("Rui", "Pereira".getBytes());
        System.out.println(PutResult5);

        System.out.println("Doing a Get");
        byte[] gResult3 = c.get("Rui");
        System.out.println(new String(gResult3));

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map3 = new HashMap<>();
        map3.put("Sofia", "Ferreira".getBytes());
        map3.put("Nuno", "Mendes".getBytes());
        map3.put("Luis", "Teixeira".getBytes());
        Boolean MultiPutResult3 = c.multiPut(map3);
        System.out.println(MultiPutResult3);

        Thread.sleep(100);
        c.login(Username,Pass);
        c.setMaxRequests(3);

        System.out.println("Doing a MultiGet");
        List<String> keysList3 = new ArrayList<>();
        keysList3.add("Marta");
        keysList3.add("Ricardo");
        keysList3.add("Luisa");
        keysList3.add("Rui");
        keysList3.add("Nuno");
        keysList3.add("Sofia");
        Set<String> keys3 = new HashSet<>(keysList3);
        Map<String, byte[]> MultiGetResult3 = c.multiGet(keys3);
        MultiGetResult3.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        System.out.println("Doing a Get");
        byte[] GetResult3 = c.get("Sofia");
        System.out.println(new String(GetResult3));

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map4 = new HashMap<>();
        map4.put("Juliana", "Costa".getBytes());
        map4.put("Tiago", "Ferreira".getBytes());
        map4.put("Lucas", "Martins".getBytes());
        Boolean MultiPutResult4 = c.multiPut(map4);
        System.out.println(MultiPutResult4);

        Thread.sleep(500);
        c.login(Username,Pass);
        c.setMaxRequests(6);

        System.out.println("Doing a Get");
        byte[] gResult4 = c.get("Tiago");
        System.out.println(new String(gResult4));

        System.out.println("Doing a MultiGet");
        List<String> keysList4 = new ArrayList<>();
        keysList4.add("Juliana");
        keysList4.add("Lucas");
        keysList4.add("Tiago");
        keysList4.add("Patricia");
        Set<String> keys4 = new HashSet<>(keysList4);
        Map<String, byte[]> MultiGetResult4 = c.multiGet(keys4);
        MultiGetResult4.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        System.out.println("Doing a Get");
        byte[] GetResult4 = c.get("Lucas");
        System.out.println(new String(GetResult4));

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map5 = new HashMap<>();
        map5.put("Ines", "Silva".getBytes());
        map5.put("David", "Lima".getBytes());
        Boolean MultiPutResult5 = c.multiPut(map5);
        System.out.println(MultiPutResult5);

        System.out.println("Doing a MultiGet");
        List<String> keysList5 = new ArrayList<>();
        keysList5.add("David");
        keysList5.add("Ines");
        keysList5.add("Maria");
        Set<String> keys5 = new HashSet<>(keysList5);
        Map<String, byte[]> MultiGetResult5 = c.multiGet(keys5);
        MultiGetResult5.forEach((k, v) -> {
            System.out.println(k + "-" + new String(v));
        });

        System.out.println("Doing a Put");
        Boolean PutResult6 = c.put("Fernanda", "Goncalves".getBytes());
        System.out.println(PutResult6);

        Thread.sleep(1000);
        c.login(Username,Pass);
        c.setMaxRequests(2);

        System.out.println("Doing a Get");
        byte[] GetResult5 = c.get("Fernanda");
        System.out.println(new String(GetResult5));

        System.out.println("Doing a MultiPut");
        Map<String, byte[]> map6 = new HashMap<>();
        map6.put("Gustavo", "Almeida".getBytes());
        map6.put("Ricardo", "Azevedo".getBytes());
        Boolean MultiPutResult6 = c.multiPut(map6);
        System.out.println(MultiPutResult6);


    }
}
