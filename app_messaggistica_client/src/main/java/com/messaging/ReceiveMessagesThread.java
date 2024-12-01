package com.messaging;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveMessagesThread implements Runnable {
    private BufferedReader in;
    private volatile boolean running = true;

    public ReceiveMessagesThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (running) {
                String message = in.readLine();
                if (message != null) {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            if (running) { 
                System.err.println("Errore il server e' morto! "  + e.getMessage());
            } else {
                // Ignora l'eccezione se il thread è stato fermato intenzionalmente
                System.out.println("Thread di ricezione interrotto.");
            }
        }
    }

    public void stopReceiving() {
        running = false; // Ferma il ciclo nel metodo `run`
        try {
            in.close(); // Chiude il BufferedReader
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del BufferedReader: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
