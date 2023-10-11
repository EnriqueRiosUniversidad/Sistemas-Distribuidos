
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IClient;
import Interfaces.IServer;

public class ChatServer extends UnicastRemoteObject implements IServer {
	/* Variables */
	List<IClient> clientes;
	private static final long serialVersionUID = 1L;

	protected ChatServer() throws RemoteException {
		super();
		clientes = new ArrayList<>();

	}

	@Override
	public void registrarCliente(IClient cliente) throws RemoteException {
		clientes.add(cliente);
		actualizarListaClientes();

	}

	@Override
	public void removerCliente(IClient cliente) throws RemoteException {
		if (clientes.contains(cliente)) {
			clientes.remove(cliente);
			actualizarListaClientes();
			System.out.println("Cliente REMOVIDO");

		} else {
			System.out.println("Cliente no registrado");
		}

	}

	@Override
	public void broadcastMessage(IClient c, String mensaje) throws RemoteException {
		System.out.println("Usuario: " + c.getNombre() + "\n Intentamandar el mensaje: " + mensaje);

		for (IClient cliente : clientes) {

			cliente.recibirMensaje(c.getNombre() + ": " + mensaje);
		}

	}

	@Override

	public List<String> getListaClientes() throws RemoteException {
		List<String> cNombres = new ArrayList<String>();
		for (IClient cliente : clientes) {
			cNombres.add(cliente.getNombre());
		}
		return cNombres;
	}

	// Notificamos o seteamos la lista de usuarios en cada cliente.
	private void actualizarListaClientes() throws RemoteException {
		List<String> cNombres = getListaClientes();
		for (IClient cliente : clientes) {
			cliente.setListaClientes(cNombres);
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("----: java ChatServer <puerto> :----");
			System.exit(1); // el programa se cierra.
		}

		try {
			int port = Integer.parseInt(args[0]);
			// Esto inicializa el servidor de chat
			ChatServer server = new ChatServer();
			// Se crea un registro RMI en el puerto especificado.
			// Esto permite que los clientes busquen y se conecten al servidor a través del
			// registro RMI en ese puerto.
			java.rmi.registry.LocateRegistry.createRegistry(port);
			// Se obtiene una referencia al registro RMI que se ha creado en el puerto
			// especificado. Esto permitirá vincular el servidor al registro RMI para que
			// los clientes puedan encontrarlo.
			java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(port);
			// El servidor se vincula al registro RMI con un nombre ("ChatServer") para que
			// los clientes puedan buscarlo utilizando ese nombre.
			registry.rebind("ChatServer", server);

			System.out.println("El servidor de chat está en funcionamiento en el puerto " + port);
		} catch (Exception e) {
			System.err.println("Error en el servidor: " + e.toString());
			e.printStackTrace();
		}
	}

}
