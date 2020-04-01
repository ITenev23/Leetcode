package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * In a given grid, each cell can have one of three values:
 * 1. the value 0 representing an empty cell;
 * 2. the value 1 representing a fresh orange;
 * 3. the value 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1 instead.
 *
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 *
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
public class RottingOranges {

    static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<Position> q = new LinkedList<>();
        int allOranges = 0;
        int rotten = 0;
        int time = 0;

        // traverse the grid, offer position of rotten orange into queue, and count the allOranges num of orange
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 || grid[i][j] == 2)
                    allOranges++;
                if (grid[i][j] == 2)
                    q.offer(new Position(i, j));
            }
        }

        // if there is no orange, return 0;
        if (allOranges == 0) return 0;

        while (! q.isEmpty() && rotten < allOranges) {
            // size is the num of rotten oranges of the last round
            int size = q.size();

            // count the num of rotten oranges, if it equals to allOranges num, return time;
            rotten += size;
            if (rotten == allOranges) return time;

            // every round, time ++
            time++;

            // Continue to dequeue until all rotten oranges of last round are removed from the queue
            for (int i = 0; i < size; i++) {
                Position p = q.poll();

                // check the cell in the left/right/top/down of the rotten orange, if it is a fresh orange, enqueue it.
                //left
                if (p.x + 1 < grid.length && grid[p.x + 1][p.y] == 1) {
                    grid[p.x + 1][p.y] = 2;
                    q.offer(new Position(p.x + 1, p.y));
                }
                //right
                if (p.x - 1 >= 0 && grid[p.x - 1][p.y] == 1) {
                    grid[p.x - 1][p.y] = 2;
                    q.offer(new Position(p.x - 1, p.y));
                }
                //up
                if (p.y + 1 < grid[0].length && grid[p.x][p.y + 1] == 1) {
                    grid[p.x][p.y + 1] = 2;
                    q.offer(new Position(p.x, p.y + 1));
                }
                //down
                if (p.y - 1 >= 0 && grid[p.x][p.y - 1] == 1) {
                    grid[p.x][p.y - 1] = 2;
                    q.offer(new Position(p.x, p.y - 1));
                }
            }
        }
        return -1;
    }


    /********************************************************************************/


    public static int orangesRotting2(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = -1;
        int freshCount = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2) queue.offer(new int[]{i, j}); //gathering rotten oranges to queue
                else if(grid[i][j] == 1) freshCount++;
            }
        }

        if(freshCount == 0) return 0; //there is no fresh orange.
        if(queue.size() == 0) return -1; //there is noting to rotten.

        while (!queue.isEmpty()) {
            minutes++;
            int size = queue.size(); //Rotten oranges simultaneously affect adjacent fresh oranges.
            for(int i = 0; i < size; i++) {
                //if using queue.size() instead of size, it will be not working properly, beacuse queue is changeable.
                int[] now = queue.poll();
                for (int[] dir : dirs) { //find fresh oragnes from adjacent directions.
                    int x = now[0] + dir[0];
                    int y = now[1] + dir[1];

                    if (x > grid.length - 1 || x < 0 || y > grid[0].length - 1 || y < 0) continue;
                    if (grid[x][y] == 1) {
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 2; //rotten..!!
                        freshCount--;
                    }
                }
            }
        }
        //if freshCount is not 0, it means that all fresh orange couldn't be rotten.
        return freshCount != 0 ? -1 : minutes;
    }


    /******************************************************************************/


    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    public int orangesRotting3(int[][] grid) {
        int R = grid.length, C = grid[0].length;

        // queue : all starting cells with rotten oranges
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> depth = new HashMap<>();
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c)
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }

        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }

        for (int[] row: grid)
            for (int v: row)
                if (v == 1)
                    return -1;
        return ans;

    }

}
