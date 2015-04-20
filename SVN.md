# Premessa #
Proveremo qui a spiegare come installare un client svn in eclipse tramite qualche plugin gia esistente.
Vi ricordo che la password che dovete usare per fare accesso a questo repository la trovate al seguente indirizzo https://code.google.com/hosting/settings , la potete rigenerare o usare quella che è stata generata di default, mentre lo username che dovete usare è lo stesso che usate per loggarvi su www.google.com .
Per ora questo è quanto ho capito. Di volta in volta che scopro qualcosa di nuovo riguardante subversion, sia parte client che server, lo postero in questa wiki. Buon Lavoro.

# Introduzione #
Subversione, detto svn, è software per il controllo di versione. E' il successore si CVS. E' un progetto creato da Collabnet nel 2000 con l'obiettivo di creare un sistema di controllo versione open-source che operasse come CVS ma che, allo stesso tempo, risolvesse dei bug che in CVS erano presenti ed aggiungesse delle features.

## Caratteristiche ##
La versione 1.0 di Subversion (rilasciata il 23 febbraio 2004) offre le seguenti caratteristiche:

  * omprende gran parte delle caratteristiche di CVS.
  * e directory, i cambi di nome, e i metadati dei file sono sotto controllo versione.
  * e commit sono vere transazioni atomiche. Una commit interrotta non lascia il repository in uno stato di incoerenza.
  * ome server centralizzato si può usare il server Web Apache, tramite il protocollo WebDAV/DeltaV, oppure un server indipendente che usa un protocollo personalizzato basato su TCP/IP.
  * l branching e il tagging sono operazioni veloci, che richiedono un tempo indipendente dalla dimensione dei dati.
  * l progetto è nativamente client/server, ed è basato su una libreria stratificata.
  * l protocollo client/server invia solo le differenze in entrambe le direzioni, e quindi i costi di comunicazione sono proporzionali alla dimensione delle modifiche, non alla dimensione dei dati.
  * file binari sono gestiti efficientemente.
  * 'output dei comandi è analizzabile da un programma esterno, e viene fornito un log opzionale in XML.
  * a licenza è Open Source, simile a quella di Apache.

La versione 1.1 ha aggiunto le seguenti caratteristiche, fra le altre:

  * messaggi dei programmi sono internazionalizzati.
  * link simbolici sono sotto controllo versione.
  * iene supportato un nuovo formato opzionale del repository, FSFS, che non fa uso di un gestore di database, ma memorizza le revisioni direttamente nel file system.

La versione 1.2 (rilasciata nel maggio 2005) ha aggiunto le seguenti caratteristiche:

  * ock dei file per i file inconciliabili
  * ompleto autoversionamento WebDAV
_tratto da www.wikipedia.it , per maggiori informazioni si rimanda ad http://subversion.apache.org/_


# Installazione #
## Eclipse plugin ##
Di seguito sarà elencata la procedura passo passo per installare il plugin subversion per eclipse.

![![](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation01.png)](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation01.png)

cliccare su Help->Eclipse Marketplace

![![](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation02.png)](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation02.png)

Cliccare sul tasto install corrispondente al plugin Subclipse

![![](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation03.png)](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation03.png)

![![](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation04.png)](http://it-unisannio-ing-kid-controller.googlecode.com/svn/images/installation04.png)

Accettare i termini di licensa e cliccare su Finish
