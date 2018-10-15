/*
ID: jonxu101
LANG: JAVA
TASK: lamps
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
public class lamps {
	public static int[] current=new int[6];
	public static int[] wanton=new int[6];
	public static int[] wantoff=new int[6];
	public static ArrayList<Integer> sums=new ArrayList<Integer>();
	public static void perms(int num, int previous){
		if(num==0){
//			System.out.println(Arrays.toString(wanton));
//			System.out.println(Arrays.toString(wantoff));
			int j=0;
			for(int index:wanton){
				if(index==1){
					if(current[j%6]==0){
						return;
					}
				}
				j++;
			}
			j=0;
			for(int index:wantoff){
				if(index==1){
					if(current[j%6]==1){
						return;
					}
				}
				j++;
			}
			int sum=0;
			for(int i=5; i>=0; i--){
				sum+=current[i]*Math.pow(2,5-i);
			}
			sums.add(sum);
			return;
		}
		for(int i=previous+1; i<=4-num+1; i++){
//			System.out.println(i);
			if(i==1){
				for(int index=0; index<6; index++){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}else if(i==2){
				for(int index=0; index<6; index+=2){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}else if(i==3){
				for(int index=1; index<6; index+=2){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}else{
				for(int index=0; index<6; index+=3){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}
			perms(num-1, i);
			if(i==1){
				for(int index=0; index<6; index++){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}else if(i==2){
				for(int index=0; index<6; index+=2){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}else if(i==3){
				for(int index=1; index<6; index+=2){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}else{
				for(int index=0; index<6; index+=3){
					current[index]+=1;
					current[index]=current[index]%2;
				}
			}
		}
		return;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/lamps.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("lamps.out"));
			int lamps=Integer.parseInt(reader.readLine());
			int flips=Integer.parseInt(reader.readLine());
			String[] wantonstring=reader.readLine().split(" ");
			for(String s:wantonstring){
				if(Integer.parseInt(s)==-1){
					break;
				}
				wanton[(Integer.parseInt(s)-1)%6]=1;
			}
			String[] wantoffstring=reader.readLine().split(" ");
			for(String s:wantoffstring){
				if(Integer.parseInt(s)==-1){
					break;
				}
				wantoff[(Integer.parseInt(s)-1)%6]=1;
			}
//			System.out.println(Arrays.toString(wanton)+Arrays.toString(wantoff));
			Arrays.fill(current, 1);
			for(int num=flips%2; num<=flips; num+=2){
				perms(num,0);
			}
			Collections.sort(sums);
//			System.out.println(Arrays.toString(sums.toArray()));
			for(int n:sums){
				StringBuilder s=new StringBuilder();
				String q=Integer.toBinaryString(n);
				if(n==0){
					for(int i=1; i<=lamps; i++){
						s.append("0");
					}
					writer.write(s.toString()+"\n");
//					System.out.println(s.toString());
				}else if(n==63){
					for(int i=1; i<=lamps; i++){
						s.append("1");
					}
					writer.write(s.toString()+"\n");
//					System.out.println(s.toString());
				}else{
					String wholestring;
					if(q.length()<6){
						for(int i=0; i<6-q.length(); i++){
							s.append("0");
						}
						s.append(q);
						wholestring=s.toString();
					}else{
						wholestring=q;
					}
					StringBuilder sb=new StringBuilder();
//					System.out.println(wholestring);
					while(sb.length()<lamps-6){
//						System.out.println(sb.length());
//						System.out.println(wholestring);
						sb.append(wholestring);
					}
					int x=sb.length();
					for(int i=0; i<lamps-x; i++){
						sb.append(wholestring.charAt(i));
					}
					writer.write(sb.toString()+"\n");
					System.out.println(sb.toString());
				}
			}
			if(sums.size()==0){
				writer.write("IMPOSSIBLE"+"\n");
				System.out.println("IMPOSSIBLE");
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
