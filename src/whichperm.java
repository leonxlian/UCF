import java.io.*;
import java.util.*;
public class whichperm {
	static int moves[];
	static int solve[];
	static int n;
	static int vis[];
	public static PrintWriter out;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			n=sc.nextInt();
			moves=new int[n];
			for(int i=0;i<n-1;i++) {
				moves[i]=sc.nextInt();
			}
			vis=new int[n];
			solve=new int[n];
			Arrays.fill(solve, -1);
			boolean possible=false;
			for(int i=0;i<n;i++) {
				vis[i]=1;
				solve[0]=i;
				if(dfs(0)) {
					possible=true;
					break;
				}
				vis[i]=0;
				solve[i]=-1;
			}
			if(possible) {
				for(int i=0;i<n;i++) {
					if(i==0) {
						out.print(solve[i]);
					}else {
						out.print(" "+solve[i]);
					}
				}
				out.println();
			}else {
				out.println("impossible");
			}
		}
		out.close();
	}
	static boolean dfs(int move) {
		int val=solve[move];
		if(move==n-1) {
			return true;
		}
		int new1=val-moves[move];
		int new2=val+moves[move];
		if(new1>=0&&vis[new1]!=1) {
			vis[new1]=1;
			solve[move+1]=new1;
			if(dfs(move+1)) {
				return true;
			}
			vis[new1]=0;
			solve[move+1]=-1;
		}
		if(new2<n&&vis[new2]!=1) {
			vis[new2]=1;
			solve[move+1]=new2;
			if(dfs(move+1)) {
				return true;
			}
			vis[new2]=0;
			solve[move+1]=-1;
		}
		return false;
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
