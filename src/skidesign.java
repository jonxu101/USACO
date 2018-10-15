/*
ID: jonxu101
LANG: JAVA		
TASK: skidesign
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
public class skidesign {
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("skidesign.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("skidesign.out"));
			int numberhills=Integer.parseInt(reader.readLine());
			int[] hills=new int[numberhills];
			for(int i=0; i<numberhills; i++){
				hills[i]=Integer.parseInt(reader.readLine());
			}
			Arrays.sort(hills);
			int[] finalheights=new int[2];
			int finalanswer=0;
			int dif=hills[numberhills-1]-hills[0];
			int diff=dif-17;
			for(int i=0; i<=dif-17; i++){
				int answer=0;
				finalheights[0]=hills[0]+i;
				finalheights[1]=hills[numberhills-1]-(diff-i);
//				System.out.println(Arrays.toString(finalheights));
				int index=0;
				while(hills[index]<finalheights[0]){
					answer+=Math.pow(finalheights[0]-hills[index], 2);
					index++;
				}
				index=numberhills-1;
				while(hills[index]>finalheights[1]){
					answer+=Math.pow(hills[index]-finalheights[1], 2);
					index--;
				}
				if(answer<finalanswer||finalanswer==0){
					finalanswer=answer;
				}
			}
			writer.write(finalanswer+"\n");
//			System.out.println(finalanswer);
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
