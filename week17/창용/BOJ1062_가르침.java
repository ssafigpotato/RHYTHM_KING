import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1062_가르침 {
    static int N, K; // 단어의 갯수 N과 알파벳의 갯수 K
    static int flag;
    static int max = 0;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]); // 단어의 갯수를 입력
        K = Integer.parseInt(s[1]); // 알파벳의 갯수를 입력
        words = new String[N]; // 단어들을 담아줄 words 배열 생성

        if(K < 5){
            // 사용할 수 있는 알파벳의 갯수가 5개 미만이라면 읽을 수 있는 단어가 존재하지 않으므로 0을 출력하고 종료한다.
            System.out.println(0);
            return;
        }else if(K == 26){
            // 사용할 수 있는 알파벳의 갯수가 26개라면 읽을 수 없는 단어가 존재하지 않으므로 주어진 단어의 갯수를 출력하고 종료한다.
            System.out.println(N);
            return;
        }else{
            for(int i = 0; i < N; i++){
                // 주어진 단어의 갯수만큼 반복한다.
                String str = br.readLine();

                // 시작 4개의 알파벳과 끝부분 4개의 알파벳은 a, n, t, i, c로 고정되어있기 때문에, 제외한다.
                words[i] = str.substring(4, str.length() - 4);
            }
            flag = 0;
            max = 0;
            // 초기 a, n, t, i, c 방문 체크
            flag |=1 << ('a' - 'a'); // | 비트 연산 중 or를 += 처럼 적용한 것이다.
            flag |=1 << ('n' - 'a'); // 예를 들어 a |= b 라면 a = a | b가 되는 것이다.
            flag |=1 << ('t' - 'a'); // 즉, 주어지는 단어는 무조건 a, n, t, i, c를 가지고 있기 때문에
            flag |=1 << ('i' - 'a'); // a, n, t, i, c 번째의 비트에 1을 넣어주는 것이다.
            flag |=1 << ('c' - 'a');
            combination(0, 0, flag);
            System.out.println(max);
        }
    }
    private  static void combination(int idx, int start, int flag){
        // 주어진 알파벳 갯수 K에서 a, n, t, i, c 를 제외한 단어 조합을 탐색할 것이다.
        // 주어진 알파벳을 가지고 조합을 완성했다면 결과를 확인한다.
        if(idx == K - 5){
            int count = 0;

            // 주어진 모든 단어를 검사한다.
            for(int i = 0; i < N; i++){
                // N 개의 단어 갯수만큼 반복한다.

                // 해당 단어를 읽을 수 있는지 판단하는 불리언 값이다.
                boolean isValid = true;

                // 선택한 단어의 각 알파벳을 대상으로 읽을 수 있는지 확인한다.
                for(int j = 0; j < words[i].length(); j++){
                    // 단어의 갯수만큼 반복할 것이다.

                    // 알파벳 하나라도 flag 방문처리가 되어있지 않다면 바로 종료한다.
                    if((flag & 1 << (words[i].charAt(j) - 'a')) == 0){
                        isValid = false;
                        break;
                    }
                }
                // 모든 알파벳을 읽을 수 있다면 카운트 해준다.
                if(isValid){
                    count++;
                }
            }
            // 해당 조합으로 만들 수 있는 최대 단어 개수를 비교한다.
            max = Math.max(max, count);
            return;
        }
        for(int i = start; i < 26; i++){ // 알파벳 a 부터 z까지 순회한다.
            if((flag & 1 << i) != 0) continue; // 순회하는 알파벳이 flag에 존재하지 않는다면 다음 알파벳으로 넘어간다.
            combination(idx + 1, i + 1, flag | 1 << i);
        }
    }
}
