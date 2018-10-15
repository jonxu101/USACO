/*
ID: jonxu101
LANG: JAVA
TASK: money
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
public class money {
	public static long answer=0;
	public static int amount;
//	public static boolean summup(int currentsum,int previousused, int[] coins){
//		if(currentsum>=amount){
//			if(currentsum==amount){
//				answer+=1;
//			}
//			return true;
//		}
//		for(int i=previousused; i<coins.length;i++){
//			if(summup(currentsum+coins[i], i,coins)){
//				break;
//			}
//		}
//		return false;
//	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("money.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("money.out"));
			String[] things=reader.readLine().split(" ");
			int numcoins=Integer.parseInt(things[0]);
			amount=Integer.parseInt(things[1]);
			String currentline=reader.readLine();
			int index=0;
			int[] coins=new int[numcoins];
			while(currentline!=null){
				String[] scoins=currentline.split(" ");				
				for(String scoin:scoins){
					coins[index]=Integer.parseInt(scoin);
					index+=1;
				}
				currentline=reader.readLine();
			}
			long[] answer=new long[amount+1];
			answer[0]=1;
			for(int coin:coins){
				int sumamount=coin;
				long[] dummyanswer=answer.clone();
				while(sumamount<=amount){
					for(int i=amount; i>=sumamount; i--){
						dummyanswer[i]+=answer[i-sumamount];
					}
					sumamount+=coin;
				}
				for(int i=0; i<answer.length; i++){
					answer[i]=dummyanswer[i];
				}
			}
//			System.out.println(answer[amount]);
			writer.write(answer[amount]+"\n");
//			Arrays.sort(coins);
//			summup(0,0,coins);
//			System.out.println(answer);
//			writer.write(answer+"\n");
//			for(int i=0; i<=amount; i++){
//				for(int coin:coins){
//					if(i+coin<=amount){
//						answer[i+coin]+=answer[i];
//					}else{
//						break;
//					}
//				}
//				System.out.println(Arrays.toString(answer));
//			}
//			System.out.println(answer[amount]);
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
