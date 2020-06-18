package _02_Chat_Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Server {
	private int port;
	
	private ServerSocket server;
	private Socket socket;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	public Server() {
		port = new Random().nextInt(7952) + 2048;
	}
	
	public void start() {
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			
			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
			os.flush();
			
			while(socket.isConnected()) {
				try {
					
				} catch (EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection lost.");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error happened.");
		}
	}
	
	public int getPort() {
		return port;
	}
	
	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch(UnknownHostException e) {
			return null;
		}
	}
}
