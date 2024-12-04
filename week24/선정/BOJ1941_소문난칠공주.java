package algo_1204;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Solution) 
 * 1. 문제 입력받기 
 * 2. 조합 백트래킹으로 여학생 조합 찾기 
 * 	  25명의 학생 중 7명을 선택하는 모든 조합을 생성
 *    임도연파의 수가 4이상이면 return
 *    
 *    참고)) 1차원 배열 활용
 *    학생 배치도가 5×5 2차원 배열이지만, 탐색을 간단히 하기 위해 이를 1차원으로 변환.
 *    변환 규칙: 2차원 좌표 (r, c)는 1차원에서 r * 5 + c로 표현.
 *    복원 규칙: 1차원 인덱스 idx는 2차원에서 (idx / 5, idx % 5)로 표현.
 * 3. 조합이 7명이 되었을 때, 인접한지 BFS로 확인 
 */ 
public class BOJ1941_소문난칠공주 {
	// 학생배치도
	static char[][]map;
	// BFS 위치 클래스
	static class location{
		int r;
		int c;
		location(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	// 델타배열
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	// 방문체크
	static boolean visited[];
	// 정답 
	static int ans = 0;
	public static void main(String[] args) {
		// 1. 문제 입력받기 
		Scanner sc = new Scanner(System.in);
		// 여학생 배치도 5*5
		map = new char[5][5];
		// 2차원인 배치도를 1차원으로 바꿔서 조합 체크 
		visited = new boolean [25];
		// 입력하기 
		for(int i = 0; i <5; i++) {
			String str = sc.next();
			for(int j = 0; j <5; j++) {
			map[i][j] = str.charAt(j);
			}
		}// 입력 끝
		
		// 0,0,0부터 시작
		combination(0,0,0);
		System.out.println(ans);
	}//main
	
	
	// 2. 백트래킹으로 여학생 조합 구하기 
	// idx: 현재 탐색 시작 위치(1차원 배열로 변환!!)
	// depth: 현재까지 선택한 학생 수
	// Ycnt: 선택된 임도연파 학생수
	static void combination(int idx, int depth, int Ycnt) {
		// 1) 임도연파가 4명 이상일 경우 return
		if(Ycnt >=4 ) return;
		
		// 2) 7명이 되었을 경우 BFS로 인접한지 확인 
		if(depth == 7) {
			int curIdx = idx-1;
	        //BFS을 통해서 연결되어 있는지 확인
			if(bfs(curIdx / 5, curIdx % 5))ans++;
			 
			return;
        }

        // 3) 현재 학생(idx)부터 끝까지 순회하면서 한명씩 선택
		// 선택한 학생이 '임도연파(Y)'인지 확인하여 Ycnt를 증가시키거나 유지.
		// 선택 후, 다시 다음 학생 탐색(combination(i+1, depth+1, Ycnt)).
        for(int i = idx; i<25; i++){
            visited[i] = true;
            //'임도연파'인 경우
            if(map[i/5][i%5] == 'Y'){
                combination(i+1, depth+1, Ycnt+1);
            }else{		//'이다솜파'인 경우
                combination(i+1, depth+1, Ycnt);
            }
            visited[i] = false;
        }
	}// combination

	
	// 3. 조합이 7명이 되었을 때, 서로 인접한지(연결되어있는지) BFS로 확인 
	private static boolean bfs(int r, int c) {
		// 1) 큐 생성 및 방문 체크
		Queue<location> que = new LinkedList<>();
		boolean[][]checked = new boolean[5][5];
		checked[r][c] = true;
		que.offer(new location(r,c));
		
		// 인접한 학생 수
		int cnt = 1;
		
		// 2) 큐 돌리기 
		while(!que.isEmpty()) {
			location curr = que.poll();
			
			// 인접한 상하좌우 탐색
			for(int d = 0; d <4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				// 2.에서 만든 조합 내에 포함되어 잇는지 확인하기 위해 1차원배열로 변환
				int next = nr*5 + nc;
				
				// 조건 체크 
				// (1) 범위 안에 존재하는가?
				if(nr<0 || nr >=5 || nc <0 || nc >=5) continue;
				// (2) bfs로 방문하지 않았는가?
				if(checked[nr][nc]) continue;
				// (3) 2.에서 만든 조합 내에 포함되어 있는가?
				if(visited[next]) {
					checked[nr][nc] = true;
					que.offer(new location(nr,nc));
					cnt++;
				}		
			}// 4방 탐색
		}// 큐 돌리기 끝
		
		// 3) 인접한 학생이 7명이면 true, 아니면 false
		return cnt == 7 ? true : false;
	}// bfs
}
