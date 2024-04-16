package week03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 물의 상태를 나타내는 클래스
class Bottle {
	int a;
	int b;
	int c;
	
	Bottle(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
public class BOJ2251_물통 {
	/**
	 * 첫번째 물통이 비어있을 때 세번째 물통에 담길 수 있는 물의 양 구하기
	 * 물통의 물을 옮기는 경우: 6가지
	 * A -> B	/	B -> A
	 * B -> C	/	C -> B
	 * A -> C	/	C -> A
	 * A,B,C 물통의 현재 상태를 표현하고, 
	 * 각 단계에서 조건을 고려해 다음 상태를 계산 후 
	 * 가능한 경우 큐에 추가
	 * 이미 방문한 상태는 다시 방문 x
	 * ###################### 어떻게 BFS로 풀게 된건지??
	 * BFS를 경로의 방문으로만 생각하지 말고 
	 * 조건에 따라 가능한 경우를 모두 '탐색'한다고 생각
	 * 가능한 상태를 큐에 넣고 그 상태를 다시 큐에서 꺼내서 물을 이리저리 옮기면서(연산하면서)
	 * 가능한 경우를 모두 구한다고 생각하기
	 * */
	static boolean visited[][][];
	static int maxA, maxB, maxC;
	static List<Integer> ans;
	// bfs 메소드
	static void BFS() {
		// 0. Bottle 타입의 큐 선언 
		Queue<Bottle> que = new LinkedList<Bottle>();
		// 1. 물통의 초기상태
		que.offer(new Bottle(0, 0, maxC));
		
		// 2. while문
		while(!que.isEmpty()) {
			// 2-1.큐에서 하나를 꺼내고 
			Bottle curr = que.poll(); // 현재 물통 상태
			
			// 2-2. 아래 두가지를 확인한다.
			// 1) 방문했던 곳이라면 건너뛰기(아니면 방문처리)
			if(visited[curr.a][curr.b][curr.c]== true) {
				continue; // 이미 방문(처리)했던 '상태'라면 건너뛰기
			} else { 
				// 아니라면 방문 처리해주고 아래 내용 시작 
				visited[curr.a][curr.b][curr.c] = true;
			}
			// 2) 현재 A물통의 상태가 0이라면 C가 정답이 될 수 있는 상태이므로
			// ans List에 넣어주기
			if(curr.a == 0) {
				ans.add(curr.c);
			}
		
			// 2-3. 이제 6가지 경우를 탐색
			// 1) B->A 물통 (C는 가만히 두기)
			if( curr.a + curr.b > maxA) { // A,B물통의 현재 상태가 A의 용량보다 큰 경우
				// A를 다 채우고 나머지는 B로
				que.add(new Bottle(maxA, curr.a + curr.b - maxA, curr.c));
			}else {
				// B에 있는 것 전부 A로 옮기기
				que.add(new Bottle(curr.a + curr.b, 0 , curr.c));
			}
			// 2) A->B 물통
			if( curr.a + curr.b > maxB) { // A,B물통의 현재 상태가 B의 용량보다 큰 경우
				// B를 다 채우고 나머지는 A로
				que.add(new Bottle(curr.a + curr.b - maxB, maxB, curr.c));
			}else {
				// A에 있는 것 전부 B로 옮기기
				que.add(new Bottle(0, curr.a + curr.b, curr.c));
			}
			
			
			// 3) C->B 물통 (A는 가만히 두기)
			if( curr.b + curr.c > maxB) { // B,C물통의 현재 상태가 B의 용량보다 큰 경우
				// B를 다 채우고 나머지는 C로
				que.add(new Bottle(curr.a, maxB, curr.b + curr.c - maxB));
			}else {
				// C에 있는 것 전부 B로 옮기기
				que.add(new Bottle(curr.a, curr.b + curr.c, 0));
			}
			// 4) B->C 물통
			if( curr.b + curr.c > maxC) { // B,C물통의 현재 상태가 C의 용량보다 큰 경우
				// C 다 채우고 나머지는 B로
				que.add(new Bottle(curr.a, curr.b + curr.c - maxC, maxC));
			}else {
				// B에 있는 것 전부 C로 옮기기
				que.add(new Bottle(curr.a, 0, curr.b + curr.c));
			}
			
			
			// 5) C->A 물통 (B는 가만히 두기)
			if( curr.a + curr.c > maxA) { // B,C물통의 현재 상태가 B의 용량보다 큰 경우
				// A를 다 채우고 나머지는 C로
				que.add(new Bottle(maxA, curr.b, curr.a + curr.c - maxA));
			}else {
				// C에 있는 것 전부 A로 옮기기
				que.add(new Bottle(curr.a + curr.c, curr.b, 0));
			}
			// 6) A->C 물통
			if( curr.b + curr.c > maxC) { // B,C물통의 현재 상태가 C의 용량보다 큰 경우
				// C 다 채우고 나머지는 A로
				que.add(new Bottle(curr.a+curr.c - maxC, curr.b, maxC));
			}else {
				// A에 있는 것 전부 C로 옮기기
				que.add(new Bottle(0, curr.b, curr.a + curr.c));
			}
			
		
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		maxA = sc.nextInt();
		maxB = sc.nextInt();
		maxC = sc.nextInt();
		visited = new boolean[maxA+1][maxB+1][maxC+1];
		// 각각의 물통이 가질 수 있는 상태 방문 처리 배열. 
		// 1부터 maxA용량이므로 +1 
		ans = new ArrayList<Integer>();
		// 정답을 담을 List
		
		BFS();
		
		Collections.sort(ans);
		
		for(int i = 0; i <ans.size(); i++) {
			System.out.print(ans.get(i)+" ");
		}
		
		
	}
}
