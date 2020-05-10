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

import polito.it.Dashboard_Dati_Covid_19_Italia.model.DatoNazionale;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.DatoRegionale;


public class DatiCovidItaliaDAO {
	
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
	
	public LinkedList<DatoRegionale> estraiDatiRegionaliPerGiornata(String dataDaCercare) {
		String sql = "SELECT data , denominazione_regione, terapia_intensiva, nuovi_positivi, dimessi_guariti, deceduti, totale_casi FROM datiregionali WHERE data=?" ;
		LinkedList<DatoRegionale> datiRegionali = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, dataDaCercare+"T170000");
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
	
	public LinkedList<DatoRegionale> estraiDatiRegionaPerRegione(String regioneDaCercare) {
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

}
