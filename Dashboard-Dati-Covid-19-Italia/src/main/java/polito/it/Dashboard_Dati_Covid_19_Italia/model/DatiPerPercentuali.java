package polito.it.Dashboard_Dati_Covid_19_Italia.model;

public class DatiPerPercentuali {
	
	private String nome;
	private int popolazioneTotale;
	private int postiTerapiaIntensiva;
	
	public DatiPerPercentuali(String nome) {
		if(nome=="Italia") {
			this.popolazioneTotale=60589085;
			this.postiTerapiaIntensiva=6741;
		}
		if(nome=="Piemonte") {
			this.popolazioneTotale=4392526;
			this.postiTerapiaIntensiva=499;
		}
		if(nome=="Puglia") {
			this.popolazioneTotale=4063888;
			this.postiTerapiaIntensiva=302;
		}
		if(nome=="Sardegna") {
			this.popolazioneTotale=1653135;
			this.postiTerapiaIntensiva=123;
		}
		if(nome=="Sicilia") {
			this.popolazioneTotale=5056641;
			this.postiTerapiaIntensiva=392;
		}
		if(nome=="P.A Bolzano") {
			this.popolazioneTotale=531430;
			this.postiTerapiaIntensiva=89;
		}
		if(nome=="Marche") {
			this.popolazioneTotale=1538055;
			this.postiTerapiaIntensiva=168;
		}
		if(nome=="Molise") {
			this.popolazioneTotale=310449;
			this.postiTerapiaIntensiva=31;
		}
		if(nome=="Lombardia") {
			this.popolazioneTotale=10018806;
			this.postiTerapiaIntensiva=1600;
		}
		if(nome=="Lazio") {
			this.popolazioneTotale=5898124;
			this.postiTerapiaIntensiva=675;
		}
		if(nome=="Liguria") {
			this.popolazioneTotale=1565307;
			this.postiTerapiaIntensiva=186;
		}
		if(nome=="Friuli Venezia Giulia") {
			this.popolazioneTotale=1217872;
			this.postiTerapiaIntensiva=127;
		}
		if(nome=="Emilia-Romagna") {
			this.popolazioneTotale=4448841;
			this.postiTerapiaIntensiva=539;
		}
		if(nome=="Campania") {
			this.popolazioneTotale=5839084;
			this.postiTerapiaIntensiva=586;
		}
		if(nome=="Calabria") {
			this.popolazioneTotale=1965128;
			this.postiTerapiaIntensiva=153;
		}
		if(nome=="Basilicata") {
			this.popolazioneTotale=570365;
			this.postiTerapiaIntensiva=49;
		}
		if(nome=="Abruzzo") {
			this.popolazioneTotale=1322247;
			this.postiTerapiaIntensiva=109;
		}
		if(nome=="P.A. Trento") {
			this.popolazioneTotale=531430;
			this.postiTerapiaIntensiva=89;
		}
		if(nome=="Toscana") {
			this.popolazioneTotale=3742437;
			this.postiTerapiaIntensiva=447;
		}
		if(nome=="Umbria") {
			this.popolazioneTotale=888908;
			this.postiTerapiaIntensiva=70;
		}
		if(nome=="Valle d'Aosta") {
			this.popolazioneTotale=126883;
			this.postiTerapiaIntensiva=30;
		}
		if(nome=="Veneto") {
			this.popolazioneTotale=4907529;
			this.postiTerapiaIntensiva=600;
		}
		
		
		this.nome = nome;
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
	
	
	

}
