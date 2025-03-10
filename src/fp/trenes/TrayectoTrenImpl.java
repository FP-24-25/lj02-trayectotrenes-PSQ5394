package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import fp.utiles.Checkers;

public class TrayectoTrenImpl implements TrayectoTren {

	//ATRIBUTOS
	private String codigoTren;
	private String nombre;
	private TipoTren Tipo;
	private List<String> estaciones;
	private List<LocalTime> horasSalida;
	private List<LocalTime> horasLlegada;
	///comentario
	public TrayectoTrenImpl (String codigoTren, 
			String nombre, TipoTren tipo,
			String origen, String destino,
			LocalTime horaSalida,
			LocalTime horaLlegada) {
		Checkers.check ("…", esCodigoTrenOK(codigoTren));
		this.codigoTren = codigoTren;
		this.nombreTrayecto = nombreTrayecto;
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
	
	@Override
	public String getCodigoTren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoTren getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getEstaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalTime> getHorasSalida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalTime> getHorasLlegada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getHoraSalida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getHoraLlegada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration getDuracion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getHoraSalida(String estacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getHoraLlegada(String estacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void anadirEstacionIntermedia(int posicion, String estacion,
			LocalTime horaLlegada, LocalTime horaSalida) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarEstacionIntermedia(String estacion) {
		// TODO Auto-generated method stub

	}

}
