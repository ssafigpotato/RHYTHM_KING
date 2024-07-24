package 서희;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ2583_영역구하기 {
	
	static int m;
	static int n;
	static int[][] paper;
	
	static int area;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt();
		n = sc.nextInt();		// m*n 크기의 모눈종이
		int k = sc.nextInt();	// k개의 직사각형
		
		paper = new int [m][n];	// 모눈종이 배열
		
		// 왼쪽 아래 꼭짓점의 x,y좌표값과 오른쪽 위 꼭짓점의 x,y 좌표값을 입력
		for(int i=1; i<=k; i++) {
			int leftX = sc.nextInt();
			int leftY = sc.nextInt();
			int rightX = sc.nextInt();
			int rightY = sc.nextInt();
			
			// k개의 직사각형을 모눈종이 배열에 1로 저장
			for(int x=leftX; x<rightX; x++) {
				for(int y=leftY; y<rightY; y++) {
					paper[y][x] = 1;
				}
			}
		}
		
		int cnt = 0;		// 분리된 영역의 개수
		List<Integer> areaList = new ArrayList<Integer>();	// 각 영역의 넓이를 저장하기 위한 배열
		
		// DFS 탐색
		for(int r=0; r<m; r++) {
			for(int c=0; c<n; c++) {
				if(paper[r][c] == 0) {
					cnt++;
					area = 0;		// 각 영역의 넓이 초기화
					dfs(r, c);
					areaList.add(area);
				}
			}
		}
		
		// 리스트 오름차순으로 정렬
		int[] areaArr = new int [areaList.size()];
		for(int i=0; i<areaList.size(); i++) {
			areaArr[i] = areaList.get(i);
		}
		Arrays.sort(areaArr);
		
		// 출력
		System.out.println(cnt);
		for(int i=0; i<areaArr.length; i++) {
			System.out.print(areaArr[i] + " ");
		}
	
		sc.close();
		
	}

	private static void dfs(int r, int c) {
		paper[r][c] = 2;	// 방문처리
		area++;				// 넓이 1 증가
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= m || nc < 0 || nc >= n || paper[nr][nc] != 0) continue;
			
			dfs(nr, nc);
		} 
	}

}
