/*
ID: jonxu101
LANG: JAVA
TASK: comehome
*/
import java.io.*;
import java.util.*;
public class comehome {
	public static int getint(char s){
		if(s>=65 && s<=90){
			started[s-65]=1;
			return s-65;
		}else{
			return s-71;
		}
	}
	public static void update(int[][] connected, int start){
		visited[start]=true;
		if(distance[start]==-1){
			distance[start]=0;
		}
		for(int i=0; i<52; i++){
			if(connected[i][start]!=0){
				if(distance[i]==-1){
					distance[i]=distance[start]+connected[i][start];
					parent[i]=start;
				}else if(distance[start]+connected[i][start]<distance[i]){
					distance[i]=distance[start]+connected[i][start];
					parent[i]=start;
				}
			}
		}
	}
	public static int getnextstart(){
		int mindistance=-1;
		for(int i=0; i<52; i++){
			if(!visited[i] && distance[i]!=-1){
				if(mindistance==-1){
					mindistance=distance[i];
				}
				if(distance[i]<mindistance){
					mindistance=distance[i];
				}
			}
		}
//		System.out.println(mindistance);
		for(int i=0; i<52; i++){
			if(!visited[i] && distance[i]!=-1){
				if(distance[i]==mindistance){
					return i;
				}
			}
		}
		return -1;
	}
	public static int[] distance=new int[52];
	public static int[] parent=new int[52];
	public static boolean[] visited=new boolean[52];
	public static int[] started=new int[52];
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("comehome.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("comehome.out"));
			int numconnected=Integer.parseInt(reader.readLine());
			int[][] connected=new int[52][52];
			for(int i=0; i<numconnected; i++){
				StringTokenizer st=new StringTokenizer(reader.readLine());
				char x=st.nextToken().charAt(0);
				char y=st.nextToken().charAt(0);
				int d=Integer.parseInt(st.nextToken());
				if(connected[getint(x)][getint(y)]==0 || d<connected[getint(x)][getint(y)]){
					connected[getint(x)][getint(y)]=d;
					connected[getint(y)][getint(x)]=connected[getint(x)][getint(y)];
				}
			}
			Arrays.fill(distance,-1);
			int start=25;
			while(start!=-1){
//				System.out.println(start);
				update(connected,start);
//				System.out.println(Arrays.toString(distance));
//				System.out.println(distance[17]);
				if(distance[17]==111){
//					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				start=getnextstart();
			}
//			System.out.println(Arrays.toString(distance));
			started[25]=0;
			char ans='a';
			int min=-1;
			for(int i=0; i<26; i++){
				if(started[i]==1 && (min==-1 || distance[i]<min)){
					ans=(char) (i+65);
					min=distance[i];
				}
			}
			writer.write(ans+" "+min+"\n");
//			System.out.println(ans+" "+min);
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
