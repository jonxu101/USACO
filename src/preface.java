/*
ID: jonxu101
LANG: JAVA
TASK: preface
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
public class preface {
	public static int countI(int num){
		int n=0;
		if(num%5==1){
			n+=1;
		}else if(num%5==2){
			n+=3;
		}else if(num%5==3){
			n+=6;
		}else if(num%5==4){
			n+=7;
		}
		n+=(num-num%5)*7/5;
		return n;
	}
	public static int countV(int num){
		int n=0;
		if(num%10==4){
			n+=1;
		}else if(num%10==5){
			n+=2;
		}else if(num%10==6){
			n+=3;
		}else if(num%10==7){
			n+=4;
		}else if(num%10==8){
			n+=5;
		}else if(num%10==9){
			n+=5;
		}
		n+=(num-num%10)*5/10;
		return n;
	}
	public static int countX(int num){
		int n=0;
		int d=num%50;
		if(d<=9){
			if(d==9){
				n++;
			}
		}else if(d<=19){
			n++;
			n+=d-9;
			if(d==19){
				n++;
			}
		}else if(d<=29){
			n+=12;
			n+=2*(d-19);
			if(d==29){
				n++;
			}
		}else if(d<=39){
			n+=33;
			n+=(d-29)*3;
			if(d==39){
				n++;
			}
		}else if(d<=49){
			n+=64;
			n+=(d-39);
			if(d==49){
				n++;
			}
		}
		n+=(num-d)*75/50;
		return n;
	}
	public static int countL(int num){
		int n=0;
		int d=num%100;
		if(d<40){
		}else if(d<90){
			n+=d-39;
		}else{
			n=50;
		}
		n+=(num-d)/100*50;
		return n;
	}
	public static int countC(int num){
		int n=0;
		int d=num%500;
		if(d<=99){
			if(d>=90){
				n+=d-90+1;
			}
		}else if(d<=199){
			n+=10;
			n+=d-99;
			if(d>=190){
				n+=d-190+1;
			}
		}else if(d<=299){
			n+=120;
			n+=2*(d-199);
			if(d>=290){
				n+=d-290+1;
			}
		}else if(d<=399){
			n+=330;
			n+=(d-299)*3;
			if(d>=390){
				n+=d-390+1;
			}
		}else if(d<=499){
			n+=640;
			n+=(d-399);
			if(d>=490){
				n+=d-490+1;
			}
		}
		n+=(num-d)*75/50;
		return n;
	}
	public static int countD(int num){
		int n=0;
		int d=num%1000;
		if(d<400){
		}else if(d<900){
			n+=d-399;
		}else{
			n=500;
		}
		n+=(num-d)/1000*500;
		return n;
	}
	public static int countM(int num){
		int n=0;
		if(num<900){
		}else if(num<1900){
			n+=num-899;
		}else if(num<2900){
			n=1000;
			n+=num-1899;
		}else if(num<3900){
			n=3000;
			n+=3*(num-2899);
		}
		return n;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("preface.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("preface.out"));
			int num=Integer.parseInt(reader.readLine());
			int i=countI(num);
			int v=countV(num);
			int x=countX(num);
			int l=countL(num);
			int c=countC(num);
			int d=countD(num);
			int m=countM(num);
			writer.write("I "+i+"\n");
//			System.out.print("I "+i+"\n");
			if(v!=0){
				writer.write("V "+v+"\n");
//				System.out.print("V "+v+"\n");
			}
			if(x!=0){
				writer.write("X "+x+"\n");
//				System.out.print("X "+x+"\n");
			}
			if(l!=0){
				writer.write("L "+l+"\n");
//				System.out.print("L "+l+"\n");
			}
			if(c!=0){
				writer.write("C "+c+"\n");
//				System.out.print("C "+c+"\n");
			}
			if(d!=0){
				writer.write("D "+d+"\n");
//				System.out.print("D "+d+"\n");
			}
			if(m!=0){
				writer.write("M "+m+"\n");
//				System.out.print("M "+m+"\n");
			}
//			System.out.print("I "+i+"\n");
//			System.out.print("V "+v+"\n");
//			System.out.print("X "+x+"\n");
//			System.out.print("L "+l+"\n");
//			System.out.print("C "+c+"\n");
//			System.out.print("D "+d+"\n");
//			System.out.print("M "+m+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
