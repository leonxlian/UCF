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

1
4 6 5
0
0
2
1 2 NNYYN
1 0 YNYNY
2 0
2 1
2 2
2 3
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
			for(int i=0;i<n-1;i++) {
				int x=sc.nextInt();
				//al.get(i).add(x);
				al.get(x).add(i+1);
			}
			time=0;
			dfs(0, -1);
			//int a[]=new int[n];
			st=new SegTree(n);
			//st.build(a);
			while(q-->0) {
				int x=sc.nextInt();
				if(x==1) {
					int par=sc.nextInt();
					String s=sc.next();
					int mask=0;
					for(int i=0;i<k;i++) {
						if(s.charAt(i)=='Y') {
							mask+=(1<<i);
						}
					}
					//long cur1=Integer.parseInt(temp);
					//out.println(cur+" "+cur1);
					st.increment(1, 0, st.size-1, enter[par], enter[par]+size[par]-1, mask);
				}else {
					int par=sc.nextInt();
					long mask=st.xor(1, 0, st.size-1, enter[par], enter[par]);
					for(int i=0;i<k;i++) {
						if(((mask>>i)&1)==1) {
							out.print('U');
						}else {
							out.print('D');
						}
					}
					out.println();
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
				tree[size+i]=0;
			}
			for(int i=size-1;i>=1;i--) {
				tree[i]=tree[2*i]^tree[2*i+1];
			}
		}
		public long xor(int node, int node_low, int node_high, int query_low, int query_high) {
			if(query_low<=node_low&&node_high<=query_high) {
				return tree[node];
			}
			if(query_high<node_low||query_low>node_high) {
				return 0;
			}
			push(node, node_low, node_high);
			tree[node]=tree[2*node]^tree[2*node+1];
			int last_in_left=(node_low+node_high)/2;
			return xor(2*node, node_low, last_in_left, query_low, query_high)^
					xor(2*node+1, last_in_left+1, node_high, query_low, query_high);
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
			if(lazy[node]!=0) {
				int last_in_left=(node_low+node_high)/2;
				increment(2*node, node_low, last_in_left, node_low, node_high, lazy[node]);
				increment(2*node+1, last_in_left+1, node_high, node_low, node_high, lazy[node]);
			}
			lazy[node]=0;
		}
		public void increment(int node, int node_low, int node_high, int query_low, int query_high, long amount) {
			if(query_low<=node_low&&node_high<=query_high) {
				tree[node]^=amount;
				lazy[node]^=amount;
				return;
			}
			if(query_low>node_high||node_low>query_high) {
				return;
			}
			push(node, node_low, node_high);
			int last_in_left=(node_low+node_high)/2;
			increment(2*node, node_low, last_in_left, query_low, query_high, amount);
			increment(2*node+1, last_in_left+1, node_high, query_low, query_high, amount);
			tree[node]=tree[2*node]^tree[2*node+1];
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