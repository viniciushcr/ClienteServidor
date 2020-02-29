import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Tela extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private String mensagemDigitada;
	private static Cliente cliente;
	private JTextArea tela;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					
					String server = "localhost";
					int porta = 1000;
					int numeroDeClientes = 1;
					
					cliente = new Cliente(server, porta, frame);
					cliente.start();
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 150, 600, 700);
		setResizable(false);
		setTitle("Bem vindo ao Chat !!!");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(12, 553, 570, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JTextArea textArea_ClienteRecebeu = new JTextArea();
		textArea_ClienteRecebeu.setBounds(12, 33, 570, 474);
		textArea_ClienteRecebeu.setEditable(false);
		contentPane.add(textArea_ClienteRecebeu);
		this.tela = textArea_ClienteRecebeu;
		
		JButton btnNewButton = new JButton("ENVIAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mensagemDigitada = textField.getText();
				if(!(mensagemDigitada.equals(""))){
					cliente.enviarMensagem(mensagemDigitada);
					//textArea_ClienteRecebeu.append(textField.getText()+"\n");
					textField.setText("");
				}
			}
		});
		btnNewButton.setToolTipText("Clique para enviar a mensagem");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(449, 615, 133, 37);
		contentPane.add(btnNewButton);
		
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewButton.doClick();
			}
		});
		
		
		
	}
	public JTextArea getTela() {
		return tela;
	}

	public void setTela(String mensagem) {
		this.tela.append(mensagem);
	}
	
}
