package com.messaging;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket socket;
    private String username;
    private BufferedReader in;
    private PrintWriter out;
    private final Map<String, ClientHandler> clients;

    public ClientHandler(Socket socket, Map<String, ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Autenticazione e gestione comandi
            while (true) {
                try {
                    out.println("Inserisci un nome utente unico: ");
                    username = in.readLine();
                    if (username == null || username.isBlank() || clients.containsKey(username)) {
                        out.println("Nome utente non valido o già in uso. Riprova.");
                    } else {
                        synchronized (clients) {
                            clients.put(username, this);
                        }
                        out.println("Benvenuto " + username + "!");
                        broadcast(username + " si è connesso!", this);
                        break;
                    }
                } catch (SocketException e) {
                    System.err.println("Connessione con il client " + username + " è stata resettata. Chiusura in corso...");
                    break; // Esci dal ciclo in caso di disconnessione
                }
            }

            // Gestione dei messaggi inviati dal client
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("LOGOUT")) {
                    break;
                }
                NexusProtocol.processCommand(message, this, clients);
            }

        } catch (IOException e) {
            System.err.println("Errore durante la gestione della connessione con " + username + ": " + e.getMessage());
        } finally {
            logout();
        }
    }

    public void sendMessage(String message) {
        System.out.println("Risposta al client " + username + ": " + message);  // Stampa la risposta inviata al client
        out.println(message);
    }
    
    public void broadcast(String message, ClientHandler exclude) {
        synchronized (clients) {
            for (ClientHandler client : clients.values()) {
                if (client != exclude) {
                    client.sendMessage(message);  // Questo invia anche il messaggio a tutti i client
                }
            }
        }
    }
    

    public String getUsername() {
        return username;
    }

    public void logout() {
        if (username != null) {
            synchronized (clients) {
                if (clients.remove(username) != null) {
                    broadcast(username + " si è disconnesso.", this);
                }
            }
            username = null;
        }
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del socket: " + e.getMessage());
        }
    }
    
}
