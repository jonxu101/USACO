/*
ID: jonxu101
LANG: JAVA
TASK: stall4
*/
import java.io.*;
import java.util.*;
public class stall4 {
	public static int numCows;
	public static int numStalls;
	public static void main(String[] args) throws IOException{
		File file = new File("stall4.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		numCows=Integer.parseInt(st.nextToken());
		numStalls=Integer.parseInt(st.nextToken());
		int[][] graph=new int[numCows][numStalls];
		for(int i=0; i<numCows; i++){
			st=new StringTokenizer(reader.readLine());
			int numStall=Integer.parseInt(st.nextToken());
			for(int j=0; j<numStall; j++){
				graph[i][Integer.parseInt(st.nextToken())-1]=1;
			}
		}
//		for(int i=1; i<=numCows; i++){
//			graph[0][i]=1;
//		}
//		for(int i=1; i<=numStalls; i++){
//			graph[i+numCows][numCows+numStalls+1]=1;
//		}
//		System.out.println(flow(graph,0,numCows+numStalls+1));
		writer.println(flow(graph,0,numCows+numStalls+1));
		writer.close();
	}
	private static int flow(int[][] map, int start, int end) {
		int ans=0;
		int[] matchStall=new int[numStalls];
		Arrays.fill(matchStall, -1);
		for(int i=0; i<numCows; i++){
			boolean[] visited = new boolean[numStalls];
			if(dfs(map,i,matchStall,visited)){
				ans++;
			}
		}
		return ans;
	}
	private static boolean dfs(int[][] map, int u,int[] matchStall,boolean[] visited) {
		for (int v = 0; v < numStalls; v++) { 
            // If applicant u is interested  
            // in job v and v is not visited 
            if (map[u][v]==1 && !visited[v]) { 
                  
                // Mark v as visited 
                visited[v] = true;  
  
                // If job 'v' is not assigned to 
                // an applicant OR previously 
                // assigned applicant for job v (which 
                // is matchR[v]) has an alternate job available. 
                // Since v is marked as visited in the  
                // above line, matchR[v] in the following 
                // recursive call will not get job 'v' again 
                if (matchStall[v] < 0 || dfs(map, matchStall[v], matchStall, visited)) { 
                    matchStall[v] = u; 
                    return true; 
                } 
            } 
        } 
        return false; 
	}
}