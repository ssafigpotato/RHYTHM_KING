package daily_1014;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1647_도시분할계획 {
	static int N;
	static int M;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static class Node implements Comparable<Node>{
		int N;
		int cost;
		
		Node(int N, int cost){
			this.N = N;
			this.cost = cost;
		}
		
		// compareTo 메서드를 구현하여 
		// PriorityQueue에서 비용이 낮은 순서로 Node 객체를 정렬
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	private static int prim() {
		// PriorityQueue -> 가장 작은 비용의 간선을 우선적으로 선택
		// 우선순위 큐에서 가장 작은 비용의 노드를 꺼냄
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(1,0)); // 시작 노드가 1번이고, 비용이 0
		int sum = 0; // 현재까지의 최소 경로 간선 합 
		int max = 0; //간선 중 가장 값이 큰 간선의 값 
		
		while(!que.isEmpty()) {
			
			Node curr = que.poll(); // 우선순위 큐에서 가장 작은 비용의 노드 꺼냄
			
			if(visited[curr.N] == false) {
				visited[curr.N] = true;
			}else continue; // 이미 방문한 노드라면 건너뜀
			
			max = Math.max(max, curr.cost);
			sum += curr.cost;
			
			// 현재 노드의 이웃 노드들 탐색
			for(int i = 0; i <list[curr.N].size(); i++) {
				Node next = list[curr.N].get(i); // 현재 노드와 연결된 이웃 노드
				if(visited[next.N] == false) { // 이웃 노드가 방문하지 않았다면
					que.offer(new Node(next.N, next.cost)); // 우선순위 큐에 추가
				}
			}
		}
		// 간선들의 합에서 제일 큰 값 빼주기
		// 마을에는 집이 하나 이상 있어야 -> 마을에 집이 하나만 있어도 됨 
		// MST를 두 개로 나누되, 나머지 길의 비용을 최소로 하려면 
		// MST 중에서 가장 cost가 큰 간선 제거
		// 더 작은 cost가 되도록 나눌 수 있는 경우는 존재하지 않음 
		return sum - max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
        M = sc.nextInt();
		
        list = new ArrayList[N+1];
		for(int i = 1; i <=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int cost = sc.nextInt();
			list[start].add(new Node(end,cost));
			list[end].add(new Node(start,cost));
		}
		
		// 확인
//		for (int i = 1; i <= N; i++) {
//		    System.out.print("Node " + i + "와 연결된 Node: ");
//		    for (Node node : list[i]) {
//		        System.out.print("Node " + node.N + " (cost: " + node.cost + "), ");
//		    }
//		    System.out.println(); // 줄바꿈
//		}
		
		
		visited = new boolean[N+1];
		System.out.println(prim());
		
	
	}
}
