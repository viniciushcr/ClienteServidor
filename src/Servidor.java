import java.net.*;
import java.util.ArrayList;
public class Servidor implements Runnable{
	
	private Servidor servidor;
	ServerSocket ss;
	private Socket clienteConectado;
	private ArrayList<Socket> clintesConectados = new ArrayList<Socket>(); 
	
	public Servidor(int porta) throws Exception{
		ss = new ServerSocket(porta);
		new Thread(this).start();
		System.out.println("Servidor ouvindo na porta:" + porta);
	}
	public void run(){
		try{
			while(true){
				System.out.println("Aguardando cliente!!!!!!!!!!!");
				clienteConectado = ss.accept(); 
				// Salva no array os clientes que vao conectando  
				clintesConectados.add(clienteConectado);
				new TrataCliente(clienteConectado,clintesConectados).start();
				System.out.println("Clientes conectados: "+ clintesConectados.size());
			}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Servidor getServidor() {
		
		if(this.servidor == null) {
			try {
				this.servidor = new Servidor(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.servidor;
		}
		return this.servidor;
	}
	
	
	public ArrayList<Socket> getClintesConectados() {
		return clintesConectados;
	}
	
	public static void main(String[] args){
		try{
			new Servidor(1000);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}