class Solution {
    /*
    * 1. 할인율 조합 생성
    * dfs를 사용한 재귀 탐색. 각 이모티콘마다 10%, 20%, 30%, 40% 중 하나 선택.
    * 예를 들어, emoticons.length = 2라면
    * [10, 10], [10, 20], [10, 30], [10, 40],
    * [20, 10], [20, 20], ... [40, 40]
    *
    * 2. 유저별 판매액 계산
    * 할인율에 따라 구매할 이모티콘의 가격 합계 계산.
    * 가격 합계가 기준을 초과하면 서비스에 가입, 그렇지 않으면 구매.
    *
    * 3. 결과 갱신
    * 모든 조합에 대해 가입자 수와 판매액을 계산.
    * 가입자 수를 최우선으로 비교, 가입자 수가 같다면 판매액으로 비교하여 최대값 갱신.
    */
    
    
    int maxSub = 0; // 최대 구독자수
    int maxSales = 0; // 최대 이모티콘 판매액
    int[] discounts = {10, 20, 30, 40}; // 고정된 할인율

    public int[] solution(int[][] users, int[] emoticons) {
        // 할인율 조합 생성 및 계산
        dfs(users, emoticons, new int[emoticons.length], 0);
        
         // 최대 가입자 수와 판매액 반환
        return new int[]{maxSub, maxSales};
    }

   /**
     * 1. 할인율 조합 생성 (DFS)
     * users 사용자 구매 기준 배열
     * emoticons 이모티콘 가격 배열
     * discountRates 현재까지 설정된 할인율 조합
     * depth 현재 탐색 중인 이모티콘 인덱스
     */
    public void dfs(int[][] users, int[] emoticons, int[] discountRates, int depth) {
        // 모든 이모티콘에 대해 할인율을 설정 완료한 경우
        if (depth == emoticons.length) {
            // 현재 할인율 조합에 대해 판매액과 가입자 계산
            calculate(users, emoticons, discountRates);
            return;
        }

        for (int d : discounts) {
            discountRates[depth] = d;
            dfs(users, emoticons, discountRates, depth + 1);
        }
    }

   /**
     * 2. 유저별 판매액 및 구독자 수 계산
     * users 사용자 구매 기준 배열
     * emoticons 이모티콘 가격 배열
     * discountRates 현재 할인율 조합
     */
    public void calculate(int[][] users, int[] emoticons, int[] discountRates) {
       
        int sub = 0; // 현재 할인율 조합에서의 구독자 수
        int sales = 0; // 현재 할인율 조합에서의 판매액

        // 유저별로 
        for (int[] user : users) {
            int userRate = user[0];
            int userLimit = user[1];
            int total = 0; // 해당 사용자의 이모티콘 구매액 합

            // 이모티콘별 할인율 적용
            // 100 - discountRates[i]) / 100: 할인율을 적용한 가격 비율.
            for (int i = 0; i < emoticons.length; i++) {
                if (discountRates[i] >= userRate) {
                     // 구매 가능한 할인율인 경우 가격 계산
                    total += emoticons[i] * (100 - discountRates[i]) / 100;
                }
            }

            // 기준 금액 초과 여부 판단
            if (total >= userLimit) {
                sub++; // 서비스 가입
            } else {
                sales += total; // 이모티콘 구매
            }
        }

        // 3. 결과 갱신
        // 구독자 수를 최우선으로, 동일하면 판매액을 비교하여 갱신
        if (sub > maxSub) {
            maxSub = sub;
            maxSales = sales;
        } else if (sub == maxSub) {
            maxSales = Math.max(maxSales, sales);
        }
    }
       
}