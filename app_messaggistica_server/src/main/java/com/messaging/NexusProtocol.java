package com.messaging;

import java.util.Map;

public class NexusProtocol {
    public static void processCommand(String command, ClientHandler client, Map<String, ClientHandler> clients) {
        String[] parts = command.split(" ", 2);
        String cmd = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";
    
        System.out.println("Comando ricevuto da " + client.getUsername() + ": " + command);  // Stampa il comando ricevuto
    
        switch (cmd.toUpperCase()) {
            case "HELP":
                displayHelp(client);
                break;
    
            case "MSGALL":
                if (!argument.isBlank()) {
                    client.broadcast(client.getUsername() + ": " + argument, null);
                } else {
                    client.sendMessage("Uso corretto: MSGALL <messaggio>");
                }
                break;
    
            case "MSG":
                handlePrivateMessage(argument, client, clients);
                break;
    
            case "LIST":
                listUsers(client, clients);
                break;
    
            case "LOGOUT":
                client.logout();
                break;
    
            default:
                client.sendMessage("Comando sconosciuto. Usa HELP per vedere i comandi disponibili.");
                break;
        }
    }
    

    private static void handlePrivateMessage(String argument, ClientHandler client, Map<String, ClientHandler> clients) {
        String[] parts = argument.split(" ", 2);
        if (parts.length == 2) {
            String recipient = parts[0];
            String msg = parts[1];
            sendPrivateMessage(recipient, client.getUsername() + " (privato): " + msg, client, clients);
        } else {
            client.sendMessage("Uso corretto: MSG <utente> <messaggio>");
        }
    }

    private static void displayHelp(ClientHandler client) {
        String helpMessage = """
            Comandi disponibili:
            - MSGALL <messaggio> : Invia un messaggio a tutti gli utenti connessi.
            - MSG <utente> <messaggio> : Invia un messaggio privato a un utente specifico.
            - LIST : Mostra la lista degli utenti connessi.
            - LOGOUT : Disconnetti dal server.
            - HELP : Mostra questo elenco di comandi.
            """;
        client.sendMessage(helpMessage);
    }

    private static void sendPrivateMessage(String recipient, String message, ClientHandler sender, Map<String, ClientHandler> clients) {
        ClientHandler client = clients.get(recipient);
        if (client != null) {
            client.sendMessage(message);
        } else {
            sender.sendMessage("Utente non trovato: " + recipient);
        }
    }

    private static void listUsers(ClientHandler client, Map<String, ClientHandler> clients) {
        StringBuilder userList = new StringBuilder("Utenti connessi:");
        synchronized (clients) {
            for (String user : clients.keySet()) {
                // Se l'utente corrente è l'utente che sta eseguendo il comando, aggiungi [TU]
                if (user.equals(client.getUsername())) {
                    userList.append("\n - ").append(user).append(" [TU]");
                } else {
                    userList.append("\n - ").append(user);
                }
            }
        }
        client.sendMessage(userList.toString());
    }
    
}
