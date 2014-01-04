import java.util.Scanner;

public class vigenere{
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter key:");
		String key=s.next();
		System.out.println("Enter msg:");
		String msg=s.next();
		String result="";
		
		System.out.println("=====menue=====");
		System.out.println("1:for encryption");
		System.out.println("2:for decryption");
		int choice=s.nextInt();
		if(choice==1){
		while(key.length()<msg.length()){
			key+=key;
		}
		for(int i=0;i<msg.length();++i){
			int flag=msg.charAt(i)-97+1+(key.charAt(i)-97-1);
			if(flag<26)
				result+=(char)(flag+97);
			else
				result+=(char)(flag+97-26);
		}
		System.out.println("Encrypted msg:"+result);
		result="";
	}
		else if(choice==2){
		for(int i=0;i<msg.length();++i){
			int flag=(msg.charAt(i)-97)-(key.charAt(i)-96)+98;
			if(flag<97)
				result+=(char)(97-flag+97);
			else
				result+=(char)(flag);
		}
		System.out.println("Decrypted msg:"+result);
		
	}
	}
}