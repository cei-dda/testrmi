package net.franciscovillegas.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

	public void registrarObserver(Cliente cliente) throws RemoteException;
	
	public void enviarMensaje(String mensaje) throws RemoteException;	
	
	public void enviarMensaje(long mensaje) throws RemoteException;	
}
