import java.io.*;
import java.util.*;

public class Main {
    static class Points implements Comparable<Points> {
        int a; // 서류 순위
        int b; // 면접 순위

        public Points(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Points other) {
            // 서류 순위(a) 기준으로 오름차순 정렬
            return Integer.compare(this.a, other.a);
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리: BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine()); // 지원자 수
            Points[] people = new Points[N];

            // 지원자 데이터 입력
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 서류 순위
                int b = Integer.parseInt(st.nextToken()); // 면접 순위
                people[i] = new Points(a, b);
            }

            // 1. 서류 순위 기준 정렬
            Arrays.sort(people);

            // 2. 면접 순위 기준으로 합격자 계산
            int cnt = 0;
            int min = Integer.MAX_VALUE;

            for (Points p : people) {
                if (p.b < min) {
	               cnt++;
                   min = p.b; // 최소 면접 순위 갱신
                }
            }

            // 결과 출력
            System.out.println(cnt);
        }
    }
}
