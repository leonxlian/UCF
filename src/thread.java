import java.io.*;
import java.util.*;

public class thread {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			double temp=x+y;
			double temp1=x+x;
			double temp2=y+y;
			double ans=Math.sqrt(temp1*temp1+temp2*temp2);
			System.out.println(ans);
		}
	}
}