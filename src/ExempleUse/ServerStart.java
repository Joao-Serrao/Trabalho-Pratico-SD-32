package ExempleUse;

import Server.ServerFacade;

import java.util.Scanner;

public class ServerStart {
    public static void main(String[] args) throws Exception {
        System.out.println("Server:");
        Scanner s = new Scanner(System.in);
        System.out.println("Insert Port number:");
        int port = s.nextInt();
        System.out.println("Insert Max Sessions:");
        int max = s.nextInt();
        ServerFacade f = new ServerFacade(port, max);
        f.listen();

        while (true) {
            System.out.println("Insert quit to close!");
            s.nextLine();
            String c = s.nextLine();
            if (c.equals("quit")) {
                f.CloseServer();
                break;
            }
        }

    }
}
