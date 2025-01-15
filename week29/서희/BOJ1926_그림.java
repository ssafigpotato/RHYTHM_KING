package boj_1926_그림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] paper;
	
	static class Path {
		int r, c;
		
		Path(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());	// 도화지의 세로 크기
		m = Integer.parseInt(st.nextToken());	// 도화지의 가로 크기
		paper = new int [n][m];		// 도화지 
		
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<m; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] answer = search();
		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}
	
	/**
	 * 도화지에 그려진 그림의 개수와 그림 중 가장 넓은 것의 넓이를 구하는 메소드
	 * @return [그림의 개수, 가장 넓은 그림의 넓이]
	 */
	public static int[] search() {
		int num = 0;
		int maxArea = 0;

		boolean[][] visited = new boolean [n][m];	// 방문체크 배열
		
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(paper[r][c] == 0 || visited[r][c]) continue;
				
				num++;
				int area = calArea(r, c, visited);
				maxArea = (area > maxArea) ? area : maxArea;
			}
		}
		
		return new int[] {num, maxArea};
	}
	
	/**
	 * 그림의 넓이를 계산하는 메소드
	 * @param r	그림이 시작하는 열번호
	 * @param c	그림이 시작하는 행번호
	 * @param visited	방문 체크를 위한 배열
	 * @return	그림의 넓이
	 */
	public static int calArea(int r, int c, boolean[][] visited) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		Queue<Path> queue = new LinkedList<Path>();
		queue.add(new Path(r, c));
		visited[r][c] = true;
		
		int area = 1;
		
		while(!queue.isEmpty()) {
			Path path = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = path.r + dr[d];
				int nc = path.c + dc[d];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if(visited[nr][nc] || paper[nr][nc] == 0) continue;
				
				area++;
				visited[nr][nc] = true;
				queue.add(new Path(nr, nc));
			}
		}
		
		return area;
	}
}
