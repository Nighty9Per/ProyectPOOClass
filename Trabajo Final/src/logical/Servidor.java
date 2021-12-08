package logical;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import com.sun.javafx.embed.swing.Disposer;

public class Servidor extends Thread {
	
	public static void main (String[] args) throws ClassNotFoundException, IOException {
		
		ServerSocket sfd = null;
		Socket nsfd = null;
		DataInputStream readFlujo = null;
		try {
			sfd = new ServerSocket(6000);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Conexión con Servidor rechazada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
		
		//Entorno de Ejecucion
		while (true) {
			try {
				nsfd = sfd.accept();
				System.out.println("Conexión aceptada de " +nsfd.getInetAddress());
				readFlujo = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
				Byte respaldo = readFlujo.readByte();
				File copy = null;
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Error", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Respaldo creado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
			
	}
	}
	
}
