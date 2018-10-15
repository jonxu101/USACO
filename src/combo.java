/*
ID: jonxu101
LANG: JAVA
TASK: combo
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class combo {
	public static int mod(int num, int d) {//custom mod method
		return num - d * ((int) (num / d));
	}
	public static void main(String[] args) {//runs in O(n) time
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("combo.in");
			reader = new BufferedReader(new FileReader(file));//reading stuff
			writer = new BufferedWriter(new FileWriter("combo.out"));//writing stuff
			int[] firstcombo=new int[3];//farmer's combination
			int max=Integer.parseInt(reader.readLine());//maximum number in combinations
			String line=reader.readLine();
			String[] intinline=line.split(" ");
			firstcombo[0]=Integer.parseInt(intinline[0]);//no point in for loop since theres
			firstcombo[1]=Integer.parseInt(intinline[1]);//only 3 terms
			firstcombo[2]=Integer.parseInt(intinline[2]);
			int[] secondcombo=new int[3];//master combination
			line=reader.readLine();
			intinline=line.split(" ");
			secondcombo[0]=Integer.parseInt(intinline[0]);
			secondcombo[1]=Integer.parseInt(intinline[1]);
			secondcombo[2]=Integer.parseInt(intinline[2]);
			int extra=1;//what we will subtract from the total
			for(int i=0; i<3; i++){//my ifs may be a little unnecessary but this is what i had
				int difference=mod(Math.abs(firstcombo[i]-secondcombo[i]),max);//calculates the minimum difference
				int diff=Math.abs(max-difference);//because if we compare 1,50 with a max 50, the difference should be 1
				if(diff<difference){//tests whichever one is smaller
					difference=diff;
				}
				difference=-difference;//we want the negative of it take for example 1,5, the difference is 4,but their
				extra=extra*(difference+5);//overlap is 1+2=5-2 =>1, which is -4+5(test other cases as well)
				if(Math.abs(difference+5)>max){//special cases like where the max is 1, and all cases are overlap
					extra=(int) Math.pow(max, 3);
				}else if(Math.abs(difference+5)>5){//if the difference is too big there is no overlap
					extra=0;
				}
			}
			if(max>=5){//there are at least 125 possible combinations
				writer.write(250-extra+"\n");//2*5^3-extra
			}else{//special case if there aren't 125 possible combinations
				int totalcombo=2*(int)Math.pow(max, 3);//calculates total combinations
				writer.write(totalcombo-extra+"\n");
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
