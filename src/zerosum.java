/*
ID: jonxu101
LANG: JAVA
TASK: zerosum
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class zerosum {
	public static int sum=0;
	public static int num;
	public static void solve(int index, BufferedWriter writer, int[] currentoperations){
		if(index==num){
			sum=0;
			try {
				if(sumup(currentoperations)==0){
					writer.write("1");
//					System.out.print(1);
					int x=2;
					for(int i=1; i<=num-1; i++){
						int n=currentoperations[i];
						if(n==0){
							writer.write(" ");
//							System.out.print(" ");
						}else if(n==1){
							writer.write("+");
//							System.out.print("+");
						}else{
							writer.write("-");
//							System.out.print("-");
						}
						writer.write(""+x);
//						System.out.print(x);
						x++;
					}
					writer.write("\n");
//					System.out.println("");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		for(int operation=0; operation<=2; operation++){
			currentoperations[index]=operation;
			solve(index+1,writer, currentoperations);
		}
		return;
	}
	public static int sumup(int[] operations){
		int blanklength=0;
		for(int i=0; i<=num-1; i++){
			if(operations[i]==0){
				blanklength++;
				if(blanklength>=2){
					sum=1;
					break;
				}
				if(operations[i-1]==2){
					sum-=(i*9+i+1);
				}else{
					sum+=(i*9+i+1);
				}
			}else if(operations[i]==1){
				blanklength=0;
				sum+=i+1;
			}else{
				blanklength=0;
				sum-=i+1;
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("zerosum.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("zerosum.out"));
			num=Integer.parseInt(reader.readLine());
			int[] currentoperations=new int[num];
			currentoperations[0]=1;
			solve(1,writer, currentoperations);
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
