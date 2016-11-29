package net.franciscovillegas.rmi.server.cliente.bloqueante;

import java.awt.EventQueue;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import net.franciscovillegas.rmi.common.Cliente;
import net.franciscovillegas.rmi.common.Server;

/**
 * Hello world!
 *
 */
public class App extends UnicastRemoteObject implements Cliente {

	private static final long serialVersionUID = 1L;

	private Server server;
	private MainWindow window;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		new App();
		new App();
	}

	public App() throws RemoteException, NotBoundException {
		System.out.println(System.currentTimeMillis());
		System.out.println("Hello World!");
		Registry registry = LocateRegistry.getRegistry(1099);
		this.server = (Server) registry.lookup("server");
		this.server.registrarObserver(this);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindow();
					window.setVisible(true);
					window.setServer(server);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void notificar(String mensaje) throws RemoteException {
		try {
			System.out.println("......");
			this.window.recibirMensaje(mensaje);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void notificar(String mensaje, boolean skipBlock) throws RemoteException {
		try {
			System.out.println("......");
			this.window.recibirMensaje(mensaje);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
