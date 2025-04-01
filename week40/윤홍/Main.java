package BOJ1644;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*
    연속 되는 소수의 합으로만 원하는 값이 만들어지는 경우의 수를 구해야 한다.
    그러면 1차 접근은 투포인터로 0 과 주어진 숫자 사이를 한칸씩 줄여가며
    그 안에 있는 소수의 숫자들의 합이 주어진 값보다 크면 다시 줄이고 다시 줄이고 이런 식으로 하면 되지 않을까.
     */

    static List<Integer> primes = new ArrayList<>();
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        SieveOfEratosthenes(N);

        TwoPointer(primes, N);


        System.out.println(ans);

    }

    static void TwoPointer(List<Integer> list, int N){
        int left = 0, right = 0, sum = 0;

        while(left < list.size() ){
            if( sum < N ){
                if(right < list.size()){
                    sum += list.get(right);
                    right ++;
                } else{
                    break;
                }
            } else if( sum > N){
                sum -= list.get(left);
                left++;
            } else{
                ans ++;
                sum -= list.get(left);
                left ++;
            }
        }
    }

    static void SieveOfEratosthenes(int N) {
        boolean[] isPrime = new boolean[N + 1];

        for(int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        for(int i = 2; i * i <= N; i++){
            if(isPrime[i]) {
                for(int j = i * i; j <= N; j += i){
                    isPrime[j] = false;
                }
            }
        }

        for(int i = 2; i <= N; i++){
            if(isPrime[i]) {
                primes.add(i);
            }
        }


    }
}
