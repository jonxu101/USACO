/*
ID: jonxu101
LANG: JAVA
TASK: talent
*/
import java.io.*;
import java.util.*;
public class talent {
	public static boolean check(long r){
		double[] dp=new double[1001];
		Arrays.fill(dp, Double.NEGATIVE_INFINITY);
		dp[0]=0;
		for(Cow c: cows){
			long value=(long)(1000*c.talent-r*c.weight);
			long inc=c.weight;
			for(int i=w; i>=0; i--){
				int j=(int) Math.min(w,i+inc);
				if(dp[i]!= -10000001){
					if (dp[j] < dp[i] + value) {
				          dp[j] = dp[i] + value;
				    }
				}
			}
		}
		return dp[w]>=0;
	}
	public static int w;
	public static Cow[] cows;
	public static void main(String[] args) throws IOException{
		File file = new File("talent.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int n=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		cows=new Cow[n];
		for(int i=0; i<n; i++){
			st=new StringTokenizer(reader.readLine());
			Cow c=new Cow(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			cows[i]=c;
		}
		long low=0; 
		long high=250000001;
		while(high>low+1){
			long mid=(long)((low+high)/2);
			if(check(mid)){
				low=mid;
			}else{
				high=mid;
			}
		}
//		System.out.println(low);
		writer.println(low);
		writer.close();
	}
	static class Cow implements Comparable<Cow>{
		public long weight, talent;
		public double r;
		public Cow(long a, long b){
			weight=a;
			talent=b;
			r=(double)b/a;
		}
		public String toString(){
			StringBuilder sb=new StringBuilder();
			sb.append(weight+","+talent+","+r+" | ");
			return sb.toString();
		}
		public int compareTo(Cow c){
			if(c.r!=r){
				return (int) (1000*(c.r-r));
			}else{
				return (int) (weight-c.weight);
			}
		}
	}

}
