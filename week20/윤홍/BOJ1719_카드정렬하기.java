import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(sc.nextInt());
        }

        int size = pq.size();

        int answer = 0;

        ArrayDeque<Integer> ad = new ArrayDeque<>(pq);


        if (size == 1) {
            answer = 0;
        } else {
            for (int i = 0; i < size; i++) {
                int sum = 0;
                int num1 = pq.poll();
                sum += num1;
                if (pq.peek() == null) {
                    break;
                }
                int num2 = pq.poll();
                sum += num2;

                answer += sum;
                pq.add(sum);

            }
        }

        System.out.println(answer);
    }

}
