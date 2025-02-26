package BOJ1043;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<List<Integer>> combi;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();

        List<Integer> truth = new ArrayList<>();

        int[][] graph = new int[N + 1][N + 1];

        for (int i = 0; i < T; i++) {
            truth.add(sc.nextInt());
        }

        List<int[]> total = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int P = sc.nextInt();
            int[] arr = new int[P];
            for (int j = 0; j < P; j++) {
                arr[j] = sc.nextInt();
            }
            total.add(arr);
        }

        combi = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int[] curr = total.get(i);
            boolean[] visited = new boolean[curr.length];

            combination(curr, visited, 0, 0, 2);
        }

        for (List<Integer> x : combi) {
            graph[x.get(0)][x.get(1)] = 1;
            graph[x.get(1)][x.get(0)] = 1;
        }

//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j <= N; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}

        for (int i = 0; i < truth.size(); i++) {
            for (int j = 0; j <= N; j++) {
                if (graph[truth.get(i)][j] == 1 && !truth.contains(j)) {
                    truth.add(j);
                }

            }
        }
        int cnt = 0;
        THIS: for (int i = 0; i < total.size(); i++) {
            int[] curr = total.get(i);
            for (int j = 0; j < curr.length; j++) {
                for (int k = 0; k < truth.size(); k++) {
                    if (curr[j] == truth.get(k)) {
                        continue THIS;
                    }
                }
            }
            cnt++;
        }

        System.out.println(cnt);

    }

    static void combination(int[] arr, boolean[] visited, int start, int dp, int r) {

        if (dp == r) {
            List<Integer> arr2 = new ArrayList<>();

            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    arr2.add(arr[i]);
                }
            }

            combi.add(arr2);

            return;
        }

        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(arr, visited, i + 1, dp + 1, r);
                visited[i] = false;
            }
        }

    }

}

