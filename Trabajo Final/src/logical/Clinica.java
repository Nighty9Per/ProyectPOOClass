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
	
	private static Clinica clinica = null;
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
		if(getClinica() == null) {
			setClinica(new Clinica());
		}
		return getClinica();
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
	public HistorialClinica crearHistorial() {
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
	public CitaMedica crearCitaMedica(String cedulaPaciente, Date fechaCita, String nombrePaciente, String telefonoPaciente, Usuario medico) {
		CitaMedica cita = new CitaMedica("CM-"+generateCodigoCita, cedulaPaciente, fechaCita, nombrePaciente, telefonoPaciente, (U_Medico) medico);
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
	
	//Crear consulta agregandola al paciente.
	public Consulta crearConsulta (String sintomas, String diagnostico, String procedimiento, String tratamiento, String comentarioExtra, Enfermedad enfermedadBajoVigilancia, String medico, Paciente paciente, boolean historial) {
		Consulta consulta = new Consulta("C-"+generateCodigoConsulta, sintomas, diagnostico, procedimiento, tratamiento, comentarioExtra, enfermedadBajoVigilancia, medico);
		generateCodigoConsulta++;
		agregarConsultaPacienteCedula(paciente.getCedula(), consulta, historial);
		agregarPacienteAMedico(medico, paciente.getCedula());
		
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
	
	// Buscar Enfermedad Bajo Vigilancia usando codigo.
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
	
	// Buscar CitaMedica usando codigo.
	public CitaMedica buscaCitaMedicaCodigo(String codigo) {
		CitaMedica citaMedica = null;
		boolean encontrado = false;
		int i = 0, cantCitaMedica = misCitas.size();
		while (!encontrado && i < cantCitaMedica) {
			if (misCitas.get(i).getCodigoCita().equalsIgnoreCase(codigo)) {
				citaMedica = misCitas.get(i);
				encontrado = true;
			}
			i++;
		}
		return citaMedica;
	}
	
	// Buscar Consulta usando codigo.
	public Consulta buscarConsultaCodigo(String codigoConsulta) {
		Consulta consulta = null;
		boolean encontrado = false;
		int indexPaciente = 0, indexConsulta = 0, cantconsulta = 0, cantPacientes = misPacientes.size();
		Paciente paciente = null;
		while (!encontrado && indexPaciente < cantPacientes) {
			paciente = misPacientes.get(indexPaciente);
			cantconsulta = paciente.getMisConsulta().size();
			while (!encontrado && indexConsulta < cantconsulta) {
				if (misPacientes.get(indexPaciente).getMisConsulta().get(indexConsulta).getCodigoConsulta().equalsIgnoreCase(codigoConsulta)) {
					consulta = misPacientes.get(indexPaciente).getMisConsulta().get(indexConsulta);
					encontrado = true;
				}
				indexConsulta++;
			}
			indexPaciente++;
		}
		return consulta;
	}
	
	// Buscar Paciente usando una consulta del Paciente.
	public Paciente buscarPacientePorCodigoConsulta(String codigoConsulta) {
		for (Paciente paciente : misPacientes) {
			for (Consulta consulta : paciente.getMisConsulta()) {
				if (consulta.getCodigoConsulta().equalsIgnoreCase(codigoConsulta)) {
					return paciente;
				}
			}
		}
		return null;
	}
	
	// Buscar Enfermedad usando codigo.
	public Enfermedad buscaEnfermedadCodigo(String codigoEnfermedad) {
		Enfermedad enfermedad = null;
		boolean encontrado = false;
		int i = 0, cantEnfermedad = misEnfermedas.size();
		while (!encontrado && i < cantEnfermedad) {
			if (misEnfermedas.get(i).getCodigoEnfermedad().equalsIgnoreCase(codigoEnfermedad)) {
				enfermedad = misEnfermedas.get(i);
				encontrado = true;
			}
			i++;
		}
		return enfermedad;
	}
	
	// Agregar una consulta a un paciente usando la cedula.
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
	
	// Agregar un paciente a lista de pacientes de un medico
	public void agregarPacienteAMedico(String codigoUsuarioMedico, String paciente) {
		Usuario medico = buscarUsuarioCodigo(codigoUsuarioMedico);
		if(medico != null && medico instanceof U_Medico) {
			int cantPaciente = ((U_Medico)medico).getMisPacientes().size();
			int i = 0;
			boolean encontrado = false;
			while(i < cantPaciente && !encontrado) {
				if(((U_Medico)medico).getMisPacientes().get(i).equalsIgnoreCase(paciente)) {
					encontrado = true;
				}
				i++;
			}
			if(!encontrado) {
				((U_Medico)medico).getMisPacientes().add(paciente);
			}
		}
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
	
	// Eliminar Cita Medica
	public boolean eliminarCitaMedicaCodigo(String codigoCita) {
		boolean eliminado = false;
		int i = 0, cantCitaMedica = misCitas.size();
		while (!eliminado && i < cantCitaMedica) {
			if (misCitas.get(i).getCodigoCita().equalsIgnoreCase(codigoCita)) {
				misCitas.remove(i);
				eliminado = true;
			}
			i++;
		}
		return eliminado;
	}
	
	// Eliminar Usuario
	public boolean eliminarUsuario(String codigoUsuario) {
		boolean eliminado = false;
		int i = 0, cantUsuario = misUsuarios.size();
		while (!eliminado && i < cantUsuario) {
			if (misUsuarios.get(i).getCodigoUsuario().equalsIgnoreCase(codigoUsuario)) {
				misUsuarios.remove(i);
				eliminado = true;
			}
			i++;
		}
		return eliminado;
	}
	
	// Eliminar Vacuna
		public boolean eliminarVacuna(String codigoVacuna) {
			boolean eliminado = false;
			int i = 0, cantVacunas = misVacunas.size();
			while (!eliminado && i < cantVacunas) {
				if (misVacunas.get(i).getCodigoVacuna().equalsIgnoreCase(codigoVacuna)) {
					misVacunas.remove(i);
					eliminado = true;
				}
				i++;
			}
			return eliminado;
		}
	
	// Editar un Usuario
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
	
	// Editar una Enfermedad
	public void editarEnfermedad(String codigo, Enfermedad sick) {
		Enfermedad aux = buscarEnfermedadCodigo(codigo);
		aux.setDescripcionEnfermedad(sick.getDescripcionEnfermedad());
		aux.setNombreEnfermedad(sick.getNombreEnfermedad());
		aux.setTipoEnfermedad(sick.getTipoEnfermedad());
	}
	
	// Editar un Paciente
	public void editarPaciente (String codigo, Paciente pat) {
		Paciente aux = buscarPacienteCedula(codigo);
		aux.setDireccion(pat.getDireccion());
		aux.setNombre(pat.getNombre());
		aux.setTelefono(pat.getTelefono());
	}
	
	// Get Pacientes del Medico
	public ArrayList<Paciente> getPacientesPorMedico(Usuario medico){
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		if(medico instanceof U_Medico) {
			Paciente paciente = null;
			for (String pacienteCedula : ((U_Medico)medico).getMisPacientes()) {
				paciente = buscarPacienteCedula(pacienteCedula);
				if(paciente != null) {
					pacientes.add(paciente);
				}
			}
		}
		return pacientes;
	}

	public Usuario getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Usuario user) {
		loginUser = user;
	}

	public static Clinica getClinica() {
		return clinica;
	}

	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
	}

	public boolean eliminarEnfermedad(String codigoEnfermedad) {
		boolean eliminado = false;
		int i = 0, cantEnfermedad = misEnfermedas.size();
		while (!eliminado && i < cantEnfermedad) {
			if (misEnfermedas.get(i).getCodigoEnfermedad().equalsIgnoreCase(codigoEnfermedad)) {
				misEnfermedas.remove(i);
				eliminado = true;
			}
			i++;
		}
		return eliminado;
	}
	
}
