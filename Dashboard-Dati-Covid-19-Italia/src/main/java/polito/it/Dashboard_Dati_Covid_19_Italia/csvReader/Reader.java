package polito.it.Dashboard_Dati_Covid_19_Italia.csvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import polito.it.Dashboard_Dati_Covid_19_Italia.db.DatiCovidItaliaDAO;

public class Reader {

	public static void leggiIlFile( String nomeFile) {
		String fileName = nomeFile;
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.next();
			while(inputStream.hasNext()) {
				String data = inputStream.next();
				System.out.println(data+";");
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {

		leggiIlFile("dpc-covid19-ita-regioni.csv");
		
	}

}
