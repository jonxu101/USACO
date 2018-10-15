
/*
ID: jonxu101
LANG: JAVA
TASK: namenum
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

public class namenum {
	static int globallength = 0;
	static int size = 0;
	static ArrayList<String> fnamelist = new ArrayList<String>();

	public static int numletterconvert(char letter) {
		switch (letter) {
		case 'C':
		case 'B':
		case 'A':
			return 2;
		case 'D':
		case 'E':
		case 'F':
			return 3;
		case 'G':
		case 'H':
		case 'I':
			return 4;
		case 'J':
		case 'K':
		case 'L':
			return 5;
		case 'M':
		case 'N':
		case 'O':
			return 6;
		case 'P':
		case 'R':
		case 'S':
			return 7;
		case 'T':
		case 'U':
		case 'V':
			return 8;
		case 'W':
		case 'X':
		case 'Y':
			return 9;
		default:
			return 0;
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		try {
			long begin = System.currentTimeMillis();
			// System.out.println(Arrays.toString(permutations(4)));
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/namenum.in");
			File file2 = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/dict.txt");
			reader = new BufferedReader(new FileReader(file));
			reader2 = new BufferedReader(new FileReader(file2));
			long intname = Long.parseLong(reader.readLine());
			String[] namelist = new String[4617];
			ArrayList<String> finalnames = new ArrayList<String>();
			// String[] names = namenumletterconvert(intname);
			long[] numberdict = new long[4617];
			String line = reader2.readLine();
			int x = 0;
			while (line != null) {
				namelist[x] = line;
				line = reader2.readLine();
				x += 1;
			}
			int index=0;
			for(String a:namelist){
				int thing=0;
				long name=0;
				for(int i=0; i<a.length(); i++){
					char q=a.charAt(i);
					if(q=='Z'||q=='Q'){
						thing=1;
						break;
					}
//					System.out.println(name);
					name=10*name+numletterconvert(q);
				}
				if(thing==0){
					numberdict[index]=name;
				}else{
					numberdict[index]=0;
				}
				index++;
			}
			for(int i=0; i<4617; i++){
				if(numberdict[i]==intname){
					finalnames.add(namelist[i]);
				}
			}
			writer = new BufferedWriter(new FileWriter("namenum.out"));
			if (finalnames.size() == 0) {
				writer.write("NONE" + "\n");
			} else {
				for (String name : finalnames) {
					writer.write(name + "\n");
				}
			}
			long end = System.currentTimeMillis();
//			System.out.println(end1 - begin1);
//			System.out.println(end2 - begin2);
			System.out.println(end - begin);
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
