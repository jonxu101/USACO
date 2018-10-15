/*
ID: jonxu101
LANG: JAVA
TASK: shuffle
*/
import java.io.*;
import java.util.*;
public class shuffle {
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("shuffle.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("shuffle.out"));
			int numcows=Integer.parseInt(reader.readLine());
			StringTokenizer s=new StringTokenizer(reader.readLine());
			int[] parent=new int[numcows];
			int[] goingto=new int[numcows];
			for(int i=0; i<numcows; i++){
				goingto[i]=Integer.parseInt(s.nextToken())-1;
				parent[goingto[i]]++;
			}
			int ans=numcows;
			LinkedList<Integer> queue=new LinkedList<Integer>();
			for(int i=0; i<numcows; i++){
				if(parent[i]==0){
					queue.add(i);
					ans--;
				}
			}
			while(!queue.isEmpty()){
				int current=queue.removeFirst();
				parent[goingto[current]]--;
				if(parent[goingto[current]]==0){
					queue.add(goingto[current]);
					ans--;
				}
			}
			writer.write(ans+"\n");
//			System.out.println(ans);
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
