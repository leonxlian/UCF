import java.io.*;
import java.util.*;

public class cheer {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int a[]=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				a[i]=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(a);
			for(int i=0;i<n/2;i++) {
				int temp=a[i];
				a[i]=a[n-i-1];
				a[n-i-1]=temp;
			}
			long max=a[0];
			int last=a[0];
			for(int i=1;i<n;i++) {
				if(last-a[i]>=2) {
					max+=a[i];
					last=a[i];
				}
			}
			System.out.println(max);
		}
	}
}
