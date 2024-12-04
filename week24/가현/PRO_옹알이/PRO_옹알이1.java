import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;

        // 발음 가능한 단어들(기본)
        String[] words = {"aya", "ye", "woo", "ma"};
        // 발음 가능한 모든 단어 리스트
        List<String> permutations = new ArrayList<>();

        // 순열 생성
        boolean[] visited = new boolean[words.length];
        // ""는 순열을 이용해 완성된 단어가 될 것
        generatePermutations(words, "", visited, permutations);

        // 주어진 babbling 배열 검사
        // babbling 배열에서 주어진 단어가 리스트에 있다면 ok
        for (String word : babbling) {
            if (permutations.contains(word)) {
                answer++;
            }
        }

        return answer;
    }

    // 순열 생성 메서드
    private void generatePermutations(String[] words, String current, boolean[] visited, List<String> permutations) {
        if (current.length() > 0) {
            permutations.add(current); // 완성된 순열 추가
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                generatePermutations(words, current + words[i], visited, permutations);
                visited[i] = false; // 백트래킹
            }
        }
    }
}
