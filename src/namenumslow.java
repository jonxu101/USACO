
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

public class namenumslow {

	public static int compare(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		if (len1 <= len2) {
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) > s2.charAt(i)) {
					return 1;
				} else if (s1.charAt(i) < s2.charAt(i)) {
					return 0;
				}
			}
			if (len1 == len2) {
				return 2;
			} else{
				return 0;
			}
		}else{
			for (int i = 0; i < s2.length(); i++) {
				if (s1.charAt(i) > s2.charAt(i)) {
					return 1;
				} else if (s1.charAt(i) < s2.charAt(i)) {
					return 0;
				}
			}
			return 1;
		}
	}

	public static int namecheck(long l, long min, String[] names, String s1) {
		long avg = (l + min) >>> 1;
//		System.out.println(avg + " | " + min + " | " + max);
		if (min > l) {
			return 0;
		}
		int x = compare(names[(int)avg], s1);
		if (x == 1) {
			return namecheck(avg - 1, min, names, s1);
		} else if (x == 0) {
			return namecheck(l, avg + 1, names, s1);
		} else {
			return 1;
		}
	}

	public static String[] numletterconvert(long num) {
		switch ((int) num) {
		case 2:
			String[] poss = { "A", "B", "C" };
			return poss;
		case 3:
			String[] poss1 = { "D", "E", "F" };
			return poss1;
		case 4:
			String[] poss2 = { "G", "H", "I" };
			return poss2;
		case 5:
			String[] poss3 = { "J", "K", "L" };
			return poss3;
		case 6:
			String[] poss4 = { "M", "N", "O" };
			return poss4;
		case 7:
			String[] poss5 = { "P", "R", "S" };
			return poss5;
		case 8:
			String[] poss6 = { "T", "U", "V" };
			return poss6;
		case 9:
			String[] poss7 = { "W", "X", "Y" };
			return poss7;
		default:
			return null;
		}
	}

	public static int digits(long n) {
		if (n < 1000000) {
			if (n < 1000) {
				if (n < 100) {
					if (n < 10) {
						return 1;
					} else {
						return 2;
					}
				} else {
					return 3;
				}
			} else {
				if (n < 100000) {
					if (n < 10000) {
						return 4;
					} else {
						return 5;
					}
				} else {
					return 6;
				}
			}
		} else {
			if (n < 10000000000L) {// 10
				if (n < 100000000) {// 8
					if (n < 10000000) {
						return 7;
					} else {
						return 8;
					}
				} else {
					if (n < 1000000000) {
						return 9;
					} else {
						return 10;
					}
				}
			} else {
				if (n < 100000000000L) {// 11
					return 11;
				} else {
					return 12;
				}
			}
		}
	}

	public static String[] combo(String[][] letters, int counter) {
		String[] names = new String[(int) power(3, counter)];
		Arrays.fill(names, "");
		// System.out.println(Arrays.deepToString(letters));
		if (counter == 1) {
			// System.out.println(Arrays.deepToString(names));
			StringBuilder sb = new StringBuilder("");
			names[0] = sb.append(letters[0][0]).toString();
			StringBuilder sb1 = new StringBuilder("");
			names[1] = sb1.append(letters[0][1]).toString();
			StringBuilder sb2 = new StringBuilder("");
			names[2] = sb2.append(letters[0][2]).toString();
			// System.out.println(Arrays.deepToString(names));
			return names;
		} else {
			int x = 0;
			int i = counter - 1;
			for (String a : combo(letters, i)) {
				StringBuilder sb = new StringBuilder(a);
				names[x] = sb.append(letters[i][0]).toString();
				StringBuilder sb1 = new StringBuilder(a);
				names[x + 1] = sb1.append(letters[i][1]).toString();
				StringBuilder sb2 = new StringBuilder(a);
				names[x + 2] = sb2.append(letters[i][2]).toString();
				x = x + 3;
			}
			// System.out.println(Arrays.deepToString(names));
			return names;
		}
	}

	public static int mod(long num, int d) {
		return (int) (num - d * ((int) (num / d)));
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

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		try {
//			long begin = System.currentTimeMillis();
			// System.out.println(Arrays.toString(permutations(4)));
			File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/namenum.in");
			File file2 = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/dict.txt");
			reader = new BufferedReader(new FileReader(file));
			reader2 = new BufferedReader(new FileReader(file2));
			String intname = reader.readLine();
			String[] namelist = new String[4617];
			ArrayList<String> finalnames = new ArrayList<String>();
			// String[] names = namenumletterconvert(intname);
			int len = intname.length();
			String[][] letters = new String[len][3];
			for (int i = 0; i < len; i++) {// gets all letters possible
				letters[i] = numletterconvert((intname.charAt(i) - 48));
			}
			// System.out.println(Arrays.deepToString(letters));
//			long begin2 = System.currentTimeMillis();
			String[] names = combo(letters, intname.length());
//			long end2 = System.currentTimeMillis();
			String line = reader2.readLine();
			int x = 0;
			while (line != null) {
				namelist[x] = line;
				line = reader2.readLine();
				x += 1;
			}

			// System.out.println(namecheck("GREG","GREG"));
//			long begin1 = System.currentTimeMillis();
			for (String name : namelist) {
				if (namecheck(power(3,len), 0, names, name) == 1) {
					finalnames.add(name);
				}
			}
//			long end1 = System.currentTimeMillis();

			// System.out.println(Arrays.toString(finalnames.toArray()));
			writer = new BufferedWriter(new FileWriter("namenum.out"));
			if (finalnames.size() == 0) {
				writer.write("NONE" + "\n");
			} else {
				for (String name : finalnames) {
					writer.write(name + "\n");
				}
			}
//			long end = System.currentTimeMillis();
//			System.out.println(end1 - begin1);
//			System.out.println(end2 - begin2);
//			System.out.println(end - begin);
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
