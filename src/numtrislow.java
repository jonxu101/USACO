/*
ID: jonxu101
LANG: JAVA
TASK: numtri
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class numtrislow {
	public static int answer=0;
	public static int maxnum=0;
	public static int solve(int layer, int index, int[][] values, int total, int[] maxrows){
		if(layer==values.length-1){
			total+=values[layer][index];
			if(total>answer){
				answer=total;
			}
			return answer;
		}
		if(total>answer){
			answer=total;
		}
		if(total+maxrows[layer]<answer){
//			System.out.println("!");
			return answer;
		}else{
			if(values[layer+1][index]>values[layer+1][index+1]){
//				System.out.println("1");
				solve(layer+1, index, values, total+=values[layer][index],maxrows);
				solve(layer+1, index+1, values, total,maxrows);
			}else{
//				System.out.println("2");
				solve(layer+1, index+1, values, total+=values[layer][index],maxrows);
				solve(layer+1, index, values, total,maxrows);
			}
			return answer;
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/numtri.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("numtri.out"));
			int length=Integer.parseInt(reader.readLine());
			int[][] values=new int[length][];
			int[] maxrows=new int[length];
			int maxsum=0;
			long start=System.currentTimeMillis();
			for(int i=0; i<length; i++){
				String[] nums=reader.readLine().split(" ");
				int[] intnums=new int[i+1];
				int index=0;
				int maxrow=0;
				for(String num: nums){
					int a=Integer.parseInt(num);
					intnums[index]=a;
					if(a>maxnum){
						maxnum=a;
					}
					if(a>maxrow){
						maxrow=a;
					}
					index+=1;
				}
				maxsum+=maxrow;
				maxrows[i]=maxsum;
				values[i]=intnums;
			}
//			System.out.println(Arrays.toString(maxrows));
			for(int i=0; i<maxrows.length; i++){
				maxrows[i]=maxrows[length-1]-maxrows[i];
			}
			long end=System.currentTimeMillis();
			System.out.println(end-start);
//			System.out.println(Arrays.toString(maxrows));
			int ans=solve(0, 0, values, 0, maxrows);
			writer.write(ans+"\n");
			System.out.println(ans);
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
