package visual;

import javax.swing.JPanel;

public class ListVacuna extends JPanel {

	private JPanel panelMain;
	/**
	 * Create the panel.
	 */
	public ListVacuna() {

		setLayout(null);
		setBounds(0, 45, 1124, 606);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1124, 606);
		add(panelMain);
		panelMain.setLayout(null);
	}

}
