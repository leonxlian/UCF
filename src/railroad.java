import java.io.*;
import java.util.*;
public class railroad {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			int n=sc.nextInt();
			int m=sc.nextInt();
			int[] a=new int[n];
			for(int i=0;i<n;i++) {
				a[i]=sc.nextInt();
			}
			int[] b=new int[m];
			for(int i=0;i<m;i++) {
				b[i]=sc.nextInt();
			}
			int[] c=new int[n+m];
			for(int i=0;i<n+m;i++) {
				c[i]=sc.nextInt();
			}
			int MOD=(int) (1e9+7);
			long dp[][]=new long[n+1][m+1];//left pointer and right pointer
			dp[0][0]=1;
			for(int i=0;i<=n;i++) {
				for(int j=0;j<=m;j++) {
					if(i==0&&j==0) {
						continue;
					}
					if(i-1>=0&&a[i-1]==c[i+j-1]) {
						dp[i][j]=dp[i][j]+dp[i-1][j]%MOD;
					}
					if(j-1>=0&&b[j-1]==c[i+j-1]) {
						dp[i][j]=(dp[i][j]+dp[i][j-1])%MOD;
					}
				}
			}
			//out.println(dp[n][m]);
			out.println(dp[n][m]>0?"possible":"not possible");
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
