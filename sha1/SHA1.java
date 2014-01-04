import java.util.ArrayList;
import java.util.Scanner;
public class SHA1 {
	public static String h0="01100111010001010010001100000001";
	public static String h1 = "11101111110011011010101110001001";
	public static String h2 = "10011000101110101101110011111110";
	public static String h3 = "00010000001100100101010001110110";
	public static String h4 = "11000011110100101110000111110000";
	public static String msg;
	public static String A,B,C,D,E;
	public static String k,f;
	public static ArrayList<String> chunks512=new ArrayList<String>();
	public static ArrayList<ArrayList<String>> words32=new ArrayList<ArrayList<String>>();
	public static String toBinaryString(String msg){
		String tmp="";
		String result="";
		for(int i=0;i<msg.length();++i){
			tmp=Integer.toBinaryString(msg.charAt(i));
			while(tmp.length()<8){
				tmp="0"+tmp;
			}
			result+=tmp;
			
		}
		return result;
	}
public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	System.out.println("Enter msg to be hashed");
	msg=s.nextLine();
	String state=toBinaryString(msg);
	String orgMsg=state;
	state=add1(state);
	state=appendZero(state);
	state=appendOrgMsgLen(state,orgMsg);
	chunk512(state);
	for (String string : chunks512) {
	}
	for (int i=0;i<chunks512.size();++i) {
		chunk32(chunks512.get(i),i);
	}
	for (ArrayList<String> list : words32) {
		for (String string : list) {
		}
	}
	for (int j=0;j<words32.size();++j) {
		
		for (int i=16;i<80;++i) {
			chunk32_80(j,words32.get(j).get(i-3),words32.get(j).get(i-8),words32.get(j).get(i-14),words32.get(j).get(i-16));
		}
	}
	A=h0;B=h1;C=h2;D=h3;E=h4;
	for (int i=0;i<80;i++) {
		String word = words32.get(0).get(i);
		if(i<20){
			f1();
		}
		else if(i<40){
			f2();
		}
		else if(i<60){
			f3();
		}
		else{
			f4();
		}
		Long temp=Long.parseLong(shiftn(A,5),2)+Long.parseLong(f,2)+Long.parseLong(E,2)+Long.parseLong(k,2)+Long.parseLong(word,2);
		String tempstr=Long.toBinaryString(temp);
		while(tempstr.length()<32){
			tempstr="0"+tempstr;
		}
		tempstr=tempstr.substring(tempstr.length()-32,tempstr.length());
		E=D;D=C;C=shiftn(B,30);B=A;A=tempstr;
	}
	Long temp=Long.parseLong(h0,2)+Long.parseLong(A,2);
	String tempstr=Long.toBinaryString(temp);
	while(tempstr.length()<32){
		tempstr="0"+tempstr;
	}
	tempstr=tempstr.substring(tempstr.length()-32,tempstr.length());
	h0=tempstr;
    temp=Long.parseLong(h1,2)+Long.parseLong(B,2);
    tempstr=Long.toBinaryString(temp);
    while(tempstr.length()<32){
		tempstr="0"+tempstr;
	}
	tempstr=tempstr.substring(tempstr.length()-32,tempstr.length());
	h1=tempstr;
	temp=Long.parseLong(h2,2)+Long.parseLong(C,2);
	tempstr=Long.toBinaryString(temp);
	while(tempstr.length()<32){
		tempstr="0"+tempstr;
	}
	tempstr=tempstr.substring(tempstr.length()-32,tempstr.length());
	h2=tempstr;
	temp=Long.parseLong(h3,2)+Long.parseLong(D,2);
	tempstr=Long.toBinaryString(temp);
	while(tempstr.length()<32){
		tempstr="0"+tempstr;
	}
	tempstr=tempstr.substring(tempstr.length()-32,tempstr.length());
	h3=tempstr;
	temp=Long.parseLong(h4,2)+Long.parseLong(E,2);
	tempstr=Long.toBinaryString(temp);
	while(tempstr.length()<32){
		tempstr="0"+tempstr;
	}
	tempstr=tempstr.substring(tempstr.length()-32,tempstr.length());
	h4=tempstr;
	h0=Long.toHexString(Long.parseLong(h0,2));
	h1=Long.toHexString(Long.parseLong(h1,2));
	h2=Long.toHexString(Long.parseLong(h2,2));
	h3=Long.toHexString(Long.parseLong(h3,2));
	h4=Long.toHexString(Long.parseLong(h4,2));
	System.out.println("Hash value:"+h0+h1+h2+h3+h4);
}
public static String shiftn(String str,int n){
	String result=str;
	for(int i=0;i<n;++i){
		result=ls1(result);
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
private static void setk(String str){
	k=str;
}
private static void f1(){
	String term1=AND(B,C);
	String term2=AND(NOT(B),D);
	f=OR(term1,term2);
	
	setk("01011010100000100111100110011001");
}
private static void f2(){
	String term1=XOR(B,C);
	String term2=XOR(term1,D);
	f=term2;
	
	setk("01101110110110011110101110100001");
}
private static void f3(){
	String term1=AND(B,C);
	String term2=AND(B,D);
	String term3=AND(C,D);
	String term4=OR(term1,term2);
	f=OR(term4,term3);
	
	setk("10001111000110111011110011011100");
	
}
private static void f4(){
	f2();
	setk("11001010011000101100000111010110");
}
private static String NOT(String str){
	String result="";
	for(int i=0;i<str.length();++i){
		if(str.charAt(i)=='0'){
			result+="1";
		}
		else 
			result+="0";
	}
	return result;
}
private static String AND(String str1,String str2){
	String result="";
	for(int i=0;i<str1.length();++i){
		if(str1.charAt(i)=='1'&&str2.charAt(i)=='1'){
			result+="1";
		}
		else 
			result+="0";
	}
	return result;
}
private static String OR(String str1,String str2){
	String result="";
	for(int i=0;i<str1.length();++i){
		if(str1.charAt(i)=='1'||str2.charAt(i)=='1'){
			result+="1";
		}
		else 
			result+="0";
	}
	return result;
}
private static void chunk32_80(int index,String word1, String word2, String word3,String word4) {
	String tmp1=XOR(word1,word2);
	String tmp2=XOR(word3,word4);
	String result=XOR(tmp1,tmp2);
	result=ls1(result);
	//System.out.println("word1:"+word1+" word2:"+word2+" word3:"+word3+" word4:"+word4);
	
	words32.get(index).add(result);
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
private static void chunk32(String string, int index) {
	words32.add(new ArrayList<String>());
	for(int i=0;i<string.length();){
		words32.get(index).add(string.substring(i,i+32));
		i+=32;
	}
	
        }
private static void chunk512(String state) {
	int j=0;
	for(int i=0;i<state.length();){
		chunks512.add(state.substring(i, i+512));
		++j;
		i+=512;
	}
	
}
private static String appendOrgMsgLen(String state, String orgMsg) {
	int len=orgMsg.length();
	String lenstr=Integer.toBinaryString(len);
	while(lenstr.length()<64){
		lenstr="0"+lenstr;
	}
	return state+lenstr;
}
private static String appendZero(String state) {
	while(state.length()%512!=448){
		state+="0";
	}
	return state;
}
private static String add1(String state) {
	return state+"1";
}
}
