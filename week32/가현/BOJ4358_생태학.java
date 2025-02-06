/*
 * 트라이(Trie)를 이용한 나무 종 분포도 계산 프로그램
 * 
 * [알고리즘 전개 방식]
 * 1. 데이터 구조 설정
 *    - TrieNode: 문자별 노드 구조 (문자 저장은 Map으로 구현)
 *    - Trie: 전체 트라이 구조 및 데이터 관리
 * 
 * 2. 데이터 입력 처리
 *    - 입력된 나무 종을 트라이에 삽입
 *    - 각 종의 출현 빈도 카운트
 * 
 * 3. 결과 계산 및 출력
 *    - 전체 나무 수 대비 각 종의 비율 계산
 *    - TreeMap을 이용한 사전순 정렬
 *    - 소수점 4자리까지 반올림하여 출력
 * 
 * 시간복잡도: O(N * L) - N: 입력 단어 수, L: 단어 평균 길이
 * 공간복잡도: O(K) - K: 모든 문자들의 총 수
 */

import java.io.*;
import java.util.*;

/**
 * 트라이의 각 노드를 표현하는 클래스
 * 각 노드는 문자들의 Map과 해당 위치까지의 단어 개수를 저장
 */
class TrieNode {
    // key: 문자, value: 다음 노드
    Map<Character, TrieNode> children;
    // 현재 노드에서 끝나는 단어의 개수
    int count;
    // 현재 노드가 단어의 끝인지 표시
    boolean isEndOfWord;
    
    /**
     * TrieNode 생성자
     * 각 노드 생성 시 필요한 변수들을 초기화
     */
    public TrieNode() {
        children = new HashMap<>();
        count = 0;
        isEndOfWord = false;
    }
}

/**
 * 전체 트라이 구조를 관리하는 클래스
 * 데이터 삽입과 통계 계산을 담당
 */
class Trie {
    // 트라이의 루트 노드
    private TrieNode root;
    // 각 단어의 출현 빈도를 저장 (TreeMap 사용으로 자동 정렬)
    private Map<String, Integer> wordCount;
    // 전체 단어 수
    private int totalWords;
    
    /**
     * Trie 생성자
     * 트라이 구조 초기화
     */
    public Trie() {
        root = new TrieNode();
        // TreeMap 사용으로 키(나무 종)가 자동으로 사전순 정렬됨
        wordCount = new TreeMap<>();
        totalWords = 0;
    }
    
    /**
     * 새로운 단어를 트라이에 삽입
     * @param word 삽입할 나무 종 이름
     */
    public void insert(String word) {
        TrieNode current = root;
        
        // 단어의 각 문자를 순회하며 트라이에 삽입
        for (char ch : word.toCharArray()) {
            // 현재 문자가 없으면 새 노드 생성
            current.children.putIfAbsent(ch, new TrieNode());
            // 다음 노드로 이동
            current = current.children.get(ch);
        }
        
        // 단어의 끝을 표시하고 카운트 증가
        current.isEndOfWord = true;
        current.count++;
        // 전체 단어 맵에 카운트 추가
        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        // 전체 단어 수 증가
        totalWords++;
    }
    
    /**
     * 각 나무 종의 분포를 계산하여 출력
     * 소수점 4자리까지 반올림하여 출력
     */
    public void printDistribution() {
        // TreeMap을 순회하며 각 종의 분포 출력
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            // 백분율 계산: (해당 종의 수 / 전체 나무 수) * 100
            double percentage = (entry.getValue() * 100.0) / totalWords;
            // 종 이름과 백분율을 소수점 4자리까지 출력
            System.out.printf("%s %.4f%n", entry.getKey(), percentage);
        }
    }
}

/**
 * 메인 클래스
 * 입력을 처리하고 결과를 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Trie trie = new Trie();
        String line;
        
        // EOF나 빈 줄이 나올 때까지 입력 처리
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            trie.insert(line.trim());
        }
        
        // 최종 분포도 출력
        trie.printDistribution();
    }
}