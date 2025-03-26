import java.util.*;

class Solution {
    int answer = 0;
    int[][] q;
    int[] ans;
    List<List<Integer>> allCombinations = new ArrayList<>();

    public int solution(int n, int[][] q, int[] ans) {
        this.q = q;
        this.ans = ans;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;

        boolean[] visited = new boolean[n];
        combination(arr, visited, 0, n, 5);  // nC5 조합 생성

        for (List<Integer> comb : allCombinations) {
            if (isValid(comb)) answer++;
        }

        return answer;
    }

    // 조합 생성 
    void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            List<Integer> comb = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    comb.add(arr[i]);
                }
            }
            allCombinations.add(comb);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    // 각 조합이 q, ans 조건을 만족하는지 확인
    boolean isValid(List<Integer> code) {
        for (int i = 0; i < q.length; i++) {
            int cnt = 0;
            for (int num : q[i]) {
                if (code.contains(num)) cnt++;
            }
            if (cnt != ans[i]) return false;
        }
        return true;
    }
}
