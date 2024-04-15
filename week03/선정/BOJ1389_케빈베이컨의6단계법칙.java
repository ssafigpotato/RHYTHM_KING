package week03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1389_케빈베이컨의6단계법칙 {
	static int N; // 유저 수
	static int M; // 관계 수
	static int[][]map; // 인접행렬
	static boolean visited[]; // 방문처리 배열
	static int [] KevinBacon; // 각 노드마다 케빈베이컨 수를 저장하는 배열
	// bfs 메소드
	static void bfs(int start) {
		// 0. Integer 타입의 큐를 선언하기
		Queue<Integer> que = new LinkedList<Integer>();
		// 1. 큐에 start 집어넣고 방문처리
		que.offer(start);
		visited[start] = true;
		// 2. 시작 노드의 케빈 베이컨 수 초기화 
		// 시작 노드의 케빈베이컨 수 : 0
		KevinBacon[start] = 0;
		// 3. 경로 거리 저장 배열 선언
		int[]distance = new int[N+1];
		
		
		// 4. while 큐가 빌 때까지 반복
		while(!que.isEmpty()) {
			// 4-1. 큐에서 하나 뽑아내기
			int curr = que.poll();
//			System.out.println(curr); // 방문 노드 출력으로 확인
			
			for(int i = 1; i <=N; i++) {
				if(!visited[i] && map[curr][i] == 1) { 
					// 방문하지 않았고, curr번 노드와 i번 노드가 연결되어 있으면
					// i를 que에 집어넣고 방문 처리
					que.offer(i);
					visited[i] = true;
					// i번 노드에서의 거리는 현재 위치에서의 거리+1
					distance[i] = distance[curr]+1;
					// 케빈베이컨 수 +=현재노드까지의 거리 +1
					KevinBacon[start] += distance[i]; 
				}
			}
		}// while문
		
	}// bfs 메소드
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][N+1]; //1번부터 시작하니까
		visited = new boolean [N+1]; // 방문처리 배열
		KevinBacon = new int[N+1]; // 각 노드의 케빈베이컨 수 저장
		
		// 1. 초기값 설정
		for(int i = 1; i <=N; i++) {
			for(int j = 1; j <=N; j++) {
				map[i][j] = Integer.MAX_VALUE;
				if(i == j) {
					map[i][j] = 0;
				}
			}
		}// 초기값 설정

		
		// 2. 관계 입력받고 인접행렬 만들기
		for(int i = 0; i <M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			map[x][y] = map[y][x] = 1;
		} 
		
		// 3. 1번노드부터 N번 노드까지 각각 bfs
		for(int i = 1; i <= N; i++) { // 1번 노드부터 N번 노드까지 각각 bfs
			// 방문 배열은 노드 마다 초기화
			visited = new boolean[N+1];
			bfs(i);
		}
		
		// 각 노드에서 케빈 베이컨의 수 확인
//		for(int i = 1; i <= N; i++) {
//			System.out.print(KevinBacon[i]+" ");
//		}
		
		// 4. 각 노드의 케빈 베이컨수를 확인해서 가장 작은 수를 가진 노드를 출력
		int min = Integer.MAX_VALUE;
		int ans = 0;
		for(int i = 1; i <=N; i++) {
			if(min> KevinBacon[i]) {
				min = KevinBacon[i];
				ans = i; // 수가 가장 작은 사람을 출력
			}
		}
		
		System.out.println(ans);
		
	
	}// main
}
