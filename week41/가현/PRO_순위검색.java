import java.util.*;

class Solution {

    // 조건 조합(key) → 해당 조건을 만족하는 지원자들의 점수 리스트(value)
    // 예: "javabackendjuniorpizza" → [150, 170, 200]
    Map<String, List<Integer>> infoMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {

        // 1. 지원자 정보 전처리
        for (String i : info) {
            String[] parts = i.split(" "); // 예: [java, backend, junior, pizza, 150]
            dfs("", 0, parts); // 4개의 조건 조합을 모든 경우의 수로 만들어 infoMap에 저장
        }

        // 2. 조건별 점수 리스트를 오름차순 정렬 (이진 탐색을 위한 준비)
        for (List<Integer> list : infoMap.values()) {
            Collections.sort(list);
        }

        int[] answer = new int[query.length];

        // 3. query 처리
        for (int i = 0; i < query.length; i++) {
            // "and" 제거하고 공백 기준으로 분리
            // 예: "java and backend and junior and pizza 100"
            //  → parts = ["java", "backend", "junior", "pizza", "100"]
            String q = query[i].replaceAll(" and ", " ");
            String[] parts = q.split(" ");

            // 조건 4개를 하나의 key로 만들어 infoMap에서 찾을 수 있게 함
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int score = Integer.parseInt(parts[4]); // 기준 점수

            // 해당 조건 조합 key에 대한 점수 리스트가 존재하면
            if (infoMap.containsKey(key)) {
                List<Integer> list = infoMap.get(key);

                /**
                 * list는 해당 조건을 만족한 지원자들의 점수 리스트
                 * 정렬된 상태이므로 이진 탐색을 통해 score 이상 받은 사람 수를 빠르게 구할 수 있음
                 * → 시간 복잡도 O(log N)
                 */
                answer[i] = countScoresAbove(list, score);
            } else {
                // 조건에 해당하는 지원자가 아무도 없는 경우
                answer[i] = 0;
            }
        }

        return answer;
    }

    /**
     * dfs로 info의 4개 조건 각각에 대해 '-' 포함 가능한 16가지 조합을 key로 생성
     * 각 key에 해당하는 점수를 infoMap에 추가
     *
     * 예: "java backend junior pizza 150" → 다음과 같은 key가 생성됨
     * - javabackendjuniorpizza
     * - javabackendjunior-
     * - java--pizza
     * - ---- (모든 조건 무시)
     * 총 16가지 key → 각 key에 150 추가
     */
    private void dfs(String prefix, int depth, String[] parts) {
        if (depth == 4) {
            String key = prefix;
            int score = Integer.parseInt(parts[4]);

            // key가 없다면 새 리스트 생성
            infoMap.putIfAbsent(key, new ArrayList<>());
            infoMap.get(key).add(score);
            return;
        }

        // 현재 조건 포함
        dfs(prefix + parts[depth], depth + 1, parts);
        // 현재 조건 무시 (‘-’ 와일드카드)
        dfs(prefix + "-", depth + 1, parts);
    }

    /**
     * 정렬된 점수 리스트에서 기준 점수 이상 받은 사람 수를 반환
     * 이진 탐색(lower bound)으로 score 이상이 처음 등장하는 위치를 찾고
     * 전체 리스트 길이 - 위치 = 조건 만족 인원 수
     *
     * 예: [50, 80, 150, 200, 210], 기준 score = 150
     * → lowerBound = 2 → list.size() - 2 = 3명
     */
    private int countScoresAbove(List<Integer> list, int score) {
        int left = 0;
        int right = list.size();

        // lower bound 탐색: 처음으로 score 이상이 등장하는 인덱스 찾기
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < score) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 전체 크기 - lower bound 위치 = 조건 만족 지원자 수
        return list.size() - left;
    }
}
