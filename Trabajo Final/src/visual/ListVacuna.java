package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.Clinica;
import logical.U_Administrador;
import logical.U_Medico;
import logical.Usuario;
import logical.Vacuna;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListVacuna extends JPanel {

	private JPanel panelMain;
	private JPanel panelFiltro;
	private JPanel panelBotones;
	private JPanel panelTable;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JButton btnResetFiltro;
	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnCrearVacuna;
	private JButton btnEliminar;
	private static DefaultTableModel model;
	private static Object[] rows;
	private ArrayList<Vacuna> filtroVacunas;
	
	/**
	 * Create the panel.
	 */
	public ListVacuna() {

		setLayout(null);
		setBounds(0, 67, 1124, 584);
		
		filtroVacunas = new ArrayList<Vacuna>();
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
				Usuario user = Clinica.getInstace().getLoginUser();
				if(user instanceof U_Medico) {
					PrincipalClinica.getInstace().viewListCitaMedicaPanel();
				}
				else if(user instanceof U_Administrador) {
					PrincipalClinica.getInstace().viewListUsuarioPanel();
				}
			}
		});
		btnNewButton.setBounds(10, 539, 119, 23);
		panelBotones.add(btnNewButton);
		
		lblTitulo = new JLabel("Vacunas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 119, 14);
		panelBotones.add(lblTitulo);
		
		btnCrearVacuna = new JButton("Crear Vacuna");
		btnCrearVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna regVacuna = new RegVacuna();
				regVacuna.setVisible(true);
				loadVacuna();
			}
		});
		btnCrearVacuna.setEnabled(false);
		btnCrearVacuna.setBounds(10, 36, 119, 23);
		panelBotones.add(btnCrearVacuna);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					Vacuna vacuna = getVacunaTable();
					if(vacuna != null) {
						Clinica.getInstace().eliminarVacuna(vacuna.getCodigoVacuna());
						JOptionPane.showMessageDialog(null, "Vacuna fue eliminda con Exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
					}
					loadVacuna();
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(10, 70, 119, 23);
		panelBotones.add(btnEliminar);
		
		panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.setBounds(0, 89, 975, 495);
		panelMain.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableButtons(true);
			}
		});
		String[] headers = {"Codigo Vacuna", "Nombre Vacuna"};
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
		
		lblNombre = new JLabel("Busqueda Por C\u00F3digo:");
		lblNombre.setBounds(10, 15, 150, 14);
		panelFiltro.add(lblNombre);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadVacuna();
			}
		});
		btnBuscar.setBounds(170, 34, 89, 23);
		panelFiltro.add(btnBuscar);
		
		if(Clinica.getInstace().getLoginUser() instanceof U_Administrador) {
			btnCrearVacuna.setEnabled(true);
		}
		
		loadVacuna();
	}
	private void resetFiltros() {
		txtBuscar.setText("");
		loadVacuna();
	}
	
	// Enable buttons
	private void enableButtons(boolean enable) {
		if(enable) {
			if(Clinica.getInstace().getLoginUser() instanceof U_Administrador) {
				btnEliminar.setEnabled(true);
			}
		}
		else {
			btnEliminar.setEnabled(false);
		}
	}
	
	// Load Usuarios a la Tabla
	public void loadVacuna() {
		enableButtons(false);
		if(Clinica.getInstace().getLoginUser() instanceof U_Administrador) {
			btnCrearVacuna.setEnabled(true);
		}
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null) {
			filterVacuna();
			model.setRowCount(0);
			rows = new Object[model.getColumnCount()];
			for (Vacuna vacuna : filtroVacunas) {
				rows[0] = vacuna.getCodigoVacuna();
				rows[1] = vacuna.getNombreVacuna();
				model.addRow(rows);
			}
		}
	}
	
	// Filtro de Usuarios
	private void filterVacuna() {
		filtroVacunas.removeAll(filtroVacunas);
		for (Vacuna vacuna : Clinica.getInstace().getMisVacunas()) {
			if(!filterTextBox(vacuna.getCodigoVacuna())) {
				continue;
			}
			filtroVacunas.add(vacuna);
		}
	}
	
	// Filtro TextBox
	private boolean filterTextBox(String text) {
		boolean pass = false;
		if(txtBuscar.getText().equalsIgnoreCase("")) {
			pass = true;
		} else {
			if(txtBuscar.getText().equalsIgnoreCase(text)) {
				pass = true;
			}
		}
		return pass;
	}
	
	// Obtener la Vacuna selecionado de la tabla
	private Vacuna getVacunaTable() {
		Vacuna selectedVacuna = null;
		if(table.getSelectedRow() != -1) {
			selectedVacuna = Clinica.getInstace().buscarVacunaCodigo(table.getValueAt(table.getSelectedRow(), 0).toString());
		}
		return selectedVacuna;
	}
}
