package boj_18352_특정거리의도시찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int n;
	static StringTokenizer st;
	static HashMap<Integer, List<Integer>> map;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());	    // 도시의 개수
		int m = Integer.parseInt(st.nextToken());	// 도로의 개수
		int k = Integer.parseInt(st.nextToken());	// 거리 정보
		int x = Integer.parseInt(st.nextToken());	// 출발 도시의 번호
		
		init(m);
		
		int[] distance = calDistance(x);	// 최단 거리를 저장하는 배열
		List<Integer> answer = sameDistance(distance, k);
		
		if(answer.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		for(int node : answer) { 
			System.out.println(node);
		}
	}
	
	/**
	 * 연결된 도로를 확인하여 map 함수를 초기화
	 * @param m 도로의 개수
	 */
	public static void init(int m) throws IOException {
		map = new HashMap<>();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			List<Integer> list = map.getOrDefault(a, new LinkedList<Integer>());
			list.add(b);
			map.put(a, list);
		}
	}
	
	/**
	 * 각 도시까지의 최단 거리 계산
	 * @param start 출발 도시의 번호
	 * @return 각 도시별 최단 거리를 저장한 배열
	 */
	public static int[] calDistance(int start) {
		int[] distance = new int [n+1];
		boolean[] visited = new boolean [n+1];
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if(!map.containsKey(now)) continue;
			
			List<Integer> list = map.get(now);
			
			for(int next : list) {
				if(visited[next]) continue;
				
				distance[next] = distance[now] + 1;
				visited[next] = true;
				queue.add(next);
			}
		}
		
		return distance;
	}
	
	/**
	 * 최단 거리가 K인 모든 도시 찾기
	 * @param distance	각 도시별 최단 거리를 저장한 배열
	 * @param k	 찾고자 하는 거리
	 * @return	최단 거리가 K인 모든 도시
	 */
	public static List<Integer> sameDistance(int[] distance, int k) {
		List<Integer> answer = new LinkedList<Integer>();
		
		for(int i=1; i<=n; i++) {
			if(distance[i] == k) {
				answer.add(i);
			}
		}
		
		return answer;
	}
}
