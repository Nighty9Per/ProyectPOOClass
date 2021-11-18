package logical;

import java.util.ArrayList;

public class Clinica {
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<CitaMedica> misCitas;
	private ArrayList<Usuario> misUsuarios;
	
	private int generateCodigoCita;
	private int generateCodigoAdministrador;
	private int generateCodigoEnfermedad;
	private int generateCodigoConsulta;
	private int generateCodigoMedico;
	
	public static Clinica alma = null;
	
	private Clinica() {
		super();
		misPacientes = new ArrayList<Paciente>();
		misVacunas = new ArrayList<Vacuna>();
		misCitas = new ArrayList<CitaMedica>();
		misUsuarios = new ArrayList<Usuario>();
		generateCodigoAdministrador = 1;
		generateCodigoCita = 1;
		generateCodigoConsulta = 1;
		generateCodigoEnfermedad = 1;
		generateCodigoMedico = 1;
		
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
	
	
}
