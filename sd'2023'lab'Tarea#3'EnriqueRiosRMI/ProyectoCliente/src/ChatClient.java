

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import Interfaces.IClient;
import Interfaces.IServer;


public class ChatClient extends UnicastRemoteObject implements IClient {
	/* Variables */
	private static final long serialVersionUID = 1L;
	IServer server;
	List<String> clientes;
	String nombre;
	private ChatClientGUI clientGUI;
	
	protected ChatClient(String nombre, IServer server, ChatClientGUI gui) throws RemoteException {
		super();
		this.nombre = nombre;
		this.server = server;
		clientes = new ArrayList<String>();
		this.clientGUI = gui;
	}

	@Override
	public void enviarMensaje(String mensaje) throws RemoteException {
		server.broadcastMessage(this, mensaje);

	}

	@Override
	public void recibirMensaje(String mensaje) throws RemoteException {
		System.out.println("Mensaje: " + mensaje);
		this.clientGUI.appendMessage(mensaje);
	}

	@Override
	public void setListaClientes(List<String> cLista) throws RemoteException {
		
		
		clientes = cLista;
		this.clientGUI.refrescarUsuarios(cLista);
	}

	@Override
	public IServer getServer() throws RemoteException {
		return server;
	}

	@Override
	public String getNombre() throws RemoteException {
		return nombre;
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Error: java RMIClient <nombre> <puerto>");
			System.exit(1);
		}
		try {
			String hostName = args[0];
			int port = Integer.parseInt(args[1]);
			//localhost
		
	
			
			//if (System.getSecurityManager() == null) {
				// Su función principal es controlar y gestionar las operaciones de seguridad en
				// un entorno de RMI para garantizar que solo se permitan acciones seguras y
				// autorizadas.
				//System.setSecurityManager(new RMISecurityManager());
			//}
			// como hostName es el nombre de "ChatServer" el cliente
			// intentará resolver ese nombre de host en una dirección IP y luego conectarse
			// al registro RMI en esa dirección IP en el puerto "port".
			Registry registry = LocateRegistry.getRegistry(hostName, port);
			//Buscamos el objeto asociado a "ChatServer" dentro de Registry
			IServer server = (IServer) registry.lookup("ChatServer");
			String nombre = JOptionPane.showInputDialog("Me darias tu nombre: ");

			ChatClientGUI gui = new ChatClientGUI();

			ChatClient cliente = new ChatClient(nombre, server, gui);
			
			gui.setCliente(cliente);
			
			server.registrarCliente(cliente);
			

		} catch (NumberFormatException e) {
			System.out.println("Puerto mal colocado");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exeption ;(");
			System.out.println(e.getMessage());
		}

	}

}
