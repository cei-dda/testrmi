package net.franciscovillegas.rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import net.franciscovillegas.rmi.common.Server;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		LocateRegistry.createRegistry(1099);
		ServerImpl obj = new ServerImpl();
		Server stub = (Server) UnicastRemoteObject.exportObject(obj, 0);
		// Bind the remote object's stub in the registry
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.bind("server", stub);

		System.out.println("Server ready");
	}
}
