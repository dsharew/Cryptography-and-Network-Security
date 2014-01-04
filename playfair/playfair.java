import java.util.HashMap;
import java.util.Scanner;
public class playfair{
	public static char Table[][]=new char[5][5];
	public static void main(String[] args) {
		System.out.println("Enter key:");
		Scanner s=new Scanner(System.in);
		String key=s.next();
		generateTable(key);
		System.out.println("Menu:");
		System.out.println("1:Encrypt");
		System.out.println("2:Decrypt");
		int choice=s.nextInt();
		if(choice==1){
			System.out.println("Enter message:");
			String msg=s.next();
			String tmp="";
			int index=0;
			while(index<msg.length()){
				if(index+1<msg.length()&&msg.charAt(index)==msg.charAt(index+1)){
					tmp+=msg.charAt(index);
					tmp+="x";
					tmp+=msg.charAt(index+1);
					index+=2;
				}else{
					tmp+=msg.charAt(index);
					++index;
				}
			}
			if(tmp.length()%2!=0)
				tmp+="x";
			System.out.println("Adjusted msg:"+tmp);
			msg=tmp;
			int[] A=new int[2];
			int[] B=new int[2];
			String encmsg="";
			for(int i=0;i<msg.length();i+=2){
				A=getXY(msg.charAt(i));
				B=getXY(msg.charAt(i+1));
				encmsg+=encrypt(A,B);
				
			}
			System.out.println("Encryped message:"+encmsg);

		}
		else if(choice==2){
			System.out.println("Enter message to be Decrypted:");
			String msg=s.next();
			
			if(msg.length()%2!=0){
				System.out.println("Invalid Input");
				System.exit(1);
			}
			int[] A=new int[2];
			int[] B=new int[2];
			String decmsg="";
			for(int i=0;i<msg.length();i+=2){
				A=getXY(msg.charAt(i));
				B=getXY(msg.charAt(i+1));
				decmsg+=decrypt(A,B);
			}
			String tmp="";
			for (int i=0;i<decmsg.length();++i) {
				if(decmsg.charAt(i)!='x')
					tmp+=decmsg.charAt(i);
			}
			decmsg=tmp;
			System.out.println("Deccrypted message:"+decmsg);
			
		}
		else{
			System.out.println("Invalid Choice");
		}
	}
	
	private static String decrypt(int[] a, int[] b) {
		String result="";
		if(a[0]==b[0]){
			a[1]--;
			b[1]--;
			if(a[1]>0)
				a[1]=4;
			if(b[1]<0)
				b[1]=4;
			result+=Table[a[0]][a[1]];
			result+=Table[b[0]][b[1]];
		}
		else if(a[1]==b[1]){
			a[0]--;
			b[0]--;
			if(a[0]<0)
				a[0]=4;
			if(b[0]<0)
				b[0]=4;
			result+=Table[a[0]][a[1]];
			result+=Table[b[0]][b[1]];
		}
		else{
			result+=Table[a[0]][b[1]];
			result+=Table[b[0]][a[1]];
		}
		return result;

	}

	private static String encrypt(int[] a, int[] b) {
		String result="";
		if(a[0]==b[0]){
			a[1]++;
			b[1]++;
			if(a[1]>4)
				a[1]=0;
			if(b[1]>4)
				b[1]=0;
			result+=Table[a[0]][a[1]];
			result+=Table[b[0]][b[1]];
		}
		else if(a[1]==b[1]){
			a[0]++;
			b[0]++;
			if(a[0]>4)
				a[0]=0;
			if(b[0]>4)
				b[0]=0;
			result+=Table[a[0]][a[1]];
			result+=Table[b[0]][b[1]];
		}
		else{
			result+=Table[a[0]][b[1]];
			result+=Table[b[0]][a[1]];
		}
		return result;
	}

	private static int[] getXY(char c) {
		int[] result=new int[2];
		for(int i=0;i<5;++i)
			for(int j=0;j<5;++j){
				if(Table[i][j]==c){
					result[0]=i;
					result[1]=j;
					return result;
				}
			}
		return null;
	}

	private static void generateTable(String key) {
		HashMap<Character,Character>set=new HashMap<Character,Character>();
		char c='a';
		int index=0;
		String tablestr="";
		while(index<key.length()){
			if(key.charAt(index)=='i'&&set.containsKey('j')){
				++index;
				continue;
			}
			if(key.charAt(index)=='j'&&set.containsKey('i')){
				++index;
				continue;
			}
			if(!set.containsKey(key.charAt(index))){
				tablestr+=key.charAt(index);
				set.put(key.charAt(index),key.charAt(index));
			}
			++index;
		}
		
		while(c<='z'){
			if(c=='i'&&set.containsKey('j')){
				++c;
				continue;
			}
			if(c=='j'&&set.containsKey('i')){
				++c;
				continue;
			}
			if(!set.containsKey(c)){
				tablestr+=c;
				set.put(c,c);
			}
			++c;
		}
		index=0;
		for(int i=0;i<5;++i)
			for(int j=0;j<5;++j){
				Table[i][j]=tablestr.charAt(index++);
			}
	}
}