import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B1941 {
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] selected;
    static boolean[] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        selected = new int[7];
        visited = new boolean[25];

        for(int i = 0; i < 5; i++){
            map[i] = br.readLine().toCharArray();
        }

        comb(0, 0, 0);

        System.out.println(result);
    }

    private static void comb(int index, int depth, int cntS){
        if(depth == 7){
            if(cntS >= 4){
                if(bfs()){
                    result++;
                }
            }
            return;
        }
        for(int i = index; i < 25; i++){
            visited[i] = true;
            selected[depth] = i;
            if(map[i/5][i%5] == 'S'){
                comb(i+1, depth+1, cntS+1);
            }else {
                comb(i+1, depth+1, cntS);
            }
            visited[i] = false;
;        }
    }
    private static boolean bfs(){
        boolean[] check = new boolean[25];
        int cnt = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(selected[0]);

        while(!q.isEmpty()){
            int cur = q.poll();
            check[cur] = true;

            for(int i = 0; i < 4; i++){
                int x = (cur/5) + dx[i];
                int y = (cur%5) + dy[i];
                if(x < 0 || y < 0 || x >= 5 || y >= 5){
                    continue;
                }
                if(check[x*5+y]){
                    continue;
                }
                if(visited[x*5+y]){
                    cnt++;
                    check[x*5+y] = true;
                    q.add(x*5+y);
                }
            }
        }
        if(cnt == 7){
            return true;
        }else {
            return false;
        }

    }
}
