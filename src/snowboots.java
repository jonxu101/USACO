/*
ID: jonxu101
LANG: JAVA
TASK: snowboots
*/
import java.io.*;
import java.util.*;
public class snowboots {

	public static void main(String[] args) throws IOException{
		BufferedReader reader = null;
		File file = new File("snowboots.in");
		reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int numtiles=Integer.parseInt(st.nextToken());
		int numboots=Integer.parseInt(st.nextToken());
		long[] tiles=new long[numtiles];
		long[][] boots=new long[numboots][2];
		st=new StringTokenizer(reader.readLine());
		for(int i=0; i<numtiles; i++){
			tiles[i]=Long.parseLong(st.nextToken());
		}
		for(int i=0; i<numboots; i++){
			st=new StringTokenizer(reader.readLine());
			boots[i][0]=Long.parseLong(st.nextToken());
			boots[i][1]=Long.parseLong(st.nextToken());
		}
		boolean[] traveledto=new boolean[numtiles];
		int  discarded=0;
		traveledto[0]=true;
		for(long[] boot:boots){
//			for(int i=1; i<=boot[1]; i++){
//				if(tiles[i]<=boot[0]){
//					traveledto[i]=true;
//				}
//			}
			for(int i=0; i<numtiles; i++){
				if(traveledto[i] && tiles[i]<=boot[0]){
					for(int j=1; j<=boot[1] && i+j<numtiles; j++){
						if(tiles[i+j]<=boot[0]){
							traveledto[i+j]=true;
						}
					}
				}
			}
//			System.out.println(Arrays.toString(traveledto));
			if(traveledto[numtiles-1]){
				break;
			}
			discarded++;
		}
//		System.out.println(discarded);
		writer.println(discarded);
		writer.close();
		
	}

}
