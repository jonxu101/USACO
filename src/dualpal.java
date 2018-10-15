/*
ID: jonxu101
LANG: JAVA
TASK: dualpal
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
public class dualpal {
	public static String baseconvert(int num, int base){
		int exp=0;
		while(num/Math.pow(base, exp)>=1){
			exp+=1;
		}
		exp-=1;
		String convnum="";
		while(exp>=0){
			int digit=0;
			while(num-(int)Math.pow(base,exp)>=0){
				digit+=1;
				num=num-(int)Math.pow(base,exp);
			}
			convnum=convnum+digit;
			exp-=1;
		}
		return convnum;
	}
	public static int palchecker(String num){
		int len = String.valueOf(num).length()-1;
		for(int n=0; n<=(int)((len+1)/2); n++){
			int d1=num.charAt(n);
			int d2=num.charAt(len-n);
			if(d1!=d2){
				return 0;
			}
		}
		return 1;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("dualpal.in");
			reader = new BufferedReader(new FileReader(file));
			String[] integersInString=new String[2];
			integersInString = reader.readLine().split(" ");
			int numbers=Integer.parseInt(integersInString[0]);
			int number=Integer.parseInt(integersInString[1]);
			int n=0;
			int[] answers=new int[numbers];
			while(n<numbers){
				number+=1;
				int counter=0;
				for(int base=2; base<=10; base++){
					if(number==11757){
						System.out.println(baseconvert(number, base));
					}
					
					counter=counter+palchecker(baseconvert(number, base));
					if(counter==2){
						answers[n]=number;
						n+=1;
						break;
					}
				}
			}
			writer = new BufferedWriter(new FileWriter("dualpal.out"));
			for(int c=0; c<numbers; c++){
				writer.write(answers[c]+"\n");
			}
			System.out.println(Arrays.toString(answers));
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
