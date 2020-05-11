package polito.it.Dashboard_Dati_Covid_19_Italia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import polito.it.Dashboard_Dati_Covid_19_Italia.model.DatiPerPercentuali;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.DatoNazionale;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.DatoPerGrafico;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.DatoRegionale;


public class DatiCovidItaliaDAO {
	
	/**
	 * Permette di estrarre Data, terapia intensiva, nuovi positivi, dimessi guariti, deceduti e totale casi dalla tabella
	 * datinazionali nel database DatiCovidItalia
	 * @return una mappa (Tree) dei Dati nazionali, con la data come chiave primaria
	 */
	
	public TreeMap<LocalDate,DatoNazionale> estraiDatiNazionali() {
		String sql = "SELECT data , terapia_intensiva, nuovi_positivi, dimessi_guariti, deceduti, totale_casi FROM datinazionali" ;
		TreeMap<LocalDate,DatoNazionale> datiNazionali = new TreeMap<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String dataString= rs.getString("data");
				LocalDate data= LocalDate.parse(((String)dataString.substring(2, 12)));
				datiNazionali.put(data,new DatoNazionale(data,
					rs.getInt("terapia_intensiva"),rs.getInt("nuovi_positivi"),
					rs.getInt("dimessi_guariti"),rs.getInt("deceduti"),rs.getInt("totale_casi")));
				
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return datiNazionali;
	}
	
	/**
	 * Permette di estrarre Data, regione, terapia intensiva, nuovi positivi, dimessi guariti, deceduti e totale casi dalla tabella
	 * datiregionali nel database DatiCovidItalia, per la data passata come parametro in formato stringa
	 * @return una lista (Linked) dei dati di ogni regione nella data passata come parametro
	 */
	public LinkedList<DatoRegionale> estraiDatiRegionaliPerGiornata(String dataDaCercare) {
		String sql = "SELECT data , denominazione_regione, terapia_intensiva, nuovi_positivi, dimessi_guariti, deceduti, totale_casi FROM datiregionali WHERE SUbstr(data,1,10)=?" ;
		LinkedList<DatoRegionale> datiRegionali = new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, dataDaCercare);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String dataString= rs.getString("data");
				LocalDate data= LocalDate.parse(((String)dataString.substring(0, 10)));
				datiRegionali.add(new DatoRegionale(	data, rs.getString("denominazione_regione"), rs.getInt("terapia_intensiva"),
															rs.getInt("nuovi_positivi"), rs.getInt("dimessi_guariti"),
															rs.getInt("deceduti"), rs.getInt("totale_casi")));
				
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return datiRegionali;
	}
	
	/**
	 * Permette di estrarre Data, regione, terapia intensiva, nuovi positivi, dimessi guariti, deceduti e totale casi dalla tabella
	 * datiregionali nel database DatiCovidItalia, per la regione passata come parametro in formato stringa
	 * @return una lista (Linked) dei dati di ogni giorno della regione passata come parametro
	 */
	public LinkedList<DatoRegionale> estraiDatiRegionePerRegione(String regioneDaCercare) {
		String sql = "SELECT data , denominazione_regione, terapia_intensiva, nuovi_positivi, dimessi_guariti, deceduti, totale_casi FROM datiregionali WHERE denominazione_regione=?" ;
		LinkedList<DatoRegionale> datiRegionali = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, regioneDaCercare);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String dataString= rs.getString("data");
				LocalDate data= LocalDate.parse(((String)dataString.substring(0, 10)));
				datiRegionali.add(new DatoRegionale(	data, rs.getString("denominazione_regione"), rs.getInt("terapia_intensiva"),
															rs.getInt("nuovi_positivi"), rs.getInt("dimessi_guariti"),
															rs.getInt("deceduti"), rs.getInt("totale_casi")));
				
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return datiRegionali;
	}
	
	
	/**
	 * Permette di estrarre Luogo, popolazione totale e posti in terapia intensiva dalla tabella
	 * statistiche popolazione nel database DatiCovidItalia.
	 * @return una mappa (Tree) dei dati di ogni regione (Italia compresa), usando il nome della regione come chiave primaria.
	 */
	public TreeMap<String,DatiPerPercentuali> estraiDatiPerPercentuali(){
		String sql = "SELECT Luogo , Popolazione_Totale, Posti_Terapia_Intensiva FROM statistichepopolazione" ;
		TreeMap<String, DatiPerPercentuali> datiPerPercentuali = new TreeMap<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				datiPerPercentuali.put(rs.getString("Luogo"),new DatiPerPercentuali(rs.getString("Luogo"),rs.getInt("Popolazione_Totale"),
						rs.getInt("Posti_Terapia_Intensiva")));
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return datiPerPercentuali;
	}
	
	public DatoPerGrafico estraiDatiPerGrafico(String regione){
		String sql;
		if(regione.equals("Italia")) 
			sql = "SELECT totale_casi, deceduti , dimessi_guariti FROM datiNazionali WHERE data=(SELECT MAX(data) FROM datiNazionali)" ;
		else
			sql="SELECT totale_casi, deceduti , dimessi_guariti FROM datiRegionali WHERE denominazione_regione=? AND data=(SELECT MAX(data) FROM datiRegionali)";
		
		DatoPerGrafico dpg;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			if(!regione.equals("Italia")) {
				st.setString(1, regione);
				System.out.println(sql);
			}
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			rs.next();
			dpg= new DatoPerGrafico(rs.getInt("totale_casi"), rs.getInt("deceduti"), rs.getInt("dimessi_guariti"));
			
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return dpg;
	}

}
