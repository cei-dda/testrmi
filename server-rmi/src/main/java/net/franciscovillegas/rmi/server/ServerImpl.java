package net.franciscovillegas.rmi.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import net.franciscovillegas.rmi.common.Cliente;
import net.franciscovillegas.rmi.common.Server;

public class ServerImpl implements Server {
	
	private List<Cliente> clientes;
	
	public ServerImpl() {
		this.clientes = new ArrayList<Cliente>();
	}

	public void registrarObserver(Cliente cliente) throws RemoteException {
		this.clientes.add(cliente);
	}

	public void enviarMensaje(String mensaje) throws RemoteException {
		for (Cliente cliente : this.clientes) {
			cliente.notificar(mensaje, false);
			System.out.println("envio mensaje");
		}
		System.out.println("termino de enviar mensaje");
	}

	public void enviarMensaje(long mensaje) throws RemoteException {
		for (Cliente cliente : this.clientes) {
			cliente.notificar(String.valueOf(mensaje), true);
			System.out.println("envio mensaje");
		}
		System.out.println("termino de enviar mensaje");
	}

}
