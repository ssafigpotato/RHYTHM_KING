// package 백준 17836 공주님을 구해라!;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 현재 좌표, 지금까지 걸린 시간, 그람 소유 여부
	static class Loc {
		int r, c, time;
		boolean gram;
		
		public Loc(int r, int c, int time, boolean gram) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.gram = gram;
		}
	}
	
	
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited; // 좌쵸 + 그람 소유 여부(0: ~그람, 1; 그람)
	// 그람을 가지고 다시 방문할 수 있으므로
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N= sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		
		map = new int[N][M];
		visited = new boolean[N][M][2]; //2인 이유는 위에서 말한대로 그람이 있꺼나 없거나 2가지 상황
		
		for(int r =0; r<N; r++) {
			for(int c=0; c<M; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		System.out.println(bfs());
	}

	private static String bfs() {
		
		Queue<Loc> queue = new LinkedList<>();
		queue.offer(new Loc(0,0,0,false)); // starting point
		visited[0][0][0] = true; // 아직 그람 없으니까 [0][0][0]
		
		while(!queue.isEmpty()) {
			Loc curr = queue.poll(); // 현재 위치
			
			if(curr.time > T) continue; // 시간 초과
			
			if(curr.r == N-1 && curr.c == M-1) { // 공주 구출!
//				return curr.time; // bfs 메서드의 반환값이 String이라 변환 필요
				return String.valueOf(curr.time);
			}
			
			
			for(int d=0; d<4; d++) { // 델타 탐색
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if(nr>=0 && nc>=0 && nr<N && nc<M) { //범위 확인
					boolean gram = curr.gram || map[nr][nc] == 2; // 그람 소유 여부: 이미 가지고 있거나 || 새로 찾았거나
					int gramgram = gram ? 1 : 0; // 새로운 위치/큐 에서 gram 소유 여부
					
					if(!visited[nr][nc][gramgram]) { // 그람 업데이트 후 좌표 방문여부
						if(map[nr][nc] != 1 || gram) { // 벽이 아니거나 그람을 가진다면 (-> 갈 수 있어!)
							visited[nr][nc][gramgram] = true; // 그람 업데이트 후 해당 좌표 방문처리
							queue.offer(new Loc(nr, nc, curr.time+1, gram)); // 시간 추가 후 큐에 넣기
						}
					}
				}
			}
		}
		
		return "Fail";
	}

}
