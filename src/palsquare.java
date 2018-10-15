/*
ID: jonxu101
LANG: JAVA
TASK: palsquare
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class palsquare {
	public static String getDigit(int digit){
		switch(digit){
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		case 16:
			return "G";
		case 17:
			return "H";
		case 18:
			return "I";
		case 19:
			return "J";
		default:
			return "-1";
		}
	}
	public static long power(long n, int e) {
			if (e == 0)
				return 1;
			if (e == 1)
				return n;
			if (e % 2 == 0)
				return power(n * n, e / 2); // even a=(a^2)^b/2
			else
				return n * power(n * n, e / 2); // odd a=a*(a^2)^b/2

	}
	public static String baseconvert(long num, int base){
		int exp=0;
		while(num/power(base,exp)>=1){
			exp+=1;
		}
		exp-=1;
		String convnum="";
		while(exp>=0){
			int digit=0;
			while(num-(int)power(base,exp)>=0){
				digit+=1;
				num=num-(int)power(base,exp);
			}
			if(digit>=10){
				StringBuilder sb=new StringBuilder(convnum);
				convnum=sb.append(getDigit(digit)).toString();
				exp-=1;
			}else{
				StringBuilder sb=new StringBuilder(convnum);
				convnum=sb.append(Integer.toString(digit)).toString();
				exp-=1;
			}
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
			writer = new BufferedWriter(new FileWriter("palsquare.out"));
			File file = new File("palsquare.in");
			reader = new BufferedReader(new FileReader(file));
			int base=Integer.parseInt(reader.readLine());
			for(int num=1; num<301; num++){
				long num2=num*num;
				String cnum=baseconvert(num2,base);
				int n=palchecker(baseconvert(num2,base));
				if(n==1){
					writer.write(baseconvert(num,base)+" "+cnum+"\n");
				}
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
