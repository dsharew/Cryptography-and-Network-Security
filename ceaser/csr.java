import java.util.Scanner;

public class csr{
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int choice=10;
		String orgMsg="",modMsg="";
		int k=3;
		while(choice!=4){
			System.out.println("\n\nCaesar Cpher");
			System.out.println("Menu:");
			System.out.println("1: Encrypt");
			System.out.println("2: Decrypt");
			System.out.println("3: Set key");
			System.out.println("4: Quit");
			System.out.println("__________________________");
		    s=new Scanner(System.in);
			choice=s.nextInt();
			if(choice==1){
				System.out.println("Message to be encrypted:");
				orgMsg=s.nextLine();
				orgMsg=s.nextLine();
				modMsg="";
				for(int i=0;i<orgMsg.length();++i){
					if(Character.isUpperCase(orgMsg.charAt(i)))
						modMsg+=(char)((orgMsg.charAt(i)+k-65)%26+65);
					else if(Character.isLowerCase(orgMsg.charAt(i))){
						modMsg+=(char)((orgMsg.charAt(i)+k-97)%26+97);
					}
					else
						modMsg+=orgMsg.charAt(i);
				}
				System.out.println("Encrypted Message:\n"+modMsg);
			}
			else if(choice==2){
				System.out.println("Message to be decrypted:");
				orgMsg=s.nextLine();
				orgMsg=s.nextLine();
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
				System.out.println("Decrypted Message:\n"+modMsg);
			}
			else if(choice==3){
				System.out.println("Enter key:");
				k=s.nextInt();
				System.out.println("Key set to "+k);
			}
			else if(choice==4){
				break;
			}
			else{
				System.out.println("Invalid choice");
			}
		}
	}
}