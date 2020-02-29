import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class TrataCliente extends Thread{
	
	private Socket client;
	private ArrayList<Socket> clintesConectados = new ArrayList<Socket>();
	
	public TrataCliente(Socket s,ArrayList<Socket> clintesConectados ){
		client = s;
		this.clintesConectados = clintesConectados;
	}
	
	public void run(){
		ObjectInputStream oi;
		while(true) {
			try{
				
				oi = new ObjectInputStream(client.getInputStream());
				String mensagem = (String) oi.readObject();
				
				//System.out.println(mensagem);
					
				for(Socket cliente : clintesConectados) {
					ObjectOutputStream oo = new ObjectOutputStream(cliente.getOutputStream());
					oo.writeObject(mensagem);
				}
			}catch(Exception e){
				//System.out.println("----------FINALIZADO----------");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}