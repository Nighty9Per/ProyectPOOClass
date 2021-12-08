package logical;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Flujo extends Thread {
	
	
	
	public static void main(String[] args) {
		String host = "LocalHost";
		try {
			Socket client = new Socket(host,6000);
			ObjectOutputStream writeFlujo = new ObjectOutputStream(new FileOutputStream("Bckup/respaldo.dat"));
			writeFlujo.writeObject(Clinica.getInstace().getClinica());
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*public Flujo (Socket sfd) {
		nsfd = sfd;
		try {
			readFlujo = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			writeFlujo = new ObjectOutputStream(new FileOutputStream("respaldo.dat"));
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "IOException(Flujo) "+ioe, "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void run() {
		Servidor.respaldos.add((Object) this);
		try {
			writeFlujo.writeObject(Clinica.getInstace().getClinica());
			JOptionPane.showMessageDialog(null, "Respaldo Guardado", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
			writeFlujo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
