import java.util.*;

// 이 문제는 bfs가 아닌 dfs로 풀어야 한다.

public class PRO81302_거리두기확인하기 {

    /*
     어떻게 풀 것인가?
     1. 모든 P를 탐색한다.
     2. P의 맨해튼 거리에 다른 P가 있는지 본다.
      a) bfs 원리를 이용.
      b) 파티션과 빈테이블을 고려한다.

    위의 계획은 이 문제 풀이에 적합하지 않았다.
    */

        static int[] dr = {1, -1, 0, 0};
        static int[] dc = {0, 0, 1, -1};
        static boolean[][] checked;
        static boolean isKeepDistance;
        static char[][] arr;

        public int[] solution(String[][] places) {
            int[] answer = new int[5];

            // 각 대기실 탐색 시작
            for(int i = 0; i < answer.length; i++){
                boolean keepDistance = true;
                isKeepDistance = true;

                String[] place = places[i];

                arr = new char[5][5];

                for(int j = 0; j < answer.length; j++){
                    arr[j] = place[j].toCharArray();
                }

                // bfs는 어떻게 동작하는가?
            /*
             1. List를 생성한다.
             2. List에 출발지를 넣고 while문을 진입한다.
             3. while문 안에서 조건에 맞는 애들을 추가한다.
            */

                for(int r = 0; r < 5; r++){
                    for(int c = 0; c < 5; c++){

                        if(arr[r][c] != 'P'){
                            continue;
                        }
                        checked = new boolean[5][5];
                        checked[r][c] = true;
                        dfs(r, c, 0);
                    }
                }
                int num = isKeepDistance ? 1 : 0;
                answer[i] = num;
            }
            return answer;
        }

        public static void dfs(int r, int c, int depth){
            if(depth >= 2){
                return;
            }

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5){
                    continue;
                }

                if(checked[nr][nc] == true){
                    continue;
                }

                if(arr[nr][nc] == 'X'){
                    continue;
                }

                if(arr[nr][nc] == 'P'){
                    // 체크 후, 다음 탐색 진행
                    isKeepDistance = false;
                    return;
                }
                checked[nr][nc] = true;
                // System.out.println("nr : " + nr + " nc : " + nc + " dist : " + depth + " boolean : " + isKeepDistance);
                dfs(nr, nc, depth + 1);
                checked[nr][nc] = false;

            }

            if(!isKeepDistance){
                return;
            }

        }

    }
