import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1577_도로의개수 {

    public static  void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    long[][] dp = new long[n + 1][m + 1];

    int[][] horizontal = new int[n][m + 1];
    int[][] vertical = new int[n + 1][m];

    int k = Integer.parseInt(br.readLine());

    for(int i = 0; i < k; i++){
        st = new StringTokenizer(br.readLine(), " ");
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        if(y1 == y2){
            horizontal[Math.min(x1, x2)][y1] = 1;
        } else {
            vertical[x1][Math.min(y1, y2)] = 1;
        } // if ~ else end

    } // for end
        // 끝단에 있는 부분들은 한 칸씩 이동하는 경로이기 때문에 전부 1로 초기화해준다.

        // 가로 초기화
        for(int i = 1; i <= n; i++){
            if(horizontal[i - 1][0] == 1){
                break;
            }
            dp[i][0] = 1L;
        }

        // 세로 초기화
        for(int i = 1; i <= m; i++){
            if(vertical[0][i - 1] == 1){
                break;
            }
            dp[0][i] = 1L;
        }

        //  각 꼭지점에서 왼쪽 꼭지점과 아래 꼭지점의 수를 합한다.
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

                // 만약 부실공사 도로라면
                if(horizontal[i - 1][j] == 1){
                    dp[i][j] -= dp[i - 1][j];
                }

                if(vertical[i][j - 1] == 1){
                    dp[i][j] -= dp[i][j - 1];
                }

            } // y for end

        } // x for end

        System.out.println(dp[n][m]);

    } // psvm end

} // class end
