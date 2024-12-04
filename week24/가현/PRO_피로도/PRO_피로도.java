import java.util.ArrayList;
import java.util.List;

class Solution {
    private int maxDungeons = 0;

    public int solution(int k, int[][] dungeons) {
        // 던전 순열 생성 및 탐험
        List<int[][]> permutations = new ArrayList<>();
        boolean[] visited = new boolean[dungeons.length];
        generatePermutations(dungeons, new ArrayList<>(), visited, permutations);

        // 순열별 탐험 가능한 던전 수 계산
        for (int[][] perm : permutations) {
            calculateMaxDungeons(k, perm);
        }

        return maxDungeons;
    }

    // 순열 생성
    private void generatePermutations(int[][] dungeons, List<int[]> current, boolean[] visited, List<int[][]> result) {
        if (current.size() == dungeons.length) {
            // 완성된 순열을 배열로 변환하여 결과에 추가
            result.add(current.toArray(new int[current.size()][]));
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(dungeons[i]); // 현재 던전 추가
                generatePermutations(dungeons, current, visited, result);
                current.remove(current.size() - 1); // 백트래킹
                visited[i] = false;
            }
        }
    }

    // 최대 던전 수 계산
    private void calculateMaxDungeons(int k, int[][] perm) {
        int count = 0;
        for (int[] dungeon : perm) { // 주어진 순서대로 던전을 탐험
            if (k >= dungeon[0]) { // 최소 필요 피로도 충족
                k -= dungeon[1];  // 소모 피로도 차감
                count++;
            } else {
                break; // 더 이상 탐험 불가능
            }
        }
        maxDungeons = Math.max(maxDungeons, count);
    }
}
