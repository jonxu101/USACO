/*
ID: jonxu101
LANG: JAVA
TASK: transform
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class transform {
	public static char[][] readfile(BufferedReader reader, int n) throws IOException {
		char[][] oarray = null;
		oarray = new char[n][n];
		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			for (int j = 0; j < n; j++) {
				oarray[i][j] = line.charAt(j);
			}
		}

		return oarray;
	}

	public static int compareArray(char[][] array1, char[][] array2) {
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array1.length; j++) {
				if (array1[i][j] != array2[i][j]) {
					return 0;
				}
			}
		}
		return 1;
	}

	public static char[][] rotate90(char[][] oarray) {
		//carray = oarray;
		int length = oarray.length;
		char[][] carray = new char[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				carray[j][length - 1 - i] = oarray[i][j];
			}
		}
		return carray;
	}
	public static char[][] rotate180(char[][] oarray) {
		//carray = oarray;
		int length = oarray.length;
		char[][] carray = new char[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				carray[length - 1 - i][length - 1 - j] = oarray[i][j];
			}
		}
		return carray;
	}
	public static char[][] rotate270(char[][] oarray) {
		//carray = oarray;
		int length = oarray.length;
		char[][] carray = new char[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				carray[length - 1 - j][i] = oarray[i][j];
			}
		}
		return carray;
	}
	public static char[][] reflection(char[][] oarray) {
		//carray = oarray;
		int length = oarray.length;
		char[][] carray = new char[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				carray[i][length-1-j] = oarray[i][j];
			}
		}
		return carray;
	}
	public static char[][] combo(char[][] oarray, int type) {
		char[][] carray=new char[oarray.length][oarray.length];
		if(type==1){
			carray=rotate90(reflection(oarray));
		} else if(type==2){
			carray=rotate180(reflection(oarray));
		} else {
			carray=rotate270(reflection(oarray));
		}
		return carray;
	}
	public static int transformationchecker(char[][] oarray, char[][] farray){
		if(compareArray(rotate90(oarray),farray)==1){
			return 1;
		}else if(compareArray(rotate180(oarray),farray)==1){
			return 2;
		}else if(compareArray(rotate270(oarray),farray)==1){
			return 3;
		}else if(compareArray(reflection(oarray),farray)==1){
			return 4;
		}else if(compareArray(combo(oarray,1),farray)==1 || compareArray(combo(oarray,2),farray)==1||compareArray(combo(oarray,3),farray)==1){
			return 5;
		}else if(compareArray(oarray,farray)==1){
			return 6;
		}else{
			return 7;
		}
	}

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("transform.in");
			reader = new BufferedReader(new FileReader(file));
			int n = Integer.parseInt(reader.readLine());
			char[][] oarray = null;
			char[][] farray = null;
			oarray = readfile(reader, n);
			farray = readfile(reader, n);
//			System.out.println(transformationchecker(oarray, farray));
			writer = new BufferedWriter(new FileWriter("transform.out"));
			writer.write(transformationchecker(oarray, farray)+"\n");
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
