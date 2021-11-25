package logical;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962584650480547382L;
	private String codigoVacuna;
	private String nombreVacuna;
	private Date fechaVacuna;
	
	public Vacuna(String codigoVacuna, String nombreVacuna, Date fechaVacuna) {
		super();
		this.codigoVacuna = codigoVacuna;
		this.nombreVacuna = nombreVacuna;
		this.fechaVacuna = fechaVacuna;
	}

	public String getCodigoVacuna() {
		return codigoVacuna;
	}

	public void setCodigoVacuna(String codigoVacuna) {
		this.codigoVacuna = codigoVacuna;
	}

	public String getNombreVacuna() {
		return nombreVacuna;
	}

	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}

	public Date getFechaVacuna() {
		return fechaVacuna;
	}

	public void setFechaVacuna(Date fechaVacuna) {
		this.fechaVacuna = fechaVacuna;
	}
	
	
}
