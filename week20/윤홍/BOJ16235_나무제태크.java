import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<List<List<Integer>>> tree;
    static int N;
    static int[][] fertilizer;
    static int[] di = {-1, 0, 1, 0, -1, 1, -1, 1};
    static int[] dj = {0, -1, 0, 1, -1, 1, 1, -1};
    static List<int[]> deadTree;

    /*
    해당 문제의 핵심은 한칸에 나무 여러개를 심을 수 있다는 것.
    1번째 List는 열을, 그 안의 2번째 List는 행을, 그안의 3번째 List는 각 나무의 나이를 의미한다.
     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        // 지도 크기
        int M = sc.nextInt();
        // 심은 나무의 수
        int K = sc.nextInt();
        // k 년이 지난 후 살아남은 나무의 수는?

        int[][] s2d2 = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                s2d2[i][j] = sc.nextInt();
            }
        }

        fertilizer = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                fertilizer[i][j] = 5;
            }
        }

        tree = new ArrayList<>();
        deadTree = new ArrayList<>();

        for(int i = 0; i < N; i++){
            List<List<Integer>> li = new ArrayList<>();
            for(int j = 0; j < N; j++){
                List<Integer> li2 = new ArrayList<>();
                li.add(li2);
            }
            tree.add(li);
        }

        for(int i = 0; i < M; i++){
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int age = sc.nextInt();

            tree.get(x).get(y).add(age);
        }

        // 여기서 특정 인덱스 값을 변경할 수 있는 set 사용
        for(int i = 0; i < K; i++){
            Spring();
            summer();
            fall();
            winter(s2d2);
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                cnt += tree.get(i).get(j).size();
            }
        }

        System.out.println(cnt);


    }

    static void Spring(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Collections.sort(tree.get(i).get(j));
                for(int k = 0; k < tree.get(i).get(j).size(); k++){
                    if(tree.get(i).get(j).get(k) <= fertilizer[i][j]){
                        fertilizer[i][j] -= tree.get(i).get(j).get(k);
                        // 비료를 소모하고
                        tree.get(i).get(j).set(k, tree.get(i).get(j).get(k) + 1);
                        // 나이를 하나 추가해주고
                    } else{
                        // 나이만큼 비료가 없는 경우는 죽어서 1/2만큼 비료가 된다.
                        int death = tree.get(i).get(j).get(k);
                        tree.get(i).get(j).remove(k);
                        deadTree.add(new int[]{i, j, death});
                        k--;
                    }
                }

            }
        }
    }

    static void summer(){
        for(int i = 0; i < deadTree.size(); i++){
            int[] curr = deadTree.get(i);
            int x = curr[0];
            int y = curr[1];
            int death = curr[2];
            fertilizer[x][y] += death/2;
        }
        deadTree = new ArrayList<>();
    }



    static boolean check(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void fall(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < tree.get(i).get(j).size(); k++){
                    if(tree.get(i).get(j).get(k) % 5 == 0){
                        // 번식하는 단계
                        for(int d = 0; d < 8; d++){
                            int ni = i + di[d];
                            int nj = j + dj[d];
                            if(check(ni, nj)){
                                tree.get(ni).get(nj).add(1);
                            }

                        }
                    }
                }
            }
        }
    }

    static void winter(int[][]arr){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                fertilizer[i][j] += arr[i][j];
            }
        }
    }

}
