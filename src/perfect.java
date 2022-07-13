import java.io.*;
import java.util.*;
public class perfect {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			Pair a[]=new Pair[n+1];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				double x=Double.parseDouble(st.nextToken());
				double y=Double.parseDouble(st.nextToken());
				a[i]=new Pair(x, y);
			}
			a[n]=new Pair(0.0, 0.0);
			Arrays.sort(a);
			/*for(int i=0;i<n+1;i++) {
				System.out.println(a[i].x+" "+a[i].y);
			}*/
			double ans=0;
			for(int i=0;i<n;i++) {
				double diffX=a[i].x-a[i+1].x;
				double diffY=a[i].y-a[i+1].y;
				ans+=Math.sqrt(diffX*diffX+diffY*diffY);
			}
			System.out.println(ans);
		}
	}
	static class Pair implements Comparable<Pair>{
		double x;
		double y;
		public Pair(double x, double y) {
			this.x=x;this.y=y;
		}
		public int compareTo(Pair o) {
			if(x-o.x<0) {
				return 1;
			}else {
				return -1;
			}
		}
	}
	
}
