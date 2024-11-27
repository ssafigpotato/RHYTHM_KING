package boj_12869_뮤탈리스크;

import java.util.Scanner;

public class Main {
	
	static int[][] deltas = {{-9, -3, -1}, {-9, -1, -3}, {-3, -9, -1}, {-3, -1, -9}, {-1, -9, -3}, {-1, -3, -9}};
	static int[][][] dp;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] scv = new int [3];
		
		for(int i=0; i<n; i++) {
			scv[i] = sc.nextInt();
		}
		
		dp = new int [61][61][61];
		min = Integer.MAX_VALUE;
		
		dfs(scv, 0);
		System.out.println(min);
		
		sc.close();
	}
	
	public static void dfs(int[] scv, int cnt) {
		int s1 = scv[0];
		int s2 = scv[1];
		int s3 = scv[2];
		
		// 공격 횟수 최솟값보다 현재 공격 횟수가 같거나 클 경우 중단
		if(min <= cnt) return;
		
		// 이미 방문했는데 기존 공격 횟수가 더 작을 경우 중단
		if(dp[s1][s2][s3] != 0 && dp[s1][s2][s3] <= cnt) return;
		
		dp[s1][s2][s3] = cnt;
		
		// 모든 scv가 죽은 경우 최솟값 갱신 및 중단
		if(s1 == 0 && s2 == 0 && s3 == 0) {
			min = Math.min(min, cnt);
			return;
		}
		
		// 6가지 공격 패턴으로 현재 scv를 공격한 후 넘김
		for(int i=0; i<6; i++) {
			dfs(new int[] {Math.max(s1 + deltas[i][0], 0), Math.max(s2 + deltas[i][1], 0), Math.max(s3 + deltas[i][2], 0)}, cnt+1);
		}
	}

}
