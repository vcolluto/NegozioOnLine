package org.generation.italy;

import java.util.HashMap;


public class Negozio {	
	public HashMap<String, Prodotto> elencoProdotti= new HashMap<>(){{
		put("AL001",new Prodotto("AL001","Pane",3.70f,100));
		put("AL002",new Prodotto("AL002","Pasta",1.50f,200));
		put("AL003",new Prodotto("AL003","Biscotti",4.20f,10));
		put("CAS001",new Prodotto("CAS001","Spugne",0.80f,5));
	}};

	//costruttore (situazione iniziale)
	public Negozio() {
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
			elencoProdotti.get(codice).setSconto(sconto);
		
		return esito;
	}
	
}
