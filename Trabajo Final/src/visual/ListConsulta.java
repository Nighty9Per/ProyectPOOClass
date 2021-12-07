package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.CitaMedica;
import logical.Clinica;
import logical.Consulta;
import logical.Paciente;
import logical.U_Administrador;
import logical.U_Medico;
import logical.Usuario;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.ListSelectionModel;

public class ListConsulta extends JPanel {

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
	private JButton btnVerPaciente;
	private JButton btnVerConsulta;
	/**
	 * Create the panel.
	 */
	public ListConsulta(ArrayList<Consulta> misConsultas, Usuario medico) {

		setLayout(null);
		setBounds(0, 67, 1124, 584);
		
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
		
		JLabel lblTitulo = new JLabel("Consultas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 119, 14);
		panelBotones.add(lblTitulo);
		
		btnVerPaciente = new JButton("Datos Paciente");
		btnVerPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableButtons(false);
			}
		});
		btnVerPaciente.setBounds(10, 70, 119, 23);
		panelBotones.add(btnVerPaciente);
		
		btnVerConsulta = new JButton("Ver Consulta");
		btnVerConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					Paciente paciente = getPacienteTable();
					Consulta consulta = getConsultaTable();
					if(consulta != null) {
						RegConsulta verConsulta = new RegConsulta(paciente, consulta.getMedicoCodigo(), consulta, null);
						verConsulta.setVisible(true);
					}
				}
				enableButtons(false);
			}
		});
		btnVerConsulta.setBounds(10, 36, 119, 23);
		panelBotones.add(btnVerConsulta);
		
		panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.setBounds(0, 89, 975, 495);
		panelMain.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableButtons(true);
			}
		});
		String[] headers = {"Codigo Consulta", "Cedula Paciente", "Nombre del Paciente", "Fecha de Consulta", "Diagnostico"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
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
				loadUsuarios();
			}
		});
		btnBuscar.setBounds(330, 34, 89, 23);
		panelFiltro.add(btnBuscar);
		
		cbxBusqueda = new JComboBox();
		cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Cedula", "Nombre"}));
		cbxBusqueda.setBounds(170, 34, 150, 23);
		panelFiltro.add(cbxBusqueda);
		
		JLabel lblFiltroDeBusqueda = new JLabel("Filtro de Busqueda:");
		lblFiltroDeBusqueda.setBounds(170, 15, 128, 14);
		panelFiltro.add(lblFiltroDeBusqueda);
		
	}
	private void resetFiltros() {
		txtBuscar.setText("");
		cbxBusqueda.setSelectedIndex(0);
		loadUsuarios();
	}
	
	// Load Usuarios a la Tabla
	public void loadUsuarios() {
		enableButtons(false);
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null && user instanceof U_Medico) {
			ArrayList<Paciente> misPacientes = Clinica.getInstace().getPacientesPorMedico(user);
			model.setRowCount(0);
			rows = new Object[model.getColumnCount()];
			for (Paciente paciente : misPacientes) {
				for (Consulta consulta : paciente.getMisConsulta()) {
					if(!filterConsultas(consulta, user.getCodigoUsuario())) {
						continue;
					}
					rows[0] = consulta.getCodigoConsulta();
					rows[1] = paciente.getCedula();
					rows[2] = paciente.getNombre();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String fecha = dateFormat.format(consulta.getFechaConsulta()).toString();
					rows[3] = fecha;
					rows[4] = consulta.getDiagnostico();
					model.addRow(rows);
				}
			}
		}
	}
	
	// Enable buttons
	private void enableButtons(boolean enable) {
		if(enable) {
			btnVerConsulta.setEnabled(true);
			btnVerPaciente.setEnabled(true);
		}
		else {
			btnVerConsulta.setEnabled(false);
			btnVerPaciente.setEnabled(false);	
		}
	}
	
	public boolean filterConsultas(Consulta consulta, String codigoMedico) {
		boolean pass = true;
		if(!consulta.getMedicoCodigo().equalsIgnoreCase(codigoMedico)) {
			pass = false;
		}
		return pass;
	}
	
	private Consulta getConsultaTable() {
		Consulta consulta = null;
		if(table.getSelectedRow() != -1) {
			consulta = Clinica.getInstace().buscarConsultaCodigo(table.getValueAt(table.getSelectedRow(), 0).toString());
		}
		return consulta;
	}
	
	private Paciente getPacienteTable() {
		Paciente paciente = null;
		if(table.getSelectedRow() != -1) {
			paciente = Clinica.getInstace().buscarPacientePorCodigoConsulta(table.getValueAt(table.getSelectedRow(), 0).toString());
		}
		return paciente;
	}
}
