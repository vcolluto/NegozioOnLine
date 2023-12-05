package org.generation.italy;

import java.util.HashMap;
import java.util.Iterator;



public class Negozio {	
	
	private HashMap<String, Prodotto> elencoProdotti= new HashMap<String, Prodotto>(){{
		put("AL001",new Prodotto("AL001","Pane",3.70f,100));
		put("AL002",new Prodotto("AL002","Pasta",1.50f,200));
		put("AL003",new Prodotto("AL003","Biscotti",4.20f,10));
		put("CAS001",new Prodotto("CAS001","Spugne",0.80f,5));
	}};
	
		
	private Carrello carrello=new Carrello();

	

	//costruttore (situazione iniziale)
	public Negozio() {
		//metodo alternativo per inizializzare l'hashmap:
		Prodotto p=new Prodotto("CAS002","Detersivo piatti",1.90f,12);
		elencoProdotti.put(p.getCodice(), p); //il codice (chiave dell'hashmap) lo prendo direttamente dal prodotto
			
	}
	
	//public inserisciProdotto(String codice, String descrizione, float prezzo, int quantitàDisponibile)

	public boolean inserisciProdotto(Prodotto prodotto) {
		boolean esito=false;
		//verifico che il codice non sia già presente
		if (!elencoProdotti.containsKey(prodotto.getCodice()))  //nel mio elencoProdotti c'è la chiave = prodotto.getCodice()?
		{
			elencoProdotti.put(prodotto.getCodice(), prodotto);
			esito=true;		//sono riuscito ad aggiungere!
		}
		return esito;
		
	}
	
	public boolean applicaSconto(String codice, float sconto) {
		boolean esito=false;
		if (elencoProdotti.containsKey(codice)) 	//se esiste il prodotto con quel codice
		{
		/*	if (elencoProdotti.get(codice).setSconto(sconto))
				esito=true;*/
			esito=elencoProdotti.get(codice).setSconto(sconto); //
		}
		
		return esito;
	}
	
	
	public boolean aggiungiAlCarrello(String codice, int quantità) {		
		boolean esito=false;
		if (elencoProdotti.containsKey(codice) && 				//se esiste il prodotto con quel codice
			elencoProdotti.get(codice).getQuantitàDisponibile()>=quantità)	//e la quantità è sufficiente				 	
		{
			carrello.aggiungiProdotto(codice, quantità);
			esito=true;
		}
		return esito;
	}
	
	public Carrello getCarrello() {
		return carrello;
	}

	public Iterable<Prodotto> getElencoProdotti() {	//non restituisco direttamente l'hasmap, ma un "iteratore di prodotti"
		return new Iterable<Prodotto>() {			
			@Override
			public Iterator<Prodotto> iterator() {				
				return elencoProdotti.values().iterator();
			}
		};
	}
	
	public boolean esisteProdotto(String codice) {
		return elencoProdotti.containsKey(codice);
	}
	
	public Prodotto getProdotto(String codice) {
		return elencoProdotti.get(codice);
	}
	
	
}
