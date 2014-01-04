import java.util.Scanner;

public class transposition{
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("key len:");
		int keylen=s.nextInt();
		int key[]=new int[keylen];
		System.out.println("Enter key:");
		for(int i=0;i<keylen;++i){
			key[i]=s.nextInt();
		}
		System.out.println("Enter msg:");
		String input=s.next();
		
		String adjustedMsg=input;
		if(input.length()%keylen!=0){
			int fillLen=keylen-input.length()%keylen;
			for(int i=0;i<fillLen;++i){
				adjustedMsg+="x";
			}
			//System.out.println("Adjusted Msg:"+adjustedMsg);
		}
		int col=keylen,row=adjustedMsg.length()/col;
		char [][] table=new char[row][col];
		//System.out.println("col:"+col+" row:"+row);
		for(int i=0;i<adjustedMsg.length();++i){
			int r=i/keylen;
			int c=i%keylen;
			table[r][c]=adjustedMsg.charAt(i);
		}
		
		/*for(int i=0;i<row;++i){
			for(int j=0;j<col;++j){
				System.out.print(table[i][j]+" ");
			}
			System.out.println();
		}*/
		System.out.println("_______MENU_________");
		System.out.println("1:column transposition");
		System.out.println("2:row transposition");
		System.out.println("_____________________");
		int choice=s.nextInt();
		if(choice==1){
			System.out.println("_______MENU_________");
			System.out.println("1:encryption");
			System.out.println("2:decryption");
			System.out.println("_____________________");
			int choice1=s.nextInt();
		if(choice1==1){
			char[] result=new char[adjustedMsg.length()];
			System.out.println("Column transposition Encrypted msg:");
			for(int i=0;i<key.length;++i){
				int c=key[i];
				for(int j=0;j<row;++j){
					result[row*(c-1)+j]=table[j][i];
				}
			}
			for (char c : result) {
				System.out.print(c);
			}
			System.out.println();
		}
		else if(choice1==2){
			for(int i=0;i<keylen;++i){
				int c=key[i];
				for(int j=0;j<row;++j){
					
					table[j][i]=input.charAt((c-1)*row+j);
				}
			}
			for(int i=0;i<row;++i){
				for(int j=0;j<keylen;++j){
					if(table[i][j]=='x')
						break;
					System.out.print(table[i][j]);
				}
			}
			System.out.println();
		}
		}
		//row transposition
		else if(choice==2){
			System.out.println("_______MENU_________");
			System.out.println("1:encryption");
			System.out.println("2:decryption");
			System.out.println("_____________________");
			int choice1=s.nextInt();
			if(choice1==1){
			int index=0;
			for(int i=0;i<col;++i){
				int c=key[i];
				for(int j=0;j<row;++j){
					table[j][i]=adjustedMsg.charAt((c-1)*row+j);
				}
			}
			System.out.println("Row transposition encrypted msg:");
			for(int i=0;i<row;++i){
				for(int j=0;j<col;++j){
					System.out.print(table[i][j]);
				}
				
			}
			System.out.println();
			}
			else if(choice1==2){
				for(int i=0;i<row;++i){
					for(int j=0;j<col;++j){
						
						table[i][j]=input.charAt(i*col+j);
					}
				}
				char[] result=new char[input.length()];
				for(int i=0;i<keylen;++i){
					int c=key[i];
					for(int j=0;j<row;++j){
						result[(c-1)*row+j]=table[j][i];
					}
				}
				for (char c : result) {
					if(c=='x') break;
					System.out.print(c);
				}
				System.out.println();
			}
		}
	}
}