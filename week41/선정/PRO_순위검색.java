

import java.util.*;

class Solution {
    static Map<String, List<Integer>> ppl = new HashMap<>();
    
    // 가능한 모든 조합을 key로 저장, score는 리스트에 추가
    static void makeCombinations(String prefix, int depth, String[] attrs, int score) {
        if (depth == 4) {
            if (!ppl.containsKey(prefix)) {
                ppl.put(prefix, new ArrayList<>());
            }
            ppl.get(prefix).add(score);
            return;
        }

        // 현재 속성 포함
        makeCombinations(prefix + attrs[depth], depth + 1, attrs, score);
        // "-" 생략 처리
        makeCombinations(prefix + "-", depth + 1, attrs, score);
    }

    // 이진 탐색: minScore 이상 몇 명인지 세기
    static int countScores(int minScore, List<Integer> scores) {
        int left = 0;
        int right = scores.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (scores.get(mid) < minScore) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return scores.size() - left;
    }

    public int[] solution(String[] info, String[] query) {
        // info 조합별로 ppl 맵에 저장
        for (String i : info) {
            String[] split = i.split(" ");
            String[] attrs = Arrays.copyOf(split, 4);
            int score = Integer.parseInt(split[4]);

            makeCombinations("", 0, attrs, score);
        }

        // 각 key별 점수 정렬 (이진 탐색을 위해)
        for (List<Integer> scores : ppl.values()) {
            Collections.sort(scores);
        }

        int[] answer = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll("and ", ""); 
            String[] split = q.split(" ");
            String key = split[0] + split[1] + split[2] + split[3];
            int score = Integer.parseInt(split[4]);

            if (ppl.containsKey(key)) {
                List<Integer> scores = ppl.get(key);
                answer[i] = countScores(score, scores);
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }
}
