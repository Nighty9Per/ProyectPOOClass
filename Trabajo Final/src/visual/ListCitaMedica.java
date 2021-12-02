package visual;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.CitaMedica;
import logical.Clinica;
import logical.U_Medico;
import logical.Usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	/**
	 * Create the panel.
	 */
	public ListCitaMedica() {

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
		
		JLabel lblTitulo = new JLabel("Citas Medicas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 119, 14);
		panelBotones.add(lblTitulo);
		
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
			}
		});
		String[] headers = {"Codigo", "Nombre del Paciente", "Fecha de Cita", "Telefono"};
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
	protected void resetFiltros() {
		txtBuscar.setText("");
		cbxBusqueda.setSelectedIndex(0);
	}
	
	public void loadCitas() {
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null) {
			if(user instanceof U_Medico) {
				loadArrayCitas(user.getCedula());
				model.setRowCount(0);
				rows = new Object[model.getColumnCount()];
				for (CitaMedica citaMedica : arrayListCita) {
					rows[0] = citaMedica.getCodigoCita();
					rows[1] = citaMedica.getNombrePaciente();
					rows[2] = citaMedica.getFechaCita();
					rows[3] = citaMedica.getTelefonoPaciente();
				}
			}
		}
	}
	
	private void loadArrayCitas(String cedulaMedico) {
		if(Clinica.getInstace().getLoginUser() != null) {
			if(Clinica.getInstace().getLoginUser() instanceof U_Medico) {
				arrayListCita.removeAll(arrayListCita);
				for (CitaMedica citaMedica : Clinica.getInstace().getMisCitas()) {
					if(citaMedica.getMedico().getCedula().equalsIgnoreCase(cedulaMedico)) {
						arrayListCita.add(citaMedica);
					}
				}
			}
		}
	}
}
