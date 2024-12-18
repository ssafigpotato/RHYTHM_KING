import java.util.*;

class Solution {

    public boolean solution(int[][] key, int[][] lock) {

        int n = lock.length;
        int m = key.length;
        int expandedSize = n + 2 * (m - 1);
        int[][] expandedLock = new int[expandedSize][expandedSize];

        // 자물쇠 확장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                expandedLock[i + m - 1][j + m - 1] = lock[i][j];
            }
        }

       // 열쇠를 회전하며 확인
        for (int rotation = 0; rotation < 4; rotation++) {

            key = rotateKey(key); // 열쇠 회전

            for (int x = 0; x <= expandedSize - m; x++) {

                for (int y = 0; y <= expandedSize - m; y++) {

                    if (canUnlock(expandedLock, key, x, y, n, m)) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    // 열쇠 90도 회전
    private int[][] rotateKey(int[][] key) {

        int m = key.length;
        int[][] rotatedKey = new int[m][m];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < m; j++) {
                rotatedKey[j][m - 1 - i] = key[i][j];
            }
        }

        return rotatedKey;

    }

    // 자물쇠 풀기 확인
    private boolean canUnlock(int[][] expandedLock, int[][] key, int startX, int startY, int n, int m) {

        // 자물쇠 + 열쇠
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < m; j++) {
                expandedLock[startX + i][startY + j] += key[i][j];
            }

        }

        // 자물쇠 전체 확인 ( 모두 1인가)
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (expandedLock[m - 1 + i][m - 1 + j] != 1) {

                    // 열쇠가 맞지 않는 경우
                    for (int x = 0; x < m; x++) {

                        for (int y = 0; y < m; y++) {
                            expandedLock[startX + x][startY + y] -= key[x][y];
                        }
                    }
                    return false;
                }

            }

        }

        // 열쇠 제거 (원상 복구)
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < m; j++) {
                expandedLock[startX + i][startY + j] -= key[i][j];
            }

        }
        return true;

    }

}

