/*
ID: jonxu101
LANG: JAVA
TASK: frac1
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
import java.util.HashMap;
public class frac1 {
	public static ArrayList<Fraction> fractions=new ArrayList<Fraction>();	
	public static int addinto(Fraction frac, int start){
//		System.out.println("");
		for(Fraction f:fractions){
//			System.out.print(f.toString()+" | ");
		}
		if(fractions.size()==0){
			fractions.add(frac);
			return 0;
		}
		if(frac.getNumerator()==1){
			fractions.add(0,frac);
		}
		if(frac.getNumerator()+1==frac.getDenominator()){
			fractions.add(fractions.size(),frac);
			return 0;
		}
		for(int i=start; i<fractions.size(); i++){
			if(frac.compare(fractions.get(i))==0){
				return i+1;
			}
			if(frac.compare(fractions.get(i))==-1){
				fractions.add(i, frac);
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("frac1.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("frac1.out"));
			int max=Integer.parseInt(reader.readLine());
			for(int d=2; d<=max; d++){
				int index=0;
				for(int n=1; n<d; n++){
					Fraction frac=new Fraction(n,d);
					index=addinto(frac, index);
				}
			}
			Fraction zero=new Fraction(0,1);
			fractions.add(0,zero);
			zero=new Fraction(1,1);
			fractions.add(zero);
			for(Fraction frac:fractions){
				writer.write(frac.toString()+"\n");
				System.out.println(frac.toString());
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
class Fraction{
	private int numerator, denominator;
	public Fraction(int num, int den){
		numerator=num;
		denominator=den;
	}
	public int getNumerator(){
		return numerator;
	}
	public int getDenominator(){
		return denominator;
	}
	public void reduce(){
		int g=gcd(numerator, denominator);
		numerator=numerator/g;
		denominator=denominator/g;
	}
	public int compare(Fraction b){
		this.reduce();
		b.reduce();
		if(this.getNumerator()==b.getNumerator() && this.getDenominator()==b.getDenominator()){
			return 0;
		}else if(this.getNumerator()*b.getDenominator()<this.getDenominator()*b.getNumerator()){
			return -1;
		}else{
			return 1;
		}
	}
	public int gcd(int a, int b){
		if(b%a==0){
			return a;
		}
		if(a%b==0){
			return b;
		}
		int l;
		if(a<b){
			l=(int)a/2;
		}else{
			l=(int)b/2;
		}
		for(int factor=l; factor>1; factor--){
			if(a%factor==0 && b%factor==0){
				return factor;
			}
		}
		return 1;
	}
	public String toString(){
		String frac=numerator+"/"+denominator;
		return frac;
	}
}
