package logical;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.sun.javafx.embed.swing.Disposer;

public class Servidor extends Thread {

	public static Clinica respaldos = null;
	
	public static void main (String[] args) throws ClassNotFoundException, IOException {
		
		ServerSocket sfd = null;
		Socket nsfd = null;
		ObjectInputStream readFlujo = null;
		try {
			sfd = new ServerSocket(6000);
			nsfd = sfd.accept();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Conexión con Servidor rechazada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
		
		//Entorno de Ejecucion
		while (true) {
			try {
				readFlujo = new ObjectInputStream(nsfd.getInputStream());
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Error", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
			Clinica clinic = (Clinica) readFlujo.readObject();
			respaldos = clinic;
			JOptionPane.showMessageDialog(null, "Respaldo creado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
			
	}
	}
	
}
