package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.CitaMedica;
import logical.Clinica;
import logical.Consulta;
import logical.Enfermedad;
import logical.HistorialClinica;
import logical.Paciente;
import logical.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextPane txtpSintomas;
	private JButton btnCancelar;
	private JButton btnRegistro;
	private JRadioButton rdbtnAfirmar;
	private JRadioButton rdbtnNegar;
	private JTextPane txtpProced;
	private JTextPane txtpComent;
	private JTextPane txtpDiag;
	private JTextPane txtpTratamiento;
	private JList listEnfermedad;
	private ArrayList<String> Sickness;
	private DefaultListModel<String> listModelEnfermedad;
	private String selected = "";
	private JLabel lblNewLabel;
	private JLabel lblTlefono;
	private JCheckBox chckbxPasarHistorial;
	private static HistorialClinica hist = null;
	private static Consulta consultaCreada = null;
	private static String med = "";
	private static Paciente pat = null;
	private static CitaMedica deleteCite = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta(null,null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta(Paciente patient, String medico, Consulta consulta, CitaMedica eliminarCita) {
		consultaCreada = consulta;
		setMed(medico);
		setPat(patient);
		setDeleteCite(eliminarCita);
		hist = patient.getHistorial();
		verConsulta();
		
		setTitle("Registro de Consulta");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 793, 705);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("S\u00EDntomas:");
			lblNewLabel_1.setBounds(10, 167, 79, 14);
			panel.add(lblNewLabel_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 192, 220, 138);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			txtpSintomas = new JTextPane();
			scrollPane.setViewportView(txtpSintomas);
			
			JLabel lblNewLabel_2 = new JLabel("Diagn\u00F3stico:");
			lblNewLabel_2.setBounds(254, 167, 90, 14);
			panel.add(lblNewLabel_2);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(502, 192, 220, 138);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, BorderLayout.CENTER);
			
			txtpProced = new JTextPane();
			scrollPane_1.setViewportView(txtpProced);
			
			JLabel lblProcedimiento = new JLabel("Procedimiento:");
			lblProcedimiento.setBounds(502, 167, 90, 14);
			panel.add(lblProcedimiento);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(254, 412, 220, 138);
			panel.add(panel_3);
			panel_3.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_2 = new JScrollPane();
			panel_3.add(scrollPane_2, BorderLayout.CENTER);
			
			txtpComent = new JTextPane();
			scrollPane_2.setViewportView(txtpComent);
			
			JLabel lblTratamiento = new JLabel("Comentarios:");
			lblTratamiento.setBounds(254, 387, 90, 14);
			panel.add(lblTratamiento);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(254, 192, 220, 138);
			panel.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_3 = new JScrollPane();
			panel_4.add(scrollPane_3, BorderLayout.CENTER);
			
			txtpDiag = new JTextPane();
			scrollPane_3.setViewportView(txtpDiag);
			
			JLabel label = new JLabel("Tratamiento:");
			label.setBounds(10, 387, 220, 14);
			panel.add(label);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBounds(10, 412, 220, 138);
			panel.add(panel_5);
			panel_5.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_4 = new JScrollPane();
			panel_5.add(scrollPane_4, BorderLayout.CENTER);
			
			txtpTratamiento = new JTextPane();
			scrollPane_4.setViewportView(txtpTratamiento);
			
			JLabel lblNewLabel_3 = new JLabel("\u00BFPosee el paciente un enfermedad en la lista de vigilancia?");
			lblNewLabel_3.setBounds(479, 349, 432, 14);
			panel.add(lblNewLabel_3);
			
			rdbtnAfirmar = new JRadioButton("Si");
			rdbtnAfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnNegar.setSelected(false);
					listEnfermedad.setEnabled(true);
					chckbxPasarHistorial.setEnabled(false);
				}
			});
			rdbtnAfirmar.setBounds(549, 370, 46, 23);
			panel.add(rdbtnAfirmar);
			
			rdbtnNegar = new JRadioButton("No");
			rdbtnNegar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnAfirmar.setSelected(false);
					listEnfermedad.setEnabled(false);
					listEnfermedad.setSelectedIndex(0);
					chckbxPasarHistorial.setSelected(false);
					chckbxPasarHistorial.setEnabled(true);
				}
			});
			rdbtnNegar.setSelected(true);
			rdbtnNegar.setBounds(502, 370, 46, 23);
			panel.add(rdbtnNegar);
			
			JPanel panel_6 = new JPanel();
			panel_6.setBounds(502, 407, 220, 143);
			panel.add(panel_6);
			panel_6.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_5 = new JScrollPane();
			panel_6.add(scrollPane_5, BorderLayout.CENTER);
			
			listEnfermedad = new JList();
			listEnfermedad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = listEnfermedad.getSelectedIndex();
					if(index != -1) {
						selected = (String) listEnfermedad.getModel().getElementAt(index);
					}
				}
			});
			listModelEnfermedad = new DefaultListModel<String>();
			Sickness = loadlist();
			listEnfermedad.setModel(listModelEnfermedad);
			listEnfermedad.setEnabled(false);
			listEnfermedad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_5.setViewportView(listEnfermedad);
			
			JPanel panel_7 = new JPanel();
			panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paciente: " +patient.getNombre(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_7.setBounds(10, 11, 745, 123);
			panel.add(panel_7);
			panel_7.setLayout(null);
			
			lblNewLabel = new JLabel("C\u00E9dula: "+patient.getCedula());
			lblNewLabel.setBounds(10, 31, 160, 14);
			panel_7.add(lblNewLabel);
			
			lblTlefono = new JLabel("T\u00E9lefono: "+patient.getTelefono());
			lblTlefono.setBounds(167, 31, 138, 14);
			panel_7.add(lblTlefono);
			
			
			JLabel lblNewLabel_4 = new JLabel("Sexo: "+patient.getGenero());
			lblNewLabel_4.setBounds(348, 31, 112, 14);
			panel_7.add(lblNewLabel_4);
			
			Date dateAux = (Date) patient.getFechaNacimiento();
			SimpleDateFormat de = new SimpleDateFormat("dd/MM/yyyy");
			String year = de.format(dateAux);
			JLabel lblNewLabel_5 = new JLabel("Fecha de Nacimiento: " +year);
			lblNewLabel_5.setBounds(492, 31, 207, 14);
			panel_7.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Direccion: " +patient.getDireccion());
			lblNewLabel_6.setBounds(10, 74, 160, 14);
			panel_7.add(lblNewLabel_6);
			
			chckbxPasarHistorial = new JCheckBox("Pasar Historial");
			chckbxPasarHistorial.setBounds(10, 588, 121, 23);
			panel.add(chckbxPasarHistorial);
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistro = new JButton("Registrar");
				btnRegistro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(btnRegistro.getText().equalsIgnoreCase("Ok")) {
							dispose();
						}
						verConsulta();
						if (consultaCreada == null) {
							if (validacion() == true) {
								boolean aHistorial = false;
								Enfermedad sick = null;
								if (rdbtnAfirmar.isSelected()) {
									String codigo = selected.substring(0, selected.lastIndexOf(":"));
									sick = Clinica.getInstace().buscarEnfermedadCodigo(codigo);
								}
								if (sick != null || chckbxPasarHistorial.isSelected()) {
									aHistorial = true;
								}
								consultaCreada =  Clinica.getInstace().crearConsulta(txtpSintomas.getText(), txtpDiag.getText(), txtpProced.getText(), txtpTratamiento.getText(), txtpComent.getText(), sick, med, pat.getCedula(), aHistorial);
								Clinica.getInstace().eliminarCitaMedicaCodigo(deleteCite.getCodigoCita());
								JOptionPane.showMessageDialog(null, "Consulta Creada", "Exito", JOptionPane.INFORMATION_MESSAGE);
								verConsulta();
							}else if (validacion() == false){
								JOptionPane.showMessageDialog(null, "No debe dejar campos vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
							}
						}
						
					}
				});
				btnRegistro.setActionCommand("OK");
				buttonPane.add(btnRegistro);
				getRootPane().setDefaultButton(btnRegistro);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private boolean validacion() {
		boolean validar = false;
		if (!txtpSintomas.getText().equals("") && !txtpDiag.getText().equals("") && !txtpProced.getText().equals("") && !txtpTratamiento.getText().equals("")) {
			validar = true;
		}
		return validar;
	}

	private ArrayList<String> loadlist() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < Clinica.getInstace().getMisEnfermedas().size(); i++) {
			String aux = new String(Clinica.getInstace().getMisEnfermedas().get(i).getCodigoEnfermedad()+": "+Clinica.getInstace().getMisEnfermedas().get(i).getNombreEnfermedad());
			listModelEnfermedad.addElement(aux);
			list.add(aux);
		}
		return list;
	}
	
	private void verConsulta() {
		if(consultaCreada != null) {
			txtpSintomas.setText(consultaCreada.getSintomas());
			txtpDiag.setText(consultaCreada.getDiagnostico());
			txtpProced.setText(consultaCreada.getProcedimiento());
			txtpComent.setText(consultaCreada.getComentarioExtra());
			txtpTratamiento.setText(consultaCreada.getTratamiento());
			txtpComent.setEditable(false);
			txtpDiag.setEditable(false);
			txtpProced.setEditable(false);
			txtpSintomas.setEditable(false);
			txtpTratamiento.setEditable(false);
			btnRegistro.setText("Ok");
		}
	}

	public static String getMed() {
		return med;
	}

	public static void setMed(String med) {
		RegConsulta.med = med;
	}

	public static Paciente getPat() {
		return pat;
	}

	public static void setPat(Paciente pat) {
		RegConsulta.pat = pat;
	}

	public static CitaMedica getDeleteCite() {
		return deleteCite;
	}

	public static void setDeleteCite(CitaMedica deleteCite) {
		RegConsulta.deleteCite = deleteCite;
	}
}
