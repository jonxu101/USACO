/*
ID: jonxu101
LANG: JAVA
TASK: hamming
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
public class hamming {
	public static ArrayList<Integer> sequence=new ArrayList<Integer>();
	public static int sequencelength;
	public static int length;
	public static int mindistance;
	public static int hammingdistance(String num, String n){
		int distance=0;
		for(int i=0; i<length; i++){
			if(num.charAt(i)!=n.charAt(i)){
				distance+=1;
			}
		}
		return distance;
	}
	public static void makesequence(){
		sequence.add(0);
		sequence.add((int)Math.pow(2, mindistance)-1);
		int num=(int)Math.pow(2, mindistance);
		while(sequence.size()<sequencelength){
//			System.out.println(Arrays.toString(sequence.toArray()));
			if(checkvalid(num)==true){
				sequence.add(num);
			}
			num+=1;
			
		}
	}
	public static boolean checkvalid(int num){
		String n=Integer.toBinaryString(num);
		n=completestring(n);
		for(int m:sequence){
			String x=Integer.toBinaryString(m);
			x=completestring(x);
			if(hammingdistance(x,n)<mindistance){
				return false;
			}
		}
		return true;
	}
	public static String completestring(String num){
		while(num.length()<length){
			num="0"+num;
//			System.out.println(num);
		}
		return num;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("hamming.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("hamming.out"));
			String[] data=reader.readLine().split(" ");
			sequencelength=Integer.parseInt(data[0]);
			length=Integer.parseInt(data[1]);
			mindistance=Integer.parseInt(data[2]);
			makesequence();
//			System.out.println(Arrays.toString(sequence.toArray()));
			int x=1;
			for(int n:sequence){
				if(x%10==0 || x==sequencelength){
					x++;
					System.out.print(n+"\n");
					writer.write(n+"\n");
				}else{
					System.out.print(n+" ");
					writer.write(n+" ");
					x++;
				}
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
