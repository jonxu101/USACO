/*
ID: jonxu101
LANG: JAVA
TASK: barn1
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

public class barn1 {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/barn1.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("barn1.out"));
			String line=reader.readLine();
			String[] data=line.split(" ");
			int boards=Integer.parseInt(data[0]);
			int cows=Integer.parseInt(data[2]);
			if(boards-1>0 && boards<cows){
				int[] occupiedstalls=new int[cows];
				int[] differences=new int[cows-1];
				for(int i=0; i<cows; i++){//puts data of stalls cows are in
					occupiedstalls[i]=Integer.parseInt(reader.readLine());
				}
				Arrays.sort(occupiedstalls);
				int total=0;
				for(int i=0; i<cows-1; i++){//generates the differences
					differences[i]=occupiedstalls[i+1]-occupiedstalls[i];
				}
				total=occupiedstalls[cows-1]-occupiedstalls[0];
				Arrays.sort(differences);
				for(int k=cows-2; k>cows-2-boards+1; k--){//subtracts largest differences
					total-=differences[k];
				}
				total+=boards;
				writer.write(total+"\n");
			}else if(cows>boards){//special case if there is only 1 board
				int[] occupiedstalls=new int[cows];
				for(int i=0; i<cows; i++){
					occupiedstalls[i]=Integer.parseInt(reader.readLine());
				}
				Arrays.sort(occupiedstalls);
				int total=occupiedstalls[cows-1]-occupiedstalls[0]+1;
				writer.write(total+"\n");
			}else{//special case if there are the same or more boards than cows
				writer.write(cows+"\n");
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
