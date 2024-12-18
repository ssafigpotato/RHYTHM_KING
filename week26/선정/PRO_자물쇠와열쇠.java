class Solution {
    /**
    * 1. 자물쇠 확장
    * 자물쇠를 확장하여 열쇠를 이동할 때 발생하는 겹침 또는 초과 처리
    * 2. 열쇠 회전 및 이동
    * 열쇠를 4가지 방향(0도, 90도, 180도, 270도)으로 회전하며 모든 위치로 이동하여 겹치는 부분을 확인
    * 3. 일치 여부 확인
    * 확장된 자물쇠 영역에서 자물쇠의 홈(0)이 열쇠의 돌기(1)와 정확히 맞아떨어지고 겹치는 돌기가 없도록 체크
    */
    public boolean solution(int[][] key, int[][] lock) {
        int point = key.length - 1;  // 자물쇠의 확장 범위 계산
        // 열쇠를 이동 가능한 모든 위치(x, y)에 대해 확인
        for (int x = 0; x < point + lock.length; x++) { 
            for (int y = 0; y < point + lock.length; y++) {
                // 열쇠를 4가지 방향(0도, 90도, 180도, 270도)으로 회전하며 시도
                for (int r = 0; r < 4; r++) {
                    // 확장된 자물쇠 배열 생성
                    int[][] newLock = new int[lock.length + key.length * 2][lock.length + key.length * 2];
                    // 확장된 자물쇠 배열 초기화 (중앙에 기존 자물쇠 배치)
                    for (int i = 0; i < lock.length; i++) {
                        for (int j = 0; j < lock.length; j++) {
                            newLock[i + point][j + point] = lock[i][j];
                        }
                    }
                    // 회전한 열쇠를 확장된 자물쇠에 더하기
                    match(newLock, key, r, x, y);
                    // 자물쇠가 모두 맞는지 확인
                    if (check(newLock, point, lock.length)) return true;
                }
            }
        }
        return false; // 모든 경우에 대해 열 수 없는 경우
    }

    // 열쇠를 회전한 상태로 확장된 자물쇠에 더하는 메서드
    public void match(int[][] newLock, int[][] key, int rot, int x, int y) {
        int len = key.length;  // 열쇠의 크기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 회전 각도에 따라 열쇠 배열의 값을 확장된 자물쇠 배열에 더함
                if (rot == 0) { // 0도 회전
                    newLock[x + i][y + j] += key[i][j];
                } else if (rot == 1) { // 90도 회전
                    newLock[x + i][y + j] += key[j][len - i - 1];
                } else if (rot == 2) { // 180도 회전
                    newLock[x + i][y + j] += key[len - i - 1][len - j - 1];
                } else { // 270도 회전
                    newLock[x + i][y + j] += key[len - j - 1][i];
                }
            }
        }
    }

    // 자물쇠의 모든 홈이 정확히 채워졌는지 확인하는 메서드
    public boolean check(int[][] newLock, int point, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 자물쇠 영역에서 값이 1이 아닌 경우(홈이 채워지지 않거나 돌기끼리 겹친 경우)
                if (newLock[point + i][point + j] != 1) return false;
            }
        }
        return true; // 모든 홈이 정확히 채워진 경우
    }
}