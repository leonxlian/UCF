import java.io.*;
import java.util.*;

public class word {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		long fact[]=new long[13];
		for(int i=1;i<13;i++) {
			if(i==1) {
				fact[i]=1;
			}else {
				fact[i]=fact[i-1]*i;
			}
		}
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			System.out.println(fact[s.length()]);
		}
	}
}
