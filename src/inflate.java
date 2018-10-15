/*
ID: jonxu101
LANG: JAVA
TASK: inflate
*/
import java.io.*;
import java.util.*;
public class inflate {

	public static void main(String[] args) throws IOException{
		File file = new File("inflate.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int m=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		int[][] data=new int[n][2];
		for(int i=0; i<n; i++){
			st=new StringTokenizer(reader.readLine());
			data[i][0]=Integer.parseInt(st.nextToken());
			data[i][1]=Integer.parseInt(st.nextToken());
		}
		int[] dp=new int[m+1];
		boolean[] a=new boolean[m+1];
		a[0]=true;
		for(int j=0; j<n; j++){
			int min=data[j][1];
			int value=data[j][0];
			for(int i=0; i<=m-min; i++){
				if(a[i]){
					dp[i+min]=Math.max(dp[i]+value,dp[i+min]);
					a[i+min]=true;
				}
			}
		}
		int max=0;
		for(int k:dp){
			max=Math.max(max, k);
		}
//		System.out.println(dp[m]);
		writer.println(max);
		writer.close();
	}

}
