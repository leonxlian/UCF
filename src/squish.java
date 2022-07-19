import java.io.*;
import java.util.*;
public class squish{
	public static PrintWriter out;
	public static void main(String[] args)throws IOException{
		Scanner sc=new Scanner();
		out=new PrintWriter(System.out);
		int t=sc.nextInt();
		double a[]=new double[100001];
		double pre=0;
		for(int i=1;i<100001;i++) {
			a[i]=(i+1+pre)/i;
			pre+=a[i];
		}
		while(t-->0) {
			int n=sc.nextInt();
			out.printf("%.7f\n", a[n]);
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