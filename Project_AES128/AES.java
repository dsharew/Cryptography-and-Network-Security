import java.util.Scanner;

public class AES{
	static int MulM[][]={{2,3,1,1},{1,2,3,1},{1,1,2,3},{3,1,1,2}};
	public static String RconTable[]={"01000000","02000000","04000000","08000000","10000000","20000000","40000000","80000000","1B000000","36000000","6C000000","D8000000","AB000000","4D000000","9A000000"};
	public static String ETable[][]={{"01","03","05","0F","11","33","55","FF","1A","2E","72","96","A1","F8","13","35"},
			{"5F","E1","38","48","D8","73","95","A4","F7","02","06","0A","1E","22","66","AA"},
			{"E5","34","5C","E4","37","59","EB","26","6A","BE","D9","70","90","AB","E6","31"},
			{"53","F5","04","0C","14","3C","44","CC","4F","D1","68","B8","D3","6E","B2","CD"},
			{"4C","D4","67","A9","E0","3B","4D","D7","62","A6","F1","08","18","28","78","88"},
			{"83","9E","B9","D0","6B","BD","DC","7F","81","98","B3","CE","49","DB","76","9A"},
			{"B5","C4","57","F9","10","30","50","F0","0B","1D","27","69","BB","D6","61","A3"},
			{"FE","19","2B","7D","87","92","AD","EC","2F","71","93","AE","E9","20","60","A0"},
			{"FB","16","3A","4E","D2","6D","B7","C2","5D","E7","32","56","FA","15","3F","41"},
			{"C3","5E","E2","3D","47","C9","40","C0","5B","ED","2C","74","9C","BF","DA","75"},
			{"9F","BA","D5","64","AC","EF","2A","7E","82","9D","BC","DF","7A","8E","89","80"},
			{"9B","B6","C1","58","E8","23","65","AF","EA","25","6F","B1","C8","43","C5","54"},
			{"FC","1F","21","63","A5","F4","07","09","1B","2D","77","99","B0","CB","46","CA"},
			{"45","CF","4A","DE","79","8B","86","91","A8","E3","3E","42","C6","51","F3","0E"},
			{"12","36","5A","EE","29","7B","8D","8C","8F","8A","85","94","A7","F2","0D","17"},
			{"39","4B","DD","7C","84","97","A2","FD","1C","24","6C","B4","C7","52","F6","01"}
	};
	
			
	public static String[][] LTable={
		{"00","00","19","01","32","02","1A","C6","4B","C7","1B","68","33","EE","DF","03"},
		{"64","04","E0","0E","34","8D","81","EF","4C","71","08","C8","F8","69","1C","C1"},
		{"7D","C2","1D","B5","F9","B9","27","6A","4D","E4","A6","72","9A","C9","09","78"},
		{"65","2F","8A","05","21","0F","E1","24","12","F0","82","45","35","93","DA","8E"},
		{"96","8F","DB","BD","36","D0","CE","94","13","5C","D2","F1","40","46","83","38"},
		{"66","DD","FD","30","BF","06","8B","62","B3","25","E2","98","22","88","91","10"},
		{"7E","6E","48","C3","A3","B6","1E","42","3A","6B","28","54","FA","85","3D","BA"},
		{"2B","79","0A","15","9B","9F","5E","CA","4E","D4","AC","E5","F3","73","A7","57"},
		{"AF","58","A8","50","F4","EA","D6","74","4F","AE","E9","D5","E7","E6","AD","E8"},
		{"2C","D7","75","7A","EB","16","0B","F5","59","CB","5F","B0","9C","A9","51","A0"},
		{"7F","0C","F6","6F","17","C4","49","EC","D8","43","1F","2D","A4","76","7B","B7"},
		{"CC","BB","3E","5A","FB","60","B1","86","3B","52","A1","6C","AA","55","29","9D"},
		{"97","B2","87","90","61","BE","DC","FC","BC","95","CF","CD","37","3F","5B","D1"},
		{"53","39","84","3C","41","A2","6D","47","14","2A","9E","5D","56","F2","D3","AB"},
		{"44","11","92","D9","23","20","2E","89","B4","7C","B8","26","77","99","E3","A5"},
		{"67","4A","ED","DE","C5","31","FE","18","0D","63","8C","80","C0","F7","70","07"}
		};
	public static String[][] SBox={{"63","7C","77","7B","F2","6B","6F","C5","30","01","67","2B","FE","D7","AB","76"},
		{"CA","82","C9","7D","FA","59","47","F0","AD","D4","A2","AF","9C","A4","72","C0"},
		{"B7","FD","93","26","36","3F","F7","CC","34","A5","E5","F1","71","D8","31","15"},
		{"04","C7","23","C3","18","96","05","9A","07","12","80","E2","EB","27","B2","75"},
		{"09","83","2C","1A","1B","6E","5A","A0","52","3B","D6","B3","29","E3","2F","84"},
		{"53","D1","00","ED","20","FC","B1","5B","6A","CB","BE","39","4A","4C","58","CF"},
		{"D0","EF","AA","FB","43","4D","33","85","45","F9","02","7F","50","3C","9F","A8"},
		{"51","A3","40","8F","92","9D","38","F5","BC","B6","DA","21","10","FF","F3","D2"},
		{"CD","0C","13","EC","5F","97","44","17","C4","A7","7E","3D","64","5D","19","73"},
		{"60","81","4F","DC","22","2A","90","88","46","EE","B8","14","DE","5E","0B","DB"},
		{"E0","32","3A","0A","49","06","24","5C","C2","D3","AC","62","91","95","E4","79"},
		{"E7","C8","37","6D","8D","D5","4E","A9","6C","56","F4","EA","65","7A","AE","08"},
		{"BA","78","25","2E","1C","A6","B4","C6","E8","DD","74","1F","4B","BD","8B","8A"},
		{"70","3E","B5","66","48","03","F6","0E","61","35","57","B9","86","C1","1D","9E"},
		{"E1","F8","98","11","69","D9","8E","94","9B","1E","87","E9","CE","55","28","DF"},
		{"8C","A1","89","0D","BF","E6","42","68","41","99","2D","0F","B0","54","BB","16"}};
	public static String exkey="";
	public static String key="0f1571c947d9e8590cb7add6af7f6798";
	//public static String key="00000000000000000000000000000000";
	public static String GFM(String a,String b){
		////System.out.println("a and b "+a+" "+b);
		if(b.length()==1)
			b="0"+b;
		if(a.length()==1)
			a="0"+a;
		if(b.equals("00")||a.equals("00"))
			return "00";
		else if(a.equals("00"))
			return b;
		else if(b.equals("01"))
			return a; 
		String result="";
		////System.out.println("length:"+a.length()+" "+b.length());
		
		int row=Integer.parseInt(a.charAt(0)+"",16);
		int col=Integer.parseInt(a.charAt(1)+"",16);
		a=LTable[row][col];
		row=Integer.parseInt(b.charAt(0)+"",16);
		col=Integer.parseInt(b.charAt(1)+"",16);
		b=LTable[row][col];
		int tmp=Integer.parseInt(a,16)+Integer.parseInt(b, 16);
		if(tmp>255)
			tmp-=255;
		result=Integer.toHexString(tmp);
		if(result.length()==1) result="0"+result;
		row=Integer.parseInt(result.charAt(0)+"",16);
		col=Integer.parseInt(result.charAt(1)+"",16);
		result=ETable[row][col];
		return result;
	}
	public static String XOR(String a, String b){
		String result;
		result=Integer.toHexString(Integer.parseInt(a,16)^Integer.parseInt(b,16));
		if(result.length()==1)
			result="0"+result;
		return result;
	}
	public static String  mixCol(String state){
		String result="";
		int index=0;
		//fill state
		String st[][]=new String[4][4];
		for(int i=0;i<4;++i){
			for(int j=0;j<4;++j){
				st[j][i]=""+state.charAt(index++)+state.charAt(index++);
				//System.out.print(st[j][i]+" ");
			}
			//System.out.println();
		}
		String tmp="";
		for(int i=0;i<4;++i){
			for(int j=0;j<4;++j){
				
				tmp="";
				for(int k=0;k<4;++k){
					if(k==0) 
						tmp=GFM(st[k][i],MulM[j][k]+"");
					else 
						tmp=XOR(tmp,GFM(st[k][i],MulM[j][k]+""));
					
				}
				result+=tmp;
			}
		}

		//System.out.println("After mixcol:"+result);
		return result;
	}
	public static String RotWord(String word){
		String A=""+word.charAt(0)+word.charAt(1);
		String B=""+word.charAt(2)+word.charAt(3);
		String C=""+word.charAt(4)+word.charAt(5);
		String D=""+word.charAt(6)+word.charAt(7);
		return B+C+D+A;
	}
	public static String SubWord(String state)
    {	String result="";
    	int row,col;
    	for(int i=0;i<state.length();i+=2){
        row=Integer.parseInt(""+state.charAt(i),16);
        col=Integer.parseInt(""+state.charAt(i+1),16);
        result+=SBox[row][col];
       
    	}
    	 //System.out.println("After sub byte:"+result);
        return result;
    }
	public static String Rcon(int val){
		return RconTable[val];
	}
	public static String EK(int offset){
		return exkey.substring(2*offset,2*offset+8);
	}
	public static String K(int offset){
		return key.substring(2*offset,2*offset+8);
	}
	public static void expandKey(){
		String temp;
		for(int i=0;i<44;++i){
			//System.out.println("Debug:"+exkey);
			if(i==0){
				exkey+=K(0);
			}
			else if(i==1){
				exkey+=K(4);
			}
			else if(i==2){
				exkey+=K(8);
			}
			else if(i==3){
				exkey+=K(12);
			}
			else if(i%4==0){
				temp=Long.toHexString(Long.parseLong(SubWord(RotWord(EK((i-1)*4))),16)^ Long.parseLong(Rcon((i/4)-1),16)^Long.parseLong(EK((i-4)*4),16));
				//padd with zero
				while(temp.length()<8){
					temp="0"+temp;
				}
				exkey+=temp;
			}
			else{
				//System.out.println("EK:"+((i-1)*4));
				temp=Long.toHexString(Long.parseLong(EK((i-1)*4),16) ^ Long.parseLong(EK((i-4)*4),16));
				while(temp.length()<8){
					temp="0"+temp;
				}
				exkey+=temp;

			}
		}
	}
	public static String[] sh1(String[] row ){
	    String orig=row[0];
	    row[0]=row[1];
	    row[1]=row[2];
	    row[2]=row[3];
	    row[3]=orig;
	    return row;
	    }
	     public static String ShiftRow(String state)
	    {        
	            String temp[][]=new String[4][4];
	            //fill state
	                String st[][]=new String[4][4];
	                int index=0;
			for(int i=0;i<4;++i){
				for(int j=0;j<4;++j){
					st[j][i]=""+state.charAt(index++)+state.charAt(index++);
					//System.out.print(st[j][i]+" ");
				}
				//System.out.println();
	              }
	                String tempState[][]=new String[4][4];
	                tempState[0]=st[0];
	                tempState[1]=sh1(st[1]);
	                tempState[2]=sh1(sh1(st[2]));
	                tempState[3]=sh1(sh1(sh1(st[3])));
	              String result="";
	              for(int i=0;i<4;++i){
				for(int j=0;j<4;++j){
					//System.out.print(st[j][i]+" ");
	                                result+=tempState[j][i];
				}
	              }
	              //System.out.println("After shiftrow:"+result);
	              return result;
	    }
	    
	    public static String addRoundKey(String state,int round)
	    {
	    String result="";
	    String currentKey=exkey.substring(round*32,round*32+32);
	    for(int i=0;i<state.length();i++)
	      {         
	         result+=Integer.toHexString(Integer.parseInt(state.substring(i, i+1),16)^Integer.parseInt(currentKey.substring(i,i+1),16));
	      }
	    //System.out.println("After add round key:"+result);
	   return result;
	    }
	public static String encrypt(String plaintext){
		String state=plaintext;
		expandKey();
		state=addRoundKey(state, 0);
		for(int i=0;i<10;++i){
			if(i==9){
				state=addRoundKey((ShiftRow(SubWord(state))),i+1);
			}
			else
				state=addRoundKey(mixCol(ShiftRow(SubWord(state))),i+1);
		}
		return state;
	}
	public static void main(String[] args) {
		String plaintext="685e5e5e5e5e5e5e5e5e5e5e5e5e5e5e";
		System.out.println("cipher:"+encrypt(plaintext));
	}
}	