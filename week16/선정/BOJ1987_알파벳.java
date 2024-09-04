package daily_0903;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1987_알파벳 {
	static int R;
	static int C;
	static int [][]map;
	static int []dr = {-1,0,1,0};
	static int []dc = {0,1,0,-1};
	static int max = 1;
	static boolean[]visitedAlp; // 알파벳 방문 표시
	static int dfs (int r, int c, int len) {
		visitedAlp[map[r][c]] = true; // 현재 알파벳 방문 표시 
		max = Math.max(max, len);
		
		for(int d = 0; d <4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr <R && nc >= 0 && nc <C) {
				if(visitedAlp[map[nr][nc]] == false) {
					dfs(nr, nc, len+1);
					visitedAlp[map[nr][nc]] = false;
				}
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new int[R][C];
		visitedAlp = new boolean[26];
		
		for(int i = 0; i <R; i++) {
			String str = sc.next();
			for(int j = 0; j <C; j++) {
				 // 알파벳 문자를 0~25의 숫자로 변환하여 저장
				 // -'0'은 문자열이 숫자를 포함할 때 빼주는거임
				map[i][j] = str.charAt(j)-'A';
			}
		}
		
		int result = dfs(0,0,1);
		
		System.out.println(result);
		
		// 확인용
//		for(int i = 0; i <R; i++) {
//			for(int j = 0; j <C; j++) {
//				System.out.print(map[i][j]+" ");
//			}System.out.println();
//		}
		
	}
}