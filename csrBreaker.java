import java.util.Scanner;

public class csrBreaker{
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int choice=10;
		String orgMsg="",modMsg="";
		int k=0;	
		System.out.println("Message to be decrypted:");
		orgMsg=s.nextLine();
		while(k<27){
		modMsg="";
		int flag;
		for(int i=0;i<orgMsg.length();++i){
			if(Character.isUpperCase(orgMsg.charAt(i))){
				flag=orgMsg.charAt(i)-k-65;
				if(flag<0)
					modMsg+=(char)(90+flag);
				else
					modMsg+=(char)(flag+65);
			
			}
			else if(Character.isLowerCase(orgMsg.charAt(i))){
				flag=orgMsg.charAt(i)-k-97;
				if(flag<0)
					modMsg+=(char)(123+flag);
				else
					modMsg+=(char)(flag+97);
			}
			else{
				modMsg+=orgMsg.charAt(i);
			}
			
		}
		System.out.println("Trial "+k+" "+modMsg);
		++k;
	}
		
	}
}