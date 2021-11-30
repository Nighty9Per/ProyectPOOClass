package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Clinica;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta() {
		setTitle("Registro de Consulta");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 793, 733);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setBounds(10, 11, 46, 14);
			panel.add(lblNewLabel);
			
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.setEditable(false);
			txtCodigo.setText("C-"+Clinica.getInstace().getGenerateCodigoConsulta());
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(57, 8, 113, 23);
			panel.add(txtCodigo);
			
			JLabel lblNewLabel_1 = new JLabel("S\u00EDntomas:");
			lblNewLabel_1.setBounds(10, 60, 79, 14);
			panel.add(lblNewLabel_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 85, 220, 138);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			txtpSintomas = new JTextPane();
			scrollPane.setViewportView(txtpSintomas);
			
			JLabel lblNewLabel_2 = new JLabel("Diagn\u00F3stico:");
			lblNewLabel_2.setBounds(10, 233, 90, 14);
			panel.add(lblNewLabel_2);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(270, 85, 220, 138);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, BorderLayout.CENTER);
			
			txtpProced = new JTextPane();
			scrollPane_1.setViewportView(txtpProced);
			
			JLabel lblProcedimiento = new JLabel("Procedimiento:");
			lblProcedimiento.setBounds(270, 60, 90, 14);
			panel.add(lblProcedimiento);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(534, 85, 220, 138);
			panel.add(panel_3);
			panel_3.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_2 = new JScrollPane();
			panel_3.add(scrollPane_2, BorderLayout.CENTER);
			
			txtpComent = new JTextPane();
			scrollPane_2.setViewportView(txtpComent);
			
			JLabel lblTratamiento = new JLabel("Comentarios:");
			lblTratamiento.setBounds(534, 60, 90, 14);
			panel.add(lblTratamiento);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(10, 258, 350, 138);
			panel.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_3 = new JScrollPane();
			panel_4.add(scrollPane_3, BorderLayout.CENTER);
			
			txtpDiag = new JTextPane();
			scrollPane_3.setViewportView(txtpDiag);
			
			JLabel label = new JLabel("Tratamiento:");
			label.setBounds(404, 234, 90, 14);
			panel.add(label);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBounds(404, 259, 350, 138);
			panel.add(panel_5);
			panel_5.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_4 = new JScrollPane();
			panel_5.add(scrollPane_4, BorderLayout.CENTER);
			
			txtpTratamiento = new JTextPane();
			scrollPane_4.setViewportView(txtpTratamiento);
			
			JLabel lblNewLabel_3 = new JLabel("\u00BFPosee el paciente un enfermedad en la lista de vigilancia?");
			lblNewLabel_3.setBounds(10, 438, 432, 14);
			panel.add(lblNewLabel_3);
			
			rdbtnAfirmar = new JRadioButton("Si");
			rdbtnAfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnNegar.setSelected(false);
					listEnfermedad.setEnabled(true);
				}
			});
			rdbtnAfirmar.setBounds(57, 459, 46, 23);
			panel.add(rdbtnAfirmar);
			
			rdbtnNegar = new JRadioButton("No");
			rdbtnNegar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnAfirmar.setSelected(false);
					listEnfermedad.setEnabled(false);
					listEnfermedad.setSelectedIndex(0);
				}
			});
			rdbtnNegar.setSelected(true);
			rdbtnNegar.setBounds(10, 459, 46, 23);
			panel.add(rdbtnNegar);
			
			JPanel panel_6 = new JPanel();
			panel_6.setBounds(10, 496, 160, 143);
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
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistro = new JButton("Registrar");
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

	private ArrayList<String> loadlist() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < Clinica.getInstace().getMisEnfermedas().size(); i++) {
			String aux = new String(Clinica.getInstace().getMisEnfermedas().get(i).getCodigoEnfermedad()+": "+Clinica.getInstace().getMisEnfermedas().get(i).getNombreEnfermedad());
			listModelEnfermedad.addElement(aux);
			list.add(aux);
		}
		return list;
	}
}
