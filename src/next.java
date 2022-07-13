import java.io.*;
import java.util.*;

public class next {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			char[]ch=s.toCharArray();
			String ans="";
			if(ch[2]=='Z') {
				if(ch[1]=='Z') {
					if(ch[0]=='Z') {
						ans="AAA";
					}else {
						ans=(char)(ch[0]+1)+"AA";
					}
				}else {
					ans=(ch[0])+""+(char)(ch[1]+1)+""+'A';
				}
			}else {
				ans=ch[0]+""+ch[1]+""+(char)(ch[2]+1);
			}
			System.out.println(ans);
		}
	}
}