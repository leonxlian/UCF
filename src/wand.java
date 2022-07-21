/*
1
7 16 3
0
0
0
1
1
4
1 0 NNY
2 6
1 2 NNY
2 2
2 0
1 3 YYN
2 3
1 1 YNY
1 5 YNY
1 4 YYN
1 6 YNN
2 5
2 6
1 1 YNY
2 5
2 6
 */
import java.io.*;
import java.util.*;
public class wand {
	public static PrintWriter out;
	static int time;
	static int enter[];
	static int size[];
	static ArrayList<ArrayList<Integer>>al;
	static SegTree st;
	static int k;
	static String ll;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			al=new ArrayList<>();
			int n=sc.nextInt();
			int q=sc.nextInt();
			k=sc.nextInt();
			size=new int[n];
			for(int i=0;i<n;i++) {
				al.add(new ArrayList<Integer>());
			}
			enter=new int[n];
			for(int i=1;i<n;i++) {
				int x=sc.nextInt();
				al.get(i).add(x);
				al.get(x).add(i);
			}
			dfs(0, -1);
			ll="";
			for(int i=0;i<k;i++) {
				ll+="0";
			}
			String a[]=new String[n];
			for(int i=0;i<n;i++) {
				a[i]=ll;
			}
			st=new SegTree(n);
			st.build(a);
			while(q-->0) {
				int x=sc.nextInt();
				if(x==1) {
					int par=sc.nextInt();
					String s=sc.next();
					String temp="";
					for(int i=0;i<k;i++) {
						if(s.charAt(i)=='Y') {
							temp+="1";
						}else {
							temp+="0";
						}
					}
					st.increment(1, 0, st.size-1, enter[par], enter[par]+size[par]-1, temp);
				}else {
					int par=sc.nextInt();
					st.down(1, 0, st.size-1, enter[par], enter[par]);
					out.println(st.tree[enter[par]]);
				}
			}
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
	static class SegTree{
		int size;
		String tree[];
		String[] lazy;
		public SegTree(int n) {
			size=1;
			while(size<n) {
				size*=2;
			}
			lazy=new String[2*size];
			tree=new String[2*size];
		}
		public void build(String a[]) {
			for(int i=0;i<2*size;i++) {
				tree[i]=ll;
				lazy[i]=ll;
			}
		}
		public void down(int node, int node_low, int node_high, int query_low, int query_high) {
			if(query_low<=node_low&&node_high<=query_high) {
				return;
			}
			if(query_high<node_low||query_low>node_high) {
				return;
			}
			push(node, node_low, node_high);
			int last_in_left=(node_low+node_high)/2;
			down(2*node, node_low, last_in_left, query_low, query_high);
			down(2*node+1, last_in_left+1, node_high, query_low, query_high);
		}
		//change
		/*public void setRecur(int node, int node_low, int node_high, int query_low, int query_high, int v) {
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
		}*/
		public void push(int node, int node_low, int node_high) {
			if(!lazy[node].equals(ll)) {
				int last_in_left=(node_low+node_high)/2;
				increment(2*node, node_low, last_in_left, node_low, node_high, lazy[node]);
				increment(2*node+1, last_in_left+1, node_high, node_low, node_high, lazy[node]);
			}
			lazy[node]=ll;
		}
		public void increment(int node, int node_low, int node_high, int query_low, int query_high, String amount) {
			if(query_low<=node_low&&node_high<=query_high) {
				String temp="";
				for(int i=0;i<k;i++) {
					temp+=(tree[node].charAt(i)-'0')^(amount.charAt(i)-'0');
				}
				tree[node]=temp;
				temp="";
				for(int i=0;i<k;i++) {
					temp+=(lazy[node].charAt(i)-'0')^(amount.charAt(i)-'0');
				}
				lazy[node]=temp;
				return;
			}
			if(query_low>node_high||node_low>query_high) {
				return;
			}
			push(node, node_low, node_high);
			int last_in_left=(node_low+node_high)/2;
			increment(2*node, node_low, last_in_left, query_low, query_high, amount);
			increment(2*node+1, last_in_left+1, node_high, query_low, query_high, amount);
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