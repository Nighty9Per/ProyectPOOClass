package logical;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6741490158387733072L;
	
	private String codigoConsulta;
	private Date fechaConsulta;
	private String sintomas;
	private String diagnostico;
	private String procedimiento;
	private String tratamiento;
	private String comentarioExtra;
	private Enfermedad enfermedadBajoVigilancia;
	private String medicoCodigo;
	
	public Consulta(String codigoConsulta, String sintomas, String diagnostico,
			String procedimiento, String tratamiento, String comentarioExtra, Enfermedad enfermedadBajoVigilancia, String medicoCodigo) {
		super();
		this.codigoConsulta = codigoConsulta;
		this.fechaConsulta = new Date();
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.procedimiento = procedimiento;
		this.tratamiento = tratamiento;
		this.comentarioExtra = comentarioExtra;
		this.enfermedadBajoVigilancia = enfermedadBajoVigilancia;
		this.medicoCodigo = medicoCodigo;
	}

	public String getCodigoConsulta() {
		return codigoConsulta;
	}

	public void setCodigoConsulta(String codigoConsulta) {
		this.codigoConsulta = codigoConsulta;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getComentarioExtra() {
		return comentarioExtra;
	}

	public void setComentarioExtra(String comentarioExtra) {
		this.comentarioExtra = comentarioExtra;
	}

	public Enfermedad getEnfermedadBajoVigilancia() {
		return enfermedadBajoVigilancia;
	}

	public void setEnfermedadBajoVigilancia(Enfermedad enfermedadBajoVigilancia) {
		this.enfermedadBajoVigilancia = enfermedadBajoVigilancia;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getMedicoCodigo() {
		return medicoCodigo;
	}

	public void setMedicoCodigo(String medicoCodigo) {
		this.medicoCodigo = medicoCodigo;
	}
}
