package com.messaging;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 7777;
    private static final Map<String, ClientHandler> clients = Collections.synchronizedMap(new HashMap<>());
    private static boolean isRunning = true; // Variabile per gestire lo stato del server

    public static void main(String[] args) {
        System.out.println("ServerChat avviato sulla porta " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Ascolta continuamente per connessioni in entrata
            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    new Thread(new ClientHandler(socket, clients)).start();
                } catch (IOException e) {
                    if (isRunning) { // Se il server è stato fermato, non generare errore
                        System.err.println("Errore durante l'accettazione della connessione: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nel server: " + e.getMessage());
        } finally {
            // Invia un messaggio di chiusura a tutti i client
            notifyClientsServerShutdown();
            System.out.println("Server chiuso.");
        }
    }

    // Metodo per notificare la chiusura del server a tutti i client
    private static void notifyClientsServerShutdown() {
        synchronized (clients) {
            for (ClientHandler client : clients.values()) {
                client.sendMessage("Il server sta per chiudere, buona giornata!");
            }
        }
    }

    // Metodo per fermare il server
    public static void stopServer() {
        isRunning = false; // Ferma il server
        notifyClientsServerShutdown(); // Notifica tutti i client
        try {
            Thread.sleep(2000); // Attendere un po' prima di chiudere definitivamente il server
        } catch (InterruptedException e) {
            System.err.println("Interruzione durante l'attesa della chiusura del server.");
        }
    }

    
    
}
