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

import logical.Consulta;
import logical.Usuario;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

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
	private ArrayList<Consulta> filterConsultas;
	private ArrayList<Consulta> misConsultas;
	private Usuario medico;
	/**
	 * Create the panel.
	 */
	public ListConsulta(ArrayList<Consulta> misConsultas, Usuario medico) {

		this.setMisConsultas(misConsultas);
		this.setMedico(medico);
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
		
		panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.setBounds(0, 89, 975, 495);
		panelMain.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
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
	private void resetFiltros() {
		txtBuscar.setText("");
		cbxBusqueda.setSelectedIndex(0);
	}
	
	
	
	
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	public Usuario getMedico() {
		return medico;
	}
	public void setMedico(Usuario medico) {
		this.medico = medico;
	}

}
