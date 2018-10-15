/*
ID: jonxu101
LANG: JAVA
TASK: heritage
*/
import java.io.*;
import java.util.*;
public class heritage {
	public static void traverse(MyTree t, PrintWriter writer) throws IOException{
		if(t.getLeft()!=null){
			traverse(t.getLeft(), writer);
		}
		if(t.getRight()!=null){
			traverse(t.getRight(), writer);
		}
		
//		System.out.print(t.getName());
		writer.print(t.getName());
		return;
	}
	public static void construct(MyTree root, String i, String p){
		char a=p.charAt(0);
		root.setName(a);
		if(i.length()==1){
			return;
		}
		int j=0;
		int k=0;
		for(int q=0; q<i.length(); q++){
			indexes[(int)i.charAt(q)-65]=q;
		}
		while((int)i.charAt(k)!=(int)a){
			k++;
		}
		while(indexes[(int)i.charAt(j)-65]<k){
			j++;
		}
//		System.out.println("A:"+a);
		if(0!=j){
			root.ltree=new MyTree('0');
//			System.out.println("indexes:"+0+" "+j+","+1+" "+(indexes[(int)a-65]+1));
			construct(root.getLeft(),i.substring(0,j),p.substring(1,k+1));
		}
		if(p.length()!=j+1){
			root.rtree=new MyTree('0');
//			System.out.println("indexes:"+(j+1)+" "+p.length()+","+(k+1)+" "+p.length());
			construct(root.getRight(),i.substring(j+1,p.length()),p.substring(k+1,p.length()));
		}
		return;
	}
	public static int[] indexes=new int[26];
	public static void main(String[] args) throws IOException{
		
		File file = new File("heritage.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		String inorder=reader.readLine();
		String preorder=reader.readLine();
		String test="abc";
		MyTree tree=new MyTree('0');
//		System.out.println(inorder+" "+preorder);
		for(int i=0; i<inorder.length(); i++){
			indexes[(int)inorder.charAt(i)-65]=i;
		}
		construct(tree,inorder, preorder);
		traverse(tree,writer);
		writer.print("\n");
		writer.close();
		reader.close();
	}

}
class MyTree{
	public MyTree ltree;
	public MyTree rtree;
	private char name;
	public MyTree(char n){
		this.name=n;
	}
	public void addLeft(char n){
		this.ltree=new MyTree(n);
	}
	public void addRight(char n){
		this.rtree=new MyTree(n);
	}
	public MyTree getLeft(){
		return ltree;
	}
	public MyTree getRight(){
		return rtree;
	}
	public char getName(){
		return name;
	}
	public void setName(char a){
		this.name=a;
	}
}
