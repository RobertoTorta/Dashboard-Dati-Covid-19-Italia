package polito.it.Dashboard_Dati_Covid_19_Italia.model;

public class Persona {
	
	private double probabilitaContagio;
	private double probabilitaGuarigione;
	private double probabilitaDecesso;
	private double eta;
	
	
	public Persona(double probabilitaContagio, double probabilitaGuarigione,
			double probabilitaDecesso) {
		this.probabilitaContagio = probabilitaContagio;
		eta=Math.random();
		if(eta<0.4) {
		this.probabilitaGuarigione = probabilitaGuarigione+0.1;
		this.probabilitaDecesso = 0;
		}
		if(eta>=0.4&&eta<0.6) {
			this.probabilitaGuarigione = probabilitaGuarigione;
			this.probabilitaDecesso = 0;
		}
		if(eta>=0.6&&eta<0.8) {
			this.probabilitaGuarigione = probabilitaGuarigione-0.1;
			this.probabilitaDecesso = probabilitaDecesso+0.1;
		}
		if(eta>=0.8) {
			this.probabilitaGuarigione = probabilitaGuarigione-0.2;
			this.probabilitaDecesso = probabilitaDecesso+0.2;
		}
	}


	public double getProbabilitaContagio() {
		return probabilitaContagio;
	}


	public void setProbabilitaContagio(double probabilitaContagio) {
		this.probabilitaContagio = probabilitaContagio;
	}


	public double getProbabilitaGuarigione() {
		return probabilitaGuarigione;
	}


	public void setProbabilitaGuarigione(double probabilitaGuarigione) {
		this.probabilitaGuarigione = probabilitaGuarigione;
	}


	public double getProbabilitaDecesso() {
		return probabilitaDecesso;
	}


	public void setProbabilitaDecesso(double probabilitaDecesso) {
		this.probabilitaDecesso = probabilitaDecesso;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(eta);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(probabilitaContagio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(probabilitaDecesso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(probabilitaGuarigione);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Persona other = (Persona) obj;
		if (Double.doubleToLongBits(eta) != Double.doubleToLongBits(other.eta))
			return false;
		if (Double.doubleToLongBits(probabilitaContagio) != Double.doubleToLongBits(other.probabilitaContagio))
			return false;
		if (Double.doubleToLongBits(probabilitaDecesso) != Double.doubleToLongBits(other.probabilitaDecesso))
			return false;
		if (Double.doubleToLongBits(probabilitaGuarigione) != Double.doubleToLongBits(other.probabilitaGuarigione))
			return false;
		return true;
	}
	
	
	

}