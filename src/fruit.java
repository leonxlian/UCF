import java.io.*;
import java.util.*;
public class fruit {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			int n=sc.nextInt();
			int m=sc.nextInt();
			char grid[][]=new char[n][m];
			ArrayList<Pair>points=new ArrayList<>();
			points.add(new Pair(0, 0));
			for(int i=0;i<n;i++) {
				String s=sc.next();
				for(int j=0;j<m;j++) {
					grid[i][j]=s.charAt(j);
					if(grid[i][j]!='.') {
						points.add(new Pair(i, j));
					}
				}
			}
			points.add(new Pair(n-1, m-1));
			/*for(Pair i:points) {
				out.println(i.x+" "+i.y);
			}*/
			n=points.size();
			int cost[][]=new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					cost[i][j]=dist(points.get(i), points.get(j));
				}
			}
			int dp[][]=new int[(1<<n)][n];
			for(int mask=0;mask<(1<<n);mask++) {
				for(int j=0;j<n;j++) {
					dp[mask][j]=Integer.MAX_VALUE;
				}
			}
			dp[1][0]=0;
			for(int mask=2;mask<(1<<n);mask++) {
				for(int j=0;j<n;j++) {
					if(((mask>>j)&1)==0) {//loop through what current node is
						continue;
					}
					int prev=mask-(1<<j);
					//dp[mask][j]=Integer.MAX_VALUE;
					for(int k=0;k<n;k++) {
						if(((prev>>k)&1)==0){
							continue;
						}
						if(dp[prev][k]!=Integer.MAX_VALUE) {
							int cur=dp[prev][k]+cost[j][k];
							dp[mask][j]=Math.min(dp[mask][j], cur);
						}
					}
				}
			}
			//int ans=Integer.MAX_VALUE;
			out.println(dp[(1<<n)-1][n-1]);
		}
		out.close();
	}
	public static int dist(Pair a, Pair b) {
		/*if(a.x==0&&a.y==0||b.x==0&&b.y==0) {
			return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
		}*/
		return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
	}
	static class Pair{
		int x; int y;
		public Pair(int x, int y) {
			this.x=x;this.y=y;
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
