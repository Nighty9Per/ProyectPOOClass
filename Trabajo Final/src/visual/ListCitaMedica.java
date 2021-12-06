package visual;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.CitaMedica;
import logical.Clinica;
import logical.Paciente;
import logical.U_Medico;
import logical.Usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class ListCitaMedica extends JPanel {

	private JPanel panelMain;
	private JPanel panelFiltro;
	private JPanel panelBotones;
	private JPanel panelTable;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JButton btnResetFiltro;
	private JLabel lblNombre;
	private JComboBox cbxBusqueda;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private ArrayList<CitaMedica> arrayListCita;
	private JButton btnConsulta;
	private JButton btnCancelarCita;
	/**
	 * Create the panel.
	 */
	public ListCitaMedica() {

		setLayout(null);
		setBounds(0, 67, 1124, 584);
		arrayListCita = new ArrayList<CitaMedica>();
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1124, 584);
		add(panelMain);
		panelMain.setLayout(null);
		
		panelBotones = new JPanel();
		panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBotones.setBounds(985, 11, 139, 573);
		panelMain.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton btnNewButton = new JButton("Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalClinica.getInstace().viewCleanPanelUser();
			}
		});
		btnNewButton.setBounds(10, 539, 119, 23);
		panelBotones.add(btnNewButton);
		
		JLabel lblTitulo = new JLabel("Citas Medicas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 119, 14);
		panelBotones.add(lblTitulo);
		
		btnConsulta = new JButton("Consultar");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					CitaMedica cita = getCitaTable();
					Paciente paciente = Clinica.getInstace().buscarPacienteCedula(cita.getCedulaPaciente());
					if(paciente != null) {
						RegConsulta consulta = new RegConsulta(paciente, Clinica.getInstace().getLoginUser().getCodigoUsuario(), null, cita);
						consulta.setVisible(true);
					}
					if(paciente == null) {
						JOptionPane.showMessageDialog(null, "Paciente debera ser registrado antes de pasar a Consulta.", "Paciente no Encontrado", JOptionPane.INFORMATION_MESSAGE);
						RegPaciente regPaciente = new RegPaciente(null, cita);
						regPaciente.setVisible(true);
						paciente = Clinica.getInstace().buscarPacienteCedula(cita.getCedulaPaciente());
					}
					loadCitas();
					btnEnable(false);
				}
			}
		});
		btnConsulta.setEnabled(false);
		btnConsulta.setBounds(10, 36, 119, 23);
		panelBotones.add(btnConsulta);
		
		btnCancelarCita = new JButton("Cancelar Cita");
		btnCancelarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					CitaMedica cita = getCitaTable();
					Clinica.getInstace().eliminarCitaMedicaCodigo(cita.getCodigoCita());
				}
			}
		});
		btnCancelarCita.setEnabled(false);
		btnCancelarCita.setBounds(10, 70, 119, 23);
		panelBotones.add(btnCancelarCita);
		
		panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.setBounds(0, 89, 975, 495);
		panelMain.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEnable(true);
			}
		});
		String[] headers = {"Codigo", "Cedula Paciente", "Nombre del Paciente", "Fecha de Cita", "Telefono"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		panelFiltro = new JPanel();
		panelFiltro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFiltro.setBounds(0, 11, 975, 67);
		panelMain.add(panelFiltro);
		panelFiltro.setLayout(null);
		
		btnResetFiltro = new JButton("Reset");
		btnResetFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFiltros();
			}
		});
		btnResetFiltro.setBounds(876, 34, 89, 23);
		panelFiltro.add(btnResetFiltro);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 34, 150, 23);
		panelFiltro.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		lblNombre = new JLabel("Busqueda:");
		lblNombre.setBounds(10, 15, 95, 14);
		panelFiltro.add(lblNombre);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadCitas();
			}
		});
		btnBuscar.setBounds(330, 34, 89, 23);
		panelFiltro.add(btnBuscar);
		
		cbxBusqueda = new JComboBox();
		cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Cedula del Paciente", "Nombre del Paciente"}));
		cbxBusqueda.setBounds(170, 34, 150, 23);
		panelFiltro.add(cbxBusqueda);
		
		JLabel lblFiltroDeBusqueda = new JLabel("Filtro de Busqueda:");
		lblFiltroDeBusqueda.setBounds(170, 15, 128, 14);
		panelFiltro.add(lblFiltroDeBusqueda);
		
	}
	
	// Reset Filtros
	private void resetFiltros() {
		txtBuscar.setText("");
		cbxBusqueda.setSelectedIndex(0);
		loadCitas();
	}
	
	// Load Citas a Tabla
	public void loadCitas() {
		btnEnable(false);
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null) {
			if(user instanceof U_Medico){
				filterCitaMedica(user);
				model.setRowCount(0);
				rows = new Object[model.getColumnCount()];
				for (CitaMedica citaMedica : arrayListCita) {
					rows[0] = citaMedica.getCodigoCita();
					rows[1] = citaMedica.getCedulaPaciente();
					rows[2] = citaMedica.getNombrePaciente();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String fecha = dateFormat.format(citaMedica.getFechaCita()).toString();
					rows[3] = fecha;
					rows[4] = citaMedica.getTelefonoPaciente();
					model.addRow(rows);
				}
			}
		}
	}
	
	// Filtro de Busqueda
	private void filterCitaMedica(Usuario user) {
		if(user != null) {
			if(user instanceof U_Medico){
				arrayListCita.removeAll(arrayListCita);
				for (CitaMedica citaMedica : Clinica.getInstace().getMisCitas()) {
					if(!citaMedica.getMedico().getCodigoUsuario().equalsIgnoreCase(user.getCodigoUsuario())) {
						continue;
					}
					if(!txtBuscar.getText().equalsIgnoreCase("")) {
						if(cbxBusqueda.getSelectedIndex() == 0) {
							if(!filtroCedula(citaMedica.getCedulaPaciente())) {
								continue;
							}
						}
						if(cbxBusqueda.getSelectedIndex() == 1) {
							if(!filtroNombre(citaMedica.getNombrePaciente())) {
								continue;
							}
						}
					}
					arrayListCita.add(citaMedica);
				}
			}
		}
	}
	
	// Enable or Disable Buttons.
	private void btnEnable(boolean enable) {
		if(enable) {
			btnConsulta.setEnabled(true);
			btnCancelarCita.setEnabled(true);
		}
		else {
			btnConsulta.setEnabled(false);
			btnCancelarCita.setEnabled(false);
		}
	}
	
	// Obtener la cita Medica de la tabla
	private CitaMedica getCitaTable() {
		CitaMedica cita = null;
		if(table.getSelectedRow() != -1) {
			cita = Clinica.getInstace().buscaCitaMedicaCodigo(table.getValueAt(table.getSelectedRow(), 0).toString());
		}
		return cita;
	}
	
	// Filtro de Cedula
	private boolean filtroCedula(String cedulaPaciente) {
		boolean pass = false;
		if(txtBuscar.getText().equalsIgnoreCase(cedulaPaciente)) {
			pass = true;
		}
		return pass;
	}
	
	// Filtro de Nombre
	private boolean filtroNombre(String nombrePaciente) {
		boolean pass = false;
		if(txtBuscar.getText().equalsIgnoreCase(nombrePaciente)) {
			pass = true;
		}
		return pass;
	}
}
