/*
ID: jonxu101
LANG: JAVA
TASK: contact
*/
import java.io.*;
import java.util.*;
public class contact {

	public static void main(String[] args) throws IOException{
		File file = new File("contact.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		String line=reader.readLine();
		while(line!=null){
			sb.append(line);
			line=reader.readLine();
		}
		String sequence=sb.toString();
		String[] subseq=new String[b-a+1];
		sb=new StringBuilder();
		for(int i=0; i<a; i++){
			sb.append(sequence.charAt(i));
		}
		subseq[0]=sb.toString();
		HashMap<String,Integer> frequencies=new HashMap<String,Integer>();
		frequencies.put(subseq[0], 1);
		for(int i=a; i<sequence.length(); i++){
//			System.out.println(Arrays.toString(subseq));
			for(int j=subseq.length-1; j>=1; j--){
				if(subseq[j-1]==null){
					continue;
				}
				sb=new StringBuilder();
				sb.append(subseq[j-1]);
				sb.append(sequence.charAt(i));
				subseq[j]=sb.toString();
			}
			sb=new StringBuilder();
			for(int l=1; l<a; l++){
				sb.append(subseq[0].charAt(l));
			}
			sb.append(sequence.charAt(i));
			subseq[0]=sb.toString();
			for(String sub:subseq){
				if(sub==null){
					break;
				}
				if(frequencies.containsKey(sub)){
					int x=frequencies.get(sub);
					frequencies.put(sub, ++x);
				}else{
					frequencies.put(sub, 1);
				}
			}
		}
//		for(String s:frequencies.keySet()){
//			System.out.print(s+" ");
//		}
//		for(int value: frequencies.values()){
//			System.out.print(value+" ");
//			q++;
//		}
		LinkedHashMap<String,Integer> ordered=sortByValues(frequencies);
		int currentvalue=-1;
//		for(String s:ordered.keySet()){
//			System.out.print(s+" ");
//		}
//		System.out.println("");
//		for(int value: ordered.values()){
//			System.out.print(value+" ");
//		}
//		System.out.println("");
		int p=1;
		int f=1;
		for(String key: ordered.keySet()){
			if(currentvalue==-1){
				currentvalue=ordered.get(key);
				writer.print(ordered.get(key)+"\n"+key);
//				System.out.print(ordered.get(key)+"\n"+key);
				continue;
			}
			if(currentvalue==ordered.get(key)){
				if(f==6){
					writer.print("\n"+key);
//					System.out.print("\n"+key);
					f=0;
				}else{
					writer.print(" "+key);
//					System.out.print(" "+key);
				}
				f++;
			}else{
				p++;
				if(p==n+1){
					break;
				}
				currentvalue=ordered.get(key);
				writer.print("\n"+currentvalue+"\n"+key);
//				System.out.print("\n"+currentvalue+"\n"+key);
				f=1;
			}
			
		}
		writer.print("\n");
		writer.close();
	}
	private static LinkedHashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	            	String o1key=((String)((Map.Entry)o1).getKey());
	            	String o2key=((String)((Map.Entry)o2).getKey());
	            	int o1Length = ((String)((Map.Entry)o1).getKey()).length();
	            	int o2Length = ((String)((Map.Entry)o2).getKey()).length();
	            	int o1value = (Integer)((Map.Entry)o1).getValue();
	       			int o2value = (Integer)((Map.Entry)o2).getValue();
	            	if(o1value==o2value){
	            		if(o1Length==o2Length){
	            			return o1key.compareTo(o2key);
	            		}else{
	            			return ((Comparable)o1Length).compareTo(o2Length);
	            		}
	       			}else{
	       				return (((Comparable) o2value).compareTo(o1value));
	       			}
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       LinkedHashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	}

}
