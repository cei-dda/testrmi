package net.franciscovillegas.rmi.server.cliente.bloqueante;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.franciscovillegas.rmi.common.Server;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private Server server;
	private JTextField enviarTextField;
	private JTextField recibirTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		enviarTextField = new JTextField();
		enviarTextField.setBounds(35, 51, 130, 26);
		frame.getContentPane().add(enviarTextField);
		enviarTextField.setColumns(10);

		JButton enviarButton = new JButton("Enviar");
		enviarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.enviarMensaje(enviarTextField.getText());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		enviarButton.setBounds(234, 99, 117, 29);
		frame.getContentPane().add(enviarButton);

		recibirTextField = new JTextField();
		recibirTextField.setBounds(35, 213, 130, 26);
		frame.getContentPane().add(recibirTextField);
		recibirTextField.setColumns(10);
	}

	public void setVisible(boolean visible) {
		this.frame.setVisible(true);
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void recibirMensaje(String mensaje) throws InterruptedException {
		if(!recibirTextField.getText().equals(mensaje)) {
			System.out.println("bloquear ----->>>>");
			Thread.sleep(5000);
		}
		this.recibirTextField.setText(mensaje);
	}
}
