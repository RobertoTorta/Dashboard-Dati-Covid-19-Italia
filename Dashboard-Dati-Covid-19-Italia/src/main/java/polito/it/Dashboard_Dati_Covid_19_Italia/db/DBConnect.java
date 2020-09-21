package polito.it.Dashboard_Dati_Covid_19_Italia.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {
	private static final String jdbcURL = "jdbc:mysql://localhost/daticoviditalia?serverTimezone=UTC";
	private static HikariDataSource ds;

	public static Connection getConnection() {

		if (ds == null) {
			
			ds = new HikariDataSource();

			ds.setJdbcUrl(jdbcURL);
			ds.setUsername("");
			ds.setPassword("");
			ds.addDataSourceProperty("cachePrepStmts", "true");
			ds.addDataSourceProperty("prepStmtCacheSize", "250");
			ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		}

		try {

			return ds.getConnection();

		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
	}

}
