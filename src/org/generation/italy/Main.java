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
		Negozio negozio=new Negozio();		//chiamo il costruttore (situa iniziale)
		Scanner sc=new Scanner(System.in);
		String scelta="", codice, descriz, risposta;
		float prezzo, sconto;
		int quantità;
		
		//  negozio.elencoProdotti=null;		//utilizzo inappropriato dell'elenco prodotti
		
		
		/* 
		 * Problema:
		 * 
		 * necessità di scorrere i prodotti del negozio (hashmap), 
		 * ma se si "espone" l'hashmap questa può essere utilizzata impropriamente
		 * (ad esempio si possono aggiungere dei prodotti bypassando i controlli 
		 * che ci sono sul metodo "inserisciProdotto") 
		 * 
		 * 
		 * Soluzione:
		 * invece di esporre l'hashmap espongo un oggetto "simile" ma che mi consente solo di scorrere
		 * questo oggetto si chiama Iterable.
		 * Passi da seguire:
		 * 1) rendere privata l'hashmap di Negozio
		 * 2) scrivere un metodo pubblico di Negozio che restituisce un "iteratore" di prodotti (sintassi "standard")
		 * 3) nella classe utilizzatrice (es. Main) utilizzare un "for-each" per scorrere tutti i prodotti dell'iteratore
		 * 
		 */

		
		// esempio di utilizzo improprio dell'hashmap (aggiungo un prodotto con codice già esistente)
		
		/*
		negozio.getElencoProdotti().put(
				"CAS001",
				new Prodotto("CAS001","XXXXX",87f,20)
				);
		*/
		
		// esempio di utilizzo improprio dell'hashmap (svuoto l'elenco dei prodotti)
		//negozio.getElencoProdotti().clear();
	
		
        
		do {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nBenvenuto nel mio negozio!");
			
			System.out.println("1 - Visualizza prodotti");
			System.out.println("2 - Inserisci prodotto");
			System.out.println("3 - Applica sconto");
			System.out.println("4 - Acquista prodotti");
			System.out.println("9 - Esci");
			System.out.print("\nInserisci la tua scelta: ");
			scelta=sc.nextLine();
			
			switch (scelta) {
			case "1":	//visualizza
				System.out.println("I prodotti disponibili sono: "); //prodotti inseriti nel costruttore/dichiarazione
				for (Prodotto prod:negozio.getElencoProdotti()) {
					System.out.println(prod.toString());					
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
				if (negozio.inserisciProdotto(new Prodotto(codice,descriz,prezzo,quantità)))
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
				if(negozio.applicaSconto(codice, sconto))
					System.out.println("Sconto correttamente applicato");
				else
					System.out.println("Sconto non applicato");
				break;
			case "4":
				float totaleCarrello=0;
				do 
				{
					System.out.print("Inserisci il codice: ");
					codice=sc.nextLine();
					if (negozio.esisteProdotto(codice)) {					
						Prodotto p;		//dichiaro un oggetto di tipo prodotto
						p=negozio.getProdotto(codice);	//restituisce il prodotto con quel codice
						System.out.println(p.toString());
						System.out.print("Inserisci la quantità desiderata: ");
						quantità=Integer.parseInt(sc.nextLine());
						if (quantità>p.getQuantitàDisponibile())
							System.out.println("Quantità disponibile insufficiente!");
						else
						{
							negozio.getCarrello().aggiungiProdotto(codice, quantità);
							System.out.println("Prodotto aggiunto correttamente");
							//mostro il carrello
							System.out.println("Il tuo carrello:");
							
							for (String cod:negozio.getCarrello().getElencoProdotti().keySet()) {
								Prodotto prodottoNelCarrello=negozio.getProdotto(cod);	//
								quantità=negozio.getCarrello().getElencoProdotti().get(cod);
								prezzo=(prodottoNelCarrello.getPrezzo()*(100-prodottoNelCarrello.getSconto())/100)*quantità;
								//mostro per ogni prodotto nel carrello: descrizione, quantità, prezzo (scontato)
								System.out.println(
									prodottoNelCarrello.getDescrizione()+ 
									" - quantità: "+quantità+
									" - prezzo: "+ 
									prezzo);
								totaleCarrello+=prezzo;
							}	
						}
					} else
						System.out.println("Prodotto non esistente!");
					System.out.println("Vuoi acquistare un altro prodotto (s/n)?");
					risposta=sc.nextLine().toLowerCase();
				} while (risposta.equals("s"));
				System.out.println("Totale carrello: "+String.format("%.2f €", totaleCarrello));
				System.out.println("Vuoi procedere con l'acquisto (s/n)?");
				risposta=sc.nextLine().toLowerCase();
				if (risposta.equals("s")) 
				{
					//aggiorno la quantità
					for (String cod:negozio.getCarrello().getElencoProdotti().keySet()) {		//per ogni codice nel carrello
						quantità=negozio.getCarrello().getElencoProdotti().get(cod);	//recupero la quantità del carrello
						int quantitàDisponibile=negozio.getProdotto(cod).getQuantitàDisponibile();	//recupero la quantità del prodotto dal negozio
						quantitàDisponibile=quantitàDisponibile-quantità;	//aggiorno la quantità
						negozio.getProdotto(cod).setQuantitàDisponibile(quantitàDisponibile);
					}
				}
				
				
				
				break;
			case "9":
				System.out.println("Arrivederci!");
				break;
			default:
				System.out.println("Scelta non valida!");
				break;
			}
			System.out.println("Premi Enter per continuare...");
			sc.nextLine();
			
		} while (!scelta.equals("9"));

		
		
		
		
	
		
		sc.close();
		
		
	
			
	}

	
	
}
