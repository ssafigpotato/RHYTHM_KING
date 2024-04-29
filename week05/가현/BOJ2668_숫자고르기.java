package dfs;

import java.util.Scanner;

public class Main {
	static int[] cards;
	static boolean[] visited;
	static boolean isCycle;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //카드 개수
		
		cards = new int[N+1]; // 카드 입력받기
		visited = new boolean[N+1];
		 for (int i = 1; i <= N; i++) {
	            int card = sc.nextInt();
	            while (card < 1 || card > N) { // 유효 범위 검사
	                card = sc.nextInt();
	            }
	            cards[i] = card; // 유효한 입력 저장
	        }
		
		//값이 같은 쌍 찾기
		// 그냥 사이클에 더해주면 됨
		for(int i =1; i<=N; i++) {
			if(i == cards[i]) {
				visited[i] = true;
				continue;
			}
			
			// 이미 처리했다면 건너뛴다
			if(visited[i])
				continue;
			
			isCycle = false; //사이클의 존재 여부: false로 초기화
			visited[i] = true; // 현재 카드를 선택
			getCycle(i,i,N); // 사이클 ㄱ
			
			if(!isCycle) // 사이클이 안만들어졌다면 카드 선택 취소
				visited[i] = false;
		}
		
		// 선택된 카드의 개수를 세고, 그 목록을 출력.
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(int i =1; i<=N; i++) {
			if(visited[i]) {
				count++;
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(count);
		System.out.println(sb.toString());
		
		
	}

	 // 사이클을 찾는 재귀 함수, DFS 
    static void getCycle(int index, int last, int n) {
    	
        int curr = cards[index];
        
        if (!visited[curr]) {
        	visited[curr] = true;
        	
            getCycle(curr, last, n); //다음 카드 이동
            
            if (!isCycle) {
            	visited[curr] = false;
            }
        }

        // 시작점으로 돌아왔을 때 사이클이 완성
        if (curr == last) {
            isCycle = true; 
        }
    }
	

}
