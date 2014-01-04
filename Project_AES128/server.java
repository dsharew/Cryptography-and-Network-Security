import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class server extends Thread{
	public static ServerSocket serversocket;
	private DataInputStream in;
	private DataOutputStream out;
	private Socket server;
	public server() throws IOException{
		serversocket=new ServerSocket(1993);
	}
	public void run(){
		
		while(true){
			try {
				server=serversocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				while(true){			
				in =new DataInputStream(server.getInputStream());
                out=new DataOutputStream(server.getOutputStream());
                String clntmsg=in.readUTF();
                String decmsg=decipher(clntmsg);
                System.out.println("client says(cipher):"+clntmsg);
                System.out.println("client says(plain text):"+decmsg);
                System.out.print("server:");
                Scanner s=new Scanner(System.in);
                out.writeUTF(cipher(s.nextLine()));
			}
			}
			catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
			}
		}
	}
	public static String decipher(String cipher){
		String decmsg="",tmp="";
	    DECAES decaes=new DECAES();
	    for(int i=0;i<cipher.length();){
	    	tmp+=decaes.decrypt(cipher.substring(i,i+32));
	    	i+=32;
	    }
	    for(int i=0;i<tmp.length()-1;i+=2){
	    	if(tmp.charAt(i)=='5'&&tmp.charAt(i+1)=='e')
	    		break;
	    	decmsg+=""+tmp.charAt(i)+tmp.charAt(i+1);
	    }
	    String result="";
	   char ch;
	    for(int i=0;i<decmsg.length();i+=2){
	    	ch=(char)Integer.parseInt(decmsg.substring(i,i+2),16);
	    	result+=ch;
	    }
	    return result;
	}
	public static String cipher(String msg){
		String result="",tmp;
		for(int i=0;i<msg.length();++i){
		        tmp=Integer.toHexString(msg.charAt(i));
		        if(tmp.length()<1)
		                tmp="0"+tmp;
		        result+=tmp;
		}
		while(result.length()%32!=0){
		tmp=Integer.toHexString('^');
		        if(tmp.length()<1)
		                tmp="0"+tmp;
		result+=tmp;
		}
		tmp=result;
		result="";
		AES aes=new AES();
		for(int i=0;i<tmp.length();){
						
			result+=aes.encrypt(tmp.substring(i,i+32));
			i+=32;
		}
		return result;
	}
	public static void main(String[] args) throws IOException {			
		server s=new server();
		s.start();
}
}
