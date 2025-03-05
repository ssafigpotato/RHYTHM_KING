package boj_1922_네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	static ArrayList<Network> networkList;
	
	static class Network implements Comparable<Network> {
		int start;
		int end;
		int cost;
		
		Network(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Network o) {
			return cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());	// 컴퓨터의 수
		int m = Integer.parseInt(br.readLine());	// 연결할 수 있는 선의 수
		networkList = new ArrayList<Network>();		// 연결할 수 있는 네트워크를 관리할 배열
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());	// a 컴퓨터
			int b = Integer.parseInt(st.nextToken());	// b 컴퓨터
			int c = Integer.parseInt(st.nextToken());	// 비용
			
			networkList.add(new Network(a, b, c));
		}

		// 각 요소의 부모 번호를 저장할 배열
		parent = new int[n+1];
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}
		
		// 비용에 따라 오름차순 정렬
		Collections.sort(networkList);
		
		// 모든 컴퓨터를 연결하는데 필요한 최소비용 계산
		int totalCost = 0;
		
		for(int i=0; i<m; i++) {
			Network network = networkList.get(i);
			
			// 사이클이 발생하는 간선 제외
			if(find(network.start) == find(network.end)) continue;
			
			totalCost += network.cost;
			union(network.start, network.end);
		}
		
		System.out.println(totalCost);
	}
	
	public static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		
		if(fx != fy) {
			parent[fy] = fx;
		}
	}
	
	public static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
}
