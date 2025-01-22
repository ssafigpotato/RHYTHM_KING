/**
 * Solution 클래스는 손님 주문 데이터를 바탕으로 가장 많이 함께 주문된 메뉴 조합을 찾아내는 알고리즘을 제공합니다.
 * 
 * 알고리즘 전개:
 * 1. 입력 데이터 정렬: 각 주문을 알파벳 순으로 정렬하여 일관성을 유지.
 * 2. 조합 생성: 주문에서 특정 길이(c)의 모든 조합을 생성하고 빈도를 기록.
 * 3. 최대 빈도 필터링: 각 코스 크기별로 가장 많이 주문된 조합만 선택.
 * 4. 결과 정렬: 선택된 조합을 사전순으로 정렬하여 반환.
 */

import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        // 결과를 담을 리스트
        List<String> result = new ArrayList<>();

        // 1. 각 주문을 알파벳 순으로 정렬하여 조합 계산 시 일관된 순서를 유지
        for (int i = 0; i < orders.length; i++) {
            char[] orderArray = orders[i].toCharArray();
            Arrays.sort(orderArray); // 주문을 정렬
            orders[i] = new String(orderArray); // 정렬된 문자열로 저장
        }

        // 2. 각 코스 크기별로 처리
        for (int c : course) {
            Map<String, Integer> combinationCount = new HashMap<>();

            // 모든 주문에서 길이 c의 조합 생성
            for (String order : orders) {
                if (order.length() >= c) {
                    generateCombinations(order, c, 0, "", combinationCount);
                }
            }

            // 3. 가장 많이 주문된 조합의 최대 빈도를 계산
            int maxCount = 0;
            for (int count : combinationCount.values()) {
                maxCount = Math.max(maxCount, count);
            }

            // 최대 빈도가 2 이상인 조합을 결과에 추가
            if (maxCount >= 2) {
                for (Map.Entry<String, Integer> entry : combinationCount.entrySet()) {
                    if (entry.getValue() == maxCount) {
                        result.add(entry.getKey());
                    }
                }
            }
        }

        // 4. 결과를 사전순으로 정렬하여 반환
        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    /**
     * 특정 길이의 조합을 생성하고 빈도를 기록하는 메서드
     * 
     * @param order 현재 주문 문자열
     * @param length 생성할 조합의 길이
     * @param index 현재 탐색 위치
     * @param current 현재까지 생성된 조합 문자열
     * @param combinationCount 조합의 빈도를 기록하는 맵
     */
    private void generateCombinations(String order, int length, int index, String current, Map<String, Integer> combinationCount) {
        // 현재 조합이 요구 길이에 도달하면 맵에 추가
        if (current.length() == length) {
            combinationCount.put(current, combinationCount.getOrDefault(current, 0) + 1);
            return;
        }

        // 주어진 문자열에서 가능한 모든 조합 생성
        for (int i = index; i < order.length(); i++) {
            generateCombinations(order, length, i + 1, current + order.charAt(i), combinationCount);
        }
    }
}
