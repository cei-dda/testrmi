package net.franciscovillegas.rmi.server.cliente.nobloqueante;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.franciscovillegas.rmi.common.Server;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainWindow {

	private JFrame frame;
	private Server server;
	private JTextField enviarTextField;
	private JTextField recibirTextField;

	private String ultimoMensaje = null;

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
				Runnable runnable = new Runnable() {
					public void run() {
						try {
							ultimoMensaje = enviarTextField.getText();
							server.enviarMensaje(enviarTextField.getText());
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				};
				new Thread(runnable).start();
			}
		});
		enviarButton.setBounds(234, 99, 117, 29);
		frame.getContentPane().add(enviarButton);

		recibirTextField = new JTextField();
		recibirTextField.setBounds(35, 213, 130, 26);
		frame.getContentPane().add(recibirTextField);
		recibirTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(37, 139, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.enviarMensaje(System.currentTimeMillis());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(178, 140, 117, 29);
		frame.getContentPane().add(btnNewButton);
	}

	public void setVisible(boolean visible) {
		this.frame.setVisible(true);
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void recibirMensaje(final String mensaje, final boolean skipBlock) throws InterruptedException {
		System.out.println("recibo mensaje");
		Runnable tareaParaHilo = new Runnable() {
			public void run() {
				System.out.println("empieza tarea para el hilo");
				System.out.println("bloquear ----->>>>");
				try {
					System.out.println("me voy a dormir");
					Thread.sleep(5000);
					System.out.println("buenos dias");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				recibirTextField.setText(mensaje);
				System.out.println("soy un vago y recien termino de hacer la tarea");
			}
		};
		new Thread(tareaParaHilo).start();
		System.out.println("recibi mensaje");
	}
}
