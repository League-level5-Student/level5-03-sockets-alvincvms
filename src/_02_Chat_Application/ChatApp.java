package _02_Chat_Application;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	private static final long serialVersionUID = 1L;
	JButton send = new JButton("send");
	JTextField text = new JTextField();
	JTextArea chats = new JTextArea(16, 30);
	JScrollPane pane = new JScrollPane(chats);
	JPanel panel = new JPanel();
	String name;
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp() {
		chats.setEditable(false);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text.setColumns(24);
		panel.add(pane);
		panel.add(text);
		panel.add(send);
		this.add(panel);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		name = JOptionPane.showInputDialog("Please enter your name:");
		String[] options = {"Host", "Join"};
		int choice = JOptionPane.showOptionDialog(null, "Please select your way to start the chat:", "Host/Client", 0, -1, null, options, 0);
		if(choice == 0) {
			int port = new Random().nextInt(7952) + 2048;
			Socket socket;
			ObjectOutputStream os;
			ObjectInputStream is;
			ServerSocket server;
			try {
				server = new ServerSocket(port);
				socket = server.accept();
				
				os = new ObjectOutputStream(socket.getOutputStream());
				is = new ObjectInputStream(socket.getInputStream());
				os.flush();
				send.addActionListener(e -> {
					sendMessage(os);
				});
				this.setVisible(true);
				while(socket.isConnected()) {
					unfinished
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An error occured.");
				System.exit(0);
			}
		} else {
			
		}
	}
	
	void sendMessage(ObjectOutputStream address) {
		String txt = text.getText();
		text.setText("");
		try {
			if(address != null) {
				address.writeObject(txt);
				chats.setText(chats.getText() + "\n" + name + ": " + txt);
				address.flush();
			}
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to send the message.");
		}
	}
}
