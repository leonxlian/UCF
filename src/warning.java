/*
1
4
1 2 3 5
1 4
2 4
4 3
 */
import java.io.*;
import java.util.*;
public class warning{
	static long dp[][];
	public static PrintWriter out;
	static ArrayList<ArrayList<Integer>> al;
	static int size[];
	static int cost[];
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			int n=sc.nextInt();
			cost=new int[n];
			for(int i=0;i<n;i++){
				cost[i]=sc.nextInt();
			}
			al=new ArrayList<>();
			for(int i=0;i<n;i++) {
				al.add(new ArrayList<Integer>());
			}
			size=new int[n];
			for(int i=0;i<n-1;i++) {
				int x=sc.nextInt()-1;
				int y=sc.nextInt()-1;
				al.get(x).add(y);
				al.get(y).add(x);
			}
			dfs(0, -1);
			/*for(int i:size) {
				out.print(i+" ");
			}*/
			dp=new long[n][2];
			for(int i=0;i<n;i++) {
				dp[i][0]=Long.MAX_VALUE;
				dp[i][1]=Long.MAX_VALUE;
			}
			dp[0][0]=0;
			dp[0][1]=0;
			long one=dp(0, 0, -1);
			long two=dp(0, 1, -1);
			out.println(Math.min(one, two));
		}
		out.close();
	}
	static void dfs(int cur, int par) {
		size[cur]=1;
		for(int next:al.get(cur)) {
			if(next!=par) {
				dfs(next, cur);
				size[cur]+=size[next];
			}
		}
	}
	
	public static long dp(int vertex, int take, int par) {
		if(size[vertex]==1&&par!=-1) {
			return dp[vertex][take]=take*cost[vertex];
		}
		if(take==0) {//if i don't take now
			long res=0;
			for(int next:al.get(vertex)) {
				if(next!=par) {
					if(dp[next][1]!=Long.MAX_VALUE) {
						res+=dp[next][1];
					}else{
						dp[next][1]=dp(next, 1, vertex);
						res+=dp[next][1];
					}
				}
			}
			return dp[vertex][0]=res;
		}else {//if i take now
			long res=cost[vertex];
			for(int next:al.get(vertex)) {
				if(next!=par) {
					res+=Math.min(dp(next, 1, vertex), dp(next, 0, vertex));
				}
			}
			return dp[vertex][1]=res;
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