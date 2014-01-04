import java.util.Random;
import java.util.Scanner;
public class RSA {
public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	Random rand=new Random();
	int p=plist[Math.abs(rand.nextInt())%plist.length];
	int q=plist[Math.abs(rand.nextInt())%plist.length];
	int n=p*q;
	int phi=(p-1)*(q-1);
	int e=Math.abs(rand.nextInt()%(phi-1))+1;
	while(findGCD(e,phi)!=1){
		e=Math.abs(rand.nextInt()%(phi-1))+1;
	}
	int d=Math.abs(rand.nextInt());
	while((e*d)%phi!=1){
		d=Math.abs(rand.nextInt());
	}
	System.out.println("p:");
	int tmp=s.nextInt();
	if(tmp!=0)
		p=tmp;
	System.out.println("q:");
	tmp=s.nextInt();
	if(tmp!=0)
		q=tmp;
	n=p*q;
	phi=(p-1)*(q-1);
	System.out.println("1:enc");
	System.out.println("2:dec");
	tmp=s.nextInt();
	if(tmp==1){
		System.out.println("e:");
		tmp=s.nextInt();
		if(tmp!=0)
			e=tmp;
		System.out.print("message :");
		int m=s.nextInt();
		int encmsg=power(m,e,n);
		System.out.println("output:"+encmsg);
	}
	else if(tmp==2){
		System.out.println("d:");
		tmp=s.nextInt();
		if(tmp!=0)
			d=tmp;
		System.out.print("message :");
		int c=s.nextInt();
		int decmsg=power(c,d,n);
		System.out.println("output:"+decmsg);
	}
}

public static int plist[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89};
public static int power(int base,int exp,int n){
	if(exp==1)
		return base%n;
	if(exp%2==0){
		int tmp=power(base,exp/2,n);
		return ((tmp%n)*(tmp%n)%n);
	}
	
	else
		return ((base%n)*power(base,exp-1,n))%n;
}
private static int findGCD(int number1, int number2) {
    if(number2 == 0){
        return number1;
    }
   
    return findGCD(number2,number1%number2);
}
}
