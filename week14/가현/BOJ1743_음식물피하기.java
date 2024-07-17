package foodwaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,K;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 통로의 세로 길이
        M = Integer.parseInt(st.nextToken()); // 통로의 가로 길이
        K = Integer.parseInt(st.nextToken()); // 음식물 쓰레기의 개수
		
		map = new int[N][M]; // 통로 생성
		visited = new boolean[N][M];
		
		for(int i =0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			// 입력되는 좌표는 index가 0부터 시작하는 것을 고려하지 않음
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
			
			// 음식물이 떨어진 곳에는 1, 없는 곳에는 0으로 표시한다.
			map[r][c] = 1;
		}
		
		int maxCompost = 0; // 최대 음식물 크기 초기화
		
		for(int r=0; r<N; r++) { //통로를 순회하다가
			for(int c=0; c<M; c++) {
				
				if(!visited[r][c] && map[r][c] ==1) { //아직 방문하지 않은 + 음식물 영역이라면
					cnt =0; // 새로운 음식물 덩어리 -> 크기 초기화
					visited[r][c] = true;
					calSize(r,c); // 사이즈 계산
				}
				
				maxCompost = Math.max(maxCompost, cnt);
			
			}
		}
		
		System.out.println(maxCompost);
		
	}


	private static void calSize(int r, int c) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r,c});
		visited[r][c] = true;
		
		cnt++; // 음식물 사이즈업
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll(); // 현재 좌표
			int cr = curr[0];
			int cc = curr[1];
			
			for (int i =0; i<4; i++) { // 사방탐색
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && map[nr][nc] == 1) { // 범위 내 확인 + 방문하지 않았던 + 음식물 영역
					visited[nr][nc] = true; // 방문 처리
					cnt++; // 음식물 사이즈 업
					queue.add(new int[] {nr, nc}); // 여기서부터 다시 탐색 시작
				}
			}
			
		}
		
	}

}
