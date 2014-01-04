import java.util.ArrayList;
import java.util.Scanner;


public class DECDES {
	public static int[] permTable64={57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29, 21,13,5,28,20,12,4};
	public static int[] permTable56={14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
	public static int[] shiftTable={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	public static int[] initpermTable={58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
	public static int[] eTable={32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};
	public static int[][][] sBox={
								{	{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
									{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
									{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
									{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
								{   {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
									{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
									{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
									{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9,}},
								{	{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
									{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
									{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
									{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},
								{	{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
									{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
									{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
									{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
								{	{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
									{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
									{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
									{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
								{	{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
									{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
									{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
									{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
								{	{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
									{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
									{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
									{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
								{	{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
									{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
									{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
									{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}
								};
	public static int[] fpermTable={16,7,20,21,
									29,12,28,17,
									1,15,23,26,
									5,18,31,10,
									2,8,24,14,
									32,27,3,9,
									19,13,30,6,
									22,11,4,25
									};
	public static int ipInverse[]={
		40,8,48,16,56,24,64,32,
		39,7,47,15,55,23,63,31,
		38,6,46,14,54,22,62,30,
		37,5,45,13,53,21,61,29,
		36,4,44,12,52,20,60,28,
		35,3,43,11,51,19,59,27,
		34,2,42,10,50,18,58,26,
		33,1,41,9,49,17,57,25
	};
	public static ArrayList<String> keys56=new ArrayList<String>();
	
	public static ArrayList<String> keys48=new ArrayList<String>();
	public static void permKey56(){
		for(int i=0;i<keys56.size();++i){
			keys48.add("");
			for(int j=0;j<permTable56.length;++j){
				System.out.println("Str len:"+keys56.get(i).length());
				keys48.set(i, keys48.get(i)+""+keys56.get(i).charAt(permTable56[j]-1));
			}
		}
	}
	public static String permKey64(String key64){
		String result="";
		for(int i=0;i<permTable64.length;++i){
			result+=""+key64.charAt(permTable64[i]-1);
		}
		return result;
	}
	public static String initPerm(String message){
		String result="";
		for(int i=0;i<initpermTable.length;++i){
			result+=""+message.charAt(initpermTable[i]-1);
		}
		return result;
	}
	public static String ls1(String bitString){
		char c=bitString.charAt(0);
		String result="";
		for(int i=1;i<bitString.length();++i){
			result+=bitString.charAt(i);
		}
		result+=c;
		return result;
	}
	public static String shiftn(String str,int n){
		String result=str;
		for(int i=0;i<n;++i){
			result=ls1(result);
		}
		return result;
	}
	public static String E(String r){
		String result="";
		for(int i=0;i<eTable.length;++i){
			result+=""+r.charAt(eTable[i]-1);
		}
		return result;
	}
	public static String XOR(String bitString1,String bitString2){
		String result="";
		for(int i=0;i<bitString1.length();++i){
			if(bitString1.charAt(i)==bitString2.charAt(i)){
				result+="0";
			}
			else 
				result+="1";
		}
		return result;
	}
	public static String f(String r, String key){
		String tmp=XOR(r,key);
		System.out.println("Xor:"+tmp);
		String result="";
		int j=0;
		for(int i=0;i<tmp.length();){
			String row=""+tmp.charAt(i)+tmp.charAt(i+5);
			String col=""+tmp.substring(i+1,i+5);
			int rw=Integer.parseInt(row,2);
			int cl=Integer.parseInt(col,2);
			System.out.println(sBox[j][rw][cl]);
			String tmpresult= Integer.toBinaryString(Integer.valueOf(""+sBox[j][rw][cl], 10));
			while(tmpresult.length()<4){
				tmpresult="0"+tmpresult;
			}
			result+=tmpresult;
			i+=6;
			++j;
		}
		
		tmp="";
		for(int i=0;i<result.length();++i){
			tmp+=""+result.charAt(fpermTable[i]-1);
		}
		System.out.println("Result:"+tmp);
		return tmp;
	}
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		//0001001100110100010101110111100110011011101111001101111111110001
		System.out.println("Enter key (binary):");
		String key64=s.next();
		String key56=permKey64(key64);
		System.out.println("perm key:"+key56);
		String L=key56.substring(0,28);
		String R=key56.substring(28,56);
		System.out.println("L:"+L+" R:"+R);
		
		for(int i=0;i<shiftTable.length;++i){
			System.out.println("L"+i+":"+L+" R"+i+":"+R);
			L=shiftn(L,shiftTable[i]);
			R=shiftn(R,shiftTable[i]);
			keys56.add(L+R);
		}
		permKey56();
		for (String string : keys48) {
			System.out.println("key:"+string);
		}
		System.out.println("Enter message(binary):");
		String message=s.next();//=1000010111101000000100110101010000001111000010101011010000000101
		message=initPerm(message);
		System.out.println("perm msg:"+message);
		L=message.substring(0, 32);
		R=message.substring(32,64);
		System.out.println("L:"+L);
		System.out.println("R:"+R);
		//System.out.println("E(R):"+E(R));
		//System.out.println("Sbox:"+f(E(R),keys48.get(0)));
		String tmpL="",tmpR="";
		//16 rounds
		for(int i=0;i<16;++i){
			tmpL=L;
			tmpR=R;
			L=R;
			R=XOR(tmpL,f(E(tmpR),keys48.get(15-i)));
		}
		//reverse L and R
		tmpL=L;
		L=R;
		R=tmpL;
		System.out.println("R16L16:"+L+R);
		String encmsg=permInverse(L+R);
		System.out.println("Decrypted msg:"+encmsg);
	}
	private static String permInverse(String state) {
		String result="";
		for(int i=0;i<state.length();++i){
			result+=state.charAt(ipInverse[i]-1);
		}
		return result;
	}
}
