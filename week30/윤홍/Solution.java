package PRO72411;

import java.util.*;
class Solution {
    static Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        for (String order : orders) {
            char[] tmp = order.toCharArray();
            Arrays.sort(tmp); // 사전 순 정렬
            for (int r : course) {
                if (r <= tmp.length) { // r이 배열 길이보다 작거나 같아야 함
                    char[] out = new char[r];
                    boolean[] visited = new boolean[tmp.length];
                    combination(tmp, out, visited, 0, 0, r);
                }
            }
        }

        // 각 코스 길이에 대해 최대 빈도 계산
        for (int r : course) {
            List<String> candidates = new ArrayList<>();
            int maxCount = 2; // 최소 2번 이상 겹쳐야 코스 요리로 인정

            for (String key : map.keySet()) {
                if (key.length() == r && map.get(key) >= maxCount) {
                    if (map.get(key) > maxCount) {
                        maxCount = map.get(key);
                        candidates.clear(); // 최대 빈도 갱신 시 기존 후보 초기화
                    }
                    if (map.get(key) == maxCount) {
                        candidates.add(key); // 같은 빈도의 조합 추가
                    }
                }
            }
            result.addAll(candidates);
        }

        // 결과를 정렬하여 반환
        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    // 조합을 생성하는 메서드 (순열 대신 조합 사용)
    public void combination(char[] arr, char[] out, boolean[] visited, int start, int depth, int r) {
        if (depth == r) {
            String tmp = new String(out); // char 배열을 문자열로 변환
            map.put(tmp, map.getOrDefault(tmp, 0) + 1); // 빈도수 카운팅
            return;
        }

        for (int i = start; i < arr.length; i++) {
            out[depth] = arr[i];
            combination(arr, out, visited, i + 1, depth + 1, r); // 조합이므로 `i + 1`
        }
    }
}
