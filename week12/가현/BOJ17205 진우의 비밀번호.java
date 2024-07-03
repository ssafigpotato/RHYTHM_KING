package jinwoo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 비밀번호의 최대 길이
        sc.nextLine(); // (개행 문자 소비)
        String pwd = sc.nextLine(); // (ㄹㅇ)비밀번호

        long ans = 0; // 정답을 저장할 변수 (맞히는 시간), 정답의 최대 = 약 100억
        int aIndex = 'a'; // aIndex= a의 ASCII 코드 값 // 몇번째 알파벳인지 알기 위해, 기준은 맨 앞인 a, int는 ASCII를 사용하므로
        N -= 1; // 비밀번호 길이를 1감소 => (인덱스로 사용하기 위해) => alphaIndex 뒤의 문자들에 대해서 등비수열을 적용할 것이니까

        // ( 첫 번째 문자부터 )  각 알파벳에 대하여 반복
        for (char alpha : pwd.toCharArray()) {
            int alphabetIndex = alpha - aIndex; // a 이후 몇번째 알파벳인지 계산
            if (alphabetIndex > 0) { // alpha가 a라면 계산할 필요 없으므로 pass

                // : 현재 위치 이전의 모든 가능한 조합 계산
                // 등비수열의 합: (첫째항 a, 공비 r인 등비수열의 n항까지의 합)
                //           : a(r^n -1) / (r-1)
                // 등비수열을 이용하는 이유: 1글자 조합(26) - 2글자 조합(2626) - 3글자 조합(262626) - ...

                // 예를 들어 [정답=cat, 길이=3] : 
                // 1. 첫번째 문자 'c'에 대하여 alphabetIndex=2
                // (a나 b로 시작하는 경우) * (남은 길이 2자리에 대한 등비수열) = 3자리인 경우의 수(22626) + 2자리인 경우의수(226)  = 1404
                // 2. 두번째 문자 'a'에 대하여 alphabetIndex=0 
                // => 'ca'를 입력하는 경우에 대해서만 +1
                // 3. 세번째 문자 't'에 대해서 alphabetIndex=19, N=0(남은 길이 없음), + 'cat'입력하는 경우

                // 마지막에 +alphabetIndex를 하는 이유 : 현재 위치에서 단일 문자 경우를 고려하기 위함 , ex) 'a', 'b'
                ans += (alphabetIndex) * 26 * (Math.pow(26, N) - 1) / 25 + alphabetIndex;
            }

            // * 에서 현재 위치 이전의 모든 가능한 조합 계산 -> 현재 자기자신은 계산 안됨 => 본인을 더해줌
            // 예를 들어 [정답 = cat]: 위()에서는 a로 시작하는 경우, b로 시작하는 경우를 모두 계산 / ans += 1을 통해 'c'를 입력하는 경우를 더해줌
            ans += 1; 

            // n번째 문자에 대한 계산 끝남 -> 다음 n+1번째 문자로 이동 -> 남은 길이 감소
            N -= 1;
        }

        System.out.println(ans); // 정답 출력
        sc.close();
    }
}