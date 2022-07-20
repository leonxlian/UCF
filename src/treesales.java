/*
1
14
ADD ROOT BILL
ADD BILL EVELYN
ADD BILL SARAH
SALE BILL 25
SALE EVELYN 75
SALE SARAH 10
QUERY BILL
ADD EVELYN MATT
ADD MATT ANYA
SALE ANYA 1000
QUERY MATT
QUERY EVELYN
QUERY BILL
QUERY SARAH
11
ADD ROOT MARILYN
ADD MARILYN GARY
ADD MARILYN REMY
ADD MARILYN BRIANNE
ADD MARILYN TAJ
SALE TAJ 10
SALE REMY 20
SALE BRIANNE 40
SALE MARILYN 30
QUERY GARY
QUERY MARILYN
 */
import java.io.*;
import java.util.*;
public class treesales{
	public static PrintWriter out;
	static int enter[];
	static int time;
	static int size[];
	static SegTree st;
	static ArrayList<ArrayList<Integer>>al;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		int counts=0;
		while(t-->0) {
			counts++;
			int q=sc.nextInt();
			HashMap<String, Integer>hm=new HashMap<>();
			al=new ArrayList<>();
			ArrayList<Integer>queryChoice=new ArrayList<>();//1=sale, 2=query
			ArrayList<Sale>sales=new ArrayList<>();
			ArrayList<String>queries=new ArrayList<>();
			int a[]=new int[q];
			for(int i=0;i<q;i++) {
				String s=sc.next();
				if(s.equals("ADD")) {
					String x=sc.next();
					String y=sc.next();
					if(x.equals("ROOT")) {
						hm.put(y, hm.size());
						al.add(new ArrayList<Integer>());
					}else {
						if(!hm.containsKey(x)) {
							hm.put(x, hm.size());
							al.add(new ArrayList<Integer>());
						}
						if(!hm.containsKey(y)) {
							hm.put(y, hm.size());
							al.add(new ArrayList<Integer>());
						}
						al.get(hm.get(x)).add(hm.get(y));
						a[hm.get(x)]++;
						al.get(hm.get(y)).add(hm.get(x));
						a[hm.get(y)]++;
					}
				}else if(s.equals("SALE")){
					String x=sc.next();
					int n=sc.nextInt();
					sales.add(new Sale(x, n));
					queryChoice.add(1);
				}else {
					String x=sc.next();
					queries.add(x);
					queryChoice.add(2);
				}
			}
			time=0;
			size=new int[al.size()];
			enter=new int[al.size()];
			/*int root=0;
			for(int i=0;i<q;i++) {
				if(a[i]==1) {
					root=i;
					break;
				}
			}*/
			//out.println(root);
			dfs(0, -1);
			st=new SegTree(al.size());
			int b[]=new int[al.size()];
			/*for(int i:b) {
				out.print(i+" ");
			}*/
			//out.println();
			/*for(int i=0;i<al.size();i++) {
			    b[enter[i]]=a[i];
			}
			st.build(b);*/
			/*for(int i:enter) {
				out.print(i+" ");
			}*/
			out.println("COMPANY"+" "+counts);
			for(int i:queryChoice) {
				//print();
				if(i==1) {
					st.increment(1, 0, st.size-1, enter[hm.get(sales.get(0).x)], 
							enter[hm.get(sales.get(0).x)], sales.get(0).val);
					sales.remove(0);
				}else if(i==2) {
					long ans=st.sum(1, 0, st.size-1, enter[hm.get(queries.get(0))], 
							enter[hm.get(queries.get(0))]+size[hm.get(queries.get(0))]-1);
					out.println(ans);
					queries.remove(0);
				}
			}
			/*for(int i=0;i<al.size();i++) {
				for(int j:al.get(i)) {
					out.println(i+" "+j);
				}
			}
			out.println(al.size());*/
			
		}
		out.close();
	}
	static void dfs(int cur, int par) {
		enter[cur]=time++;
		size[cur]=1;
		for(int next:al.get(cur)) {
			if(next!=par) {
				dfs(next, cur);
				size[cur]+=size[next];
			}
		}
	}
	static class Add{
		String x; String y;
		public Add(String x, String y) {
			this.x=x;this.y=y;
		}
	}
	static class Sale{
		String x;int val;
		public Sale(String x, int val) {
			this.x=x;this.val=val;
		}
	}
	static void print() {
		for(int i=1;i<=4;i++) {
			for(int j=1<<(i-1);j<1<<i;j++) {
				out.print(st.tree[j]+" ");
			}
			out.println();
		}
		for(int i=1;i<=4;i++) {
			for(int j=1<<(i-1);j<1<<i;j++) {
				out.print(st.lazy[j]+" ");
			}
			out.println();
		}
	}
	static class SegTree{
		int size;
		long tree[];
		long[] lazy;
		public SegTree(int n) {
			size=1;
			while(size<n) {
				size*=2;
			}
			lazy=new long[2*size];
			tree=new long[2*size];
		}
		public void build(int a[]) {
			for(int i=0;i<a.length;i++) {
				tree[size+i]=a[i];
			}
			for(int i=size-1;i>=1;i--) {
				tree[i]=tree[2*i]+tree[2*i+1];
			}
		}
		public long sum(int node, int node_low, int node_high, int query_low, int query_high) {
			if(query_low<=node_low&&node_high<=query_high) {
				return tree[node];
			}
			if(query_high<node_low||query_low>node_high) {
				return 0;
			}
			push(node, node_low, node_high);
			tree[node]=tree[2*node]+tree[2*node+1];
			int last_in_left=(node_low+node_high)/2;
			return sum(2*node, node_low, last_in_left, query_low, query_high)
					+sum(2*node+1, last_in_left+1, node_high, query_low, query_high);
		}
		//change
		public void setRecur(int node, int node_low, int node_high, int query_low, int query_high, int v) {
			if(query_low<=node_low&&node_high<=query_high) {
				tree[node]=v;
				return;
			}
			if(query_high<node_low||query_low>node_high) {
				return;
			}
			int last_in_left=(node_low+node_high)/2;
			setRecur(2*node, node_low, last_in_left, query_low, query_high, v);
			setRecur(2*node+1, last_in_left+1, node_high, query_low, query_high, v);
			tree[node]=tree[2*node]+tree[2*node+1];
		}
		public void push(int node, int node_low, int node_high) {
			if(lazy[node]>0) {
				int last_in_left=(node_low+node_high)/2;
				increment(2*node, node_low, last_in_left, node_low, node_high, lazy[node]);
				increment(2*node+1, last_in_left+1, node_high, node_low, node_high, lazy[node]);
			}
			lazy[node]=0;
		}
		public void increment(int node, int node_low, int node_high, int query_low, int query_high, long amount) {
			if(query_low<=node_low&&node_high<=query_high) {
				tree[node]+=amount*(node_high-node_low+1);
				lazy[node]+=amount;
				return;
			}
			if(query_low>node_high||node_low>query_high) {
				return;
			}
			push(node, node_low, node_high);
			int last_in_left=(node_low+node_high)/2;
			increment(2*node, node_low, last_in_left, query_low, query_high, amount);
			increment(2*node+1, last_in_left+1, node_high, query_low, query_high, amount);
			tree[node]=tree[2*node]+tree[2*node+1];
		}
	}
	public static class Scanner {
	    BufferedReader br;
	    StringTokenizer st;
	
	    public Scanner() {
	        br = new BufferedReader(new InputStreamReader(System.in));
	    }

	    String next() {
	        while (st == null || !st.hasMoreElements()) {
	            try {
	                st = new StringTokenizer(br.readLine());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return st.nextToken();
	    }
	
	    int nextInt() {
	        return Integer.parseInt(next());
	    }
	
	    long nextLong() {
	        return Long.parseLong(next());
	    }
	
	    double nextDouble() {
	        return Double.parseDouble(next());
	    }
	
	    String nextLine(){
	        String str = "";
	        try {
	            str = br.readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return str;
	    }
	}
}

