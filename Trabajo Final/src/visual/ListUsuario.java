package visual;

import javax.swing.JPanel;
import javax.swing.JButton;

public class ListUsuario extends JPanel {

	private JPanel panelMain;
	/**
	 * Create the panel.
	 */
	public ListUsuario() {

		setLayout(null);
		setBounds(0, 45, 1124, 606);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1124, 606);
		add(panelMain);
		panelMain.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(392, 299, 89, 23);
		panelMain.add(btnNewButton);
	}

}
