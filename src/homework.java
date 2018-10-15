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
public class homework {
	public static int length;
//	public static double getgrade(int[] grades, int start){
//		long grade=0;
//		long num=grades.length-start;
//		int mingrade=10000;
//		for(int i=start; i<grades.length; i++){
//			grade+=grades[i];
//			if(mingrade>grades[i]){
//				mingrade=grades[i];
//			}
//		}
//		grade-=mingrade;
//		return grade/(num-1);
//	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("homework.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("homework.out"));
			length=Integer.parseInt(reader.readLine());
			String[] sgrades=reader.readLine().split(" ");
			int[] grades=new int[length];
			int i=0;
			int totalgrade=0;
			int mingrade=10000;
			for(String s:sgrades){
				grades[i]=Integer.parseInt(s);
				i++;
			}
			ArrayList<Integer> maxindexes=new ArrayList<Integer>();
			int num=0;
			double maxgrade=0;
			int nummax=0;
			double grade=0;
			for(int start=length-1; start>=1; start--){
				if(mingrade>grades[start]){
					mingrade=grades[start];
				}
				totalgrade+=grades[start];
				num+=1;
				if(num-1==0){	
				}else{
					grade=(double)(totalgrade-mingrade)/(num-1);
				}
//				System.out.println(grade+" "+totalgrade+" "+num+" "+mingrade);
				if(grade>maxgrade){
					maxgrade=grade;
					maxindexes.add(start);
					nummax=1;
				}else if(grade==maxgrade){
					maxindexes.add(start);
					nummax++;
				}				
			}
			for(int a=maxindexes.size()-nummax; a<=maxindexes.size()-1;a++){
				writer.write(maxindexes.get(a)+"\n");
				System.out.println(maxindexes.get(a));
			}
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
