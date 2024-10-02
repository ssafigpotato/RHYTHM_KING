import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5175_문제없는문제 {
    static List<Integer> arr = new ArrayList<>();
    static List<List<Integer>> source;
    static int bit;
    static List<Character> visitedProblem;
    static List<Character> finalProblem;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st  = new StringTokenizer(br.readLine());

        int test_case = Integer.parseInt(st.nextToken());


        for(int t = 1; t <= test_case; t++) {
            bit = 1;

            st  = new StringTokenizer(br.readLine());
            int algorithmNum = Integer.parseInt(st.nextToken());
            int problemNum = Integer.parseInt(st.nextToken());
            visitedProblem = new ArrayList<>();
            finalProblem = new ArrayList<>();
            source = new ArrayList<>();

            for(int i = 0; i < problemNum; i++) {
                List<Integer> temp = new ArrayList<>();

                st = new StringTokenizer(br.readLine());

                while(st.hasMoreTokens()){
                    temp.add(Integer.parseInt(st.nextToken()));
                }

                source.add(temp);
            }

            for(int i = 0; i < algorithmNum; i++) {
                bit |= 1 << i;
            }

            combination(0, problemNum, 0);

            String answer = "";

            for(int i = 0; i < finalProblem.size(); i++) {
                answer += finalProblem.get(i) + " ";
            }

            if(t >= 2){
                System.out.println();
            }
            System.out.println("Data Set " + t + ": " + answer);

        }

    }

    public static void combination(int problem, int depth, int calBit){
        if(problem == depth){
            if(bit == calBit){
                if(finalProblem.isEmpty() || finalProblem.size() > visitedProblem.size()){
                    finalProblem.removeAll(finalProblem);
                    finalProblem.addAll(visitedProblem);
                }
            }
            return;
        }

        List<Integer> probAlgo = source.get(problem);

        int algoBit = 0;

        for(int i = 0; i < probAlgo.size(); i++){
            int tmpBit = 0;

            tmpBit |= 1 << (probAlgo.get(i) - 1);

            algoBit |= tmpBit;
        }

        algoBit |= calBit;

        // 문제를 선택했을 때
        char ch =  (char) (problem + 65);
        visitedProblem.add(ch);
        combination(problem + 1, depth, algoBit);
        visitedProblem.remove(visitedProblem.size() - 1); // 백트래킹

        // 문제를 선택하지 않았을 때
        combination(problem + 1, depth, calBit);
    }
}
