package polito.it.Dashboard_Dati_Covid_19_Italia.model;

import java.util.LinkedList;
import java.util.List;

import polito.it.Dashboard_Dati_Covid_19_Italia.db.DatiCovidItaliaDAO;

public class Model {
	List<datoNazionale> datiNazionali = new LinkedList<>();
	
	public Model() {
		DatiCovidItaliaDAO dao= new DatiCovidItaliaDAO();
		datiNazionali=dao.estraiDatiNazionali();
		System.out.println(datiNazionali.toString());
	}
	
	public void run() {
		
	}
	
	
	public static void main(String[] args) {
		Model m= new Model();
				
	}
}
