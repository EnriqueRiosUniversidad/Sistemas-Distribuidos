package Interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
    void registrarCliente(IClient cliente) throws RemoteException;
    void removerCliente(IClient cliente) throws RemoteException;
    void broadcastMessage(IClient c, String mensaje) throws RemoteException;
    List<String> getListaClientes() throws RemoteException;
}
