import java.util.Scanner;


public class RailFence {
public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	String msg=s.nextLine();
	System.out.println("please enter key:");
	
	int key=s.nextInt();
	if(key>msg.length()){
		System.out.println("key must be less than or equal to message length");
		System.exit(1);
	}
	int col=(int)Math.ceil((double)msg.length()/key);
	char[][] Table=new char[key][col];
	String result="";
	for(int i=0;i<col;++i){
		for(int j=0;j<key&&i*key+j<msg.length();++j){
			Table[j][i]=msg.charAt(i*key+j);
		}
	}
	for(int i=0;i<key;++i){
		for(int j=0;j<col;++j){
			if(Table[i][j]!='\0')
			result+=""+Table[i][j];
		}
	}
	System.out.println("Encrypted message:"+result);	
}
}
