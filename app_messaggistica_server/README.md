# 🚀 **Chat Server Protocol** 🖥️

Benvenuto nel **Protocollo di Comunicazione** per il nostro **Chat Server**! Questo sistema semplifica la comunicazione tra client e server, offrendo una serie di comandi facili da usare per inviare messaggi, interagire con gli utenti online e gestire le connessioni.

---

## 🔧 **Comandi Disponibili**

I client possono inviare i seguenti comandi al server. Ecco una panoramica completa dei comandi disponibili:

### 1. **`HELP`**: Mostra la lista dei comandi disponibili.

Se hai bisogno di sapere quali comandi puoi utilizzare, invia semplicemente `HELP`!

#### Sintassi:
> HELP

#### Risposta:
```
Comandi disponibili:

    - MSGALL <messaggio> : Invia un messaggio a tutti gli utenti connessi.
    - MSG <utente> <messaggio> : Invia un messaggio privato a un utente specifico.
    - LIST : Mostra la lista degli utenti connessi.
    - LOGOUT : Disconnetti dal server.
    - HELP : Mostra questo elenco di comandi.
```

### 2. **`MSGALL <messaggio>`**: Invia un messaggio a tutti gli utenti connessi.

Con `MSGALL`, il tuo messaggio verrà visto da tutti gli utenti online! 😎

#### Sintassi:
> MSGALL <messaggio>

#### Esempio:
> MSGALL Ciao a tutti!

#### Risposta: Tutti gli utenti connessi riceveranno il messaggio: "username: Ciao a tutti!"

### 3. **`MSG <utente> <messaggio>`**: Invia un messaggio privato a un altro utente.

Vuoi scrivere a qualcuno in privato? Usa `MSG` per inviare un messaggio riservato!

#### Sintassi:
> MSG <utente> <messaggio>

#### Esempio:
> MSG luca Ciao Luca, come va?

#### Risposta:
L'utente `luca` riceverà il messaggio privato: "username (privato): Ciao Luca, come va?"

Se l'utente non esiste, riceverai un errore: Utente non trovato: luca

### 4. **`LIST`**: Mostra gli utenti connessi.

Con `LIST`, puoi vedere chi è online e interagire con gli utenti attivi.

#### Sintassi:
> LIST

#### Risposta: Utenti connessi: luca [TU] john
L'utente che esegue il comando sarà contrassegnato con `[TU]`.

### 5. **`LOGOUT`**: Disconnetti dal server.

Quando hai finito di chattare, puoi disconnetterti usando `LOGOUT`. 👋

#### Sintassi:
> LOGOUT

#### Risposta: Il server sta per chiudere, buona giornata! 