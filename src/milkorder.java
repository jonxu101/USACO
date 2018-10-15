/*
ID: jonxu101
LANG: JAVA
TASK: milkorder
*/
import java.io.*;
import java.util.*;
public class milkorder {

	public static void main(String[] args) throws IOException {
		File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/milkorder.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int num=Integer.parseInt(st.nextToken());
		int numobs=Integer.parseInt(st.nextToken());
		int[][] data=new int[numobs][num];
		for(int i=0; i<num; i++){
			st=new StringTokenizer(reader.readLine());
			int n=Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++){
				data[i][j]=Integer.parseInt(reader.readLine());
			}
		}
		for(int i=0; i<data[0].length; i++){
			for(int k:data[0]){
				answer.add(k);
			}
		}
	}
	public static void merge(int[] a){
		for(int i=0; i<a.length-1; i++){
			if(!answer.contains(a[i])){
				for(int j=0; j<answer.size(); j++){
					if(answer.get(j)==a[i+1]){
						
					}
				}
			}else{
				
			}
		}
	}
	public static LinkedList<Integer> answer=new LinkedList<Integer>();
	

}
