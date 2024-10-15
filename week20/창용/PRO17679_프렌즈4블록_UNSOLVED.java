import java.util.*;

public class PRO17679_프렌즈4블록_UNSOLVED {

    class Solution {
        public int solution(int m, int n, String[] board) {
            StringBuilder sb = new StringBuilder();

            // String 타입 대신 char 배열을 만들어 주어진 board 배열을 변환할 것이다.
            // 메모리 절약을 위한 것.
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
                // 삭제하는 블록의 개수를 저장할 delete 변수
                int delete = 0;
                // 한 열에서 삭제해야할 블록의 개수
                // 이 정보를 통해 각 블록의 몇 개 위의 블록을 가져와야할지 탐색한다.
                int columnNum[] = new int[n];
                // 삭제해야할 블록의 위치를 저장할 boardToDel 배열이다.
                int[][] boardToDel = new int[m][n];

                // 2 * 2 를 탐색해야 하기 때문에 전체 높이, 폭 보다 하나 앞까지만 탐색 가능
                for(int i = 0; i < m - 1; i++){
                    for(int j = 0; j < n - 1; j++){

                        char a = charBoard[i][j];
                        char b = charBoard[i][j + 1];
                        char c = charBoard[i + 1][j];
                        char d = charBoard[i + 1][j + 1];

                        // a, b, c, d가 같아야 하며 동시에 빈 블록이라는 의미의 X가 아니어야 한다.
                        if(a == b && b == c && c == d && a != 'X'){
                            // 삭제하는 블록의 위치를 boardToDel 배열에 저장한다.
                            boardToDel[i][j] = 1;
                            boardToDel[i][j + 1] = 1;
                            boardToDel[i + 1][j] = 1;
                            boardToDel[i + 1][j + 1] = 1;
                        }

                    }// 탐색 폭 반복문 끝
                }// 탐색 높이 반복문 끝

                // 한 열에서 삭제할 블록의 개수 탐색
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < m; j++){
                        // 삭제할 배열을 바탕으로 한 열에서 몇 개의 블록을 삭제할지 표시한다.
                        if(boardToDel[j][i] == 1){
                            columnNum[i]++;
                        }
                    }
                }

                //삭제를 위한 반복문
                // boardToDel에서 1인 index에 대응되는 블록을 지정하여
                // columnNum만큼 위에 있는 문자를 가져와 넣는다.
                // 만일 columnNum만큼 위가 배열의 바깥으로 나갈 경우 그보다 아래 있는 것을 가져온다.
                for(int i = m - 1; i >= 0; i--){
                    for(int j = n - 1; j >= 0; j--){
                        // 삭제해야할 블록을 찾았을 경우,
                        if(boardToDel[i][j] == 1){

                            int step = 0;
                            // 해당 열에 있는 모든 블록에 삭제 및 이동 처리를 해줄 것이다.
                            while(i - step >= 0){
                                // 만일 블록의 위치(i - step)에서 해당 열의 삭제할 블록의 갯수(columnNum[j])만큼 위의 위치가,
                                // 배열 내부에 있으며, 비어있지('X' 표시) 않다면,
                                // 삭제할 블록의 갯수(columnNum[j])만큼 위의 블록의 내용을 가져온다.
                                if(i - step - columnNum[j] >= 0 && charBoard[i - step - columnNum[j]][j] != 'X'){
                                    charBoard[i - step][j] = charBoard[i - step - columnNum[j]][j];

                                    // 만일 해당 블록이 삭제할 블록이라고 표시되어있었다면,
                                    // 삭제할 블록의 개수인 delete 를 추가해준다.
                                    if(boardToDel[i - step][j] == 1){
                                        delete++;
                                        boardToDel[i - step][j] = 0;
                                    }

                                }

                                // 만일 블록의 위치(i - step)에서 해당 열의 삭제할 블록의 갯수(columnNum[j])만큼 위의 위치가,
                                // 배열 밖에 있거나 또한 비어있('X' 표시)다면,
                                // 해당 자리에 'X' 표시를 해준다.
                                if(i - step - columnNum[j] < 0 || charBoard[i - step - columnNum[j]][j] == 'X'){
                                    charBoard[i - step][j] = 'X';

                                    if(boardToDel[i - step][j] == 1){
                                        delete++;
                                        boardToDel[i - step][j] = 0;
                                    }

                                }
                                // 찾은 블록의 열을 기준으로 위에 있는 모든 블록에 로직을 적용하기 위해 step을 1 증가시키고 로직을 반복한다.
                                step++;
                            }

                        }
                    }
                } //삭제 반복문 끝

                // 만일 삭제한 블록이 없다면 while문을 빠져나온다.
                if(delete == 0){
                    break;
                }

                answer += delete;

            }//while문 끝
            return answer;
        }
    }
}
