import java.io.FileNotFoundException;
import java.util.*;

public class TestMain {
    public static Scanner scan = new Scanner(System.in);
    static final int inf = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try {
            List<int[][]> graphs = GraphLoader.loadGraph("input1.txt"); // 그래프 인접행렬 생성

            System.out.println("1. 그래프 탐방 수행 결과\n");

            for(int i=0;i<graphs.size();i++){
                System.out.println("그래프 ["+(i+1)+"]");
                System.out.println("-".repeat(30));
                int [][] graph = graphs.get(i);
                DFS(graph, 1);
                BFS(graph);
                System.out.println("=".repeat(30));
            }

            System.out.println("\n2.최단 경로 구하기 수행 결과\n");
            List<int[][]> graphs2 = GraphLoader.loadGraph2("input2.txt"); // 가중치가 포함된 그래프 인접행렬 생성
            Dijkstra(graphs2); // 다익스트라 알고리즘 적용


        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        }
    }


    public static void BFS(int[][] D) {

        System.out.println("너비 우선 탐색");

        Queue<Integer> queue = new LinkedList<>(); //배열 진행 상황에 따른 큐
        ArrayList<Integer> print = new ArrayList<>(); //중복 없이 방문한 정점
        ArrayList<Integer> visit = new ArrayList<>(); //중복 상관없이 순서에 따라 방문한 정점

        //0부터 방문
        queue.offer(0);

        while (!queue.isEmpty()) {

            //큐에 있는 정점을 방문
            print.add(queue.peek());
            visit.add(queue.peek());
            int k = queue.poll();

            //큐의 앞에 있는 정점에 인접한 정점들을 조사
            for (int i = 0; i < D.length; i++) {

                if (D[k][i] == 1) {
                    boolean visited = false;

                    //visit에 있는 방문한 정점들 중 겹치는 것이 있는 지 조사
                    for (int j = 0; j < visit.size(); j++) {

                        if(visit.get(j) == i)
                            visited = true;
                    }
                    //겹치는 것이 없다면 i(인접한 정점)을 추가
                    if(visited == false) {
                        queue.offer(i);
                        visit.add(i);
                    }
                }
            }
        }

        //중복 없이 방문한 정점들을 print
        for (int i = 0; i < print.size(); i++) {
            if (i == 0) {
                System.out.print((print.get(i) + 1));
            } else {
                System.out.print(" - " + (print.get(i) + 1));
            }
        }
        System.out.println();
    }

    // DFS 메서드 (기존 DFS 클래스의 메서드를 수정하여 통합)
    public static void DFS(int[][] graph, int startnode) {

        System.out.println("깊이 우선 탐색");

        int numnodes = graph.length; // 노드의 개수를 나타내는 변수
        boolean[] visited = new boolean[numnodes]; // 방문한 노드 확인을 하는 visited

        Stack<Integer> stack = new Stack<>();
        stack.push(startnode); // 시작노드를 스택에 넣음

        while (!stack.isEmpty()) {

            int currentnode = stack.pop(); //스택에 있는 맨위의 객체를 현재노드에 대입, 가져온 객체는 스택에서 제거

            if(currentnode==1) {
                System.out.print(currentnode);
                visited[currentnode - 1] = true;
            }

            for (int neighbor = numnodes - 1; neighbor >= 0; neighbor--) {
                if (graph[currentnode - 1][neighbor] == 1 && !visited[neighbor]) { //인접행렬이고, 방문하지 않은 neighbor에 해당하는 노드
                    stack.push(neighbor + 1); //그에 해당하는 노드를 스택에 push
                }
            }
            if (!visited[currentnode - 1]&&currentnode!=1) {
                System.out.print(" - "+currentnode);
                visited[currentnode - 1] = true; // 방문한 노드 확인

            }
        }
        System.out.println();
    }






    private static void Dijkstra(List<int[][]> graphs) {
        for(int i=0;i<graphs.size();i++) { //그래프의 개수만큼 실행
            int[][] graph = graphs.get(i); //여러 그래프들 중에서 그래프 하나 불러오기
            ArrayList<Integer> S = new ArrayList<>(); //지금까지 어떤 정점 했는지 확인용
            ArrayList<ArrayList<Integer>> path = new ArrayList<>(); //정점마다 그래프 경로 담아두는 용도 이중 ArrayList로 구현

            for(int j=0;j<graph.length;j++){ //이중 ArrayList에서 각 경로는 모두 1부터 시작하므로 이를 위한 초기화용 for문
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(1); //경로 1추가
                path.add(arr);
            }
            /*
            0단계 정점 1에서 가장 작은 값 찾아 w담기
             */
            int[] D = graph[0];
            S.add(0);
            int w = 1;
            for(int j=1;j<D.length;j++){ //D에서 가장 작은값 찾기
                if(D[j] < D[w]){
                    w = j;
                }
            }
            path.get(w).add(w+1); //가장 작은 인덱스를 경로에 추가함 ex)4번째 정점이 가장 작은 가중치를 가질 경우 4번째 정점의 경로는 1-4가됨.
            S.add(w); //중복방지를 위해 s리스트에 추가함.
            /*
                0단계 끝(
             */
            while(S.size()<D.length){ //리스트 S에 모든 정점들이 채워질 때 까지(0단계->D배열의 가중치 조정->D배열의 최솟값 인덱스 구하기->앞 과정 반복)
                int min=-1; //최솟값을 구하는 인덱스 변수(예외상황으로 인해 -1로 초기화).
                for(int k=1;k<D.length;k++){ //가중치 조정하는 for문
                    if(!S.contains(k)) { //S리스트에 k값이 있는지 검사
                        if (D[k] > D[w] + graph[w][k] && graph[w][k] != inf) { //여기서 graph[w][k]!= inf 해준 이유는 최대의 int값이므로 더하면 음수가 됨.
                            D[k] = D[w] + graph[w][k];
                            path.get(k).clear(); //경로 없애기(새로 경로를 다시 넣기 위해)
                            path.get(k).addAll(path.get(w)); //w의 경로를 k에 모두 다시 넣기
                        }
                        min = k; //최솟값 임의 지정.
                    }
                }
                if(min != -1){
                    w = min; //w를 활용해야 하기 때문에 임시 최솟값 변수인 min을 w에 대입함.
                    for(int k=1;k<D.length;k++){ //최솟값 검사 for문
                        if(D[k] < D[w] && !S.contains(k)){
                            w = k;
                        }
                    }
                    path.get(w).add(w+1); //마지막으로 최솟값인 인덱스를 경로 추가
                    S.add(w); //최솟값 인덱스는 앞으로 검사 안하므로 S에 추가
                }
            }

            System.out.println("그래프 ["+(i+1)+"]");
            System.out.println("-".repeat(30));
            System.out.println("시작점: 1");
            for(int j=2;j<=path.size();j++){
                System.out.print("정점 ["+j+"] : ");
                String str= "";
                for(int k : path.get(j-1)){
                    str += k+" - ";
                }
                str = str.substring(0, str.length()-3);
                System.out.print(str);
                System.out.println(", 길이: "+D[j-1]);
            }
            System.out.println("=".repeat(30));
            System.out.println();
        }

    }
}

