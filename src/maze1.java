/*
ID: jonxu101
LANG: JAVA
TASK: maze1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
public class maze1 {
	public static void update(int[] start){
		if(data[2*start[1]+1][2*start[0]+2].equals(" ")){
			if(start[0]+1<dim[0]){
				if(parent[start[1]][start[0]+1]==0){
					parent[start[1]][start[0]+1]=1;
					distance[start[1]][start[0]+1]=distance[start[1]][start[0]]+1;
				}else{
					if(distance[start[1]][start[0]+1]>distance[start[1]][start[0]]+1){
						distance[start[1]][start[0]+1]=distance[start[1]][start[0]]+1;
						parent[start[1]][start[0]+1]=1;
					}
				}
			}
		}
		if(data[2*start[1]+1][2*start[0]].equals(" ")){
			if(start[0]-1>=0){
				if(parent[start[1]][start[0]-1]==0){
					parent[start[1]][start[0]-1]=1;
					distance[start[1]][start[0]-1]=distance[start[1]][start[0]]+1;
				}else{
					if(distance[start[1]][start[0]-1]>distance[start[1]][start[0]]+1){
						distance[start[1]][start[0]-1]=distance[start[1]][start[0]]+1;
						parent[start[1]][start[0]-1]=1;
					}
				}
			}
		}
		if(data[2*start[1]+2][2*start[0]+1].equals(" ")){
			if(start[1]+1<dim[1]){
				if(parent[start[1]+1][start[0]]==0){
					parent[start[1]+1][start[0]]=1;
					distance[start[1]+1][start[0]]=distance[start[1]][start[0]]+1;
				}else{
					if(distance[start[1]+1][start[0]]>distance[start[1]][start[0]]+1){
						distance[start[1]+1][start[0]]=distance[start[1]][start[0]]+1;
						parent[start[1]+1][start[0]]=1;
					}
				}
			}
			
		}
		if(data[2*start[1]][2*start[0]+1].equals(" ")){
			if(start[1]-1>=0){
				if(parent[start[1]-1][start[0]]==0){
					parent[start[1]-1][start[0]]=1;
					distance[start[1]-1][start[0]]=distance[start[1]][start[0]]+1;
				}else{
					if(distance[start[1]-1][start[0]]>distance[start[1]][start[0]]+1){
						distance[start[1]-1][start[0]]=distance[start[1]][start[0]]+1;
						parent[start[1]-1][start[0]]=1;
					}
				}
			}
		}
		return;
	}
	public static int[] findstart(){
		for(int i=0; i<parent.length; i++){
			for(int j=0; j<parent[0].length; j++){
				if(parent[i][j]==1){
					int[] thing={j,i};
					parent[i][j]++;
					return thing;
				}
			}
		}
		int[] thing={-1,-1};
		return thing;
	}
	public static void solve(){
		int[] start=findstart();
//		System.out.println(Arrays.deepToString(data));
		while(start[0]!=-1){
//			System.out.println("start: "+Arrays.toString(start));
//			System.out.println("before: "+Arrays.deepToString(parent));
			update(start);
			start=findstart();
//			System.out.println("after: "+Arrays.deepToString(parent));
//			System.out.println(Arrays.deepToString(distance));
		}
		return;
	}
	public static void updatemins(){
		for(int i=0; i<currentmins.length; i++){
			for(int j=0; j<currentmins[0].length; j++){
				if(currentmins[i][j]>distance[i][j]){
					currentmins[i][j]=distance[i][j];
				}
			}
		}
	}
	public static int[][] distance=null;
	public static int[][] parent=null;
	public static int[][] currentmins=null;
	public static String[][] data=null;
	public static int[] dim=new int[2];
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("maze1.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("maze1.out"));
			String[] sdim=reader.readLine().split(" ");
			dim[0]=Integer.parseInt(sdim[0]);
			dim[1]=Integer.parseInt(sdim[1]);
			String line=reader.readLine();
			data=new String[2*dim[1]+1][2*dim[0]+1];
			int row=0;
			while(line!=null){
				String[] stuff=line.split("");
				data[row]=stuff;
				row++;
				line=reader.readLine();
			}
			parent=new int[dim[1]][dim[0]];
			distance=new int[dim[1]][dim[0]];
			currentmins=new int[dim[1]][dim[0]];
			int counter=0;
			for(int j=1; j<=2*dim[0]-1; j+=2){
				if(data[dim[1]*2][j].equals(" ")){
					parent[dim[1]-1][(j-1)/2]=1;
					distance[dim[1]-1][(j-1)/2]=1;
					solve();
					if(counter==0){
						currentmins=distance.clone();
						counter++;
					}else{
						updatemins();
					}
//					System.out.println(Arrays.deepToString(currentmins));
					parent=new int[dim[1]][dim[0]];
					distance=new int[dim[1]][dim[0]];
				}
				if(data[0][j].equals(" ")){
					parent[0][(j-1)/2]=1;
					distance[0][(j-1)/2]=1;
					solve();
					if(counter==0){
						currentmins=distance.clone();
						counter++;
					}else{
						updatemins();
					}
//					System.out.println(Arrays.deepToString(currentmins));
					parent=new int[dim[1]][dim[0]];
					distance=new int[dim[1]][dim[0]];
				}	
			}
			for(int i=1; i<=2*dim[1]-1; i+=2){
				if(data[i][0].equals(" ")){
					parent[(i-1)/2][0]=1;
					distance[(i-1)/2][0]=1;
					solve();
					if(counter==0){
						currentmins=distance.clone();
						counter++;
					}else{
						updatemins();
					}
//					System.out.println(Arrays.deepToString(currentmins));
					parent=new int[dim[1]][dim[0]];
					distance=new int[dim[1]][dim[0]];
				}
				if(data[i][2*dim[0]].equals(" ")){
					parent[(i-1)/2][dim[0]-1]=1;
					distance[(i-1)/2][dim[0]-1]=1;
					solve();
					if(counter==0){
						currentmins=distance.clone();
						counter++;
					}else{
						updatemins();
					}
//					System.out.println(Arrays.deepToString(currentmins));
					parent=new int[dim[1]][dim[0]];
					distance=new int[dim[1]][dim[0]];
				}
			}
			int max=0;
			for(int i=0; i<currentmins.length; i++){
				for(int j=0; j<currentmins[0].length; j++){
					if(max<currentmins[i][j]){
						max=currentmins[i][j];
					}
				}
			}
			writer.write(max+"\n");
//			System.out.println(max);
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
