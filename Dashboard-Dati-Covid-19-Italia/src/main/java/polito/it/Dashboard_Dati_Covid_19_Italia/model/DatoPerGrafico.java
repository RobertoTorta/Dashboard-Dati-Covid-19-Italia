package polito.it.Dashboard_Dati_Covid_19_Italia.model;

public class DatoPerGrafico {

	private Integer totaleCasi;
	private Integer totaleDecessi;
	private Integer guariti;
	
	public DatoPerGrafico(Integer totaleCasi, Integer totaleDecessi, Integer guariti) {
		super();
		this.totaleCasi = totaleCasi;
		this.totaleDecessi = totaleDecessi;
		this.guariti = guariti;
	}

	public Integer getTotaleCasi() {
		return totaleCasi;
	}

	public Integer getTotaleDecessi() {
		return totaleDecessi;
	}

	public Integer getGuariti() {
		return guariti;
	}

	@Override
	public String toString() {
		return "[totaleCasi=" + totaleCasi + ", totaleDecessi=" + totaleDecessi + ", guariti=" + guariti;
	}
	
	
	
	
}
