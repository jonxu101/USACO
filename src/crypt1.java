/*
ID: jonxu101
LANG: JAVA
TASK: crypt1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class crypt1 {
	public static int numdigits(int num){
		if(num<100){
			if(num<10){
				return 1;
			}else{
				return 2;
			}
		}else{
			if(num<1000){
				return 3;
			}else{
				return 4;
			}
		}
	}
	public static int mod(int num, int d) {
		return num - d * ((int) (num / d));
	}
	public static long power(int n, int e) {
		{
			if (e == 0)
				return 1;
			if (e == 1)
				return n;
			if (e % 2 == 0)
				return power(n * n, e / 2); // even a=(a^2)^b/2
			else
				return n * power(n * n, e / 2); // odd a=a*(a^2)^b/2

		}
	}
	public static int[] getdigits(int num){
		int digits=numdigits(num);
		int[] numdigit=new int[digits];
		for(int e=0; e<digits; e++){
			int x=(int)(num/(power(10,e)));
			numdigit[digits-1-e]=mod(x,10);
		}
		return numdigit;
	}
	public static int[] combos(int[] digits,int length){
		int[] combos=new int[(int) power(digits.length,length)];
		if(length==0){
			return combos;
		}else{
			int index=0;
			for(int num:combos(digits, length-1)){
				for(int digit: digits){
					combos[index]=num*10+digit;
					index+=1;
				}
			}
			return combos;
		}
	}
	public static int checker(HashMap<Integer, Integer> digits, int number){
		int[] numdigits=getdigits(number);
		for(int digit:numdigits){
			if(digits.get(digit)==null){
				return 0;
			}
		}
		return 1;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/crypt1.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("crypt1.out"));
			int length=Integer.parseInt(reader.readLine());
			HashMap<Integer, Integer> digitshash = new HashMap<Integer, Integer>();
			int[] digits=new int[length];
			String[] intInString = reader.readLine().split(" ");
			for(int i=0; i<length; i++){
				digitshash.put(Integer.parseInt(intInString[i]),1);
				digits[i]=Integer.parseInt(intInString[i]);
			}
			Arrays.sort(digits);
			int[] threedigit=combos(digits,3);
			int[] twodigit=combos(digits,2);
			int x=0;
			int answers=0;
//			System.out.println(checker(digitshash,23469));
			for(int threedigitnum:threedigit){
				for(int twodigitnum: twodigit){
					int[] adigits=getdigits(twodigitnum);
//					System.out.println(Arrays.toString(adigits))
					int partialproducta=adigits[0]*threedigitnum;
					int partialproductb=adigits[1]*threedigitnum;
					if(numdigits(partialproducta)>3){
						break;
					}else if(numdigits(partialproductb)>3){
						continue;
					}else if(checker(digitshash, partialproducta)==0 ||checker(digitshash, partialproductb)==0){
						continue;
					}else{
						int product=partialproducta*10+partialproductb;
						if(numdigits(product)>4){
							x=1;
							break;
						}else{
							if(checker(digitshash, product)==1){
//								System.out.println(threedigitnum+" | "+twodigitnum+" | "+threedigitnum*twodigitnum+" | "+product);
								answers+=1;
							}
						}
					}
				}
				if(x==1){
					break;
				}
			}
//			System.out.println(answers);
			writer.write(answers+"\n");
//			System.out.println(answers);
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
