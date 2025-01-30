// row 방향으로 먼저 움직여야 하는데, column 방향으로 움직이게 만들었음.

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 로봇의 갯수
        int robotNum = routes.length;

        // 각 로봇에 해당하는 좌표값을 갖고 있을 2차원 배열
        int[][] robotCoordi = new int[robotNum][2];

        // 각 로봇에게 좌표를 할당해줄 것임
        for(int i = 0; i < robotNum; i++){
            robotCoordi[i][0] = points[routes[i][0] - 1][0];
            robotCoordi[i][1] = points[routes[i][0] - 1][1];
        }

        // 현재 움직이고 있는 로봇의 갯수
        int movingRobot = robotNum;

        // 해당 로봇이 움직이고 있는지 바로 확인할 수 있는 불리안 배열
        boolean[] isNotMoving = new boolean[robotNum];

        // 모든 로봇이 이동을 완료할 때까지 반복
        while(movingRobot > 0){

            // 2차원 boolean 배열을 생성한다.
            // 해당 2차원 배열은 턴이 시작될 때 생성한 이후, 로봇의 이동이 끝나면 체크한다.
            // 체크하면서 해당 좌표의 값을 1증가시킨다.
            // 모든 이동 행위가 끝난 이후에, 값이 2 이상인 좌표를 탐색하면서 찾는다.
            // 찾은 좌표 개수를 answer에 넣는다.
            int[][] isVisited = new int[101][101];

            // column 방향으로 우선 이동하는 로직 필요

            // 필요한 변수 : 로봇의 좌표, 로봇이 도착해야할 좌표
            for(int i = 0; i < robotNum; i++){
                // 만일 해당 로봇이 이동을 완료했다면, 차례를 넘긴다.
                if(isNotMoving[i]){
                    continue;
                }

                // 해당 로봇이 이동을 완료하지 않았다면 이동시킨다.
                // 로봇의 좌표
                int robotRow = robotCoordi[i][0];
                int robotColumn = robotCoordi[i][1];

                // 도착지의 좌표
                int destRow = points[routes[i][1] - 1][0];
                int destColumn = points[routes[i][1] - 1][1];

                int differRow = destRow - robotRow;
                int differColumn = destColumn - robotColumn;

                // 로봇의 좌표를 이동
                if(differColumn != 0){
                    if(differColumn < 0){
                        robotCoordi[i][1]--;
                    }else{
                        robotCoordi[i][1]++;
                    }
                }else{
                    if(differRow < 0){
                        robotCoordi[i][0]--;
                    }else{
                        robotCoordi[i][0]++;
                    }
                }

                isVisited[robotCoordi[i][0]][robotCoordi[i][1]]++;

                // 로봇이 목적지에 도착했는지 체크
                if(robotCoordi[i][0] == destRow && robotCoordi[i][1] == destColumn){
                    // 불리언 배열에 체크
                    isNotMoving[i] = true;

                    // 움직이고 있는 로봇 수 감소
                    movingRobot--;
                }

            }

            // 한 차례 모두 이동했을 때 2개 이상의 로봇이 같이 있는 공간의 갯수를 파악하는 로직 필요
            for(int i = 0; i < 101; i++){
                for(int j = 0; j < 101; j++){
                    if(isVisited[i][j] >= 2){
                        answer++;
                        System.out.println("i = " + i + ", j = " + j);
                    }
                }
            }

            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    System.out.print(isVisited[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

        } // while 문의 끝

        return answer;
    }
}