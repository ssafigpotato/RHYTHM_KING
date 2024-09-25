package algo_0908;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// 1,2,3이 한 번에 1초가 걸린다는 말이 아님
// 각각이 1초걸리는데 어떤 연산을 쓸지는 자유
// 예) 2개 -> 1: @복사 2: @붙여넣기
// 예) 4개 -> 1: @복사 2: @붙여넣기 3: @@복사 4: @@붙여넣기 
// 예) 6개 -> 1: @복사 2: @붙여넣기 3: @붙여넣기 4: @@@복사 5: @@@붙여넣기 
public class BOJ14226_이모티콘 {
	static int S; //이모티콘 개수
	static int answer; // 걸리는 시간(초)
	static boolean [][]visited;
	static class emoticon {
		int clipboard;
		int display;
		int time;
		
		emoticon(int clipboard, int display, int time){
			this.clipboard = clipboard;
			this.display = display;
			this.time = time;
		}
	}
	static void bfs(int s) {
		Queue<emoticon> que = new LinkedList<>();
		// 큐에 초기 상태 집어넣기
		// 클립보드 이모티콘 수: 0, 화면 이모티콘 수: 1, 시간: 0초 
		que.offer(new emoticon(0,1,0));
		// [클립보드 이모티콘 수:0][화면 이모티콘 수:1] 방문표시
		visited[0][1] = true;
		
		while(!que.isEmpty()) {
			emoticon curr = que.poll();
			// 화면의 이모티콘 수가 S개가 되면 그 때의 시간을 출력하고 return
			if(curr.display == S) {
				System.out.println(curr.time);
				return;
			}
			
			// 클립보드에 저장(복사), 붙여넣기, 삭제 각각 조건 만들어주기
			
			// 1. 화면에 있는 이모티콘을 복사해서 클립보드에 저장
			// 클립보드에는 화면과 같은 수를 넣고, 초는 +1 해주기
			// 옛날에 풀었던 2251물통이랑 비슷한 느낌
			que.offer(new emoticon(curr.display, curr.display, curr.time+1));
			visited[curr.display][curr.display] = true;
			
			// 2. 클립보드에 있는 이모티콘을 화면에 붙여넣기
			// 1) 클립보드에 있는 이모티콘 수가 0개가 아니면서
			// 2) 붙여넣기 한 이모티콘 + 기존 화면 이모티콘 수 <= S 여야함
			// 3) 이전에 방문한 적이 없어야함 (최솟값을 찾기 위해)
			if(curr.clipboard != 0 && curr.display + curr.clipboard <= S && !visited[curr.clipboard][curr.display+curr.clipboard]) {
				que.offer(new emoticon(curr.clipboard, curr.clipboard + curr.display, curr.time+1));
				visited[curr.clipboard][curr.clipboard + curr.display] = true;
			}
			
			// 3. 화면에 잇는 이모티콘 중 하나를 삭제
			// 1) 화면에 있는 이모티콘 총 개수가 1보다 크거나 같아야함
			// 2) 방문한 적 없어야 함 (최솟값을 찾기 위해)
			if(curr.display >=1 && !visited[curr.clipboard][curr.display-1]) {
				que.offer(new emoticon(curr.clipboard, curr.display-1, curr.time+1));
				visited[curr.clipboard][curr.display-1] = true;		
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 2개 부터 1000까지 니까
		// [클립보드 이모티콘 수][화면 이모티콘 수]
		// [clipboard][display] 방문 배열
		// 방문 체크를 해야 최솟값 구할 수 있음.
		visited = new boolean[1001][1001];
		S = sc.nextInt();
		bfs(S);
	}
}
