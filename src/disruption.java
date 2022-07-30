import java.io.*;
import java.util.*;
public class disruption {
	public static PrintWriter out;
	static ArrayList<ArrayList<Integer>>al;
	static ArrayList<TreeSet<Pair>>set;
	static int parent[];
	static long ans[];
	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("disrupt.in"));
	    //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("disrupt.out")));
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    out = new PrintWriter(System.out);
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int n=Integer.parseInt(st.nextToken());
	    int m=Integer.parseInt(st.nextToken());
	    Edge[] e=new Edge[n-1];
	    al=new ArrayList<>();
	    set=new ArrayList<>();
	    for(int i=0;i<n;i++) {
	    	al.add(new ArrayList<Integer>());
	    	set.add(new TreeSet<Pair>());
	    }
	    for(int i=0;i<n-1;i++) {
	    	st=new StringTokenizer(br.readLine());
	    	int a=Integer.parseInt(st.nextToken())-1;
	    	int b=Integer.parseInt(st.nextToken())-1;
	    	e[i]=new Edge(a, b);
	    	al.get(b).add(a);
	    	al.get(a).add(b);
	    }
	    ans=new long[n];
	    for(int i=0;i<m;i++) {
	    	st=new StringTokenizer(br.readLine());
	    	int a=Integer.parseInt(st.nextToken())-1;
	    	int b=Integer.parseInt(st.nextToken())-1;
	    	int r=Integer.parseInt(st.nextToken());
	    	Pair p=new Pair(r, i);
	    	set.get(a).add(p);//id with its weight
	    	set.get(b).add(p);
	    }
	    parent=new int[n];
	    dfs(0, -1);
	    for(int i=0;i<n-1;i++) {
	    	int a=e[i].a;
	    	int b=e[i].b;
	    	if(parent[a]==b) {//if parent is b
	    		out.println(ans[a]);
	    		//pw.println(ans[a]);
	    	}else {//if parent is a
	    		out.println(ans[b]);
	    		//pw.println(ans[b]);
	    	}
	    }
	    /*for(int i=0;i<n;i++) {
	    	for(Pair p:set.get(i)) {
	    		out.println(i+" "+p.val+" "+p.idx);
	    	}
	    }*/
	    for(int i:parent) {
	    	out.println(i);
	    }
	    out.close();
	    //pw.close();
	}
	static int dfs(int cur, int par) {
		parent[cur]=par;//get the parent of every node
		for(int next:al.get(cur)) {
			if(next!=par) {
				int size=dfs(next, cur);//return size of next
				if(size>set.get(cur).size()) {//swap if size of current is smaller
					TreeSet<Pair> temp=set.get(cur);
					set.set(cur, set.get(next));
					set.set(next, temp);
				}
				for(Pair p:set.get(next)) {//loop through smaller set
					if(set.get(cur).contains(p)) {//if both contains
						set.get(cur).remove(p);//remove because subtree it connects itself
					}else {
						set.get(cur).add(p);
					}
				}
			}
		}
		if(set.get(cur).size()==0) {
			ans[cur]=-1;
		}else {
			ans[cur]=set.get(cur).first().val;
		}
		return set.get(cur).size();
	}
	static class Pair implements Comparable<Pair>{
		int val; int idx;
		public Pair(int val, int idx) {
			this.val=val;this.idx=idx;
		}
		public int compareTo(Pair o) {
			return Integer.compare(val, o.val);
		}
	}
	static class Edge{
		int a; int b;
		public Edge(int a, int b) {
			this.a=a;this.b=b;
		}
	}
}
