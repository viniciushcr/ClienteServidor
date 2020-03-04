import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.io.*;

public class Cliente extends Thread{

	private String ipServer;
	private int porta;
	

	private String mensagemDigitada;
	private Socket serverSocket;
	private String nomeCliente;
	private Tela tela;
	
	public Cliente(Tela tela){
		this.tela = tela;
		perguntarDados();
	}
	
	public static void main(String[] args){
		try{
			Tela frame = new Tela();
			frame.setVisible(true);
	
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
			
}
	
	public void perguntarDados(){
		
		JLabel ip = new JLabel("IpServer:");
		JTextField campoIp = new JTextField("localhost");
		JLabel porta = new JLabel("Porta:");
		JTextField campoPorta = new JTextField("1000");
		JLabel nome = new JLabel("Nome Cliente:");
		JTextField campoNome = new JTextField();
		
		Object[] texts = {ip,campoIp, porta, campoPorta, nome, campoNome};
		JOptionPane.showMessageDialog(null, texts);
		
		this.ipServer = campoIp.getText();
		this.porta = Integer.parseInt(campoPorta.getText());
		this.nomeCliente = campoNome.getText();
		
		if(this.nomeCliente.equals("") ||this.nomeCliente.equals(" ") || this.nomeCliente.equals(null)) {
			this.nomeCliente = "NOME OCULTO";
		}
		this.nomeCliente = this.nomeCliente.toUpperCase();
		  
	}
	
	public void run(){
		
		try {
			tela.setTitle(this.getNomeCliente());
			
		}catch (Exception e) {
			this.nomeCliente = "NOME OCULTO";
		}
			
			
		try{
			serverSocket = new Socket(ipServer, porta);
			
			System.out.println("Conectado a " + ipServer + ":" + porta);
			
			EscutadorServidor escutadorServidor = new EscutadorServidor(serverSocket.getInputStream(), this.tela);
			escutadorServidor.start();
			
		} 	
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void enviarMensagem(String mensagemRecebida) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date hora = Calendar.getInstance().getTime();
		String horaFormatada = sdf.format(hora);
		
		try {
			mensagemDigitada= (this.nomeCliente+": "+mensagemRecebida+" ("+horaFormatada+")");
			if(!(mensagemDigitada.equals(""))){
				ObjectOutputStream oo = new ObjectOutputStream(serverSocket.getOutputStream());
				oo.writeObject(mensagemDigitada+"\n");
				mensagemDigitada="";
				//s.close();
				//Thread.sleep(2000);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getNomeCliente() {
		return nomeCliente;
	}

		public Socket getServerSocket() {
		return serverSocket;
	}
}