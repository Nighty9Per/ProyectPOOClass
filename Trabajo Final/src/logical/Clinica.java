package logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Clinica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2645705140098860303L;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<CitaMedica> misCitas;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Enfermedad> misEnfermedas;
	
	private int generateCodigoCita;
	private int generateCodigoAdministrador;
	private int generateCodigoEnfermedad;
	private int generateCodigoConsulta;
	private int generateCodigoMedico;
	private int generateCodigoHistorial;
	private int generateCodigoVacuna;
	
	public static Clinica alma = null;
	private Usuario loginUser = null;
	
	private Clinica () {
		super();
		misPacientes = new ArrayList<Paciente>();
		misVacunas = new ArrayList<Vacuna>();
		misCitas = new ArrayList<CitaMedica>();
		misUsuarios = new ArrayList<Usuario>();
		misEnfermedas = new ArrayList<Enfermedad>();
		
		generateCodigoAdministrador = 1;
		generateCodigoCita = 1;
		generateCodigoConsulta = 1;
		generateCodigoEnfermedad = 1;
		generateCodigoMedico = 1;
		generateCodigoHistorial = 1;
		generateCodigoVacuna = 1;
		
	}
	
	public static Clinica getInstace() {
		if(alma == null) {
			alma = new Clinica();
		}
		return alma;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}

	public ArrayList<CitaMedica> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<CitaMedica> misCitas) {
		this.misCitas = misCitas;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public int getGenerateCodigoCita() {
		return generateCodigoCita;
	}

	public int getGenerateCodigoAdministrador() {
		return generateCodigoAdministrador;
	}

	public int getGenerateCodigoEnfermedad() {
		return generateCodigoEnfermedad;
	}

	public int getGenerateCodigoConsulta() {
		return generateCodigoConsulta;
	}

	public int getGenerateCodigoMedico() {
		return generateCodigoMedico;
	}
	
	public ArrayList<Enfermedad> getMisEnfermedas() {
		return misEnfermedas;
	}

	public void setMisEnfermedas(ArrayList<Enfermedad> misEnfermedas) {
		this.misEnfermedas = misEnfermedas;
	}

	public int getGenerateCodigoHistorial() {
		return generateCodigoHistorial;
	}

	public int getGenerateCodigoVacuna() {
		return generateCodigoVacuna;
	}

	// Crear Historial Medico.
	private HistorialClinica crearHistorial() {
		HistorialClinica historial = new HistorialClinica("H-" + generateCodigoHistorial);
		generateCodigoHistorial++;
		return historial;
	}
	
	// Crear Paciente.
	public Paciente crearPaciente(String cedula, String nombre, String genero, Date fechaNacimiento, String direccion, String telefono) {
		Paciente paciente = new Paciente(cedula, nombre, genero, fechaNacimiento, direccion, telefono);
		HistorialClinica historial = crearHistorial();
		paciente.setHistorial(historial);
		addPaciente(paciente);
		return paciente;
	}
	
	// Crear Cita Medica.
	public CitaMedica crearCitaMedica(Date fechaCita, String nombrePaciente, String telefonoPaciente, Usuario medico) {
		CitaMedica cita = new CitaMedica("CM-"+generateCodigoCita, fechaCita, nombrePaciente, telefonoPaciente, (U_Medico) medico);
		generateCodigoCita++;
		addCitaMedica(cita);
		return cita;
	}
	
	// Crear Doctor/Medico
	public U_Medico crearMedico(String cedula, String login, String password, String nombre, String telefono, String direccion, String codigoMedico, String especialidad) {
		U_Medico medico = new U_Medico("M-"+generateCodigoMedico, cedula, login, password, nombre, telefono, direccion, codigoMedico, especialidad);
		generateCodigoMedico++;
		addUsuario(medico);
		return medico;
	}
	
	// Crear Administrador
	public U_Administrador crearAdministrado(String cedula, String login, String password, String nombre, String telefono, String direccion, String puestoLaboral) {
		U_Administrador administrador = new U_Administrador("A-"+generateCodigoAdministrador, cedula, login, password, nombre, telefono, direccion, puestoLaboral);
		generateCodigoAdministrador++;
		addUsuario(administrador);
		return administrador;
	}
	
	// Crear Enfermedad Bajo Vigilancia
	public Enfermedad crearEnfermedadBajoVigilancia (String nombreEnfermedad, String tipoEnfermedad, String descripcionEnfermedad) {
		Enfermedad enfermedad = new Enfermedad("E-"+generateCodigoEnfermedad, nombreEnfermedad, tipoEnfermedad, descripcionEnfermedad);
		generateCodigoEnfermedad++;
		addEnfermedadBajoVigilancia(enfermedad);
		return enfermedad;
	}
	
	//Crear consulta sin agregarla al paciente.
	public Consulta crearConsulta (String sintomas, String diagnostico, String procedimiento, String tratamiento, String comentarioExtra, Enfermedad enfermedadBajoVigilancia) {
		Consulta consulta = new Consulta("C-"+generateCodigoConsulta, sintomas, diagnostico, procedimiento, tratamiento, comentarioExtra, enfermedadBajoVigilancia);
		generateCodigoConsulta++;
		return consulta;
	}
	
	// Crear Vacuna Bajo Vigilacia
	public void crearVacunaBajoVigilacia (String nombreVacuna) {
		Vacuna vacuna = new Vacuna("V-"+generateCodigoVacuna, nombreVacuna, null);
		generateCodigoVacuna++;
		addVacunaBajoVigilancia(vacuna);
	}
	
	// Buscar Usuario por login
	public Usuario buscarUsuarioLogin(String login) {
		Usuario returnUsuario = null;
		boolean encontrado = false;
		int i = 0, cantUsuario = misUsuarios.size();
		while(!encontrado && i < cantUsuario) {
			if(misUsuarios.get(i).getLogin().equals(login)) {
				returnUsuario = misUsuarios.get(i);
				encontrado = true;
			}
			i++;
		}
		return returnUsuario;
	}
	
	// Buscar Usuario por CodigoUsuario
		public Usuario buscarUsuarioCodigo(String codigo) {
			Usuario returnUsuario = null;
			boolean encontrado = false;
			int i = 0, cantUsuario = misUsuarios.size();
			while(!encontrado && i < cantUsuario) {
				if(misUsuarios.get(i).getCodigoUsuario().equalsIgnoreCase(codigo)) {
					returnUsuario = misUsuarios.get(i);
					encontrado = true;
				}
				i++;
			}
			return returnUsuario;
		}
	
	// Buscar Paciente usando Cedula.
	public Paciente buscarPacienteCedula(String cedula) {
		Paciente returnPaciente = null;
		boolean encontrado = false;
		int i = 0, cantPacientes = misPacientes.size();
		while(!encontrado && i < cantPacientes) {
			if(misPacientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				returnPaciente = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return returnPaciente;
	}
	
	// Buscar Vacuna Bajo Vigilancia usando codigo vacuna.
	public Vacuna buscarVacunaCodigo(String codigoVacuna) {
		Vacuna returnVacuna = null;
		boolean encontrado = false;
		int i = 0, cantVacunas = misVacunas.size();
		while(!encontrado && i < cantVacunas) {
			if(misVacunas.get(i).getCodigoVacuna().equalsIgnoreCase(codigoVacuna)) {
				returnVacuna = misVacunas.get(i);
				encontrado = true;
			}
			i++;
		}
		return returnVacuna;
	}
	
	public Enfermedad buscarEnfermedadCodigo(String codigo) {
		Enfermedad enf = null;
		boolean encontrado = false;
		int i = 0, cantEnfermedades = misEnfermedas.size();
		while (!encontrado && i < cantEnfermedades) {
			if (misEnfermedas.get(i).getCodigoEnfermedad().equalsIgnoreCase(codigo)) {
				enf = misEnfermedas.get(i);
				encontrado = true;
			}
			i++;
		}
		return enf;
	}
	
	//Agregar una consulta a un paciente usando la cedula.
	public boolean agregarConsultaPacienteCedula(String cedula, Consulta consulta, boolean consultaAHistorial) {
		boolean agregado = false;
		Paciente paciente = buscarPacienteCedula(cedula);
		if(paciente != null) {
			paciente.getMisConsulta().add(consulta);
			if(consulta.getEnfermedadBajoVigilancia() != null || consultaAHistorial) {
				paciente.getHistorial().getMisConsultas().add(consulta);
			}
			agregado = true;
		}
		return agregado;
	}
	
	// add Paciente.
	public void addPaciente(Paciente paciente) {
		misPacientes.add(paciente);
	}
	// add Cita Medica.
	public void addCitaMedica(CitaMedica cita) {
		misCitas.add(cita);
	}
	// add Usuario.
	public void addUsuario(Usuario usuario) {
		misUsuarios.add(usuario);
	}
	// add Vacuna bajo Vigilancia.
	public void addVacunaBajoVigilancia(Vacuna vacuna) {
		misVacunas.add(vacuna);
	}
	// add Enfermedad Bajo Vigilancia.
	public void addEnfermedadBajoVigilancia(Enfermedad enfermedad) {
		misEnfermedas.add(enfermedad);
	}

	// agregar una vacuna a un paciente.
	public boolean addVacunaAPaciente(String cedula, Vacuna vacuna) {
		Paciente paciente = buscarPacienteCedula(cedula);
		boolean agregado = false;
		if(paciente != null) {
			paciente.getHistorial().getMisVacunas().add(vacuna);
			agregado = true;
		}
		return agregado;
	}
	
	// Comprobar el login y el password de un Usuario y retornarlo
	public Usuario comprobarUsuario(String login, String password) {
		Usuario user = buscarUsuarioLogin(login);
		if(user != null) {
			if(!user.getPassword().equals(password)) {
				user = null;
			}
		}
		return user;
	}
	
	public void editarUsuario(String codigo, Usuario userUpdate) {
		Usuario user = buscarUsuarioCodigo(codigo);
		user.setCedula(userUpdate.getCedula());
		user.setNombre(userUpdate.getNombre());
		user.setTelefono(userUpdate.getTelefono());
		user.setDireccion(userUpdate.getDireccion());
		user.setLogin(userUpdate.getLogin());
		user.setPassword(userUpdate.getPassword());
		
		if (user instanceof U_Administrador) {
			U_Administrador aux = (U_Administrador)user;
			U_Administrador mod = (U_Administrador)userUpdate;
			
			aux.setPuestoLaboral(mod.getPuestoLaboral());
		}else if (user instanceof U_Medico) {
			U_Medico aux = (U_Medico) user;
			U_Medico mod = (U_Medico) userUpdate;
			
			aux.setCodigoMedico(mod.getCodigoMedico());
			aux.setEspecialidad(mod.getEspecialidad());
		}
		
	}
	
	public void editarEnfermedad(String codigo, Enfermedad sick) {
		Enfermedad aux = buscarEnfermedadCodigo(codigo);
		aux.setDescripcionEnfermedad(sick.getDescripcionEnfermedad());
		aux.setNombreEnfermedad(sick.getNombreEnfermedad());
		aux.setTipoEnfermedad(sick.getTipoEnfermedad());
	}
	
	public void editarPaciente (String codigo, Paciente pat) {
		Paciente aux = buscarPacienteCedula(codigo);
		aux.setDireccion(pat.getDireccion());
		aux.setNombre(pat.getNombre());
		aux.setTelefono(pat.getTelefono());
	}

	public Usuario getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Usuario user) {
		loginUser = user;
	}
	
}
