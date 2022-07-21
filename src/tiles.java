import java.io.*;
import java.util.*;

public class tiles {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			String x=st.nextToken();
			String y=st.nextToken();
			int n=x.length();
			ArrayDeque<Character>xq=new ArrayDeque<>();
			for(int i=0;i<n;i++) {
				xq.add(x.charAt(i));
			}
			ArrayDeque<Character>yq=new ArrayDeque<>();
			for(int i=0;i<n;i++) {
				yq.add(y.charAt(i));
			}
			long ans=0;
			for(int i=0;i<n;i++) {
				if(xq.peek()==yq.peek()) {
					xq.poll();
					yq.poll();
				}else {
					ArrayDeque<Character>temp=new ArrayDeque<>();
					while(yq.peek()!=xq.peek()) {
						temp.add(yq.poll());
						ans++;
					}
					xq.poll();
					yq.poll();
					while(yq.size()>0) {
						temp.add(yq.pollFirst());
					}
					yq=temp;
				}
			}
			out.println(ans);
		}
		out.close();
	}
}
