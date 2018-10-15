/*
ID: jonxu101
LANG: JAVA
TASK: runround
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
public class runround {
	public static int testdone(int[] n){
		int counter=0;
		for(int a:n){
			if(a==2){
				return 0;
			}else if(a==1){
				counter+=1;
			}
		}
		if(counter==n.length){
			return 2;
		}
		return 1;
	}
	public static int testrunround(String num){
		int[] digits=new int[10];
		for(int i=0; i<num.length(); i++){
			digits[num.charAt(i)-'0']++;
		}
		if(digits[0]>0){
			return 0;
		}
		for(int n:digits){
			if(n>1){
				return 0;
			}
		}
		int[] integers=new int[num.length()];
		int currentindex=0;
		int state=1;
		int counter=0;
		while(state==1 && counter<num.length()){
			state=testdone(integers);
			currentindex=num.charAt(currentindex)-'0'+currentindex;
			currentindex=currentindex%num.length();
			integers[currentindex]+=1;
			counter++;
		}
		state=testdone(integers);
		integers[currentindex]+=1;
		if(state==2 && currentindex==0){
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/runround.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("runround.out"));
			int testnum=Integer.parseInt(reader.readLine())+1;
			while(testnum<=999999999){
				if(testrunround(Integer.toString(testnum))==1){
					writer.write(testnum+"\n");
					System.out.println(testnum);
					break;
				}
				testnum++;
			}
//			System.out.println(testrunround("81362"));
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
