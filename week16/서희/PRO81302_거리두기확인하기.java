package 서희;

import java.util.LinkedList;
import java.util.Queue;

public class PRO81302_거리두기확인하기 {
	
	public static void main(String[] args) {
		String[][] places = {
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
			{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
			{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
		};

		int[] answer = solution(places);
		
		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}
	
    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int p = 0; p < 5; p++) {
            String[][] room = new String[5][5];
            for (int row = 0; row < 5; row++) {
                room[row] = places[p][row].split("");
            }
            
            boolean isOk = true;
            for (int r = 0; r < 5 && isOk; r++) {
                for (int c = 0; c < 5 && isOk; c++) {
                    if (room[r][c].equals("P")) {
                        if (!bfs(r, c, room)) {
                            isOk = false;
                        }
                    }
                }
            }
            
            answer[p] = isOk ? 1 : 0;
        }
        
        return answer;
    }

    private static boolean bfs(int r, int c, String[][] room) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = position[0] + dr[i];
                int nc = position[1] + dc[i];
                
                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || (nr == r && nc == c)) {
                    continue;
                }
                
                int d = Math.abs(nr - r) + Math.abs(nc - c);
                
                if (room[nr][nc].equals("P") && d <= 2) {
                    return false;
                } else if (room[nr][nc].equals("O") && d < 2) {
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
        
        return true;
    }
}

