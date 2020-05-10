package polito.it.Dashboard_Dati_Covid_19_Italia.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import polito.it.Dashboard_Dati_Covid_19_Italia.db.DatiCovidItaliaDAO;

public class Model {
	TreeMap<LocalDate,DatoNazionale> datiNazionali = new TreeMap<>();
	LinkedList<DatoRegionale> datiRegionaliPerGiornata = new LinkedList<>();
	LinkedList<DatoRegionale> datiRegionaliPerRegione = new LinkedList<>();
	
	public Model() {
		
		DatiCovidItaliaDAO dao= new DatiCovidItaliaDAO();
		datiNazionali=dao.estraiDatiNazionali();
		System.out.println("\nDATI A LIVELLO NAZIONALE GIORNO PER GIORNO:");
		for(DatoNazionale dn: datiNazionali.values()) {
			System.out.println(dn.toString());
		}
		
		String dataEsempio= "2020-04-10";
		datiRegionaliPerGiornata= dao.estraiDatiRegionaliPerGiornata(dataEsempio);
		System.out.println("\nDATI A LIVELLO REGIONALE NEL GIORNO "+dataEsempio);
		for(DatoRegionale dr: datiRegionaliPerGiornata) {
			System.out.println(dr.toString());
		}
		
		String regioneEsempio= "Piemonte";
		datiRegionaliPerRegione= dao.estraiDatiRegionaPerRegione(regioneEsempio);
		System.out.println("\nDATI DELLA REGIONE "+regioneEsempio);
		for(DatoRegionale dr: datiRegionaliPerRegione) {
			System.out.println(dr.toString());
		}
		
		System.out.println("\nPercentuale contagiati nella regione "+ regioneEsempio+ " il giorno "+ dataEsempio) ;
		System.out.println(calcolaPercentualeAmmalatiPerLuogoNelGiorno(regioneEsempio, dataEsempio));
		
		System.out.println("\nPosti ancora liberi in terapia intensiva in Italia il 2020-04-03");
		System.out.println(calcolaPostiLiberiTerapiaIntensivaPerLuogoNelGiorno("Italia", "2020-04-03"));
		
		System.out.println("\nTasso di mortalità in Lombardia il 2020-04-11");
		System.out.println(calcolaTassoMortalitaPerLuogoNelGiorno("Lombardia", "2020-04-11"));
		
	}
	
	public float calcolaPercentualeAmmalatiPerLuogoNelGiorno(String luogo, String data) {
		float percentuale=0;
		LocalDate giorno= LocalDate.parse(data);
		if(luogo=="Italia") {
			if(datiNazionali.containsKey(giorno)) {
				DatiPerPercentuali dp= new DatiPerPercentuali(luogo);
				percentuale= ((float) datiNazionali.get(giorno).getTotaleCasi()*100)/(float)dp.getPopolazioneTotale();
				return percentuale;
			}
		}
			
		DatoRegionale temp=null;
		DatiCovidItaliaDAO dao= new DatiCovidItaliaDAO();
		for(DatoRegionale dr:dao.estraiDatiRegionaliPerGiornata(data)) {
			if(dr.getRegione().equals(luogo))
				temp= dr;
		}
		DatiPerPercentuali dp= new DatiPerPercentuali(luogo);
		percentuale = ((float)temp.getTotaleCasi()*100)/(float)dp.getPopolazioneTotale();
	
		
		return percentuale;
	}
	
	public int calcolaPostiLiberiTerapiaIntensivaPerLuogoNelGiorno(String luogo, String data) {
		int postiLiberi=0;
		LocalDate giorno= LocalDate.parse(data);
		if(luogo=="Italia") {
			if(datiNazionali.containsKey(giorno)) {
				DatiPerPercentuali dp= new DatiPerPercentuali(luogo);
				postiLiberi= dp.getPostiTerapiaIntensiva()-datiNazionali.get(giorno).getTerapiaIntensiva();
				return postiLiberi;
			}
		}
			
		DatoRegionale temp=null;
		DatiCovidItaliaDAO dao= new DatiCovidItaliaDAO();
		for(DatoRegionale dr:dao.estraiDatiRegionaliPerGiornata(data)) {
			if(dr.getRegione().equals(luogo))
				temp= dr;
		}
		DatiPerPercentuali dp= new DatiPerPercentuali(luogo);
		postiLiberi = dp.getPostiTerapiaIntensiva()-temp.getTerapiaIntensiva();	
		
		return postiLiberi;
	}
	
	public float calcolaTassoMortalitaPerLuogoNelGiorno(String luogo, String data) {
		float tasso=0;
		LocalDate giorno= LocalDate.parse(data);
		if(luogo=="Italia") {
			if(datiNazionali.containsKey(giorno)) {
				tasso=((float)datiNazionali.get(giorno).getDeceduti()*100)/(float)datiNazionali.get(giorno).getTotaleCasi();
				return tasso;
			}
		}
			
		DatoRegionale temp=null;
		DatiCovidItaliaDAO dao= new DatiCovidItaliaDAO();
		for(DatoRegionale dr:dao.estraiDatiRegionaliPerGiornata(data)) {
			if(dr.getRegione().equals(luogo))
				temp= dr;
		}
		tasso = ((float)temp.getDeceduti()*100)/temp.getTotaleCasi();	
		
		return tasso;
	}
	
	public static void main(String[] args) {
		Model m= new Model();
				
	}
}
