package algo_0610;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BOJ14500_테트로미노 {
	static int N;
	static int M;
	static int[][]map;
	static boolean[][]visited;
	static int[]dr = {-1,0,1,0};
	static int[]dc = {0,1,0,-1};
	static int max = Integer.MIN_VALUE;
	
	// dfs 메소드 
	static void dfs(int r, int c, int depth, int sum) {
		// 깊이가 4가 되면 sum 중 최댓값으로 갱신하고 return 
		if(depth == 4) {
			if(max <sum) {
				max = sum;
			}
			return;
		}
		
		// 사방 탐색하면서 dfs 재귀
		for(int d = 0; d <4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 방문했거나 범위를 벗어나면 continue
			if(nr < 0 || nr >= N || nc < 0 || nc >=M) continue;
			if(visited[nr][nc]) continue;
			
			// ㅏ.ㅓ.ㅗ.ㅜ 인 경우 ( 탐색 깊이가 2일때(두번째 칸) 현 위치에서 다시 한 번 dfs )
			if(depth == 2) {
                visited[nr][nc] = true;
                dfs(r, c, depth+1, sum + map[nr][nc]);
                visited[nr][nc] = false;
            }
			
			
			visited[nr][nc] = true;
			dfs(nr, nc, depth+1, sum+map[nr][nc]);
			visited[nr][nc] = false;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i <N; i++) {
			for(int j = 0; j <M; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력 끝
		// 회전도 되고, 대칭도 되니까 모습을 정해놓고 끼워맞추는 건 너무 비효율적,,,
		
		for(int r = 0; r <N; r++) {
			for(int c = 0; c <M; c++) {
				// 백 트래킹 
				/**
				 * visited[r][c] = true -> 재귀 호출 ->  visited[r][c] = false 이유:
				 * DFS(깊이 우선 탐색)에서 백트래킹(backtracking)을 하기 위해서.
				 * 백트래킹은 현재 경로에서 가능한 모든 후보를 탐색하고, 
				 * 탐색을 완료한 후에는 이전 상태로 되돌아가서 다른 후보들을 탐색할 수 있도록 하는 방법.
				 *
				 * 현재 위치에서 모든 가능한 경로를 탐색한 후, 이전 위치로 돌아가기 전에 방문 표시를 제거
				 * 이를 통해 다른 경로를 탐색할 때 이 위치를 다시 방문할 수 있게 됨.
				 * 즉, 이전 상태로 돌아가서 새로운 경로를 탐색할 수 있도록 함.
				 * */
				
				visited[r][c] = true;
				dfs(r,c,1,map[r][c]);
				visited[r][c] = false;
				
			}
		}
		
		System.out.println(max);
		
	}//main
}
