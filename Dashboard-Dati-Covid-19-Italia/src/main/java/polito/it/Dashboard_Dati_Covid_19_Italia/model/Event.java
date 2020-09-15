package polito.it.Dashboard_Dati_Covid_19_Italia.model;

public class Event implements Comparable<Event> {
	
	public enum EventType{
		 CONTAGIA,
		 GUARISCI,
		 DECEDI,
	}
	
	private EventType type;
	private Integer giorno;
	private Persona persona;

	public EventType getType() {
		return type;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public Persona getPersona() {
		return persona;
	}

	public Event(EventType contagia, Integer giorno, Persona persona) {
		this.type = contagia;
		this.giorno = giorno;
		this.persona = persona;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		if(this.giorno==o.giorno) {
				if(this.persona.equals(o.persona)) {
					if(this.type.equals(EventType.CONTAGIA)){
						return -1;
					}else {return +1;}
				}
				if((this.type.equals(EventType.CONTAGIA))&&!(o.type.equals(EventType.CONTAGIA))){
					return -1;
				}else {return +1;}
				
			
				
			}
		
			return this.giorno.compareTo(o.giorno);
		
	}

}
