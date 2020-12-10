package 백준;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준6087_레이저통신 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char C = 'C';
    static final int START = 0;
    static final int END = 1;
    static int w, h, idx;
    static char[][] map;
    static Point[] p;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        p = new Point[2];
        map = new char[h][w];
        visited = new boolean[h][w];
        turnCnt = new int[h][w];
        idx = 0;

        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == C) {
                    p[idx++] = new Point(i, j);
                }
            }
            Arrays.fill(turnCnt[i], Integer.MAX_VALUE);
        }
        min = Integer.MAX_VALUE;
        visited[p[START].x][p[START].y] = true;
        dfs(p[START].x, p[START].y,-1, 0);

        System.out.println(turnCnt[p[END].x][p[END].y] - 1);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] visited;
    static int[][] turnCnt;
    static int min;

    private static void printMap(int[][] map)
    {
        for (int i=0; i<map.length; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    private static void dfs(int x, int y, int exDir, int turn) {
        if (x == p[END].x && y == p[END].y) {
            turnCnt[x][y] = turn;
            printMap(turnCnt);
            return;
        }

        turnCnt[x][y] = turn;

        printMap(turnCnt);
        for (int dir = 0; dir < 4; dir++) {
            int nx, ny;

            nx = x + dx[dir];
            ny = y + dy[dir];
            if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny]
            && map[nx][ny] != WALL)
            {
//                System.out.println(nx+" "+ny);
//                System.out.println("dir : "+dir +" exDir : "+exDir+" turn : "+turn+" turnCnt[nx][ny] : "+turnCnt[nx][ny]);
                visited[nx][ny] = true;
                if (dir == exDir)
                {
                    if (turnCnt[nx][ny] >= turn)
                    {
                        dfs(nx, ny, dir, turn);
                    }
                }
                else
                {
                    if (turnCnt[nx][ny] >= turn + 1)
                    {
                        dfs(nx, ny, dir, turn + 1);
                    }
                }
                visited[nx][ny] = false;
            }

        }


    }

    /**
     * 최단 거리? 아님 최단거리로 가더라도 거울 최소개수만큼 가는것이 보장이 안됌
     * <p>
     * 그럼 거울 최소 개수로 가는 방법은 어떻게 구해야 하나. dfs인데 백트래킹?
     * <p>
     * 꺽는 횟수를 저장해서 처음에는 min보다 적게 꺽으면 갈 수 있게 함.
     * <p>
     * 한번 도착하면 min 갱신되고 min보다 많이 꺽는 방향은 방문하지 못하게 함.
     * <p>
     * 이거 같은데
     */

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
