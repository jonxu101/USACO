/*
ID: jonxu101
LANG: JAVA
TASK: humble
*/
import java.io.*;
import java.util.*;
public class humble {

	public static void main(String[] args) throws IOException{
		File file = new File("humble.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int k=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		long[] primes=new long[k];
		st=new StringTokenizer(reader.readLine());
		for(int i=0; i<k; i++){
			primes[i]=Long.parseLong(st.nextToken());
		}
//		long[] hum=new long[n+1];
//		Arrays.fill(hum, Long.MAX_VALUE);
//		hum[0]=1;
//		int[] min=new int[n+1];
//		for(int i=0; i<n; i++){
//			for(int j=i; j>=0; j--){
//				for(int x=min[j]; x<k; x++){
//					long temp = hum[j]*primes[x];
//					if(temp >hum[i]){
//						hum[i+1]=Math.min(hum[i+1], temp);
//						min[j]=x;
//						if(j!=0){
//							min[j-1]=x;
//						}
//						break;
//					}
//				}
//			}
//		}
		long[] hums=new long[n+1];
		int nhum=1;
		int[] min=new int[k];
		hums[0]=1;
		while(nhum<n+1){
			long m=Long.MAX_VALUE;
			for(int i=0; i<k; i++){
				while(primes[i]*hums[min[i]]<=hums[nhum-1]){
					min[i]++;
				}
				m=Math.min(primes[i]*hums[min[i]], m);
			}
			hums[nhum++]=m;
		}
//		System.out.println(Arrays.toString(hums));
//		System.out.println(hums[n]);
		writer.println(hums[n]);
		writer.close();
	}

}
