import java.net.*;
import java.util.Scanner;
import java.io.*;

public class client
{

private static OutputStream outputstream;
private static DataOutputStream out;
private static InputStream inputstream;
private static DataInputStream in;
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
public static void main(String [] args)
   {
      String serverName = "localhost";
      int port = 1993;
      try
      {
         Socket client = new Socket(serverName, port);
         outputstream = client.getOutputStream();
         out = new DataOutputStream(outputstream);
         inputstream = client.getInputStream();
         while(client.isConnected()){
         Scanner s=new Scanner(System.in);
         System.out.print("client:");
         out.writeUTF(cipher(s.nextLine()));
         in = new DataInputStream(inputstream);
         String servermsg=in.readUTF();
         
         System.out.println("Server says:(cipher) " + servermsg);
         System.out.println("Server says:(plain text) " + decipher(servermsg));
         }
         
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
