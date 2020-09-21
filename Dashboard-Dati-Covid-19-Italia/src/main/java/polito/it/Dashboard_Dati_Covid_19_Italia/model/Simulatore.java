package polito.it.Dashboard_Dati_Covid_19_Italia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import polito.it.Dashboard_Dati_Covid_19_Italia.model.Event.EventType;

public class Simulatore {
	// PARAMETRI MODIFICABILI DA INTERFACCIA
	private double trattamentoSpecializzato;
	private double ospedaliSaturi;
	private double distanziamento;
	private int contagiatiIniziali;
	private int settimanaDiFine;
	private double probContagioBase;
	private double probMorteBase;
	private double probGuarigioneBase;
	private int giorniDaAsintomatico;

	public Simulatore(int contagiatiIniziali, int settimanaDiFine, double distanziamento, double trattamentoPlasma,
			double ospedaliSaturi,int giorniDaAsintomatico, double probabilitaContagioIniziale, double probabilitaDecessoIniziale, double probabilitaGuarigioneIniziale) {
		this.contagiatiIniziali = contagiatiIniziali;
		this.settimanaDiFine = settimanaDiFine;
		this.distanziamento = distanziamento;
		this.trattamentoSpecializzato = trattamentoPlasma;
		this.ospedaliSaturi = ospedaliSaturi;
		this.probContagioBase=probabilitaContagioIniziale;
		this.probMorteBase=probabilitaDecessoIniziale;
		this.probGuarigioneBase=probabilitaGuarigioneIniziale;
		this.giorniDaAsintomatico=giorniDaAsintomatico;
	}

	// PARAMETRI DI SIMULAZIONE
	private double diminuazioneProbContagio = 0.1;
	private double aumentoProbMorte = 0.05;
	private double aumentoProbGuarigione = 0.35;
	private double probContagioIniziale;
	private double probMorteIniziale;
	private double probGuarigioneIniziale;

	// OUTPUT

	private int totaleContagiati;
	private int totaleMorti;
	private int totaleGuariti;

	// CODA DEGLI EVENTI

	private PriorityQueue<Event> queue;

	// INIZIALIZZAZIONE

	public void init() {
		//Pulisco eventuali simulazioni vecchie ed aggiorno le probabilità a seconda dei parametri di input
		this.queue = new PriorityQueue<Event>();
		this.totaleContagiati = this.totaleGuariti = this.totaleMorti = 0;
		this.probContagioIniziale = this.probContagioBase - this.distanziamento;
		this.probMorteIniziale = this.probMorteBase + this.ospedaliSaturi;
		this.probGuarigioneIniziale = this.probGuarigioneBase + trattamentoSpecializzato;

		// genero gli eventi iniziali
		for (int i = 0; i < contagiatiIniziali; i++) {
			Persona persona = new Persona(probContagioIniziale, probGuarigioneIniziale, probMorteIniziale);
			queue.add(new Event(EventType.CONTAGIA, 1, persona));
			queue.add(new Event(EventType.GUARISCI, 1, persona));
			totaleContagiati++;
		}
	}

	// ESECUZIONE
	public void run() {
		while (!queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		double probabilita;
		switch (e.getType()) {
		case CONTAGIA:
			// la persona potrebbe contagiare un'altra persona, in questo caso creo una persona nuova ed aggiungo
			//due nuovi eventi (contagia e guarisci) per il primo giorno finito il periodo da asintomatico
			probabilita = e.getPersona().getProbabilitaContagio();
			if (Math.random() <= probabilita && e.getGiorno() < settimanaDiFine*7) {
				Persona persona = new Persona(probContagioIniziale, probGuarigioneIniziale, probMorteIniziale);
				queue.add(new Event(EventType.CONTAGIA, e.getGiorno() + giorniDaAsintomatico, persona));
				queue.add(new Event(EventType.GUARISCI, e.getGiorno() + giorniDaAsintomatico, persona));
				totaleContagiati++;
			}
			break;
		case GUARISCI:
			// la persona potrebbe guarire, se non guarisce mi aggiuge alla coda la
			// probabilità di morire nello stesso giorno
			probabilita = e.getPersona().getProbabilitaGuarigione();
			if (Math.random() <= probabilita) {
				totaleGuariti++;
			} else {
				queue.add(new Event(EventType.DECEDI, e.getGiorno(), e.getPersona()));
			}
			break;
		case DECEDI:
			// la persona potrebbe morire, altrimenti si modificano le probabilità e si crea
			// un evento contagia e uno guarisci per il giorno dopo
			probabilita = e.getPersona().getProbabilitaDecesso();
			if (Math.random() <= probabilita) {
				totaleMorti++;
				break;
			}
			// Aggiorno le probabilità, diminuendo la probabilità di contagiare ed aumentando quella di guarire e di morire
			if (e.getPersona().getProbabilitaContagio() > 0)
				e.getPersona().setProbabilitaContagio(e.getPersona().getProbabilitaContagio() - diminuazioneProbContagio);
			if (e.getPersona().getProbabilitaDecesso() < 1)
				e.getPersona().setProbabilitaDecesso(e.getPersona().getProbabilitaDecesso() + aumentoProbMorte);
			if (e.getPersona().getProbabilitaGuarigione() < 1)
				e.getPersona().setProbabilitaGuarigione(e.getPersona().getProbabilitaGuarigione() + aumentoProbGuarigione);

			queue.add(new Event(EventType.CONTAGIA, e.getGiorno() + 1, e.getPersona()));
			queue.add(new Event(EventType.GUARISCI, e.getGiorno() + 1, e.getPersona()));
			break;
		}
	}

	public int getTotaleContagiati() {
		return totaleContagiati;
	}

	public int getTotaleMorti() {
		return totaleMorti;
	}

	public int getTotaleGuariti() {
		return totaleGuariti;
	}

}