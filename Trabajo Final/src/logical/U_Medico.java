package logical;

import java.util.ArrayList;

public class U_Medico extends Usuario {

	private String codigoMedico;
	private String especialidad;
	private ArrayList<String> misPacientes;	
	public U_Medico(String codigoUsuario, String cedula, String login, String password, String nombre, String telefono,
			String direccion, String codigoMedico, String especialidad) {
		super(codigoUsuario, cedula, login, password, nombre, telefono, direccion);
		this.codigoMedico = codigoMedico;
		this.especialidad = especialidad;
		misPacientes = new ArrayList<String>();
	}
	public String getCodigoMedico() {
		return codigoMedico;
	}
	public void setCodigoMedico(String codigoMedico) {
		this.codigoMedico = codigoMedico;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public ArrayList<String> getMisPacientes() {
		return misPacientes;
	}
	public void setMisPacientes(ArrayList<String> misPacientes) {
		this.misPacientes = misPacientes;
	}

}
