/*
ID: jonxu101
LANG: JAVA
TASK: fence6
*/
import java.io.*;
import java.util.*;
public class fence6 {
	public static void main(String[] args) throws IOException{
		File file = new File("fence6.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int num=Integer.parseInt(st.nextToken());
		ArrayList<Node> connections=new ArrayList<Node>();
		boolean[][] connected=new boolean[num][num];
		int[] lengths=new int[num];
		int[][] nodeindexes=new int[num][2];
		for(int i=0; i<num; i++){
			Arrays.fill(nodeindexes[i],-1);
		}
		int nodeindex=0;
		for(int i=0; i<num; i++){
			st=new StringTokenizer(reader.readLine());
			int currentedge=Integer.parseInt(st.nextToken())-1;
			int length=Integer.parseInt(st.nextToken());
			lengths[currentedge]=length;
			int linetwo=Integer.parseInt(st.nextToken());
			int linethree=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(reader.readLine());
			boolean newnode=true;
			int[] edges=new int[linetwo+1];
			for(int j=0; j<linetwo; j++){
				edges[j]=Integer.parseInt(st.nextToken())-1;
				if(!connected[edges[j]][currentedge]){
					connected[edges[j]][currentedge]=true;
					connected[currentedge][edges[j]]=true;
				}else{
					newnode=false;
				}
			}
			edges[linetwo]=currentedge;
			if(newnode){
				Node node=new Node(edges);
				connections.add(node);
				for(int edge:edges){
					if(nodeindexes[edge][0]==-1){
						nodeindexes[edge][0]=nodeindex;
					}else if(nodeindexes[edge][1]==-1){
						nodeindexes[edge][1]=nodeindex;
					}
				}
				nodeindex++;
			}
			st=new StringTokenizer(reader.readLine());
			newnode=true;
			edges=new int[linethree+1];
			for(int j=0; j<linethree; j++){
				edges[j]=Integer.parseInt(st.nextToken())-1;
				if(!connected[edges[j]][currentedge]){
					connected[edges[j]][currentedge]=true;
					connected[currentedge][edges[j]]=true;
				}else{
					newnode=false;
				}
			}
			edges[linethree]=currentedge;
			if(newnode){
				Node node=new Node(edges);
				connections.add(nodeindex,node);
				for(int edge:edges){
					if(nodeindexes[edge][0]==-1){
						nodeindexes[edge][0]=nodeindex;
					}else if(nodeindexes[edge][1]==-1){
						nodeindexes[edge][1]=nodeindex;
					}
				}
				nodeindex++;
			}
		}
		int min=Integer.MAX_VALUE;
		for(int i=0; i<num; i++){
			LinkedList<Integer> queue=new LinkedList<Integer>();
			queue.add(nodeindexes[i][0]);
			int[] distance=new int[connections.size()];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[nodeindexes[i][0]]=0;
			while(!queue.isEmpty()){
				int current=queue.remove();
				for(int edge: connections.get(current).getEdges()){
					if(edge==i){
						continue;
					}
					if(nodeindexes[edge][0]==current){
						if(distance[nodeindexes[edge][1]]>distance[nodeindexes[edge][0]]+lengths[edge]){
							distance[nodeindexes[edge][1]]=distance[nodeindexes[edge][0]]+lengths[edge];
							queue.add(nodeindexes[edge][1]);
						}
					}else{
						if(distance[nodeindexes[edge][0]]>distance[nodeindexes[edge][1]]+lengths[edge]){
							distance[nodeindexes[edge][0]]=distance[nodeindexes[edge][1]]+lengths[edge];
							queue.add(nodeindexes[edge][0]);
						}
					}
				}
			}
//			System.out.println(Arrays.toString(distance));
			if(distance[nodeindexes[i][1]]!=Integer.MAX_VALUE){
				min=Math.min(distance[nodeindexes[i][1]]+lengths[i],min);
			}
		}
//		System.out.println(min);
		writer.println(min);
		writer.close();
		reader.close();
	}

}
class Node{
	private int[] edges;
	public Node(int[] a){
		edges=a;
	}
	public int[] getEdges(){
		return edges;
	}
}
