package org.generation.italy;

public class Prodotto {
	private String codice, descrizione;
	private float prezzo, sconto;
	private int quantitàDisponibile;
	
	//costruttore
	public Prodotto(String codice, String descrizione, float prezzo, int quantitàDisponibile) {		
		//valori parametrici
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.quantitàDisponibile = quantitàDisponibile;
		//valore predefinito
		this.sconto=0;		
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		if (!codice.isEmpty())
			this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		if (!descrizione.isEmpty())
			this.descrizione = descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		if (prezzo>0)
			this.prezzo = prezzo;
	}

	public float getSconto() {
		return sconto;
	}

	public void setSconto(float sconto) {
		if (sconto>=0 && sconto<=100)
			this.sconto = sconto;
	}

	public int getQuantitàDisponibile() {
		return quantitàDisponibile;
	}

	public void setQuantitàDisponibile(int quantitàDisponibile) {
		if (quantitàDisponibile>0)
			this.quantitàDisponibile = quantitàDisponibile;
	}

	@Override
	public String toString() {		//genera una stringa contenente tutti i dati del prodotto
		return "Prodotto [codice=" + codice + ", descrizione=" + descrizione + ", prezzo=" + prezzo + ", sconto="
				+ sconto + ", quantitàDisponibile=" + quantitàDisponibile + "]";
	}
	
	
	
	
	
	
}
