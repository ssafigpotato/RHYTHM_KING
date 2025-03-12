import java.util.*;

class Main {
    static int N, M, count; // N: 자리 수, M: 나눌 값, count: 나누어떨어지는 개수
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // N 입력
        M = sc.nextInt(); // M 입력
        sc.close();
        
        count = 0; // 결과값 초기화
        generatePalindromes("", 0, 0); // 붙인드롬 직접 생성 및 카운트 시작
        System.out.println(count); // 결과 출력
    }
    
    /**
     * 붙인드롬을 직접 생성하면서 M으로 나누어떨어지는 개수를 카운트하는 함수
     * @param current 현재까지 생성된 숫자 문자열 (디버깅용)
     * @param firstNum 붙인드롬의 앞부분 숫자 (정수형)
     * @param depth 현재까지 생성한 자리 수 (N/2까지 생성)
     */
    static void generatePalindromes(String current, long firstNum, int depth) {
        int halfLength = N / 2; // 절반 길이 계산
        
        if (depth == halfLength) { // 절반 길이 도달 시 붙인드롬 생성 및 검사
            long secondNum = reverseNumber(firstNum, halfLength); // 앞부분을 뒤집어서 뒷부분 생성
            long combined = firstNum * (long) Math.pow(10, halfLength) + secondNum; // 두 개의 팰린드롬을 이어붙여 붙인드롬 완성
            if (combined % M == 0) { // M으로 나누어 떨어지면 개수 증가
                count++;
            }
            return;
        }
        
        // 첫 번째 자리 숫자는 0이 올 수 없고, 이후 자리수는 0~9 가능
        for (char digit = (current.isEmpty() ? '1' : '0'); digit <= '9'; digit++) {
            generatePalindromes(current + digit, firstNum * 10 + (digit - '0'), depth + 1);
        }
    }
    
    /**
     * 숫자를 뒤집어 두 번째 팰린드롬 생성
     * @param num 첫 번째 팰린드롬 숫자
     * @param length 길이 (N/2)
     * @return 뒤집힌 숫자 (두 번째 팰린드롬)
     */
    static long reverseNumber(long num, int length) {
        long rev = 0;
        for (int i = 0; i < length; i++) { // 각 자리 숫자를 뒤집기
            rev = rev * 10 + num % 10; // 현재 숫자의 마지막 자리를 추가
            num /= 10; // 숫자를 한 자리 줄이기
        }
        return rev; // 뒤집힌 숫자 반환
    }
}
