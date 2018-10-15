/*
ID: jonxu101
LANG: JAVA
TASK: sprime
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
public class sprime {
	public static ArrayList<Integer> answers=new ArrayList<Integer>();
	public static int[] primes={2,3,5,7};
	public static int[] odds={1,3,7,9};
	public static int max;
	public static boolean primechecker(int num){
		if(num<2){
			return false;
		}else if(num % 2==0){
			return true;
		}
		int max=(int) Math.pow(num, .5);
		for(int factor=3; factor<=max; factor+=2){
			if(num % factor==0){
				return false;
			}
		}
		return true;
	}
	public static void solve(int num, int digitnum){
		if(digitnum==max+1){
			if(primechecker(num)==true){
				answers.add(num);
			}
			return;
		}
		if(digitnum==1){
			for(int digit:primes){
				num+=digit;
				solve(num, digitnum+1);
				num-=digit;
			}
		}else{
			for(int digit:odds){
				num=num*10+digit;
				if(primechecker(num)==false){
					num=(int)num/10;
					continue;
				}
				solve(num, digitnum+1);
				num=(int)num/10;
			}
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("sprime.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("sprime.out"));
			max=Integer.parseInt(reader.readLine());
			solve(0,1);
			System.out.println(Arrays.toString(answers.toArray()));
			for(int answer:answers){
				writer.write(answer+"\n");
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
