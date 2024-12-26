package ExempleUse;

import Client.Client;

import java.nio.ByteBuffer;
import java.util.*;

public class ClientStart {
    public static Scanner s = new Scanner(System.in);

    public static void Register(Client c) throws Exception{
        if (!c.reg) {
            System.out.println("Insert Username:");
            s.nextLine();
            String username = s.nextLine();
            System.out.println("Insert Password:");
            String pass = s.nextLine();

            Boolean result = c.register(username, pass);

            if (!result) {
                System.out.println("Username Already Taken!");
            } else {
                System.out.println("Successfully Registered!");
            }
        }
        else {
            System.out.println("Already Registered!");
        }
    }

    public static void Login(Client c) throws Exception{
        if (!c.aut) {
            System.out.println("Insert Username:");
            s.nextLine();
            String username = s.nextLine();
            System.out.println("Insert Password:");
            String pass = s.nextLine();

            Boolean result = c.login(username, pass);

            if (!result) {
                System.out.println("Username or Password Incorrect");
            } else {
                System.out.println("Successfully Loginned!");
                c.aut = true;
            }
        }
        else {
            if (c.reg) {
                System.out.println("Already Loginned!");
            }
            else {
                System.out.println("Not Registered!");
            }
        }

    }

    public static void put(Client c) throws Exception{
        if(c.aut) {
            System.out.println("Insert Key:");
            s.nextLine();
            String key = s.nextLine();
            System.out.println("Insert Type: 1-String | 2-Integer");
            int tag = s.nextInt();
            Boolean result = false;
            if (tag == 1) {
                System.out.println("Insert String data:");
                s.nextLine();
                String value = s.nextLine();
                result = c.put(key, value.getBytes());
            }
            else {
                if (tag == 2) {
                    System.out.println("Insert Integer data:");
                    int value = s.nextInt();
                    byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
                    result = c.put(key,bytes);
                }
                else {
                    System.out.println("Invalid Type");
                }
            }

            if (!result) {
                System.out.println("Problem saving data!");
            }
            else {
                System.out.println("Data saved!");
            }
            if(!c.aut) {
                System.out.println("Max request reached! Logout made!");
            }
        }
        else {
            System.out.println("Not Loginned!");
        }
    }

    public static void get(Client c) throws Exception{
        if(c.aut) {
            System.out.println("Insert Key:");
            s.nextLine();
            String key = s.nextLine();
            byte[] result = c.get(key);
            System.out.println("Insert Type: 1-String | 2-Integer");
            int tag = s.nextInt();
            if (result.length == 0) {
                System.out.println("Data not found!");
                return;
            }
            if (tag == 1) {
                System.out.println("Received data:");
                System.out.println(new String(result));
            }
            else {
                if (tag == 2) {
                    System.out.println("Received data:");
                    if (result.length != 4) {
                        System.out.println("Invalid data Type received");
                    }
                    ByteBuffer buffer = ByteBuffer.wrap(result);
                    int i = 0;
                    try {
                        i = buffer.getInt();
                    } catch (Exception e) {
                        System.out.println("Invalid data Type received");
                    }
                    System.out.println(i);
                }
                else {
                    System.out.println("Invalid Type");
                }
            }
            if(!c.aut) {
                System.out.println("Max request reached! Logout made!");
            }
        }
        else {
            System.out.println("Not Loginned!");
        }
    }

    public static void multiPut(Client c) throws Exception{
        if (c.aut) {
            System.out.println("Insert how many itens to put:");
            int p = s.nextInt();
            Map<String,byte[]> map = new HashMap<>();

            s.nextLine();
            for(int i = 0; i<p; i++) {
                System.out.println("Insert Key:");
                String key = s.nextLine();
                System.out.println("Insert Type: 1-String | 2-Integer");
                int tag = s.nextInt();
                if (tag == 1) {
                    System.out.println("Insert String data:");
                    s.nextLine();
                    String value = s.nextLine();
                    map.put(key,value.getBytes());
                }
                else {
                    if (tag == 2) {
                        System.out.println("Insert Integer data:");
                        int value = s.nextInt();
                        byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
                        map.put(key,bytes);
                    }
                    else {
                        System.out.println("Invalid Type");
                    }
                }

            }
            Boolean result = c.multiPut(map);
            if (!result) {
                System.out.println("Problem Saving Data!");
            }
            else {
                System.out.println("Data saved!");
            }

            if(!c.aut) {
                System.out.println("Max request reached! Logout made!");
            }
        }
        else {
            System.out.println("Not Loginned!");
        }
    }

    public static void multiGet(Client c) throws Exception{
        if (c.aut) {
            System.out.println("Insert how many itens to get:");
            int p = s.nextInt();
            List<String> k = new ArrayList<>();

            s.nextLine();
            for(int i = 0; i<p; i++) {
                System.out.println("Insert Key:");
                String key = s.nextLine();

                k.add(key);

            }
            Set<String> keys = new HashSet<>(k);
            Map<String,byte[]> result = c.multiGet(keys);
            if (result.isEmpty()) {
                System.out.println("Problem Getting Data!");
            }
            else {
                result.forEach((e,v) -> {
                    System.out.println(e);
                    System.out.println("Insert Type: 1-String | 2-Integer");
                    int tag = s.nextInt();

                    if (tag == 1) {
                        System.out.println("Received data:");
                        System.out.println(e + ": " + new String(v));
                    }
                    else {
                        if (tag == 2) {
                            System.out.println("Received data:");
                            if (v.length != 4) {
                                System.out.println("Invalid data Type received");
                            }
                            ByteBuffer buffer = ByteBuffer.wrap(v);
                            int i = 0;
                            try {
                                i = buffer.getInt();
                            } catch (Exception et) {
                                System.out.println("Invalid data Type received");
                            }
                            System.out.println(e + ": " + i);
                        }
                        else {
                            System.out.println("Invalid Type");
                        }
                    }
                });
            }

            if(!c.aut) {
                System.out.println("Max request reached! Logout made!");
            }
        }
        else {
            System.out.println("Not Loginned!");
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Insert Port number: \n");
            int port = s.nextInt();
            System.out.println("Insert Max Requests: \n");
            int maxr = s.nextInt();
            System.out.println("Insert Max Parallel: \n");
            int maxp = s.nextInt();

            Client c = new Client(port, maxr, maxp);

            while (true) {
                System.out.println("Select Request:");
                System.out.println("1-Register;");
                System.out.println("2-Login;");
                System.out.println("3-Put;");
                System.out.println("4-Get");
                System.out.println("5-MultiPut");
                System.out.println("6-MultiGet");
                System.out.println("7-Logout");
                System.out.println("8-Close");


                int tag = s.nextInt();

                switch (tag) {
                    case 1:
                        Register(c);
                        break;
                    case 2:
                        Login(c);
                        break;
                    case 3:
                        put(c);
                        break;
                    case 4:
                        get(c);
                        break;
                    case 5:
                        multiPut(c);
                        break;
                    case 6:
                        multiGet(c);
                        break;
                    case 7:
                        if (c.aut) {
                            c.logout();
                            System.out.println("Logout from Server!");
                        }
                        else {
                            System.out.println("Not loggined!");
                        }
                        break;
                    case 8:
                        c.logout();
                        System.out.println("Closing...");
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println("Couldnt Connect");
        }
    }

}
