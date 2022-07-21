import java.io.*;
import java.util.*;

public class block {
	public static PrintWriter out;
	static int dx[]= {-1, 1, 0, 0};
	static int dy[]= {0, 0, -1, 1};
	static int n, m;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken());
			int sy=Integer.parseInt(st.nextToken());
			int ex=Integer.parseInt(st.nextToken());
			int ey=Integer.parseInt(st.nextToken());
			boolean isBlock[][]=new boolean[n][m+1];
			for(int i=0;i<r;i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				isBlock[x][y]=true;
			}
			boolean vis[][]=new boolean[n][m];
			vis[sx][sy]=true;
			ArrayDeque<Point>q=new ArrayDeque<>();
			int count=0;
			q.add(new Point(sx, sy));
			while(!q.isEmpty()) {
				if(vis[ex][ey]) {
					break;
				}
				ArrayDeque<Point>temp=new ArrayDeque<>();
				count++;
				while(q.size()>0) {
					Point cur=q.poll();
					for(int c=0;c<4;c++) {
						int nextX=cur.x+dx[c];
						int nextY=cur.y+dy[c];
						if(inBounds(nextX,nextY)&&!vis[nextX][nextY]&&!isBlock[nextX][nextY]) {
							temp.add(new Point(nextX, nextY));
							vis[nextX][nextY]=true;
						}
					}
				}
				q=temp;
			}
			if(vis[ex][ey]) {
				out.println(count);
			}else {
				out.println(":(");
			}
		}
		out.close();
	}
	public static boolean inBounds(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<m;
	}
	public static class Point{
		int x; int y;
		public Point(int x, int y) {
			this.x=x;this.y=y;
		}
	}
}
