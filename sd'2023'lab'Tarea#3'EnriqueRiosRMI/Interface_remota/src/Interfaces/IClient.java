package Interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface IClient extends Remote {
    String getNombre() throws RemoteException;
    IServer getServer() throws RemoteException;
    void setListaClientes(List<String> cLista) throws RemoteException;
    void enviarMensaje(String mensaje) throws RemoteException;
    void recibirMensaje(String mensaje) throws RemoteException;
}