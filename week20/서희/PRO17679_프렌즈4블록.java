import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {

        char[][] block = new char[m][n];

        for(int i=0; i<m; i++) {
            block[i] = board[i].toCharArray();
        }

        int answer = 0;

        // 몇 번 지워질지 모르니 무한 반복
        while(true) {
            boolean stop = true;
            boolean[][] check = new boolean [m][n];

            // 블록체크
            for(int r=0; r<m-1; r++) {
                for(int c=0; c<n-1; c++) {
                    if(block[r][c] == '-') {
                        continue;
                    }

                    char type = block[r][c];

                    if(block[r][c+1] == type && block[r+1][c+1] == type && block[r+1][c] == type) {
                        check[r][c] = true;
                        check[r][c+1] = true;
                        check[r+1][c+1] = true;
                        check[r+1][c] = true;
                        stop = false;
                    }
                }
            }

            // 지울 블록이 없으면 반복문 종료
            if(stop) {
                break;
            }

            // 체크된 블록 삭제
            for(int r=0; r<m; r++) {
                for(int c=0; c<n; c++) {
                    if(check[r][c]) {
                        block[r][c] = '-';
                        answer++;
                    }
                }
            }

            // 블록 내리기 (아래에서 위로 탐색)
            for(int c=0; c<n; c++) {
                for(int r=m-1; r>=0; r--) {
                    if(block[r][c] == '-') {
                        for(int i=r-1; i>=0; i--) {
                            if(block[i][c] != '-') {
                                block[r][c] = block[i][c];
                                block[i][c] = '-';
                                break;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }
}
