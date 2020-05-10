package polito.it.Dashboard_Dati_Covid_19_Italia.model;

import java.time.LocalDate;

public class DatoNazionale {
	private LocalDate data;
	private int terapiaIntensiva;
	private int nuoviPositivi;
	private int dimessiGuariti;
	private int deceduti;
	private int totaleCasi;

	public DatoNazionale(LocalDate data, int terapiaIntensiva, int nuoviPositivi, int dimessiGuariti, int deceduti,
			int totaleCasi) {
		super();
		this.data = data;
		this.terapiaIntensiva = terapiaIntensiva;
		this.nuoviPositivi = nuoviPositivi;
		this.dimessiGuariti = dimessiGuariti;
		this.deceduti = deceduti;
		this.totaleCasi = totaleCasi;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getTerapiaIntensiva() {
		return terapiaIntensiva;
	}

	public void setTerapiaIntensiva(int terapiaIntensiva) {
		this.terapiaIntensiva = terapiaIntensiva;
	}

	public int getNuoviPositivi() {
		return nuoviPositivi;
	}

	public void setNuoviPositivi(int nuoviPositivi) {
		this.nuoviPositivi = nuoviPositivi;
	}

	public int getDimessiGuariti() {
		return dimessiGuariti;
	}

	public void setDimessiGuariti(int dimessiGuariti) {
		this.dimessiGuariti = dimessiGuariti;
	}

	public int getDeceduti() {
		return deceduti;
	}

	public void setDeceduti(int deceduti) {
		this.deceduti = deceduti;
	}

	public int getTotaleCasi() {
		return totaleCasi;
	}

	public void setTotaleCasi(int totaleCasi) {
		this.totaleCasi = totaleCasi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatoNazionale other = (DatoNazionale) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "data=" + data + ", nuoviPositivi=" + nuoviPositivi + ", deceduti=" + deceduti
				+ ", totaleCasi=" + totaleCasi  ;
	}
	
	
	
	
	
	
	

}
