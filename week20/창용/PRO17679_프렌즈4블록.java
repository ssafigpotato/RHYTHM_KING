
/**
 *
 * 문제 설명 :
 *
 * 프렌즈4블록은 주어진 2차원 배열 상에서 2 * 2 영역, 즉 윗열 2개와 아랫열 2개의 블록이 붙어있는 4개의 영역이 같은 문자인 곳을 탐색하여,
 *
 * 해당 영역을 지우고 위의 블록을 아래로 옮기는 방식으로 변화시키며 총 삭제한 블록의 갯수를 반환하는 문제이다.
 *
 */

/**
 *
 * 문제를 풀지 못한 이유 :
 *
 * 언뜻 간단해보이는 문제였으나, 로직을 잘못 설계하여 몇 가지 테스트 케이스를 통과하지 못했다.
 *
 * 먼저 나는 주어진 2차원 배열을 탐색하여 각 열에 해당하는 삭제할 블록의 갯수를 저장하였다.
 * 예를 들면, 어떤 열은 2개를 삭제하고 어떤 열은 3개, 어떤 열은 0개 이런식으로 저장해놓는 것이다.
 *
 * 이후, 맨 아래 행부터 탐색하며 삭제해야할 블록을 만나면 해당 열에서 가장 위의 행까지 올라가며 지워질 블록의 갯수만큼 위의 블록을 가져왔다.
 * 예를 들면, 지워질 블록이 위치한 열에 총 2개의 블록이 삭제된다면, 지워질 블록의 두 칸 위에 있는 블록의 내용을 가져와서 넣어주는 것이다.
 * 만약 두 칸 위의 블록이 비어있거나, 배열 밖으로 나가게 되면 블록이 비어있다는 표시를 해주었다.
 *
 * 이렇게 진행할경우, 프로그래머스의 10번째 테스트케이스를 통과하지 못한다.
 *
 * chat gpt의 도움을 받고, 각 열에서 지워질 블록의 갯수만큼 위의 블록을 가져오는 것이 아닌,
 * 각 지워질 블록을 기준으로 맨 윗 행의 블록까지 하나씩 내용물을 아래로 내리는 방식으로 바꾸면 10번째 테스트케이스도 통과가 된다.
 *
 * 하지만 두 로직의 차이를 파악하지 못했으며, 내가 처음 작성했던 로직의 반례를 찾지 못하였다.
 *
 */


class PRO17679_프렌즈4블록 {

    public int solution(int m, int n, String[] board) {
        StringBuilder sb = new StringBuilder();

        // String 타입 대신 char 배열을 만들어 주어진 board 배열을 변환할 것이다.
        char[][] charBoard = new char[m][n];

        // String 배열을 char 배열로 옮기는 반복문
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                charBoard[i][j] = board[i].charAt(j);
            }
        }

        // 정답 출력을 위한 변수 answer
        int answer = 0;

        // 지울 수 있는 것이 존재하면 계속 반복할 수 있게 한다.
        while(true){
            // 지우는 블록의 갯수를 저장하는 delete 변수
            int delete = 0;
            // 지우는 블록의 위치를 저장할 boardToDel 2차원 변수.
            int[][] boardToDel = new int[m][n];

            // 2 * 2 를 탐색해야 하기 때문에 전체 높이, 폭 보다 하나 앞까지만 탐색하였다.
            for(int i = 0; i < m - 1; i++){
                for(int j = 0; j < n - 1; j++){
                    char a = charBoard[i][j];
                    char b = charBoard[i][j + 1];
                    char c = charBoard[i + 1][j];
                    char d = charBoard[i + 1][j + 1];

                    // a, b, c, d가 같아야 하며 동시에 빈 블록이라는 의미의 X가 아니어야 한다.
                    if(a == b && b == c && c == d && a != 'X'){
                        boardToDel[i][j] = 1;
                        boardToDel[i][j + 1] = 1;
                        boardToDel[i + 1][j] = 1;
                        boardToDel[i + 1][j + 1] = 1;
                    }
                }
            }

            // 삭제할 블록이 있는지 체크
            // 원래 해당 반복문에서는 각 열의 삭제할 블록 갯수를 저장해주었지만,
            // 10번째 테스트케이스를 통과하기 위해 각 위치를 비어있다는 'X' 표시로 바꾸었다.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (boardToDel[i][j] == 1) {
                        charBoard[i][j] = 'X';
                        delete++;
                    }
                }
            }

            // 삭제된 블록이 없으면 반복 종료
            if (delete == 0) break;
            answer += delete;

            // 블록을 위에서 아래로 내리는 로직
            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    // 만약 현재 위치가 'X'라면 해당 열의 위쪽 블록을 내려야 한다.
                    if (charBoard[i][j] == 'X') {
                        // 이 위치에서 첫 번째 행까지 위로 올라가면서 블록을 내린다.
                        for (int k = i - 1; k >= 0; k--) {
                            // 위 블록이 비어있다면 굳이 위에 있는 블록을 내릴 필요가 없다.
                            if (charBoard[k][j] != 'X') {
                                // 위쪽 블록을 아래로 내리고, 위쪽은 'X'로 만든다.
                                charBoard[i][j] = charBoard[k][j];
                                charBoard[k][j] = 'X';
                                break; // 한 블록만 내려야 하므로 break
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}
