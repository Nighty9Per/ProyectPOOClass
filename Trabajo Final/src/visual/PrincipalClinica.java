package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Usuario;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalClinica extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_3;
	private JMenu mnNewMenu_4;
	private JMenu mnNewMenu_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalClinica frame = new PrincipalClinica(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param acceso 
	 */
	public PrincipalClinica(Usuario acceso) {
		setTitle("Cl\u00EDnica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 416);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu_1 = new JMenu("Citas");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Agendar Cita");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		mnNewMenu_2 = new JMenu("Usuarios");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Usuario");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		mnNewMenu_3 = new JMenu("Consultas");
		menuBar.add(mnNewMenu_3);
		
		mnNewMenu_4 = new JMenu("Pacientes");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listado de Pacientes");
		mnNewMenu_4.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Historial de Pacientes");
		mnNewMenu_4.add(mntmNewMenuItem_2);
		
		mnNewMenu = new JMenu("Sesi\u00F3n");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Cambiar Usuario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccesoSistema access = new AccesoSistema();
				dispose();
				access.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		JLabel label = new JLabel("\u00A9Todos los derechos reservados. ");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		panel.add(label);
	}
}
