# NexusProtocol - Chat Server and Client Protocol

NexusProtocol è un protocollo di comunicazione utilizzato in un'applicazione di messaggistica che permette la comunicazione tra client e server. Questo protocollo supporta messaggi pubblici, privati, gestione degli utenti connessi, e comandi per la disconnessione e l'aiuto.

## Comandi Supportati

Il protocollo supporta i seguenti comandi:

### 1. `HELP`
**Descrizione**: Mostra l'elenco dei comandi disponibili per l'utente.  
**Uso**: `HELP`  
**Esempio di risposta**:  
Comandi disponibili:

    - MSGALL <messaggio> : Invia un messaggio a tutti gli utenti connessi.
    - MSG <utente> <messaggio> : Invia un messaggio privato a un utente specifico.
    - LIST : Mostra la lista degli utenti connessi.
    - LOGOUT : Disconnetti dal server.
    - HELP : Mostra questo elenco di comandi.

### 2. `MSGALL`
**Descrizione**: Invia un messaggio a tutti gli utenti connessi al server.  
**Uso**: `MSGALL <messaggio>`  
**Esempio di comando**: 
    ``` MSGALL Ciao a tutti! ```

**Esempio di risposta**: Il messaggio verrà inviato a tutti gli utenti connessi.

### 3. `MSG`
**Descrizione**: Invia un messaggio privato a un utente specifico.  
**Uso**: `MSG <utente> <messaggio>`  
**Esempio di comando**:  -> ```MSG Marco Ciao, come va?```

**Esempio di risposta**: Il messaggio verrà inviato solo all'utente "Marco".

### 4. `LIST`
**Descrizione**: Mostra la lista degli utenti attualmente connessi al server.  
**Uso**: `LIST`  
**Esempio di comando**: -> ``` LIST ```

**Esempio di risposta**:
```
    Utenti connessi:

    - Maria [TU]
    - Luca
    - Marco
```

### 5. `LOGOUT`
**Descrizione**: Disconnette l'utente dal server.  
**Uso**: `LOGOUT`  
**Esempio di comando**: -> LOGOUT
**Esempio di risposta**: "Maria si è disconnesso."

---

## Funzionamento del Protocollo

### Autenticazione
Quando un client si connette, il server richiede un nome utente unico. Se il nome utente è già preso o non valido, il client dovrà fornire un altro nome utente.

### Invio di Messaggi
I messaggi possono essere inviati come:
- **Messaggi pubblici** (`MSGALL`), che sono visibili a tutti gli utenti connessi.
- **Messaggi privati** (`MSG`), che vengono inviati solo all'utente specificato.

### Disconnessione
L'utente può disconnettersi dal server utilizzando il comando `LOGOUT`. Il server invierà un messaggio a tutti gli altri client notificando che l'utente si è disconnesso.

### Elenco Utenti
Con il comando `LIST`, l'utente può visualizzare l'elenco di tutti gli utenti attualmente connessi al server.

### Chiusura del Server
Se il server viene fermato, invia un messaggio a tutti i client per informarli che il server sta per chiudere.

---


## Esempio di Flusso di Lavoro

1. Un client si connette al server e inserisce un nome utente.
2. Dopo l'autenticazione, il client può inviare messaggi privati o pubblici utilizzando i comandi `MSG` e `MSGALL`.
3. Il client può consultare la lista degli utenti connessi con il comando `LIST`.
4. Il client può disconnettersi usando il comando `LOGOUT`.

## Creato da un Team da **[💥]** - **Innovatori in Azione!**

### 🚀 **Leonardo Tozzi**  
   _"Il Codice è il mio linguaggio, la Creatività la mia guida."_  
   🧑‍💻 Architetto del codice, costruttore di soluzioni

### ⚡ **Mattia Negri**  
   _"Immagino, sviluppo e poi... lo faccio funzionare!"_  
   👨‍💻 Programmatore, mago delle interfacce

### 💡 **Leon Lo Preiato**  
   _"Ogni progetto è una nuova opportunità per cambiare il mondo."_  
   👾 Developer, visionario delle esperienze digitali

---

**Unisciti a noi** e fai parte di questa **rivoluzione digitale**! 💻💬
