import java.net.*;
import java.util.ArrayList;
public class Servidor implements Runnable{
	
	private static Servidor servidor;
	ServerSocket ss;
	private Socket clienteConectado;
	private ArrayList<Socket> clintesConectados = new ArrayList<Socket>(); 
	
	private Servidor(int porta) throws Exception{
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
	
	public static Servidor getServidor() {
		
		if(servidor == null) {
			try {
				servidor = new Servidor(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return servidor;
	}
	
	
	public ArrayList<Socket> getClintesConectados() {
		return clintesConectados;
	}
	
	public static void main(String[] args){
		try{
			Servidor servidor;
			servidor = Servidor.getServidor();
			//new Servidor(1000);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}