package logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Paciente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5698020486666860908L;
	private String cedula;
	private String nombre;
	private String genero;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private HistorialClinica historial;
	private ArrayList<Consulta> misConsulta;
	
	public Paciente(String cedula, String nombre, String genero, Date fechaNacimiento, String direccion,
			String telefono) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.historial = null;
		misConsulta = new ArrayList<Consulta>();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public HistorialClinica getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialClinica historial) {
		this.historial = historial;
	}

	public ArrayList<Consulta> getMisConsulta() {
		return misConsulta;
	}

	public void setMisConsulta(ArrayList<Consulta> misConsulta) {
		this.misConsulta = misConsulta;
	}
	
	
}
