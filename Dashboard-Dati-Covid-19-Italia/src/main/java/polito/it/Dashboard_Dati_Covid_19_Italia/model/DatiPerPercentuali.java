package polito.it.Dashboard_Dati_Covid_19_Italia.model;

public class DatiPerPercentuali {
	
	private String nome;
	private int popolazioneTotale;
	private int postiTerapiaIntensiva;
	
	

	public DatiPerPercentuali(String nome, int popolazioneTotale, int postiTerapiaIntensiva) {
		super();
		this.nome = nome;
		this.popolazioneTotale = popolazioneTotale;
		this.postiTerapiaIntensiva = postiTerapiaIntensiva;
	}

	public String getNome() {
		return nome;
	}

	public int getPopolazioneTotale() {
		return popolazioneTotale;
	}

	public int getPostiTerapiaIntensiva() {
		return postiTerapiaIntensiva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		DatiPerPercentuali other = (DatiPerPercentuali) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "nome=" + nome + ", popolazioneTotale=" + popolazioneTotale
				+ ", postiTerapiaIntensiva=" + postiTerapiaIntensiva ;
	}
	
	
	

}
