/*
ID: jonxu101
LANG: JAVA
TASK: sort3
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
public class sort3 {
	public static int moves=0;
	public static int[] solve(int[][] nums, int[] num){
		int x=0;
		while(x>=0 && x<num.length){
			int n=num[x];
			if(x<=nums[n-1][1] && x>=nums[n-1][0]){
				x+=1;
				continue;
			}else{
				int switchindex=nums[n-1][0];
				for(int i=nums[n-1][0]; i<=nums[n-1][1]; i++){
					int m=num[i];
					if(switchindex==nums[n-1][0] && m!=n){
						switchindex=i;
					}
					if(x<=nums[m-1][1] && x>=nums[m-1][0]){
						switchindex=i;
						break;
					}
				}
				moves+=1;
				int t=num[x];
//				System.out.println(num[x]+" | "+num[switchindex]);
				num[x]=num[switchindex];
				num[switchindex]=t;
//				System.out.println(num[x]+" | "+num[switchindex]);
//				System.out.println(Arrays.toString(num));
			}
		}
		return num;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/sort3.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("sort3.out"));
			int i=Integer.parseInt(reader.readLine());
			int[] num=new int[i];
			for(int j=0; j<i; j++){
				num[j]=Integer.parseInt(reader.readLine());
			}
			int[] nums=new int[3];
			for(Integer n:num){
				if(n==1){
					nums[0]+=1;
				}else if(n==2){
					nums[1]+=1;
				}else{
					nums[2]+=1;
				}
			}
			int[][] senum=new int[3][2];
			senum[0][0]=0;
			senum[0][1]=nums[0]-1;
			senum[1][0]=nums[0];
			senum[1][1]=nums[0]+nums[1]-1;
			senum[2][0]=nums[0]+nums[1];
			senum[2][1]=i-1;
//			System.out.println(Arrays.deepToString(senum));
//			System.out.println(Arrays.toString(num));
			num=solve(senum,num);
//			System.out.println(Arrays.toString(num));
			System.out.println(moves);
			writer.write(moves+"\n");
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
