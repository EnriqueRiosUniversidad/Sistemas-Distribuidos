

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import Interfaces.IClient;
import Interfaces.IServer;

public class ChatClientGUI {
	private ChatClient cliente;
	private JFrame frame;
	private JTextArea chatTextArea;
	private JTextArea usuarios;
	private JTextField mensageTextField;

	public ChatClientGUI() {

		// Creamos la ventana Jframe
		frame = new JFrame("Chat Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		// El cuerpo del chat y lista usuarios.
		JPanel mainPanel = new JPanel(new BorderLayout());
		// El panel de los usuarios conectados.
		usuarios = new JTextArea(5, 20);
		usuarios.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(usuarios);
		mainPanel.add(scrollPane2, BorderLayout.NORTH);

		// Creo un cuadro de los chats, y permito que se haga scroll.
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatTextArea);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Agrego un panel donde escribira el usaurio.
		JPanel inputPanel = new JPanel(new BorderLayout());
		mensageTextField = new JTextField();
		inputPanel.add(mensageTextField, BorderLayout.CENTER);

		// Los botones para enviar mensaje y desconectarse
		JButton sendButton = new JButton("Enviar");
		JButton disconnectButton = new JButton("Desconectar");
		// Enviamos mensaje al dar click
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		// Nos desconectamos al dar click
		disconnectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				disconnect();
			}
		});

		inputPanel.add(sendButton, BorderLayout.EAST);
		inputPanel.add(disconnectButton, BorderLayout.WEST);
		mainPanel.add(inputPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public void setCliente(ChatClient cliente) {
		this.cliente = cliente;
	}

	/*
	 * Envia el mensaje al servidor limpia el text input
	 */
	private void sendMessage() {
		String mensaje = mensageTextField.getText();
		if (!mensaje.isEmpty()) {
			try {
				cliente.enviarMensaje(mensaje);
				System.out.println("el mensaje que intento mandar es: " + mensaje);
				mensageTextField.setText("");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Le decimos al servidor que queremos salir de su registro Cerramos la ventaja
	 * de chat exit(0) cerramos el programa sin errores
	 */
	private void disconnect() {
		try {
			cliente.getServer().removerCliente(cliente);
			frame.dispose();
			System.exit(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Agregamos el mensaje el servidor retorna un string "Usuario" "mensaje"
	 */
	public void appendMessage(String message) {
		chatTextArea.append(message + "\n");
		chatTextArea.repaint();
	}

	/*
	 * Refresco la lista de usuarios conectados.
	 */
	public void refrescarUsuarios(List<String> cLista) {
		this.usuarios.setText("");
		for (String i : cLista) {
			this.usuarios.append(i + "\n");

		}
		usuarios.repaint();

	}

}
