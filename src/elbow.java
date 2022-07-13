import java.io.*;
import java.util.*;
public class elbow {
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			double table=Double.parseDouble(st.nextToken());
			double b[]=new double[n];
			double c[]=new double[n];
			double min=Double.MAX_VALUE;
			double heightLow=1e18;
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				double width=Double.parseDouble(st.nextToken());
				b[i]=Double.parseDouble(st.nextToken());
				c[i]=Double.parseDouble(st.nextToken());
				heightLow=Math.min(heightLow, b[i]-c[i]);
				min=Math.min(min, b[i]);
				table-=width;
			}
			double low=heightLow;
			double high=min;
			for(int i=0;i<100;i++){
				double mid=(low+high)/2;
				//System.out.println(mid + " " + check(mid, b, c, table));
				if(check(mid, b, c, table)) {
					low=mid;
				}else {
					high=mid;
				}
				
				//if (mid < 1) break;
			}
			
			System.out.println(Arrays.toString(b) + "\n" + Arrays.toString(c));
			System.out.println(low);
		}
	}
	static boolean check(double mid, double height[], double arm[], double table) {
		double sum=0;
		for(int i=0;i<arm.length;i++) {
			double hyp=(arm[i]*arm[i]);
			double leg=(height[i]-mid)*(height[i]-mid);
			sum+=2*Math.sqrt(hyp-leg);
			System.out.println("-- " + hyp + " " + leg + " " + 2 * Math.sqrt(hyp - leg));
		}
		return sum<=table;
	}
}
