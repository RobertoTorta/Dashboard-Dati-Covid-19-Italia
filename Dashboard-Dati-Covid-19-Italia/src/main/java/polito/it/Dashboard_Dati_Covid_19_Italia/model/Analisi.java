package polito.it.Dashboard_Dati_Covid_19_Italia.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import polito.it.Dashboard_Dati_Covid_19_Italia.db.DatiCovidItaliaDAO;

public class Analisi {

	TreeMap<LocalDate, DatoNazionale> datiNazionali;
	LinkedList<DatoRegionale> datiRegionaliPerGiornata;
	LinkedList<DatoRegionale> datiRegionaliPerRegione;
	TreeMap<String, DatiPerPercentuali> datiPerPercentuali;
	DatiCovidItaliaDAO dao;

	public Analisi() {
		datiNazionali = new TreeMap<>();
		datiRegionaliPerGiornata = new LinkedList<>();
		datiRegionaliPerRegione = new LinkedList<>();
		datiPerPercentuali = new TreeMap<>();
		dao = new DatiCovidItaliaDAO();

		datiPerPercentuali = dao.estraiDatiPerPercentuali();
		datiNazionali = dao.estraiDatiNazionali();
		}

	/**
	 * Permette di ricavare la percentuale di contagiati sulla popolazione, nel
	 * luogo e nella data passati da parametro Funziona sia a livello regionale che
	 * a livello nazionale
	 * 
	 * @return la percentuale float dei contagiati nella regione e nel giorno
	 *         passati come parametro
	 */
	public float calcolaPercentualeAmmalatiPerLuogoNelGiorno(String luogo, String data) {
		float percentuale = 0;
		DatiPerPercentuali dp = datiPerPercentuali.get(luogo);
		LocalDate giorno = LocalDate.parse(data);
		if (luogo.equals("Italia")) {
			if (datiNazionali.containsKey(giorno)) {
				percentuale = ((float) datiNazionali.get(giorno).getTotaleCasi() * 100)
						/ (float) dp.getPopolazioneTotale();
				return percentuale;
			}
		}

		DatoRegionale temp = null;
		DatiCovidItaliaDAO dao = new DatiCovidItaliaDAO();
		for (DatoRegionale dr : dao.estraiDatiRegionaliPerGiornata(data)) {
			if (dr.getRegione().equals(luogo))
			temp = dr;
		}

		percentuale = ((float) temp.getTotaleCasi() * 100) / (float) dp.getPopolazioneTotale();

		return percentuale;
	}

	/**
	 * Permette di calcolare i posti ancora liberi in terapia intensiva, nel luogo e
	 * nella data passati da parametro Funziona sia a livello regionale che a
	 * livello nazionale
	 * 
	 * @return il numero intero dei posti ancora disponibili in terapia intensiva
	 *         nella regione e nel giorno passati come parametro
	 */
	public int calcolaPostiLiberiTerapiaIntensivaPerLuogoNelGiorno(String luogo, String data) {
		int postiLiberi = 0;
		DatiPerPercentuali dp = datiPerPercentuali.get(luogo);
		LocalDate giorno = LocalDate.parse(data);
		if (luogo.equals("Italia")) {
			if (datiNazionali.containsKey(giorno)) {
				postiLiberi = dp.getPostiTerapiaIntensiva() - datiNazionali.get(giorno).getTerapiaIntensiva();
				return postiLiberi;
			}
		}

		DatoRegionale temp = null;
		DatiCovidItaliaDAO dao = new DatiCovidItaliaDAO();
		for (DatoRegionale dr : dao.estraiDatiRegionaliPerGiornata(data)) {
			if (dr.getRegione().equals(luogo))
				temp = dr;
		}
		postiLiberi = dp.getPostiTerapiaIntensiva() - temp.getTerapiaIntensiva();

		return postiLiberi;
	}

	/**
	 * Permette di calcolare il tasso di mortalità (deceduti rispetto ai contagiati)
	 * nel luogo e nella data passati da parametro Funziona sia a livello regionale
	 * che a livello nazionale
	 * 
	 * @return il tasso (float) di mortalità nella regione e nel giorno passati come
	 *         parametro
	 */
	public float calcolaTassoMortalitaPerLuogoNelGiorno(String luogo, String data) {
		float tasso = 0;
		LocalDate giorno = LocalDate.parse(data);
		if (luogo.equals("Italia")) {
			if (datiNazionali.containsKey(giorno)) {
				tasso = ((float) datiNazionali.get(giorno).getDeceduti() * 100)
						/ (float) datiNazionali.get(giorno).getTotaleCasi();
				return tasso;
			}
		}

		DatoRegionale temp = null;
		DatiCovidItaliaDAO dao = new DatiCovidItaliaDAO();
		for (DatoRegionale dr : dao.estraiDatiRegionaliPerGiornata(data)) {
			if (dr.getRegione().equals(luogo))
				temp = dr;
		}
		tasso = ((float) temp.getDeceduti() * 100) / (float) temp.getTotaleCasi();

		return tasso;
	}

	/**
	 * Permette di calcolare il tasso di contagiosità R0 (quanti persone infetta
	 * ogni contagiato) nel luogo e nella data passati da parametro Funziona sia a
	 * livello regionale che a livello nazionale 
	 * @return il tasso (float) di contagiosità nella regione e nel giorno passati
	 *         come parametro
	 */
	public float calcolaTassoContagiosità(String luogo, String data) {
		float tasso = 0;
		LocalDate giorno = LocalDate.parse(data);
		LocalDate giornoPrima = giorno.minusDays(1);
		String dataGiornoPrima = giornoPrima.toString();
		if (luogo.equals("Italia")) {
			if (datiNazionali.containsKey(giorno)) {
				tasso = ((float) datiNazionali.get(giorno).getNuoviPositivi())
						/ (float) datiNazionali.get(giornoPrima).getNuoviPositivi();
				return tasso;
			}
		}

		DatoRegionale temp = null;
		DatiCovidItaliaDAO dao = new DatiCovidItaliaDAO();
		for (DatoRegionale dr : dao.estraiDatiRegionaliPerGiornata(data)) {
			if (dr.getRegione().equals(luogo))
				temp = dr;
		}

		DatoRegionale tempGiornoPrima = null;
		for (DatoRegionale dr : dao.estraiDatiRegionaliPerGiornata(dataGiornoPrima)) {
			if (dr.getRegione().equals(luogo))
				tempGiornoPrima = dr;
		}
		tasso = ((float) temp.getNuoviPositivi() / (float) tempGiornoPrima.getNuoviPositivi());

		return tasso;
	}
	
	public DatoPerGrafico estraiDatiPerGrafico(String regione,LocalDate data){
		DatiCovidItaliaDAO dao = new DatiCovidItaliaDAO();
		
		return dao.estraiDatiPerGrafico(regione,data);
	}


}
