/*
ID: jonxu101
LANG: JAVA
TASK: nocows
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class nocows {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("nocows.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("nocows.out"));
			String[] s=reader.readLine().split(" ");
			int nodes=Integer.parseInt(s[0]);
			int height=Integer.parseInt(s[1]);
			long[][] answer=new long[201][101];
			for(int h=1; h<=height; h++){
				answer[1][h]=1;
			}
			for(int n=3; n<=nodes; n+=2){
				for(int h=2; h<=height; h++){
					for(int x=1; x<=n-2; x+=2){
						answer[n][h]+=(answer[x][h-1]*answer[n-x-1][h-1]);
						answer[n][h]%=9901;
					}
				}
			}
			writer.write((answer[nodes][height]-answer[nodes][height-1]+9901)%9901+"\n");
//			System.out.println((answer[nodes][height]-answer[nodes][height-1]+9901)%9901);
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
