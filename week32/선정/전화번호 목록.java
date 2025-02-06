

import java.util.*;


public class Main {
    // TrieNode 클래스 생성
    static class TrieNode {
        // <현재 문자열, 다음 노드>
        HashMap<Character, TrieNode> children;
        // 단어의 끝인지 여부
        boolean end;
        // 생성자
        TrieNode(){
            children = new HashMap<>();
            end = false;
        }
    }
    static TrieNode root; // TrieNode타입의 루트를 선언
    static boolean insertAndSearch(String word){
        TrieNode currNode = root; // 루트 부터 돌면서
        for(char c : word.toCharArray()){
            if(currNode.end){ // 현재노드가 단어의 끝이라면 접두어가 존재함 -> 기존의 번호가 이번에 넣은 번호의 접두어인지 확인
                return false;
            }
            // 현재 문자열과 다음 노드를 삽입
            currNode.children.putIfAbsent(c, new TrieNode());
            // 현재노드는 다음노드로 갱신
            currNode = currNode.children.get(c);
        }
        // 만약 끝까지 왔다면 이번에 추가하려는 번호가 기존 번호의 접두사인지를 검사!
        // 예) 911125426이 저장된 상태에서 911을 추가하려고하면, 911이 기존 번호의 접두사가 되어버림
        // 근데 사실 리스트를 오름차순으로 정렬해두면 긴 번호가 나중에 저장되므로 이거에 걸릴 확률은 거의 없어짐
        if(!currNode.children.isEmpty()) return false;
        // 마지막 문자열은 단어의 끝이므로 true
        currNode.end = true;
        return true;
    }
    static int T;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(T-- >0){
            int N = sc.nextInt();
            List<String> num = new ArrayList<>();

            root = new TrieNode(); // 새로운 Trie객체 생성

            for(int i = 0; i <N; i++){
                num.add(sc.next());
            }

            // 전화번호를 정렬 (짧은 번호부터 삽입해야 일관성 검사가 정확함)
            Collections.sort(num);

            boolean isConsistent = true;
            for(String n : num){
                if(!insertAndSearch(n)){
                    isConsistent = false;
                    break;
                }
            }

            System.out.println(isConsistent ? "YES" : "NO");

        }
    }
}
