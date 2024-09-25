package boj_21608_상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] seat = new int [n+1][n+1];
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int[] sequence = new int [n*n+1];			// 순서
		int[][] likeList = new int[n*n+1][4];		// 각 학생이 좋아하는 학생 번호를 저장한 배열
		
		for(int i=1; i<=n*n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			
			sequence[i] = num;
			for(int l=0; l<4; l++) {
				likeList[num][l] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 학생 자리 배치
		for(int i=1; i<=n*n; i++) {
			int num = sequence[i];			// 학생의 번호
			int[] friends = likeList[num];	// num 학생이 좋아하는 학생 4명의 번호
			
			int seatR = 0 ;			// 배정된 자리 (R)
			int seatC = 0 ;			// 배정된 자리 (C)
			int likeCnt = -1 ;		// 인접한 칸에 있는 좋아하는 학생의 수
			int blankCnt = -1 ;		// 인접한 칸 중에 비어있는 칸의 수
			
			for(int r=1; r<=n; r++) {
				for(int c=1; c<=n; c++) {
					if(seat[r][c] != 0) continue;
					
					int nowLikeCnt = 0 ;
					int nowBlankCnt = 0;
					
					for(int d=0; d<4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr <= 0 || nr > n || nc <= 0 || nc > n) continue;
						
						if(seat[nr][nc] == 0) {
							nowBlankCnt++;
						} else {
							for(int f=0; f<4; f++) {
								if(seat[nr][nc] == friends[f]) {
									nowLikeCnt++;
									break;
								}
							}
						}
					}
					
					if (nowLikeCnt > likeCnt || 
						(nowLikeCnt == likeCnt && nowBlankCnt > blankCnt) ||
	                    (nowLikeCnt == likeCnt && nowBlankCnt == blankCnt && (r < seatR || (r == seatR && c < seatC)))) {
		                    seatR = r;
		                    seatC = c;
		                    likeCnt = nowLikeCnt;
		                    blankCnt = nowBlankCnt;
					}
				}
			}
			
			seat[seatR][seatC] = num ;
		}
		
		// 만족도 계산
		int answer = 0 ;
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				int num = seat[r][c];
				int[] friends = likeList[num];
				int likeCnt = 0;
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr <= 0 || nr > n || nc <= 0 || nc > n) continue;
					
					for(int f=0; f<4; f++) {
						if(seat[nr][nc] == friends[f]) {
							likeCnt++;
							break;
						}
					}
				}
				
				if(likeCnt == 1) {
					answer += 1;
				} else if (likeCnt == 2) {
					answer += 10;
				} else if (likeCnt == 3) {
					answer += 100;
				} else if (likeCnt == 4) {
					answer += 1000;
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
