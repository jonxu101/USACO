/*
ID: jonxu101
LANG: JAVA
TASK: ditch
*/
import java.io.*;
import java.util.*;
public class ditch {

	public static void main(String[] args) throws IOException{
		File file = new File("ditch.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
		String line=reader.readLine();
		StringTokenizer st=new StringTokenizer(line);
		int pathNumber = Integer.parseInt(st.nextToken());
		int nodeNumber = Integer.parseInt(st.nextToken());
		int[][] map = new int[nodeNumber + 1][nodeNumber + 1];
		for (int i = 0; i < pathNumber; ++i) {
			line=reader.readLine();
			st=new StringTokenizer(line);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cap = Integer.parseInt(st.nextToken());
			map[from][to] += cap;
		}
		int start = 1;
		int end = nodeNumber;
//		System.out.println(edmondsKarp(map, start, end));
		writer.println(flow(map, start, end));
		writer.close();
		reader.close();
//		System.out.println(totalflow);
	}
	private static int flow(int[][] map, int start, int end) {
		int rst = 0;
		int[] prev = new int[map.length];
		while (bfs(map, prev, start, end)) {
			int min = 0x3f3f3f3f;
			for (int i = end; i != start; i = prev[i]) {
				min = Math.min(min, map[prev[i]][i]);
			}
			for (int i = end; i != start; i = prev[i]) {
				map[prev[i]][i] -= min;
				map[i][prev[i]] += min;
			}
			rst += min;
		}
		return rst;
	}
	private static boolean bfs(int[][] map, int[] prev, int start, int nodeNumber) {
		Arrays.fill(prev, -1);
		prev[start] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int first = queue.poll();
			for (int i = 1; i <= nodeNumber; ++i) {
				if (prev[i] == -1 && map[first][i] != 0) {
					queue.offer(i);
					prev[i] = first;
					if (i == nodeNumber) {
						return true;
					}
				}
			}
		}
		return false;
	}
}