/*
 * Scrivere un programma che simula il funzionamento di un negozio online.

Le funzionalità da realizzare sono:

Inserimento prodotto: consente di inserire i dati di un nuovo prodotto al negozio. 
Ogni prodotto è caratterizzato da:
codice (String): codice identificativo del prodotto (non è possibile inserire due prodotti con lo stesso codice)
Descrizione (String)
Prezzo (float)
Sconto (float): percentuale di sconto da applicare eventualmente al prodotto (di default 0)
Quantità disponibile (int)

Elenco prodotti: per ogni prodotto vengono visualizzate le informazioni
Applica sconto: si inserisce il codice di un prodotto e la percentuale di sconto da applicare
Vendita prodotti:
si chiede all'utente di scegliere un prodotto dall'elenco (inserendo il codice);
si visualizzano le informazioni del prodotto scelto;
si chiede la quantità da acquistare;
se la quantità disponibile è sufficiente, il prodotto si aggiunge al "carrello della spesa" applicando eventuali sconti (in questo caso mostrare sia il prezzo originale che il prezzo scontato)
si visualizza il carrello attuale
si chiede se si vuole procedere all'acquisto (in caso positivo si mostra il totale e si aggiornano le quantità disponibili)
 
Classi da realizzare:

Prodotto
Carrello
Negozio
Main
 */

package org.generation.italy;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		Negozio n=new Negozio();		//chiamo il costruttore (situa iniziale)
		Scanner sc=new Scanner(System.in);
		String scelta="", codice, descriz;
		float prezzo, sconto;
		int quantità;
		
		
		do {
			System.out.println("\n\n\n\n\n\nBenvenuto nel mio negozio!");
			
			System.out.println("1 - Visualizza prodotti");
			System.out.println("2 - Inserisci prodotto");
			System.out.println("3 - Applica sconto");
			System.out.println("9 - Esci");
			System.out.print("\nInserisci la tua scelta: ");
			scelta=sc.nextLine();
			
			switch (scelta) {
			case "1":	//visualizza
				System.out.println("I prodotti disponibili sono: "); //prodotti inseriti nel costruttore/dichiarazione
				for (String c:n.elencoProdotti.keySet()) {
					System.out.println(n.elencoProdotti.get(c).toString());					
				}
				break;
			case "2":	//inserisci
				System.out.print("Inserisci il codice: ");
				codice=sc.nextLine();
				System.out.print("Inserisci la descrizione: ");
				descriz=sc.nextLine();
				System.out.print("Inserisci il prezzo: ");
				prezzo=Float.parseFloat(sc.nextLine());
				System.out.print("Inserisci la quantità: ");
				quantità=Integer.parseInt(sc.nextLine());
				if (n.inserisciProdotto(new Prodotto(codice,descriz,prezzo,quantità)))
					System.out.println("Prodotto correttamente inserito");
				else
					System.out.println("Prodotto non inserito: codice già esistente!");
				break;
			case "3":	//applica sconto
				System.out.print("Inserisci il codice: ");
				codice=sc.nextLine();
				System.out.print("Inserisci lo sconto da applicare: ");
				sconto=Float.parseFloat(sc.nextLine());
				
				//chiamata al metodo "applica sconto"
				if(n.applicaSconto(codice, sconto))
					System.out.println("Sconto correttamente applicato");
				else
					System.out.println("Sconto non applicato");
				break;
			case "9":
				System.out.println("Arrivederci!");
				break;
			default:
				System.out.println("Scelta non valida!");
				break;
			}
			
		} while (!scelta.equals("9"));

		
		
		
		
	
		
		n.inserisciProdotto(new Prodotto("CAS003","Scopa",5f,10));  ; //prodotto aggiunto a quelli esistenti
		
		if(!n.inserisciProdotto(new Prodotto("CAS003","Pettine",1f,10)))  ; //codice già esistente! => prodotto non aggiunto
			System.out.println("Codice già esistente!");

		System.out.println("Dopo l'aggiunta i prodotti disponibili sono: ");
		for (String c:n.elencoProdotti.keySet()) {
			System.out.println(n.elencoProdotti.get(c).getCodice());
			System.out.println(n.elencoProdotti.get(c).getDescrizione());
			System.out.println("\n");
		}
		
		
	
			
	}

	
	
}
