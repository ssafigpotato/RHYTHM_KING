package BOJ3671;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    /*
    진짜 웬 듣도보도 못한 알고리즘
    바로 '에라토스테네스의 체';;;

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            // 여기서 숫자를 받고
            Set<Integer> primes = new HashSet<>();
            // 소수로 저장한 값들이 중복되는 것을 방지하기 위해 Set으로 구현
            Set<String> seenPermutaions = new HashSet<>();
            // 여기는 이제 이미 확인한 순열인지 아닌지 확인용 Set으로 시간 단축
            Permutaion("", str, primes, seenPermutaions);
            System.out.println(primes.size());

        }
    }

    static void Permutaion(String str, String remaining, Set<Integer> primes, Set<String> seenPermutaions) {
        if (!str.isEmpty() && !seenPermutaions.contains(str)) {
            seenPermutaions.add(str);
            // 바로 추가해줘서 중복방지
            int num = Integer.parseInt(str);
            if (isPrime(num)) {
                primes.add(num);
            }
        }

        for (int i = 0; i < remaining.length(); i++) {
            Permutaion(str + remaining.charAt(i),
                    remaining.substring(0, i) + remaining.substring(i + 1),
                    primes, seenPermutaions);
        }
    }

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
