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

import logical.Clinica;
import logical.Enfermedad;
import logical.U_Administrador;
import logical.U_Medico;
import logical.Usuario;
import logical.Vacuna;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListEnfermedad extends JPanel {

	private JPanel panelMain;
	private JPanel panelFiltro;
	private JPanel panelBotones;
	private JPanel panelTable;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JButton btnResetFiltro;
	private JLabel lblNombre;
	private JScrollPane scrollPane;
	private JTable table;
	private ArrayList<Enfermedad> arrayListEnfermedad;
	private static DefaultTableModel model;
	private static Object[] rows;
	
	/**
	 * Create the panel.
	 */
	public ListEnfermedad() {

		setLayout(null);
		setBounds(0, 67, 1124, 584);
		
		arrayListEnfermedad = new ArrayList<Enfermedad>();
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
		
		JLabel lblTitulo = new JLabel("Enfermedades");
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
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		String[] headers = {"Codigo Enfermedad", "Nombre" , "Tipo", "Descipción"};
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
				loadEnfermedades();
			}
		});
		btnBuscar.setBounds(170, 34, 89, 23);
		panelFiltro.add(btnBuscar);
		
		loadEnfermedades();
	}
	
	private void resetFiltros() {
		txtBuscar.setText("");
		loadEnfermedades();
	}
	
	// Load Enfermedades a la Tabla
	public void loadEnfermedades() {
		enableButtons(false);
		filterVacuna();
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null) {
			filterVacuna();
			model.setRowCount(0);
			rows = new Object[model.getColumnCount()];
			for (Enfermedad enfermedad : arrayListEnfermedad) {
				rows[0] = enfermedad.getCodigoEnfermedad();
				rows[1] = enfermedad.getNombreEnfermedad();
				rows[2] = enfermedad.getTipoEnfermedad();
				rows[3] = enfermedad.getDescripcionEnfermedad();
				model.addRow(rows);
			}
		}
	}
	
	// Filtro de Usuarios
	private void filterVacuna() {
		arrayListEnfermedad.removeAll(arrayListEnfermedad);
		for (Enfermedad enfermedad : Clinica.getInstace().getMisEnfermedas()) {
			/*
			if(!filterTextBox(enfermedad.getCodigoEnfermedad())) {
				continue;
			}
			*/
			arrayListEnfermedad.add(enfermedad);
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

	// Enable buttons
	private void enableButtons(boolean enable) {
		if(enable) {
			
		}
		else {
			
		}
	}
	
	// Obtener la Vacuna selecionado de la tabla
	private Enfermedad getEnfermedadTable() {
		Enfermedad selectedEnfermedad = null;
		if(table.getSelectedRow() != -1) {
			selectedEnfermedad = Clinica.getInstace().buscarEnfermedadCodigo(table.getValueAt(table.getSelectedRow(), 0).toString());
		}
		return selectedEnfermedad;
	}
}
