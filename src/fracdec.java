/*
ID: jonxu101
LANG: JAVA
TASK: fracdec
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
public class fracdec {
	public static int[] remainders=new int[100000];
	public static int[] digits=new int[100000];
	public static int numremain=0;
	public static int divide(int n, int d){
		int currentnum=n*10;
		int largest=(int)(currentnum/d);
		digits[numremain]=largest;
		int remainder=currentnum-largest*d;
		if(remainder==0){
			return -2;
		}
		currentnum=remainder*10;
		numremain++;
		remainders[numremain-1]=remainder;
		int status=checkrepeat(remainders,remainder,largest, numremain-1);
		while(status==-2){
//			System.out.println(currentnum);
			largest=(int)(currentnum/d);
			digits[numremain]=largest;
			remainder=currentnum-largest*d;
			if(remainder==0){
				return -2;
			}
			currentnum=remainder*10;
			numremain++;
			remainders[numremain-1]=remainder;
			status=checkrepeat(remainders,remainder,largest, numremain-1);
		}
		numremain-=1;
		return status;
	}
	public static int checkrepeat(int[] remainders, int remainder, int digit, int numremain){
		for(int i=0; i<numremain; i++){
			if(remainders[i]==remainder && digits[i]==digit){
				return i-1;
			}
		}
		return -2;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/fracdec.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("fracdec.out"));
			String[] line=reader.readLine().split(" ");
			int n=Integer.parseInt(line[0]);
			int d=Integer.parseInt(line[1]);
			int x=(int)n/d;
			n=n-x*d;
			writer.write(x+".");
			System.out.print(x+".");
			int index=divide(n,d);
			Integer k=x;
			int counter=k.toString().length()+1;
			if(index==-2){
				for(int i=0; i<=numremain; i++){
					writer.write(digits[i]+"");
//					System.out.print(digits[i]);
					counter++;
					if(counter%76==0){
						writer.write("\n");
//						System.out.println("");
					}
				}
				writer.write("\n");
			}else{
				for(int i=0; i<=index; i++){
					writer.write(digits[i]+"");
					System.out.print(digits[i]);
					counter++;
					if(counter%76==0){
						writer.write("\n");
						System.out.println("");
					}
				}
				writer.write("(");
				System.out.print("(");
				counter++;
				for(int i=index+1; i<=numremain-1; i++){
					writer.write(digits[i]+"");
					System.out.print(digits[i]);
					counter++;
					if(counter%76==0){
						writer.write("\n");
						System.out.println("");
					}
				}
				writer.write(")\n");
				System.out.println(")");
			}
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
