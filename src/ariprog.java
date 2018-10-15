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
import java.util.HashMap;
public class ariprog {
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/ariprog.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("ariprog.out"));
			int length=Integer.parseInt(reader.readLine());
			int max=Integer.parseInt(reader.readLine());
			int maxbisquare=(int) (2*Math.pow(max, 2));
			int[] bisquares=new int[maxbisquare+1];
			ArrayList<int[]> answers=new ArrayList<int[]>();
			for(int index=0; index<=max; index++){
				int bisquare=0;
				bisquare+=Math.pow(index,2);
				for(int i=index; i<=max; i++){
					bisquare+=Math.pow(i,2);
					bisquares[bisquare]=1;
					bisquare-=Math.pow(i,2);
				}
			}
			int maxdiff=(int)maxbisquare/(length-1);
			for(int difference=1; difference<=maxdiff; difference++){
				int maxstart=maxbisquare-difference*(length-1);
				for(int start=0; start<=maxstart; start++){
					int x=0;
					for(int i=0; i<=length-1; i++){
						if(bisquares[start+difference*i]==0){
							x=1;
							break;
						}
					}
					if(x==0){
						int[] answer=new int[2];
						answer[0]=start;
						answer[1]=difference;
						answers.add(answer);
					}
				}
			}
			if(answers.size()==0){
				writer.write("NONE\n");
			}else{
				for(int[] answer: answers){
					writer.write(answer[0]+" "+answer[1]+"\n");
					System.out.println(Arrays.toString(answer));
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
