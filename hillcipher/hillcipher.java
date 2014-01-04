import java.util.*;
public class hillcipher {
	public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	System.out.println("menu:");
	System.out.println("1:Encryption");
	System.out.println("2:Decryptioin");
	int choice=s.nextInt();
	if(choice==1){
		int [][] enckey;
		int k;
		
		System.out.println("Enter key:");
		k=s.nextInt();
		enckey=new int[k][k];
		System.out.println("Enter Enckey matrix:");
		for(int i=0;i<k;++i)
			for(int j=0;j<k;++j)
				enckey[i][j]=s.nextInt();
		System.out.println("Enter your message:");
		String msg=s.next();
		while(msg.length()%k!=0)//add filler
			msg+="x";
		String result="";
		for(int i=0;i<msg.length();){
			result+=mul(enckey,msg.substring(i, i+k));
			i+=k;
		}
		System.out.println("Encrypted message:"+result);
	}
	else if(choice==2){
		int [][] deckey;
		int k;
		System.out.println("Enter key:");
		k=s.nextInt();
		deckey=new int[k][k];
		System.out.println("Enter Deckey matrix:");
		for(int i=0;i<k;++i)
			for(int j=0;j<k;++j)
				deckey[i][j]=s.nextInt();
		System.out.println("Enter your message to be decrypted:");
		String msg=s.next();
		String result="";
		for(int i=0;i<msg.length();){
			result+=mul(deckey,msg.substring(i, i+k));
			i+=k;
		}
		while(result.charAt(result.length()-1)=='x'){//truncate the trailing x's
			result=result.substring(0,result.length()-1);
		}
		System.out.println("Decrypted message:"+result);
	}
	else{
		System.out.println("Invalid choice");
	}
}

	private static String mul(int[][] enckey, String msgcol) {
		int[] msg=new int[msgcol.length()];
		for(int i=0;i<msgcol.length();++i){
			msg[i]=(int)msgcol.charAt(i)-97;
		}
		String result="";
		int tmp;
			for(int j=0;j<msgcol.length();++j){
				tmp=0;
				for(int k=0;k<msgcol.length();++k){
					tmp+=msg[k]*enckey[j][k];
				}
				result+=(char)((tmp%26)+97);
			}
			return result;
	}
}
