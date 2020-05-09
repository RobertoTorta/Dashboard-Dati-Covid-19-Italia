package polito.it.Dashboard_Dati_Covid_19_Italia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import polito.it.Dashboard_Dati_Covid_19_Italia.model.datoNazionale;


public class DatiCovidItaliaDAO {
	
	public List<datoNazionale> estraiDatiNazionali() {
		String sql = "SELECT data , terapia_intensiva, nuovi_positivi, dimessi_guariti, deceduti, totale_casi FROM datinazionali" ;
		List<datoNazionale> datiNazionali = new LinkedList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				datiNazionali.add(new datoNazionale(rs.getString("data"),
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

}
