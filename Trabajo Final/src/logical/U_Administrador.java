package logical;

public class U_Administrador extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String puestoLaboral;
	public U_Administrador(String codigoUsuario, String cedula, String login, String password, String nombre,
			String telefono, String direccion, String puestoLaboral) {
		super(codigoUsuario, cedula, login, password, nombre, telefono, direccion);
		this.puestoLaboral = puestoLaboral;
	}
	public String getPuestoLaboral() {
		return puestoLaboral;
	}
	public void setPuestoLaboral(String puestoLaboral) {
		this.puestoLaboral = puestoLaboral;
	}

}
