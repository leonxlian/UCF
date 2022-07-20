/*
 * 50601
 */
import java.io.*;
import java.util.*;
public class dealing {
	public static PrintWriter out;
	static ArrayList<ArrayList<Integer>>connections;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			connections=new ArrayList<>();
			ArrayList<Integer>perm=new ArrayList<>();
			for(int i=0;i<k;i++) {
				connections.add(new ArrayList<Integer>());
			}
			for(int i=0;i<n;i++) {
				connections.get(i%k).add(i);
			}
			/*for(int i=0;i<n;i++) {
				for(int node:connections.get(i)) {
					out.println(i+" "+node);
				}
			}*/
			for(int i=0;i<connections.size();i++){
				Collections.reverse(connections.get(i));
				for(int j:connections.get(i)) {
					perm.add(j);
				}
			}
			/*for(int i:perm) {
				out.print(i+" ");
			}
			out.println();*/
			boolean v[]=new boolean[n];
			long ans=1;
			for(int i=0;i<n;i++) {
				if(v[i]) {
					continue;
				}
				int cur=i;
				long cycle=0;
				while(!v[cur]) {
					v[cur]=true;
					cur=perm.get(cur);
					cycle++;
				}
				ans=ans/gcd(ans, cycle)*cycle;
			}
			out.println(ans);
		}
		out.close();
	}
	public static long lcm(long a, long b) {
		return (long)(a*b/gcd(a, b));
	}
	public static long gcd(long a, long b) {
		while (b != 0) {
			a %= b;
			long temp = a;
			a = b;
			b = temp;
        }
        return a;
	}

}
