import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ11559_PuyoPuyo {
	
	static char[][] map = new char[12][6]; // 맵의 크기
	
	static int pop = 0; // 최종 pop 횟수
	static boolean isPop = false; // 터졌나요?
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1, -1, 0, 0};
	
	// 개별 뿌요에 좌표와 색상을 담을 예정
	static class Puyo {
		int r,c;
		char color;
		
		Puyo(int r, int c, char color){
			this.r = r;
			this.c = c;
			this.color = color;
		}
	}
	
	// main 함수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 뿌요 맵을 입력받음
		for(int r =0; r<12; r++) {
			String line = sc.nextLine();
			for(int c=0; c<6; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		
		
		while(true) {
			isPop = false; //아직 연쇄 아니야!!
			
			bfs(); // bfs를 돌려용~
			toTheFloor(); //바닥으로 내려주기
			
			if(isPop==false) { // bfs 돌리면서 연쇄가 안일어났다면?
				break; // 이제 그만 ~ 
			}
			pop++; // 연쇄가 일어났다면 pop 증가
		}
		
		System.out.println(pop);	// 답을 출력해보아요
	}
	
	
	static void bfs() {
		Queue<Puyo> queue = new LinkedList<>(); // puyo를 담을 큐
		boolean[][] visited = new boolean[12][6]; //방문처리
		
		for(int r=0; r<12; r++) {
			for(int c=0; c<6; c++) {
				
				if(map[r][c] != '.' && !visited[r][c]) { //처음 본 뿌요라면?
					ArrayList<int[]> list = new ArrayList<>(); // 좌표를 담아줄 리스트
					
					queue.add(new Puyo(r,c,map[r][c])); // new Puyo를 큐에 넣어준다
					list.add(new int[] {r,c}); //뿌요의 위치도 list에 넣어준다
					visited[r][c] = true; // 방문완.
					
					while(!queue.isEmpty()) {
						Puyo p = queue.poll(); // 뿌요 하나를 뽑는다
						
						// 현재 좌표와 컬러를 저장
						int currR = p.r;  
						int currC = p.c;
						char currColor = p.color;
						
						//delta 탐색
						for(int d=0; d<4; d++) {
							int nr = currR + dr[d];
							int nc = currC + dc[d];
							
							// 범위를 나갔다면 그만 ~!
							if(nr<0 || nc<0 || nr>=12 || nc >=6) 
								continue;
							
							// 방문 한 적 없고 + 지금 뿌요 색이랑 같다면
							if(!visited[nr][nc] && map[nr][nc] == currColor) {
								// 큐에 델타 탐색으로 찾은 새로운 뿌요 삽입
								queue.add(new Puyo(nr, nc, map[nr][nc]));
								// 리스트에도 좌표를 새롭게 삽입
								list.add(new int[] {nr, nc});
								// 새로운 뿌요 방문처리 완.
								visited[nr][nc] = true;
							}
						}	
					}
					
					// 좌표 4개 이상 모였다면
					// 터뜨리기 + 연쇄 처리 완.
					if(list.size() >=4) {
						for(int k=0; k<list.size(); k++) {
							int x = list.get(k)[0];
							int y = list.get(k)[1];
							
							map[x][y] = '.';
							
							isPop=true;
						}
					}
				}
			}
		}
	}
	
	// 바닥으로 내려주기 (각각의 열 별로 처리)
	static void toTheFloor() {
		for(int i=0; i<6; i++) {
			down(i);
		}
	}
	
	// 찐 내려주는 메서드
	static void down(int j) {
		//뿌요를 담을 puyo 큐 입니다
		Queue<Puyo> puyo = new LinkedList<>();
		// 총 12행이라서 
		int idx = 11;
		
		// 바닥부터 올라가요
		for(int i =11; i>=0; i--) {
			// 각 열의 뿌요를 모두 puyo 큐에 넣는다
			// 그리고 그 자리들은 일단 다 빈공간으로 처리해둠
			if(map[i][j] != '.') {
				puyo.add(new Puyo(i,j,map[i][j]));
				map[i][j] = '.';
			}
		}
		
		// puyo 큐가 빌 때까지
		while(!puyo.isEmpty()) {
			//큐에 있던 뿌요를 뽑아서
			Puyo p = puyo.poll();
			char color = p.color;
			// 채워준다
			map[idx][j] = color;
			// 바닥부터 채우면서 위로 올라간다.
			idx--;
		}
	}

}
