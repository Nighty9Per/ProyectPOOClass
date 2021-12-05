package visual;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.CitaMedica;
import logical.Clinica;
import logical.U_Administrador;
import logical.U_Medico;
import logical.Usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListUsuario extends JPanel {

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
	private ArrayList<Usuario> arrayListUsuario;
	private JButton btnCrearUsuario;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JComboBox cbxTipoUsuario;
	/**
	 * Create the panel.
	 */
	public ListUsuario() {

		arrayListUsuario = new ArrayList<Usuario>();
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
		
		JLabel lblTitulo = new JLabel("Usuarios");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 119, 14);
		panelBotones.add(lblTitulo);
		
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario regUser = new RegUsuario(null);
				regUser.setVisible(true);
				loadUsuarios();
			}
		});
		btnCrearUsuario.setBounds(10, 36, 119, 23);
		panelBotones.add(btnCrearUsuario);
		
		btnModificar = new JButton("Modificar User");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					Usuario selectedUser = getUsuarioTable();
					if(selectedUser != null) {
						RegUsuario modUser = new RegUsuario(selectedUser);
						modUser.setVisible(true);
						loadUsuarios();
					}
					enableButtons(false);
				}
			}
		});
		btnModificar.setBounds(10, 70, 119, 23);
		panelBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar User");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					Usuario selectedUser = getUsuarioTable();
					if(selectedUser != null) {
						if(!Clinica.getInstace().getLoginUser().getCodigoUsuario().equalsIgnoreCase(selectedUser.getCodigoUsuario())) {
							Clinica.getInstace().eliminarUsuario(selectedUser.getCodigoUsuario());
						}else {
							JOptionPane.showMessageDialog(null, "No se puede Eliminar el Usuario mientras esta en la sección.", "Error al intentar Elminar", JOptionPane.WARNING_MESSAGE);
						}
						
					}
					enableButtons(false);
				}
			}
		});
		btnEliminar.setBounds(10, 104, 119, 23);
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
		String[] headers = {"Codigo Usuario", "Cedula Usurio", "Nombre del Usuario", "Trabajo", "Telefono"};
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
		btnBuscar.setBounds(490, 34, 89, 23);
		panelFiltro.add(btnBuscar);
		
		cbxBusqueda = new JComboBox();
		cbxBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadUsuarios();
			}
		});
		cbxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "C\u00E9dula", "Nombre", "Trabajo"}));
		cbxBusqueda.setBounds(170, 34, 150, 23);
		panelFiltro.add(cbxBusqueda);
		
		JLabel lblFiltroDeBusqueda = new JLabel("Filtro de Busqueda:");
		lblFiltroDeBusqueda.setBounds(170, 15, 128, 14);
		panelFiltro.add(lblFiltroDeBusqueda);
		
		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadUsuarios();
			}
		});
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"<<Todos>>", "Medico", "Administrado"}));
		cbxTipoUsuario.setBounds(330, 35, 150, 23);
		panelFiltro.add(cbxTipoUsuario);
		
		JLabel lblNewLabel = new JLabel("Medicos o Administradores");
		lblNewLabel.setBounds(330, 15, 150, 14);
		panelFiltro.add(lblNewLabel);
		
		loadUsuarios();
	}
	
	// Resetear los Filtros de la busqueda de Usuarios
	private void resetFiltros() {
		txtBuscar.setText("");
		cbxBusqueda.setSelectedIndex(0);
		cbxTipoUsuario.setSelectedIndex(0);
		loadUsuarios();
	}

	// Load Usuarios a la Tabla
	public void loadUsuarios() {
		enableButtons(false);
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null) {
			filterUsuario();
			model.setRowCount(0);
			rows = new Object[model.getColumnCount()];
			for (Usuario usuario : arrayListUsuario) {
				rows[0] = usuario.getCodigoUsuario();
				rows[1] = usuario.getCedula();
				rows[2] = usuario.getNombre();
				if(usuario instanceof U_Medico) {
					rows[3] = ((U_Medico) usuario).getEspecialidad();
				}
				else if(usuario instanceof U_Administrador) {
					rows[3] = ((U_Administrador) usuario).getPuestoLaboral();
				}
				rows[4] = usuario.getTelefono();
				model.addRow(rows);
			}
		}
	}
	
	// Filtro de Usuarios
	private void filterUsuario() {
		arrayListUsuario.removeAll(arrayListUsuario);
		for (Usuario usuario : Clinica.getInstace().getMisUsuarios()) {
			if(cbxTipoUsuario.getSelectedIndex() != 0) {
				if(!filterTipoUsuario(cbxTipoUsuario.getSelectedIndex(), usuario)) {
					continue;
				}
			}
			if (cbxBusqueda.getSelectedIndex() == 0) {
				if(!filterTextBox(usuario.getCodigoUsuario())) {
					continue;
				}
			}
			else if (cbxBusqueda.getSelectedIndex() == 1) {
				if(!filterTextBox(usuario.getCedula())) {
					continue;
				}
			}
			else if (cbxBusqueda.getSelectedIndex() == 2) {
				if(!filterTextBox(usuario.getNombre())) {
					continue;
				}
			}
			else if (cbxBusqueda.getSelectedIndex() == 3) {
				if(usuario instanceof U_Medico) {
					if(!filterTextBox(((U_Medico)usuario).getEspecialidad())) {
						continue;
					}
				}
				else if(usuario instanceof U_Administrador) {
					if(!filterTextBox(((U_Administrador)usuario).getPuestoLaboral())) {
						continue;
					}
				}
			}
			arrayListUsuario.add(usuario);
		}
	}
	
	// Filtro TextBox
	private boolean filterTextBox(String infoUsuario) {
		boolean pass = false;
		if(txtBuscar.getText().equalsIgnoreCase("")) {
			pass = true;
		} else {
			if(txtBuscar.getText().equalsIgnoreCase(infoUsuario)) {
				pass = true;
			}
		}
		return pass;
	}
	
	// Filtro Tipo Usuario
	private boolean filterTipoUsuario(int selectedIndex, Usuario user) {
		boolean pass = false;
		if(selectedIndex == 0) {
			pass = true;
		}
		if(selectedIndex == 1 && user instanceof U_Medico) {
			pass = true;
		}
		if(selectedIndex == 2 && user instanceof U_Administrador) {
			pass = true;
		}
		return pass;
	}
	
	
	// Enable buttons
	private void enableButtons(boolean enable) {
		if(enable) {
			btnModificar.setEnabled(true);
			btnEliminar.setEnabled(true);
		}
		else {
			btnModificar.setEnabled(false);
			btnEliminar.setEnabled(false);
		}
	}
	
	// obtener el usuario selecionado de la tabla
	private Usuario getUsuarioTable() {
		Usuario selectedUser = null;
		if(table.getSelectedRow() != -1) {
			selectedUser = Clinica.getInstace().buscarUsuarioCodigo(table.getValueAt(table.getSelectedRow(), 0).toString());
		}
		return selectedUser;
	}
}
