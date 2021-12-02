package logical;

import java.io.Serializable;
import java.util.Date;

public class CitaMedica implements Serializable{
	
	private static final long serialVersionUID = -8563401896355971408L;
	/**
	 * 
	 */
	private String codigoCita;
	private Date fechaCita;
	private String cedulaPaciente;
	private String nombrePaciente;
	private String telefonoPaciente;
	private U_Medico medico;
	private Boolean cancelada;
	
	public CitaMedica(String codigoCita, String cedulaPaciente, Date fechaCita, String nombrePaciente, String telefonoPaciente,
			U_Medico medico) {
		super();
		this.codigoCita = codigoCita;
		this.cedulaPaciente = cedulaPaciente;
		this.fechaCita = fechaCita;
		this.nombrePaciente = nombrePaciente;
		this.telefonoPaciente = telefonoPaciente;
		this.medico = medico;
		this.cancelada = false;
	}
	public String getCodigoCita() {
		return codigoCita;
	}
	public void setCodigoCita(String codigoCita) {
		this.codigoCita = codigoCita;
	}
	public Date getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getTelefonoPaciente() {
		return telefonoPaciente;
	}
	public void setTelefonoPaciente(String telefonoPaciente) {
		this.telefonoPaciente = telefonoPaciente;
	}
	public U_Medico getMedico() {
		return medico;
	}
	public void setMedico(U_Medico medico) {
		this.medico = medico;
	}
	public Boolean getCancelada() {
		return cancelada;
	}
	
	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}
	public String getCedulaPaciente() {
		return cedulaPaciente;
	}
	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

}
