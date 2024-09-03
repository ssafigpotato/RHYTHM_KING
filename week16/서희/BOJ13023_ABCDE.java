package 서희;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ13023_ABCDE {
	
	static int n;
	static ArrayList<ArrayList<Integer>> adjList;
	static boolean[] visited ;
	
	static int answer;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		n = sc.nextInt();		// 사람의 수
		int m = sc.nextInt();	// 친구 관계의 수
		
		// 인접리스트 초기화
		adjList = new ArrayList<>();
		for(int i=0; i<n; i++) {
			adjList.add(new ArrayList<>());
		}
		
		visited = new boolean [n];	// 방문체크
		
		answer = 0;
		
		// 친구 관계 입력
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}
		
		// DFS 탐색
		for(int i=0; i<n; i++) {
			if(answer == 0) {
				dfs(i, 1);
			}
		}
		
		System.out.println(answer);
		
		sc.close();
		
	}

	private static void dfs(int idx, int cnt) {
		visited[idx] = true;
		
		if(cnt >= 5) {
			answer = 1;
			return;
		}
		
		for(int next : adjList.get(idx)) {
			if(!visited[next]) {
				dfs(next, cnt + 1);
			}
		}
		
		visited[idx] = false;
	}

}
