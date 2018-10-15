/*
ID: jonxu101
LANG: JAVA
TASK: concom
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
public class concom {
	public static int[][] owns=new int[101][101];
	public static boolean[][] controls=new boolean[101][101];
	public static void addcontroller(int i, int j){
		if(controls[i][j]){
			return;
		}
		controls[i][j]=true;	
		for(int k=0; k<=100; k++){
			owns[i][k]+=owns[j][k];
		}
		for(int k=0; k<=100; k++){
			if(controls[k][i]){
				addcontroller(k, j);
			}
		}
		for(int k=0; k<=100; k++){
			if(owns[i][k]>50){
				addcontroller(i,k);
			}
		}
		return;
	}
	public static void addowner(int i, int j, int p){
		for(int k=0; k<=100; k++){
			if(controls[k][i]){
				owns[k][j]+=p;
//				System.out.println(Arrays.deepToString(owns));
			}
		}
		for(int k=0; k<=100; k++){
			if(owns[k][j]>50){
				addcontroller(k,j);
			}
		}
		return;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("concom.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("concom.out"));
			int num=Integer.parseInt(reader.readLine());
			String[][] sdata=new String[num][3];
			for(int i=0; i<num; i++){
				sdata[i]=reader.readLine().split(" ");
			}
			int[][] data=new int[num][3];
			for(int k=1; k<=100; k++){
				controls[k][k]=true;
			}
			for(int i=0; i<num; i++){
				for(int j=0; j<3; j++){
					data[i][j]=Integer.parseInt(sdata[i][j]);
				}
			}
//			System.out.println(Arrays.deepToString(data));
			for(int[] d:data){
				addowner(d[0],d[1],d[2]);
//				System.out.println(Arrays.deepToString(owns));
			}
			for(int i=0; i<=100; i++){
				for(int j=1; j<=100; j++){
					if(i==j){
						continue;
					}
					if(controls[i][j]){
						writer.write(i+" "+j+"\n");
//						System.out.println(i+" "+j);
					}
				}
			}
//			System.out.println(owns[1][30]);
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
