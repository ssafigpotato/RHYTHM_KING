package BOJ_10026_적록색약;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static boolean[][] visited;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static char[][] arr;
 	

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		arr = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 'R' && !visited[i][j]) {
					bfs(i, j, 'R');
					cnt++;
				}else if(arr[i][j] == 'G' && !visited[i][j]) {
					bfs(i, j, 'G');
					cnt++;
				}else if(arr[i][j] == 'B' && !visited[i][j]) {
					bfs(i, j, 'B');
					cnt++;
				}
			}
		}
		System.out.print(cnt + " ");
		
		visited = new boolean[N][N];
		
		cnt = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if((arr[i][j] == 'R' || arr[i][j] == 'G') && !visited[i][j]) {
					bfs2(i, j, 'R', 'G');
					cnt++;
				}else if(arr[i][j] == 'B' && !visited[i][j]) {
					bfs(i, j, 'B');
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		
		
	}
	
	static void bfs(int x, int y, char A) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		while(!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			for(int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];
				if(check(nx, ny) && arr[nx][ny] == A && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		
	}
	
	static void bfs2(int x, int y, char A, char B) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		while(!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			for(int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];
				if(check(nx, ny) && (arr[nx][ny] == A || arr[nx][ny] == B) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		
	}
	
	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}
