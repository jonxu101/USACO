/*
ID: jonxu101
LANG: JAVA
TASK: milk3
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
public class milk3 {
	public static int[] maxamounts=new int[3];
	public static ArrayList<Integer> answers=new ArrayList<Integer>();
	public static int[] transfer(int start, int end, int[] amounts){
		int[] dummy=amounts.clone();
		dummy[end]=dummy[start]+dummy[end];
		dummy[start]=0;
		if(dummy[end]>maxamounts[end]){
			dummy[start]=dummy[end]-maxamounts[end];
			dummy[end]=maxamounts[end];
		}
		return dummy;
	}
	public static ArrayList<Integer> solve(int step,int start, int end, int[] amounts){
		if(step>maxamounts[2]/2+4){
			return answers;
		}
		int[] dummyamounts=amounts.clone();
		int nothing=0;
		for(int j=0; j<3; j++){
			if(j!=start && j!=end){
				nothing=j;
				break;
			}
		}
//		System.out.println(Arrays.toString(amounts));
		if(amounts[nothing]!=0){
			amounts=transfer(nothing, start, dummyamounts);
//			System.out.println(Arrays.toString(amounts));
			if(amounts[0]==0){
				if(answers.contains(amounts[2])==false){
					answers.add(amounts[2]);
				}
			}
			solve(step+1,nothing, start, amounts);
		}
		if(amounts[nothing]!=0){
			amounts=transfer(nothing, end, dummyamounts);
			if(amounts[0]==0){
				if(answers.contains(amounts[2])==false){
					answers.add(amounts[2]);
				}
			}
			solve(step+1,nothing, end, amounts);
		}
		if(amounts[start]!=0){
			amounts=transfer(start, nothing, dummyamounts);
			if(amounts[0]==0){
				if(answers.contains(amounts[2])==false){
					answers.add(amounts[2]);
				}
			}
			solve(step+1,start, nothing, amounts);
		}
		if(amounts[end]!=0){
			amounts=transfer(end, nothing, dummyamounts);
			if(amounts[0]==0){
				if(answers.contains(amounts[2])==false){
					answers.add(amounts[2]);
				}
			}
			solve(step+1,end, nothing, amounts);
		}
		return answers;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("milk3.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("milk3.out"));
			String[] nums=reader.readLine().split(" ");
			int[] buckets=new int[3];
			buckets[0]=Integer.parseInt(nums[0]);
			buckets[1]=Integer.parseInt(nums[1]);
			buckets[2]=Integer.parseInt(nums[2]);
			maxamounts=buckets.clone();
			buckets[0]=0;
			buckets[1]=0;
//			System.out.println(Arrays.toString(transfer(0,1,transfer(2,1,transfer(2,0,buckets)))));
			answers=solve(0,2,0,buckets);
			Collections.sort(answers);
//			System.out.println(Arrays.toString(answers.toArray()));
//			for(int i=0; i<answers.size()-1; i++){
//				System.out.print(answers.get(i)+" ");
//				writer.write(answers.get(i)+" ");
//			}
			int index=0;
			for(int a:answers){
				if(index==answers.size()-1){
					writer.write(a+"");
					System.out.print(a);
				}else{
					writer.write(a+" ");
					System.out.print(a+" ");
				}
				index+=1;
			}
//			int thing=answers.get(answers.size()-1);
//			System.out.println(thing);
//			writer.write(thing);
			
			writer.write("\n");
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
