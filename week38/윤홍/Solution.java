package PRO86971;

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        int[][] map = new int[n + 1][n + 1];

        for(int i = 0; i < wires.length; i++){
            map[wires[i][0]][wires[i][1]] = map[wires[i][1]][wires[i][0]] = 1;
        }

        for(int i = 0; i < wires.length; i++){
            int n1 = wires[i][0];
            int n2 = wires[i][1];

            // 전선을 끊어 버린다.
            map[n1][n2] = map[n2][n1] = 0;

            // 그 상태에서 노드의 갯수 세기
            int cnt = countNodes(n, map, n1);

            // 노드의 갯수 비교하기
            int diff = Math.abs((n - cnt) - cnt);
            answer = Math.min(diff, answer);

            // 전선을 복구해야 한다
            map[n1][n2] = map[n2][n1] = 1;

        }

        return answer;
    }

    static int countNodes(int n, int[][] map, int start){
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;
        int count = 1;

        while(!que.isEmpty()){
            int tmp = que.poll();
            for(int i = 1; i <= n; i++){
                if(map[tmp][i] == 1 && !visited[i]){
                    visited[i] = true;
                    que.add(i);
                    count++;
                }
            }
        }

        return count;
    }
}