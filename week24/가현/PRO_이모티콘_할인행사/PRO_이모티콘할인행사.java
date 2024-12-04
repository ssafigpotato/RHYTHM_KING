class Solution {
    private static final int[] DISCOUNT_RATES = {10, 20, 30, 40};
    
    public int[] solution(int[][] users, int[] emoticons) {
        int m = emoticons.length;
        
        // 결과를 저장할 변수
        int maxPlusUsers = 0;
        int maxSales = 0;
        
        // 모든 할인율 조합의 경우의 수
        int cases = (int) Math.pow(4, m);
        
        // 모든 할인 조합 탐색
        for (int combi = 0; combi < cases; combi++) {
            int[] discounts = new int[m];
            
            // 현재 할인 조합 생성
            for (int i = 0; i < m; i++) {
                discounts[i] = DISCOUNT_RATES[(combi / (int)Math.pow(4, i)) % 4];
            }
            
            // 현재 할인 조합으로 결과 계산
            int plusUsers = 0;
            int totalSales = 0;
            
            // 각 사용자별 구매 및 서비스 가입 여부 판단
            for (int[] user : users) {
                int userDiscount = user[0];
                int userMaxPrice = user[1];
                
                int userPurchaseTotal = 0;
                
                // 사용자의 할인 기준에 맞는 이모티콘 구매
                for (int i = 0; i < m; i++) {
                    if (discounts[i] >= userDiscount) {
                        int discountedPrice = emoticons[i] * (100 - discounts[i]) / 100;
                        userPurchaseTotal += discountedPrice;
                    }
                }
                
                // 구매 총액이 최대 허용 금액 초과 시 서비스 가입
                if (userPurchaseTotal >= userMaxPrice) {
                    plusUsers++;
                } else {
                    totalSales += userPurchaseTotal;
                }
            }
            
            // 최적해 업데이트
            if (plusUsers > maxPlusUsers || 
                (plusUsers == maxPlusUsers && totalSales > maxSales)) {
                maxPlusUsers = plusUsers;
                maxSales = totalSales;
            }
        }
        
        return new int[]{maxPlusUsers, maxSales};
    }
}