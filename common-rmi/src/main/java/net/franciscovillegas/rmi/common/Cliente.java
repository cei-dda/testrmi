package net.franciscovillegas.rmi.common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cliente extends Remote, Serializable {
	
	public void notificar(String mensaje, boolean skipBlock) throws RemoteException;
	
}
