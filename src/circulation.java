/*1
5
1 2 2
1 3 5
1 4 3
4 5 1

1
8
1 2 3
1 3 6
3 4 6
3 5 2
5 6 4
5 8 5
5 7 3

//109
 
1
8
1 2 4
1 3 9
1 4 19
1 5 7
1 6 20
1 7 10
1 8 10*/
import java.io.*;
import java.util.*;
public class circulation {
	public static PrintWriter out;
	static ArrayList<ArrayList<Integer>>al;
	static int time=0;
	static int enter[];
	static long sum[];
	static int size[];
	static long ans;
	static SegTree st;
	static HashMap<String, Integer>hm;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			int n=sc.nextInt();
			al=new ArrayList<>();
			hm=new HashMap<>();
			for(int i=0;i<n;i++) {
				al.add(new ArrayList<>());
			}
			ans=0;
			enter=new int[n];
			sum=new long[n];
			size=new int[n];
			time=0;
			for(int i=0;i<n-1;i++) {
				int x=sc.nextInt()-1;
				int y=sc.nextInt()-1;
				int weight=sc.nextInt();
				al.get(x).add(y);
				al.get(y).add(x);
				hm.put(x+" "+y, weight);
				hm.put(y+" "+x, weight);
			}
			dfs(0, -1);
			st=new SegTree(n);
			long b[]=new long[n];
			for(int i=0;i<n;i++) {
				b[enter[i]]=sum[i];
			}
			st.build(b);
			dfs1(0, -1);
			out.println(ans);
		}
		out.close();
	}
	static void dfs1(int cur, int par) {
		ans+=st.max(1, 0, st.size-1, 0, st.size-1);
		for(int next:al.get(cur)) {
			if(next!=par) {
				st.increment(1, 0, st.size-1, 0, enter[next]-1, hm.get(cur+" "+next));
				st.increment(1, 0, st.size-1, enter[next], enter[next]+size[next]-1, -hm.get(cur+" "+next));
				st.increment(1, 0, st.size-1, enter[next]+size[next], st.size-1, hm.get(cur+" "+next));
				dfs1(next, cur);
				st.increment(1, 0, st.size-1, 0, enter[next]-1, -hm.get(cur+" "+next));
				st.increment(1, 0, st.size-1, enter[next], enter[next]+size[next]-1, hm.get(cur+" "+next));
				st.increment(1, 0, st.size-1, enter[next]+size[next], st.size-1, -hm.get(cur+" "+next));
			}
		}
	}
	static void dfs(int cur, int par) {
		enter[cur]=time++;
		size[cur]=1;
		if(par!=-1) {
			sum[cur]=sum[par]+hm.get(par+" "+cur);
		}
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
		public void build(long a[]) {
			for(int i=0;i<a.length;i++) {
				tree[size+i]=a[i];
			}
			for(int i=size-1;i>=1;i--) {
				tree[i]=Math.max(tree[2*i],tree[2*i+1]);
			}
		}
		public long max(int node, int node_low, int node_high, int query_low, int query_high) {
			if(query_low<=node_low&&node_high<=query_high) {
				return tree[node];
			}
			if(query_high<node_low||query_low>node_high) {
				return Long.MIN_VALUE;
			}
			push(node, node_low, node_high);
			tree[node]=Math.max(tree[2*node], tree[2*node+1]);
			int last_in_left=(node_low+node_high)/2;
			return (Math.max(max(2*node, node_low, last_in_left, query_low, query_high),
					max(2*node+1, last_in_left+1, node_high, query_low, query_high)));
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
				tree[node]+=amount;
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
			tree[node]=Math.max(tree[2*node], tree[2*node+1]);
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
