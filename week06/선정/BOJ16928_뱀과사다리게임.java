package week06;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//1. 사다리 정보와 뱀의 정보를 1차원 배열에 담기
//	 인덱스(기존 위치)와 요소(최종 위치)가 한 쌍
//2. visited[101]배열을 만들고 각 칸에 몇번만에 왔는지를 저장하면서 이동(방문했던 칸은 방문하지 않음) 
//3. 100번째칸에 왔을때 몇번만에 왔는지 출력
 
public class BOJ16928_뱀과사다리게임 {
	static int N; // 사다리의 수
	static int M; // 뱀의 수
	static int x,y,u,v; // 사다리 정보, 뱀 정보
	static int[] board; // 보드 배열
	static int[] visited; // 방문 횟수 기록 배열 
	static void bfs(int start) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(start);
		visited[start] = 0; // 시작은 0으로 초기화 (1과 100은 뱀과 사다리의 시작 또는 끝이 아님)
		
		while(!que.isEmpty()) {
			int curr = que.poll();
			if(curr == 100) break;
			
			// 주사위 굴리기. 1~6이 나오는 경우 
			// 예) 처음 주사위를 돌려서 1이 나왔으면
			// 그 다음 위치는 2~7이 올 수 있음. 
			for(int i = 1; i <7; i++) {
				int nc = curr + i;
				
				// 새 위치가 100을 넘었으면 건너뛰기
				// -> 주사위를 굴린 결과가 100번 칸을 넘어간다면 이동할 수 없음.
				if (nc > 100) continue;
				
				// 방문한적이 없고,사다리/뱀이 없는 곳이면  
				if(visited[nc] == 0 && board[nc]== 0) {
					visited[nc] = visited[curr]+1;
					que.offer(nc);
					
				}else if (board[nc] != 0) { // 뱀이나 사다리가 있음 
					if(visited[board[nc]] == 0) { // 뱀/사다리로 이동한 최종위치에 방문한 적이 없었다면
						visited[board[nc]] = visited[curr]+1;
						que.offer(board[nc]);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 보드 정보 1차원 배열로 선언(인덱스와 요소가 한 쌍. 2차원 배열로 할 필요 없음) 
		board = new int[101];
		visited = new int[101];
		
		// 사다리 정보 -> 보드 배열 갱신
		// 인덱스가 원래 위치. 요소가 이동한 위치 
		for(int i = 0; i <N; i++) {
			x = sc.nextInt(); // x번 칸에 도착하면
			y = sc.nextInt(); // y번 칸으로 이동 
			// 단 x < y
			board[x] = y;
		}
		
		// 뱀 정보 -> 보드 배열 갱신 
		// 인덱스가 원래 위치. 요소가 이동한 위치 
		for(int i = 0; i <M; i++) {
			u = sc.nextInt(); // u번 칸에 도착하면
			v = sc.nextInt(); // v번 칸으로 이동
			// 단 u > v
			board[u] = v;
		}
		
//		System.out.println(Arrays.toString(board));
		bfs(1); // 1번 칸부터 bfs 시작
		System.out.println(visited[100]);
		
		
		
	}
}
