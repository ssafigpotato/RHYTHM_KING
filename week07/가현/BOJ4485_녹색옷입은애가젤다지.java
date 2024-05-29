import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// (뇌피셜)이번 문제의 핵심: 해당 거리까지 잃는 루피를 기준으로 우선순위 큐를 사용
class Zelda implements Comparable<Zelda> {
	int r, c, cost;
	
	Zelda(int r, int c, int cost){
		this.r = r;
		this.c = c;
		this.cost = cost;
	}
	
	// 잃는 루피 = cost
	/*
	 * 양수 반환: this.cost > other.cost
	 * : this 객체가 other 객체보다 크다
	 * : PriorityQueue에서 this는 other보다 뒤에 위치하게 된다.
	 * 
	 * 음수 반환: this.cost < other.cost
	 * : this 객체는 other 객체보다 작다.
	 * : PriorityQueue에서 this는 other보다 앞에 위치하게 됩니다.
	 */
	public int compareTo(Zelda o) {
		return this.cost - o.cost;
	}
	
	/*
	 * 왜 this.cost - other.cost 인가?
	 * : PriorityQueue에서 가장 낮은 비용을 가진 노드를 우선적으로 처리하고자 할 때
	 *   this.cost - other.cost를 사용하면, 
	 *   작은 cost 값을 가진 Node 객체가 우선순위 큐에서 더 높은 우선순위를 갖게 되어, 
	 *   큐에서 먼저 나오게 됩니다. 
	 *   이는 최소 힙(min-heap) 구조와 유사하며, 
	 *   가장 낮은 값이 "루트"에 위치하는 특성을 가집니다.
	 */
	
}


public class Main {
	
	// 델타 배열: 상-우-하-좌 (시계방향)
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int problem = 1; // 몇 번째 문제인가
		
		while(true) { // 테스트케이스 개수가 정해져 있지 않음
			
			int N = sc.nextInt(); //맵 크기
			if(N==0) // 종료
				break;
			
			int[][] map = new int[N][N]; 
			
			for (int r = 0; r<N; r++) { // 맵 입력
				for(int c = 0; c<N; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			int ans = dijkstra(map, N); //다익스트라 알고리즘 
			System.out.println("Problem "+ problem + ": " + ans);
			problem++;
		}
		
		
	}

	private static int dijkstra(int[][] map, int N) {
		
		int[][] dp = new int[N][N]; // 최소 비용을 저장할 map 배열
		for(int[] row : dp) {
			Arrays.fill(row, Integer.MAX_VALUE); //fill을 사용해 우선 최댓값으로 배열 초기화
		}
		
		dp[0][0] = map[0][0];
		
		PriorityQueue<Zelda> queue = new PriorityQueue<>(); // 우선순위 큐 사용 -> 가장 낮은 비용의 노드가 항상 먼저 처리 -> 방문처리 불필요
		queue.add(new Zelda(0,0,dp[0][0])); //현재 위치([0][0])을 큐에 넣어준다.
		
		
		while(!queue.isEmpty()) {
			
			Zelda curr = queue.poll(); // 가장 작은 비용을 가지는게 지금 위치
			
			if(curr.r == N-1 && curr.c == N-1) { // 도착 지점에 위치한다면
				return dp[N-1][N-1]; 			 // 리턴합니다.
			}
			
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if(nr>=0 && nc>=0 && nr<N && nc<N) { //범위 확인
					int newCost = map[nr][nc] + dp[curr.r][curr.c]; //새로운 dp값을 구해보자!
					
					if(newCost<dp[nr][nc]) { //최솟값을 갱신할 수 있다면?
						dp[nr][nc] = newCost; // 업데이트
						queue.add(new Zelda(nr, nc, newCost)); // 큐에 더해주기
					}
				}
			}
		}
		
		return dp[N-1][N-1];
		
		
	}

}