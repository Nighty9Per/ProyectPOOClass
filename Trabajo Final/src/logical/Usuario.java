package logical;

import java.io.Serializable;

public abstract class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 761593449595315208L;
	
	protected String codigoUsuario;
	protected String cedula;
	protected String login;
	protected String password;
	protected String nombre;
	protected String telefono;
	protected String direccion;
	
	public Usuario(String codigoUsuario, String cedula, String login, String password, String nombre, String telefono,
			String direccion) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.cedula = cedula;
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
