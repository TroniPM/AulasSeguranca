import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread {
	private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("Cliente Conectado, aguardando mensagem");
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			String msg = in.readLine();
			
			System.out.println("Mensagem recebida: " + msg);
			
			Teste t1 = Teste.convertFromString(msg);
			
			System.out.println(t1.getSeila());
			System.out.println(t1.getData());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	} 
	

	
	

}
