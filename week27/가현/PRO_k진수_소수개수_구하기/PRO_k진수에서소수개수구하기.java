import java.util.*;

class Solution {
    /**
     * n을 k진수로 변환하고 조건에 맞는 소수의 개수를 구하는 메서드.
     * 1. n을 k진수 문자열로 변환
     * 2. 문자열을 '0'을 기준으로 분할하여 소수 후보를 추출
     * 3. 각 숫자가 소수인지 확인하고 소수의 개수를 카운트
     */
    public int solution(int n, int k) {
        // 1. n을 k진수 문자열로 변환
        String kBase = Integer.toString(n, k);

        // 2. '0'을 기준으로 분할하여 소수 후보 추출
        String[] candidates = kBase.split("0");

        int primeCount = 0; // 소수의 개수

        // 3. 각 후보 숫자에 대해 소수인지 확인
        for (String candidate : candidates) {
            if (!candidate.isEmpty()) { // 빈 문자열 무시
                long number = Long.parseLong(candidate); // 후보 숫자를 정수로 변환
                if (isPrime(number)) { // 소수 판별
                    primeCount++;
                }
            }
        }

        return primeCount;
    }

    /**
     * 주어진 숫자가 소수인지 확인하는 메서드
     */
    private boolean isPrime(long num) {
        if (num < 2) {
            return false; // 2보다 작은 숫자는 소수가 아님
        }
        for (long i = 2; i * i <= num; i++) { // 에라토스테네스의 체 방식 사용
            if (num % i == 0) {
                return false; // 약수가 존재하면 소수가 아님
            }
        }
        return true; // 약수가 없으면 소수
    }
}
