package BOJ1911;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 웅덩이의 수
        int N = sc.nextInt();

        // 널빤지의 수
        int L = sc.nextInt();

        List<Integer> list = new ArrayList<>();


        for (int i = 0; i < N * 2; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);
        // 여기까지 물웅덩이 사이의 길을 오름차순으로 정렬
        // 그럼 이제 0부터 짝수 숫자의 위치는 시작지점
        // 1 부터 홀수 자리의 위치는 끝지점이 된다

//        System.out.println(list);

        int count = 0; // 사용된 널빤지 갯수
        int currEnd = 0; // 현재 널빤지가 끝나는 위치

        for (int i = 0; i < N * 2 - 1; i += 2) {
            int start = list.get(i);
            int end = list.get(i + 1);

//            System.out.println(start + " " + end);

            if (currEnd < start) {
                /*
                여기서의 조건은 내가 지금까지 놓은 널빤지의 끝이 다음 시작 지점보다 작은 경우에 해당
                여기서 사용되는 식은 지금 웅덩이의 끝지점 - 시작지점을 널빤지 길이로 나눈것의 올림을 구하는 것.
                그러면 필요한 널빤지의 갯수가 나오고
                그만큼 시작 지점에서 놓은 널빤지의 길이만큼 널빤지가 끝나는 위치를 갱신해준다.
                 */
                int need = (int) Math.ceil((double) (end - start) / L);
                count += need;
                currEnd = start + (L * need);
            } else if (currEnd < end) {
                /*
                여기서는 이제 내가 지금 놓은 널빤지의 위치가 웅덩이 시작 지점은 넘었지만
                그 해당 웅덩이의 끝에는 도달하지 못한 경우에 대한 식이다.
                로직은 비슷하게 가져간다.
                 */
                int need = (int) Math.ceil((double) (end - currEnd) / L);
                count += need;
                currEnd += (need * L);
            }
        }

        System.out.println(count);


    }
}
