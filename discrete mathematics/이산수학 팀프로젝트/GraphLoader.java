
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphLoader {
    /**
     * input1.txt 파일로부터 그래프 정보를 읽어 인접 행렬 리스트를 생성. (텍스트 파일에 그래프 정보가 여러 개 포함될 수 있음)
     *
     * @param filename 읽을 파일의 이름.
     * @return 각 그래프에 대한 인접 행렬의 리스트.
     * @throws FileNotFoundException 파일을 찾을 수 없을 때 발생하는 예외.
     */
    public static List<int[][]> loadGraph(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename)); // 파일 읽기를 위한 스캐너
        List<int[][]> graphs = new ArrayList<>(); // 각 그래프의 인접 행렬을 저장할 리스트

        // 파일의 끝까지 반복
        while (scanner.hasNextInt()) {
            int vertices = scanner.nextInt(); // 그래프의 정점 개수
            int[][] adjacencyMatrix = new int[vertices][vertices]; // 그래프의 인접 행렬

            scanner.nextLine(); // 다음 줄로 이동

            // 각 정점에 대하여
            for (int i = 0; i < vertices; i++) {
                String line = scanner.nextLine(); // 한 줄 읽기
                String[] tokens = line.split(" "); // 줄을 공백 기준으로 나눔
                int node = Integer.parseInt(tokens[0]) - 1; // 현재 정점 번호

                // 인접한 정점들 처리
                for (int j = 1; j < tokens.length; j++) {
                    int adjacentNode = Integer.parseInt(tokens[j]) - 1; // 인접한 정점 번호
                    adjacencyMatrix[node][adjacentNode] = 1; // 인접 행렬 업데이트
                }
            }

            graphs.add(adjacencyMatrix); // 완성된 인접 행렬을 리스트에 추가
        }

        scanner.close(); // 스캐너 종료
        return graphs; // 인접 행렬 리스트 반환
    }

    /**
     * input2.txt 파일로부터 그래프 정보를 읽어 인접 행렬 리스트를 생성.
     *
     * @param filename 읽을 파일의 이름.
     * @return 각 그래프에 대한 인접 행렬의 리스트.
     * @throws FileNotFoundException 파일을 찾을 수 없을 때 발생하는 예외.
     */
    public static List<int[][]> loadGraph2(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename)); // 파일 읽기를 위한 스캐너
        List<int[][]> graphs = new ArrayList<>(); // 각 그래프의 인접 행렬을 저장할 리스트

        // 파일의 끝까지 반복
        while (scanner.hasNextInt()) {
            int vertices = scanner.nextInt(); // 그래프의 정점 개수
            int[][] adjacencyMatrix = new int[vertices][vertices]; // 그래프의 인접 행렬

            scanner.nextLine(); // 다음 줄로 이동

            // 각 정점에 대하여
            for (int i = 0; i < vertices; i++) {
                String line = scanner.nextLine(); // 한 줄 읽기
                String[] tokens = line.split(" "); // 줄을 공백 기준으로 나눔
                int node = Integer.parseInt(tokens[0]) - 1; // 현재 정점 번호

                // 인접한 정점들과 가중치 처리
                for (int j = 1; j < tokens.length; j += 2) {
                    int adjacentNode = Integer.parseInt(tokens[j]) - 1; // 인접한 정점 번호
                    int weight = Integer.parseInt(tokens[j + 1]); // 간선의 가중치
                    adjacencyMatrix[node][adjacentNode] = weight; // 인접 행렬에 가중치 저장
                }

                // 연결되지 않은 정점 간의 거리를 무한대로 설정
                for(int j=0; j<vertices; j++){
                    if(adjacencyMatrix[i][j] == 0 && i != j){
                        adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            graphs.add(adjacencyMatrix); // 완성된 인접 행렬을 리스트에 추가
        }

        scanner.close(); // 스캐너 종료
        return graphs; // 인접 행렬 리스트 반환
    }
}
