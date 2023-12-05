package org.generation.italy;

import java.util.HashMap;

public class Carrello {
	private HashMap<String, Integer> elencoProdotti=new HashMap<String, Integer>();
	
	
	public void aggiungiProdotto(String codice, int quantità) {
		elencoProdotti.put(codice, quantità);
	}


	public HashMap<String, Integer> getElencoProdotti() {
		return elencoProdotti;
	}
	
	
}
