package ExempleUse;

import Client.Client;
import Server.ServerFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Window {
    private ServerFacade sf;
    private JFrame frame;
    private Boolean running = true;
    private Map<JButton,Client> clients = new HashMap<>();

    private void Register(JFrame frame, Client c) throws Exception {
        String input = JOptionPane.showInputDialog(null, "Enter Username:", "Input", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            String input2 = JOptionPane.showInputDialog(null, "Enter Password:", "Input", JOptionPane.QUESTION_MESSAGE);
            if(input != null){
                System.out.println("When???");
                Boolean r = c.register(input,input2);
                System.out.println("here?");
                if(!r){
                    this.Register(frame, c);
                    System.out.println("What???");
                }
                System.out.println("When???");
            }
        } else {
            System.out.println("User cancelled the input.");
        }
        System.out.println("What???");
    }

    private void definceClientStartButto(JButton button, JFrame frame, Window w) {
        button.setBounds(75, 200, 250, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    w.clients.keySet().forEach(b -> {
                        frame.remove(b);
                    });
                    Client c = new Client(12345, 3, 3);
                    w.Register(frame,c);
                    String s = "Select Client " + w.clients.size();
                    JButton newButton = new JButton(s);
                    w.clients.put(newButton,c);
                    AtomicInteger y = new AtomicInteger(250); // Use AtomicInteger for a mutable y
                    w.clients.keySet().forEach(b -> {
                        b.setBounds(75, y.get(), 250, 50);
                        y.addAndGet(50); // Update y's value
                        frame.add(b);
                        frame.revalidate();
                        frame.repaint();
                    });

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void defineServerStopButton(JButton button, JFrame frame, Window w) {
        button.setBounds(75, 100, 250, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    w.sf.CloseServer();
                    w.clients = new HashMap<>();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                frame.getContentPane().removeAll();
                JButton newButton = new JButton("Open Server");
                w.defineServerStartButton(newButton, frame, w);
                frame.remove(button);
                frame.add(newButton);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private void defineServerStartButton(JButton button, JFrame frame, Window w) {
        button.setBounds(75, 100, 250, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    w.sf = new ServerFacade(12345, 3);
                    w.sf.listen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                JLabel label = new JLabel("Server Running!", SwingConstants.CENTER);
                label.setBounds(50, 150, 300, 50);
                frame.add(label);
                JButton newButton = new JButton("Close Server");
                w.defineServerStopButton(newButton, frame, w);
                JButton newButton2 = new JButton("Create Client");
                w.definceClientStartButto(newButton2, frame, w);
                frame.remove(button);
                frame.add(newButton);
                frame.add(newButton2);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    protected void setupFrame() {
        JFrame frame = new JFrame("System Interface");
        this.frame = frame;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        this.frame.setSize((int) (screenWidth*0.75), screenHeight);

        // Calculate the x and y coordinates for centering
        int x = (screenWidth - this.frame.getWidth()) / 2;
        int y = (screenHeight - this.frame.getHeight()) / 2;

        // Set the frame's location
        this.frame.setLocation(x, y);// Set the size of the window
        this.frame.setLayout(null);

        JLabel label = new JLabel("Welcome to Swing GUI", SwingConstants.CENTER);
        this.frame.add(label, BorderLayout.CENTER);

        // Add a JButton (a button)
        JButton button = new JButton("Create Server");
        this.defineServerStartButton(button, this.frame, this);
        this.frame.add(button);
        this.frame.setVisible(true);
    }

    protected void run() {

    }
}
