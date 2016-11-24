package net.franciscovillegas.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {

	public void notificar(String mensaje) throws RemoteException;
	
}
