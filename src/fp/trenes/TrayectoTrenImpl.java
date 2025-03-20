package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Validators;

public class TrayectoTrenImpl implements TrayectoTren {

	//ATRIBUTOS
	private String codigoTren;
	private String nombre;
	private TipoTren tipo;
	private List<String> estaciones;
	private List<LocalTime> horasSalida;
	private List<LocalTime> horasLlegada;
	
	//CONSTRUCTOR
	public TrayectoTrenImpl (String codigoTren, 
			String nombre, TipoTren tipo,
			String origen, String destino,
			LocalTime horaSalida,
			LocalTime horaLlegada) {
		Checkers.check ("Codigo no válido", (checkCodigoTrenOK(codigoTren)));
		this.codigoTren = codigoTren;
		this.nombre = nombre;
		this.tipo = tipo;
		Checkers.checkNoNull(horaSalida, horaLlegada);
		Checkers.check("…", horaSalida.isBefore(horaLlegada));
		this.estaciones = new LinkedList<String>();
		estaciones.add(origen); estaciones.add(destino);
		this.horasSalida = new LinkedList<LocalTime>();
		horasSalida.add(horaSalida); horasSalida.add(null);
		this.horasLlegada = new LinkedList<LocalTime>();
		horasLlegada.add(null);horasLlegada.add(horaLlegada);
	}
	
	private Boolean checkCodigoTrenOK(String codigoTren) {
		return codigoTren.length()==5 && Validators.sonDigitos(codigoTren);
	}

	//GETTERS

	@Override
	public String getCodigoTren() {
		Checkers.check ("Codigo no válido", checkCodigoTrenOK(codigoTren));
		return codigoTren;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public TipoTren getTipo() {
		return tipo;
	}

	@Override
	public List<String> getEstaciones() {
		return new ArrayList<String>(estaciones);
	}

	@Override
	public List<LocalTime> getHorasSalida() {
		return new ArrayList<LocalTime>(horasSalida);
	}

	@Override
	public List<LocalTime> getHorasLlegada() {
		return horasLlegada;
	}

	@Override
	public LocalTime getHoraSalida() {
		return horasSalida.get(0);
	}

	@Override
	public LocalTime getHoraLlegada() {
		return horasLlegada.get(0);
	}

	@Override
	public Duration getDuracion() {
		return Duration.between(getHoraSalida(), getHoraLlegada());
	}

	@Override
	public LocalTime getHoraSalida(String estacion) {
		LocalTime res = null;
		int pos = estaciones.indexOf(estacion);
		if (pos>=0) {
			res = horasSalida.get(pos);
		}
		return res;
	}

	@Override
	public LocalTime getHoraLlegada(String estacion) {
		LocalTime res = null;
		int pos = estaciones.indexOf(estacion);
		if (pos>=0) {
			res = horasLlegada.get(pos);
		}
		return res;
	}
	
	//FUNCIONES AUXILIARES

	@Override
	public void anadirEstacionIntermedia(int posicion, String estacion,
			LocalTime horaLlegada, LocalTime horaSalida) {
		Checkers.check("Posición intermedia no válida",
				posicion>0 && posicion<estaciones.size());
		Checkers.check("Hora de salida posterior a la de llegada",
				horaSalida.isAfter(horaLlegada));
		Checkers.check("Hora de llegada tiene que ser posterior a la hora de salida de la estación anterior",
				horaLlegada.isAfter(horasSalida.get(posicion-1)));
		Checkers.check("Hora de salida es anterior a la hora de llegada de la siguiente estacion",
				horaSalida.isBefore(horasLlegada.get(posicion-1)));
		estaciones.add(posicion,nombre);
		horasSalida.add(posicion, horaSalida);
		horasLlegada.add(posicion, horaLlegada);

	}

	@Override
	public void eliminarEstacionIntermedia(String estacion) {
		Checkers.check("No puede ser la primera estación", 
				!(estacion.equals(estaciones.getFirst())));
		Checkers.check("No puede ser la ultima estación", 
				!(estacion.equals(estaciones.getLast())));
		Checkers.check("La estacion no está", estaciones.contains(estacion));
		estaciones.remove(estacion);
	}
	
	//EQUALS
	
	public boolean equals (Object o) {
		boolean res = false;
		if (o instanceof TrayectoTrenImpl) {
			TrayectoTrenImpl t = (TrayectoTrenImpl) o;
			res = this.nombre.equals(t.nombre) &&
					this.codigoTren.equals(t.codigoTren) &&
					this.getHoraSalida().equals(t.getHoraSalida());
		}
		return res;
	}

	//HASHCODE
	
	public int hashCode() {
		return this.nombre.hashCode() +
				31* this.codigoTren.hashCode() +
				31*31* this.getHoraSalida().hashCode();
	}
	
	//COMPARETO
	
	public int compareTo(TrayectoTren t) {
		int res = nombre.compareTo(t.getNombre());
		if (res==0) {
			res = getHoraSalida().compareTo(t.getHoraSalida());
			if (res == 0) {
				res = codigoTren.compareTo(t.getCodigoTren());
			}
		}
		return res;
	}
	
	//TOSTRING
	
	@Override
	public String toString() {
		String res = getNombre() + "-" + getTipo() + "(" +
				getCodigoTren() + ")" + "\n";
		for (int i = 0; i<estaciones.size(); i++) {
			res = res+estaciones.get(i) + "\t";
		}
		return null;
	}

}

