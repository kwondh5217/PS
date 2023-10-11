import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B4485 {
    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int problem = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) {
                System.out.println(sb);
                System.exit(0);
            }

            map = new int[n][n];
            dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Integer.MAX_VALUE - 1; // 초기화
                }
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            sb.append("Problem ").append(problem).append(": ").append(dist[n - 1][n - 1]).append("\n");
            problem++;
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.money, o2.money));
        q.add(new Node(0, 0, map[0][0]));
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int money = poll.money;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                int newMoney = money + map[nx][ny];
                if (newMoney < dist[nx][ny]) {
                    dist[nx][ny] = newMoney;
                    q.add(new Node(nx, ny, newMoney));
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int money;

        public Node(int x, int y, int money) {
            this.x = x;
            this.y = y;
            this.money = money;
        }
    }
}
