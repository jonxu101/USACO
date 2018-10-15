/*
ID: jonxu101
LANG: JAVA
TASK: ariprog
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;
public class ariprogslow {
	public static int sequencelength;
	public static int arisequencecheck(int[] numbers, int length){
		int difference=numbers[1]-numbers[0];
		int mindifference=difference;
		if(mindifference<numbers[length-1]-numbers[length-2]){
			return 2;
		}
		boolean x=true;
		for(int i=2; i<length; i++){
			int diff=numbers[i]-numbers[i-1];
			if(diff!=difference){
				x=false;
				if(diff<mindifference){
					mindifference=diff;
				}
			}
		}
		if(mindifference<numbers[length-1]-numbers[length-2]){
			return 2;
		}else if(mindifference==difference && x==true){
			return 1;
		}else{
			return 0;	
		}
	}
	public static ArrayList<ArrayList<Integer>> arisequence(int length, ArrayList<Integer> bisquares, int[] numbers,ArrayList<ArrayList<Integer>> answers, int i){
//		System.out.println(Arrays.toString(numbers));
		for(int index=i; index<bisquares.size(); index++){
			numbers[sequencelength-length]=bisquares.get(index);
			if(length<=sequencelength-2){
				int state=arisequencecheck(numbers,sequencelength-length+1);
				if(state==1 && length==1){
//					System.out.println("!");
					ArrayList<Integer> answer=new ArrayList<Integer>(2);
					answer.add(numbers[0]);
					answer.add(numbers[1]-numbers[0]);
					answers.add(answer);
					break;
					//return answers;
				}else if(state==1){
					arisequence(length-1,bisquares,numbers,answers,index+1);
				}else if(state==2){
					break;
					//return answers;
				}
				//else{
				//	continue;
				//}
			}else{
				arisequence(length-1,bisquares,numbers,answers,index+1);
			}
		}
		return answers;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/ariprog.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("ariprog.out"));
			int length=Integer.parseInt(reader.readLine());
			sequencelength=length;
			int bisqlimit=Integer.parseInt(reader.readLine());
			ArrayList<Integer> bissquareint=new ArrayList<Integer>(bisqlimit);
			for(int i=0; i<=bisqlimit; i++){
				bissquareint.add(i);
			}
			ArrayList<Integer> bisquares=new ArrayList<Integer>();
			long start=System.currentTimeMillis();
			for(int index=0; index<=bisqlimit; index++){
				int bisquare=0;
				bisquare+=Math.pow(bissquareint.get(index),2);
				for(int i=index; i<=bisqlimit; i++){
					bisquare+=Math.pow(bissquareint.get(i),2);
					if(bisquares.contains(bisquare)==false){
						bisquares.add(bisquare);
					}
					bisquare-=Math.pow(bissquareint.get(i),2);
				}
			}
			Collections.sort(bisquares);
			System.out.println("bisqaure size="+bisquares.size());
			long end=System.currentTimeMillis();
			System.out.println(end-start);
//			System.out.println(Arrays.deepToString(bisquares.toArray()));
			int[] blank=new int[length];
			ArrayList<ArrayList<Integer>> answers=new ArrayList<ArrayList<Integer>>();
//			ArrayList<Integer> thing=new ArrayList<Integer>();
//			thing.add(1);
//			thing.add(2);
//			thing.add(3);
//			thing.add(5);
//			System.out.println(arisequencecheck(thing));
			answers=arisequence(length, bisquares,blank,answers,0);
			end=System.currentTimeMillis();
			System.out.println(end-start);
			Collections.sort(answers, new Comparator<ArrayList<Integer>>(){
				@Override
				public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
					return o1.get(1).compareTo(o2.get(1));
				}               
			});	
			
			end=System.currentTimeMillis();
			System.out.println(end-start);
			
			if(answers.size()==0){
				writer.write("NONE\n");
			}else{
				for(ArrayList<Integer> answer: answers){
					writer.write(answer.get(0)+" "+answer.get(1)+"\n");
					System.out.println(Arrays.toString(answer.toArray()));
				}
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
