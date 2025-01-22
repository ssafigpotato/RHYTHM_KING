package boj_11660_구간합구하기5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] dp = new int [n+1][n+1];
		
		for(int r=1; r<=n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=1; c<=n; c++) {
				dp[r][c] = dp[r][c-1] + Integer.parseInt(st.nextToken());
			}
		}
		
		for(int tc=1; tc<=m; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			for(int x=x1; x<=x2; x++) {
				answer += (dp[x][y2] - dp[x][y1-1]);
			}
			
			System.out.println(answer);
		}
	}
}
