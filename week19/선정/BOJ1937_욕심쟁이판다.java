package daily_1002;

import java.util.Scanner;

public class BOJ1937_욕심쟁이판다 {
	// 욕심쟁이 판다는 어떤 지역에서 대나무를 먹기 시작한다. 
	// 그리고 그 곳의 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동을 한다.
	// 그리고 또 그곳에서 대나무를 먹는다. 
	// 그런데 단 조건이 있다. 이 판다는 매우 욕심이 많아서 대나무를 먹고 자리를 옮기면 
	// 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.
	// 상하좌우에 자기 보다 큰 것이 없을 때 멈춰
	// n의 값이 100만까지 들어갈 수 있기 때문에 100만x 100만의 배열을 dfs로 구하면 안됨
	// 그러므로 메모이제이션 필요! 
	static int N;
	static int[][] map;
	static int[]dr = {-1, 0, 1, 0};
	static int[]dc = {0, 1, 0, -1};
	static int [][]dp;
	static int max = Integer.MIN_VALUE;
	static int DFS( int r, int c) {
		// 0이 아니면 이미 방문한 길이라는 뜻이므로 return 시킴
		if (dp[r][c] != 0) {
            return dp[r][c];
        }
		// 방문한 곳에 일단 1넣어주기
		dp[r][c] = 1;
		for(int d = 0; d <4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<N) {
				if(map[nr][nc] > map[r][c]) {// 내 주변이 더 크다면 
					dp[r][c] = Math.max(dp[r][c], DFS(nr,nc)+1); 
//					해당 출발지점에 더 나아간 것과 비교해서 경로 길이를 넣어줌!
				}
			}
		}// for
		return dp[r][c];
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		dp = new int [N][N];
		for(int i = 0; i <N; i++) {
			for(int j = 0; j <N; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력 끝
		
		// 전체 맵을 돌면서 dfs 
		for(int i = 0; i <N; i++) {
			for(int j = 0; j <N; j++) {
				max = Math.max(DFS(i,j), max);
			}
		}
		System.out.println(max);
		
		
	}
}
