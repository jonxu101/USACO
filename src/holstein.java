/*
ID: jonxu101
LANG: JAVA
TASK: holstein
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
public class holstein {
	public static int length=0;
	public static int[] answer;
	public static int status=0;
	public static void solve(int currentlength, int[][] feeds, int[] needed, int[] currentsum, int[] usedfeeds, int previousused){
		if(currentlength==0){
//			System.out.println(Arrays.toString(currentsum));
			for(int i=0; i<currentsum.length; i++){
				if(currentsum[i]<needed[i]){
					return;
				}
			}
//			System.out.println(Arrays.toString(needed));
//			System.out.println(Arrays.toString(currentsum));
			status=1;
			int[] ans=new int[usedfeeds.length+1];
			ans[0]=length;
			for(int i=1; i<=usedfeeds.length; i++){
				ans[i]=usedfeeds[i-1]+1;
			}
			answer=ans;
			return;
		}
		for(int i=previousused+1; i<feeds.length-currentlength+1; i++){
			if(status==1){
				break;
			}
//			System.out.println("I: "+i);
			for(int j=0; j<currentsum.length; j++){
				currentsum[j]+=feeds[i][j];
			}
//			System.out.println(Arrays.toString(currentsum));
			usedfeeds[length-currentlength]=i;
			solve(currentlength-1, feeds, needed, currentsum, usedfeeds, i);
			for(int j=0; j<currentsum.length; j++){
				currentsum[j]-=feeds[i][j];
			}
//			System.out.print(status);
//			System.out.println(Arrays.toString(currentsum));
		}
		return;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("holstein.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("holstein.out"));
			int numvitamins=Integer.parseInt(reader.readLine());
			String[] neededstring=reader.readLine().split(" ");
			int[] needed=new int[numvitamins];
			for(int i=0; i<numvitamins; i++){
				needed[i]=Integer.parseInt(neededstring[i]);
			}
//			System.out.println(Arrays.toString(needed));
			int availablefeeds=Integer.parseInt(reader.readLine());
			String[][] feedsstring=new String[availablefeeds][numvitamins];
			for(int i=0; i<availablefeeds; i++){
				feedsstring[i]=reader.readLine().split(" ");
			}
//			System.out.println(Arrays.deepToString(feedsstring));
			int[][] feeds=new int[availablefeeds][numvitamins];
			for(int i=0; i<availablefeeds; i++){
				for(int j=0; j<numvitamins; j++){
					feeds[i][j]=Integer.parseInt(feedsstring[i][j]);
				}
			}
			int[] currentsum=new int[needed.length];
//			System.out.println(Arrays.deepToString(feeds));
			for(int l=1; l<=feeds.length; l++){
				length=l;
				int[] usedfeeds=new int[length];
				Arrays.fill(usedfeeds, -1);
				solve(length, feeds, needed, currentsum, usedfeeds,-1);
			}
//			System.out.println(Arrays.toString(answer));
			for(int i=0; i<answer.length-1; i++){
				writer.write(answer[i]+" ");
			}
			writer.write(answer[answer.length-1]+"\n");
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
