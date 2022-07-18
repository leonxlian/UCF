import java.io.*;
import java.util.*;
public class relatives {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			int n=sc.nextInt();
			double cost[][]=new double[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					cost[i][j]=sc.nextDouble();
				}
			}
			double dp[][]=new double[(1<<n)][n];
			for(int mask=0;mask<(1<<n);mask++) {
				for(int j=0;j<n;j++) {
					dp[mask][j]=Double.MAX_VALUE;
				}
			}
			dp[1][0]=0;
			for(int mask=2;mask<(1<<n);mask++) {
				for(int j=0;j<n;j++) {
					if(((mask>>j)&1)==0) {//loop through what current node is
						continue;
					}
					int prev=mask-(1<<j);
					//dp[mask][j]=Double.MAX_VALUE;
					for(int k=0;k<n;k++) {
						if(((prev>>k)&1)==0){
							continue;
						}
						if(dp[prev][k]!=Double.MAX_VALUE) {
							double cur=dp[prev][k]+cost[k][j];
							dp[mask][j]=Math.min(dp[mask][j], cur);
						}
					}
				}
			}
			double ans=Double.MAX_VALUE;
			for(int i=1;i<n;i++) {
				if(dp[(1<<n)-1][i]!=Double.MAX_VALUE) {
					ans=Math.min(dp[(1<<n)-1][i]+cost[i][0], ans);
				}
			}
			out.printf("%.2f\n", ans);
		}
		out.close();
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
