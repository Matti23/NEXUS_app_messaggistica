package com.messaging;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        ReceiveMessagesThread receiveThread = null;

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Avvia thread per ricevere messaggi
            receiveThread = new ReceiveMessagesThread(in);
            Thread thread = new Thread(receiveThread);
            thread.start();

            // Invio comandi al server
            while (true) {
                String input = scanner.nextLine();
                out.println(input);
                if (input.equalsIgnoreCase("LOGOUT")) {
                    receiveThread.stopReceiving(); // Ferma il thread
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Errore di connessione al server: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                System.err.println("Errore durante la chiusura delle risorse: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
