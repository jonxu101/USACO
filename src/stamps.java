/*
ID: jonxu101
LANG: JAVA
TASK: stamps
*/
import java.io.*;
import java.util.*;
public class stamps {

	public static void main(String[] args) throws IOException{
		File file = new File("stamps.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int k=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		int biggest=0;
		int[] stamps=new int[n];
		int l=0;
		while(l<n){
			while(st.hasMoreTokens()){
				stamps[l]=Integer.parseInt(st.nextToken());
				if(biggest<stamps[l]){
					biggest=stamps[l];
				}
				l++;
			}
			if(l<n){
				st=new StringTokenizer(reader.readLine());
			}
		}
		int[] dp=new int[2000001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0]=0;
		for(int i=0; i<n; i++){
			for(int j=0; j<=k*biggest; j++){
				if(dp[j]<k){
					if(dp[j+stamps[i]]>1+dp[j]){
						dp[j+stamps[i]]=1+dp[j];
					}
				}
			}
		}
//		for(int i=0; i<20; i++){
//			System.out.print(dp[i]+",");
//		}
		int ans=0;
		while(dp[ans+1]<=k){
			ans++;
		}
//		System.out.println(ans);
		writer.println(ans);
		writer.close();
	}

}
