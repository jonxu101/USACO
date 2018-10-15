/*
ID: jonxu101
LANG: JAVA
TASK: pprime
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
public class pprime {
	public static ArrayList<Integer> answers=new ArrayList<Integer>();
	public static int tempmax;
	public static boolean primechecker(int num){
		int max=(int) Math.pow(num, .5);
		for(int factor=2; factor<=max; factor++){
			if(num % factor==0){
				return false;
			}
		}
		return true;
	}
	public static void palindromes(int numdigit, int num){
//		System.out.println(numdigit);
//		System.out.println((int)tempmax/2);
		if(numdigit==0){
			for(int digit=1; digit<=9; digit++){
				if(numdigit==tempmax-numdigit){
					num+=digit*(Math.pow(10, numdigit));
//					System.out.println(num);
					if(primechecker(num)==true){
						answers.add(num);
					}
					num-=digit*(Math.pow(10, numdigit));
				}else{
					num+=digit*(Math.pow(10, numdigit)+Math.pow(10, tempmax-numdigit));
//					System.out.println(num);
					if(primechecker(num)==true){
						answers.add(num);
					}
					num-=digit*(Math.pow(10, numdigit)+Math.pow(10, tempmax-numdigit));
				}
			}
			return;
		}
		for(int digit=0; digit<=9; digit++){
			if(numdigit==tempmax-numdigit){
				num+=digit*(Math.pow(10, numdigit));
				palindromes(numdigit-1, num);
				num-=digit*(Math.pow(10, numdigit));
			}else{
				num+=digit*(Math.pow(10, numdigit)+Math.pow(10, tempmax-numdigit));
				palindromes(numdigit-1, num);
				num-=digit*(Math.pow(10, numdigit)+Math.pow(10, tempmax-numdigit));
			}
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("pprime.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("pprime.out"));
			String[] minmax=reader.readLine().split(" ");
			int min=Integer.parseInt(minmax[0]);
			int max=Integer.parseInt(minmax[1]);
			int maxdigits=(int) Math.log10(max);
			for(int numdigits=0; numdigits<=maxdigits; numdigits++){
				tempmax=numdigits;
				palindromes((int)numdigits/2, 0);
			}
			Collections.sort(answers);
			for(int answer: answers){
				if(answer>max){
					break;
				}else if(answer>=min){
//					System.out.println(answer);
					writer.write(answer+"\n");
					continue;
				}else{
					continue;
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
