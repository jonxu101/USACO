/*
ID: jonxu101
LANG: JAVA
TASK: sort
*/
import java.io.*;
import java.util.*;
public class sort {

	public static void main(String[] args) throws IOException{
		File file = new File("sort.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int n=Integer.parseInt(reader.readLine());
		long[] numbers=new long[n];
		for(int i=0; i<n; i++){
			numbers[i]=Long.parseLong(reader.readLine());
		}
		boolean sorted=false;
		long ans=0;
//		System.out.println(Arrays.toString(numbers));
		int index=0;
		while(!sorted){
			sorted=true;
			ans++;
			for(int i=index; i<=n-index-2; i++){
				if(numbers[i+1]<numbers[i]){
					long a=numbers[i];
					numbers[i]=numbers[i+1];
					numbers[i+1]=a;
	            }
			}
			for(int i=n-index-2; i>=index; i--){
				if(numbers[i+1]<numbers[i]){
					long a=numbers[i];
					numbers[i]=numbers[i+1];
					numbers[i+1]=a;
				}
			}
			index++;
			for(int i=index; i<=n-index-2; i++){
				if(numbers[i+1]<numbers[i]){
					sorted=false;
					break;
				}
			}
		}
//		System.out.println(ans);
		writer.println(ans);
		writer.close();
	}

}
///*
//ID: jonxu101
//LANG: JAVA
//TASK: sort
//*/
//import java.io.*;
//import java.util.*;
//public class sort {
//
//	public static void main(String[] args) throws IOException{
//		File file = new File("sort.in");
//		BufferedReader reader = new BufferedReader(new FileReader(file));
//		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
//		int n=Integer.parseInt(reader.readLine());
//		long[] numbers=new long[n];
//		for(int i=0; i<n; i++){
////			Numm number=new Numm(i,Long.parseLong(reader.readLine()));
//			numbers[i]=Long.parseLong(reader.readLine());
//		}
////		Arrays.sort(numbers);
////		long max=0;
////		for(int i=0; i<(int)n; i++){
////			long moves=0;
////			if(numbers[i].index<i){
////				for(int j=i; j<n; j++){
////					if(numbers[j].index<i && numbers[j].index>numbers[i].index){
////						moves++;
////					}
////				}
////			}else{
////				for(int j=i; j>=0; j--){
////					if(numbers[j].index>i && numbers[j].index<numbers[i].index){
////						moves++;
////					}
////				}
////			}
////			max=Math.max(moves, max);
////		}
//		boolean sorted=false;
//		long ans=0;
//		while(!sorted){
//			sorted=true;
//			ans++;
//			for(int i=0; i<=n-2; i++){
//				int j=n-i-2;
//				if(numbers[i+1]<numbers[i]){
//					long a=numbers[i];
//					numbers[i]=numbers[i+1];
//					numbers[i+1]=a;
//				}
//				if(numbers[j+1]<numbers[j]){
//					long a=numbers[j];
//					numbers[j]=numbers[j+1];
//					numbers[j+1]=a;
//				}
//			}
//			for(int i=0; i<n-2; i++){
//				if(numbers[i+1]<numbers[i]){
//					sorted=false;
//					break;
//				}
//			}
//		}
////		System.out.println(ans);
//		writer.println(ans);
//		writer.close();
//	}
////	static class Numm implements Comparable<Numm>{
////		public int index;
////		public long value;
////		public Numm(int a, long b){
////			index=a;
////			value=b;
////		}
////		public int compareTo(Numm n){
////			return (int) (value-n.value);
////		}
////	}
//}