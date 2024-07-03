package crazyrobot;

import java.util.Scanner;

public class Main {

    static int N; // N번 이동한다
    static double[] prob = new double[4]; // 동서남북으로 갈 확률
    static boolean[][] visited = new boolean[30][30];// N은 14이하의 수 => 대략 양 옆으로 해서 30x30
    static int[] dr = {0, 0, 1, -1}; // 동서남북
    static int[] dc = {1, -1, 0, 0};    // 동서남북 확률이 순서대로 주어지기 때문에 델타탐색도 같은 순서로 해야 함!

public static void main(String[] args) { 
    
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt(); // 이동횟수 입력
    
    for (int i = 0; i < 4; i++) {  // 동서남북으로 갈 확률 (동+서+남+북 = 1)
        prob[i] = sc.nextInt() / 100.0; 
    }

    visited[15][15] = true; // 시작점 방문 표시
    double result = dfs(15, 15, 0); // 대충 중앙에서 시작

    System.out.println(result); // 결과 출력

    sc.close(); 
}

static double dfs(int r, int c, int depth) { // (r좌표 , c좌표 , 이동횟수)
    if (depth == N) { // 이동 횟수 다 채움
        return 1.0; // 단순 경로 완성 -> 결과에 1을 곱해줌 (멈춤 = 이동하지 않음 = 1의 확률)
    }

    double ret = 0.0; // 단순할 확률을 저장할 변수
    for (int i = 0; i < 4; i++) { // 델타탐색
        
        int nx = r + dr[i]; // new! 좌표
        int ny = c + dc[i];
        
        if (nx >= 0 && nx < 30 && ny >= 0 && ny < 30 && !visited[nx][ny]) { //범위 확인 + 방문 아직 안했으면(=단순한 경로라면)
            visited[nx][ny] = true; // 방문 처리
            ret += prob[i] * dfs(nx, ny, depth + 1); // 단순할 확률 = (해당 방향으로 갈 확률) * (새로운 좌표에서부터 끝까지 단순할 확률)
            visited[nx][ny] = false; // 백트래킹 -> 방문 처리 삭제
        }
    }
    return ret; // 단순할 확률 반환
}
}