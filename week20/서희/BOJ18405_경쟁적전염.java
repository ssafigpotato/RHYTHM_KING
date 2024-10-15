import java.util.Scanner;

public class Main {
	static class VirusInfo {
		int time;
		int virusNum;
		
		VirusInfo(int time, int virusNum) {
			this.time = time;
			this.virusNum = virusNum;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();							// n*n 크기의 시험관
		int k = sc.nextInt();							// 1번부터 k번까지의 바이러스
		VirusInfo[][] arr = new VirusInfo [n+1][n+1];	// 시험관 정보
		
		// 입력
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				int v = sc.nextInt();
				
				if(v != 0) {
					arr[r][c] = new VirusInfo(0, v);
				}
			}
		}
		
		// s초 뒤에 (x, y)에 존재하는 바이러스의 종류
		int s = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for(int t=1; t<=s; t++) {
			for(int r=1; r<=n; r++) {
				for(int c=1; c<=n; c++) {
					
					if(arr[r][c] == null || arr[r][c].time == t) continue;
					
					int virusNum = arr[r][c].virusNum;
					
					for(int d=0; d<4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr <= 0 || nr > n || nc <= 0 || nc > n) continue;
						
						if(arr[nr][nc] == null) {
							arr[nr][nc] = new VirusInfo(t, virusNum);
							continue;
						}
						
						if(arr[nr][nc].time == t && arr[nr][nc].virusNum > virusNum) {
							arr[nr][nc] = new VirusInfo(t, virusNum);
						}
					}
				}
			}
			
			if(arr[x][y] != null) {
				break;
			}
		}
		
		if(arr[x][y] == null) {
			System.out.println("0");
		} else {
			System.out.println(arr[x][y].virusNum);
		}
		
		sc.close();
		
	}
}