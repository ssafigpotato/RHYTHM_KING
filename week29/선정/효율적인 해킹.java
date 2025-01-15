import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, A, B, hackingResult[], max;
	static List<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
		}
		
		hackingResult = new int[N + 1]; // hackingResult[i] : i번컴퓨터해킹시 해킹가능한총컴퓨터수
		
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			visited[i] = true;
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				for (int next : graph[cur]) {
					if (!visited[next]) {
						visited[next] = true;
						q.offer(next);
						hackingResult[next]++;
					}
				}
			}
		}
		
		for (int value : hackingResult) {
			max = Math.max(max, value);
		}
		
		for (int i = 1; i <= N; i++) {
			if (hackingResult[i] == max) {
				sb.append(i).append(" ");
			}
		}
		sb.append("\n");
		
		System.out.print(sb);
	}

}