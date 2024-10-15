import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ3671_산업스파이의편지 {

    /**
     *
     * 문제설명 :
     *
     * 테스트케이스 횟수가 주어지고, 각 테스트케이스마다 하나의 숫자가 주어진다.
     *
     * 주어진 숫자를 이루는 0~9까지의 숫자를 하나의 단위로 놓고 조합할 수 있는 모든 숫자 중 소수의 갯수를 출력해야 한다.
     *
     * ex) 17이 주어진다면 : 1, 7, 17, 71이렇게 조합할 수 있다.
     * => 이 때는 소수가 7, 17, 71로 3개가 존재한다.
     *
     * ex) 만약 011이 주어진다면 : 011은 11로 취급한다. 만약 조합 결과로 101이라는 숫자가 두 번 나와도 소수의 갯수를 하나로 봐야한다.
     *
     */


    /**
     *
     * 1. 어떻게 조합을 이룰 것인가.
     *
     * 순열 조합 문제에는 여러 방식의 조합법이 사용된다.
     *
     * 모든 요소가 한 번씩 반드시 사용되면서 순서가 바뀌는 방식 (123, 132, 213...)
     * 모든 요소에 대해 사용되지 않을 경우도 고려하여 조합하는 방식(1, 2, 3, 12, 13, 123...)
     *
     * 하지만 요소가 사용되지 않는 경우를 고려하는 방식 또한,
     * 메서드 내부에 for문이 사용되는가 사용되지 않는가에 따라 결과가 다르다.
     * for문을 사용하지 않을때 (1, 12, 123, 13...) => 순서가 바뀌지 않는다. (31이 불가능)
     * for문을 사용할 때 (1, 12, 123, 2, 21, 213...) => 순서가 바뀌는 경우도 조합 가능.
     *
     *
     * 2. 어떻게 소수임을 판단할 것인가.
     *
     * 모든 자연수들의 가장 큰 소인수는 자기자신을 제곱근한 값보다 같거나 작다. (2와 3을 제외하고 생각)
     *
     * 9를 예로 들어본다면 9의 소인수는 3이다. 따라서 9가 소수인지 판별하기 위해서는 1부터 3까지만 나누기를 시행하여 나머지를 살펴보면 된다.
     *
     * 13을 예로 들어보자.
     * 13의 제곱근은 대략 3.%%%일 것이다.
     * 따라서 13또한 1부터 3을 이용해서 나누었을 때 나누어떨어지지 않으면 소수라고 확정지을 수 있다.
     *
     * 16의 약수는 1, 2, 4, 8, 16이다.
     * 16이 소수임을 알기 위해서는 8까지 탐색해야하는 것이 아닌, 16의 제곱근이 4까지만 탐색해도 되는 것이다.
     * 16이라는 약수의 존재는 1과 대응되고, 8이라는 약수의 존재는 2와 대응되기에 2가 확인되면 굳이 8을 확인할 필요가 없는 것이다.
     *
     */

    /**
     *
     * 문제를 풀지 못한 이유.
     *
     * 로직은 예제를 올바르게 처리하였지만 제출시 메모리 초과가 발생하였고, 혼자서 메모리 소모량을 줄이는 방법을 찾지 못했다.
     *
     * 해당 문제에서 메모리 소모량을 줄이기 위해서는 String 객체 사용량을 줄이고 조합을 위한 재귀를 최소화해야한다.
     *
     * String 객체 사용량 줄이기와 조합 재귀 최소화 둘 중 하나만 적용하면 어김없이 메모리 초과가 발생하였다.
     *
     */

    // 소수의 개수를 출력하기 위한 Integer 타입 변수 answer
    private static Integer answer;
    // 조합을 하며 반복문을 돌릴 때, 이미 사용한 숫자를 또 사용하지 않도록 해줄 boolean 타입의 배열.
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 입력값을 빠르게 받아오기 위한 BufferedReader와 StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 소수에 해당하는 숫자가 중복되지 않도록 모아두는 Set 저장소
        HashSet<Integer> testCasePrimes = new HashSet<>();

        int test_case = Integer.parseInt(st.nextToken());

        for(int t = 0; t < test_case; t++) {
            answer = 0;

            // 사용되는 숫자의 갯수는 최대 7개이므로 7 크기의 boolean 배열을 생성한다.
            visited = new boolean[7];

            // 메모리 소모를 최소화하기 위해 하나의 Set 저장소를 테스트 케이스마다 비워주면서 재사용한다.
            testCasePrimes.clear();

            // 스파이가 조합해야할 정보를 받아와서 nt 변수에 저장한다.
            st = new StringTokenizer(br.readLine());
            String nt = st.nextToken();

            // 조합의 결과물 길이를 미리 정해두어 조합을 진행한다.
            // 1길이의 조합, 2길이의 조합...
            for(int i = 1; i <= nt.length(); i++){
                // numberText : 주어진 입력값
                // depth : 조합 중 선택을 몇 번 했는지 파악하기 위한 값
                // numberLength: 주어진 입력값의 길이
                // depthTarget : 조합의 결과물 길이
                // testCasePrimes : 소수를 담아둘 Set
                // output : 선택한 각 숫자를 담아둘 char 타입의 배열
                combination(nt, 0, nt.length(), i, testCasePrimes, new char[i]);
            }
            
            // 정답 출력
            System.out.println(answer);
        }
    }

    public static void combination(String numberText, int depth, int numberLength, int depthTarget, HashSet<Integer> testCasePrimes, char[] output){

        // 정해준 길이의 조합 결과물이 완성되었을 경우
        if(depth == depthTarget){

            // 메모리 소모를 최소화하기 위해 String 객체를 생성하지 않고 StringBuilder를 이용.
            StringBuilder sb = new StringBuilder();
            // 선택한 요소를 넘어둔 output 배열을 가져와 전부 StringBuilder에 넣어준다.
            for(char ch: output){
                sb.append(ch);
            }

            // 조합물을 Integer 타입의 정수로 변환.
            int integerNumber = Integer.parseInt(sb.toString());

            // 해당 숫자가 소수이며, Set 자료 저장소에 존재하는지 판단.
            // 사실 이러면 중복을 방지하기 때문에 Set 자료구조일 필요가 없음.
            if(detectPrime(integerNumber) && !testCasePrimes.contains(integerNumber)){
                testCasePrimes.add(integerNumber);
                answer++;
                return;
            }

            // 소수가 아니라면 아무런 수행 없이 return
            return;

        }

        for(int i = 0; i < numberLength; i++){
            // visited를 구현해야 한 번 사용된 숫자는 다시 사용되지 않음.
            if(visited[i]){
                continue;
            }

            // char 타입 배열에 선택한 요소를 할당한다.
            output[depth] = numberText.charAt(i);

            // 방문체크
            visited[i] = true;
            combination(numberText, depth + 1, numberLength, depthTarget, testCasePrimes, output);
            // 백트래킹을 위한 방문체크 해제
            visited[i] = false;

        }
    }

    public static boolean detectPrime(int number){
        int rootN = (int) Math.sqrt(number);

        if(number == 1){
            return false;
        }

        // 2와 3의 경우 제곱근 또는 제곱근 바로 아래의 정수까지 탐색하면 소수로 판별되지 않으므로,
        // 따로 처리를 해줘야 한다.
        if(number == 2 || number == 3){
            return true;
        }

        boolean isPrime = false;

        for(int i = 2; i <= rootN; i++){
            if(number % i == 0){
                break;
            }

            // 제곱근 또는 제곱근 바로 아래의 정수까지 탐색해도 나누어 떨어지지 않으면,
            // 소수로 학정한다.
            if(i == rootN){
                isPrime = true;
            }
        }

        return isPrime;
    }

}
