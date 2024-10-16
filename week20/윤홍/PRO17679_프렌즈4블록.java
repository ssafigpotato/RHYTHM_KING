import java.util.*;

class Solution {

    static char[][] map;
    static int cnt;
    static Set<List<Integer>> set;
    static int[] di = {-1, -1, 0};
    static int[] dj = {0, 1, 1};

    public int solution(int m, int n, String[] board) {

        // m 은 높이
        // n 은 폭

        map = new char[m][n];
        cnt = 0;
        boolean finish = false;

        for(int i = 0; i < m; i++){
            String str = board[i];
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j);
            }
        }


        while(!finish){
            set = new HashSet<>();
            finish = crash(m, n);
            cnt += set.size();
            down(m, n);
        }





        int answer = cnt;
        return answer;
    }

    static void down(int m, int n){
        for(List<Integer> x : set){
            map[x.get(0)][x.get(1)] = 'x';
        }// 일단 바꿔주고 
        for(int i = m - 2; i >= 0; i--){
            for(int j = 0; j < n; j++){
                int cnt = 1;
                int tmp = 0;
                while(check2(i + cnt, m) && map[i + cnt][j] == 'x'){
                    map[i + cnt][j] = map[i + tmp][j];
                    map[i + tmp][j] = 'x';
                    cnt++;
                    tmp++;
                }
            }
        }
    }


    static boolean crash(int m, int n){
        // 같은 4개면 부서지는 메소드 
        boolean flag = true;
        for(int i = m - 1; i >= 0; i--){
            for(int j = 0; j < n; j++){
                if(check_box(i, j, m, n)){
                    flag = false;
                    set.add(Arrays.asList(i, j));
                    for(int k = 0; k < 3; k++){
                        set.add(Arrays.asList(i + di[k], j + dj[k]));
                    }
                }
            }
        }
        return flag;
    }

    static boolean check_box(int x, int y, int m, int n){
        boolean flag = true;
        for(int i = 0; i < 3; i++){
            int nx = x + di[i];
            int ny = y + dj[i];

            if(check(nx, ny, m, n) && map[x][y] == map[nx][ny]){
                continue;
            }else{
                flag = false;
                break;
            }
        }
        return flag;
    }


    static boolean check(int x, int y, int m, int n){
        return x >= 0 && x < m && y >= 0 && y < n && map[x][y] != 'x';
    }

    static boolean check2(int x, int m){
        return x >= 0 && x < m;
    }
}