import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EscutadorServidor extends Thread{

	private InputStream servidor;
	private Tela tela;

	
	
	
	public EscutadorServidor(InputStream servidor, Tela tela) {
		this.servidor = servidor;
		this.tela = tela;
	}
	
	public void run(){
		ObjectInputStream oi;
		while(true) {
			try{
				
				oi = new ObjectInputStream(servidor);
				String mensagem = (String) oi.readObject();
				tela.setTela(mensagem);
					
			}catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}
