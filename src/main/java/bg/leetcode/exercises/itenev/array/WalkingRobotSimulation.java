package bg.leetcode.exercises.itenev.array;

import java.util.HashSet;
import java.util.Set;

/**
 * A robot on an infinite grid starts at point (0, 0) and faces north.
 * The robot can receive one of three possible types of commands:
 *           -2: turn left 90 degrees
 *           -1: turn right 90 degrees
 *           1 <= x <= 9: move forward x units
 *
 * Some of the grid squares are obstacles.
 * The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
 * If the robot would try to move onto them,
 * the robot stays on the previous grid square instead (but still continues following the rest of the route.)
 *
 * Return the square of the maximum Euclidean distance that the robot will be from the origin.
 *
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: robot will go to (3, 4)
 *
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 */
public class WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {

        //define a 2d array to store the direction from row 0 - 3 respectively right (because default is face north = right), up, left, down
        int[][] dir = {{0,1},
                {-1,0},
                {0,-1},
                {1,0}};
        //i and j as coordinate for our robot, di is the pointer to turn direction
        int i=0,j=0,di=0,eDist=0;
        Set<String> obs = new HashSet<>();

        //store all the obstacles in a set
        for(int[] ob : obstacles){
            int a = ob[0];
            int b = ob[1];
            obs.add(a+" "+b);
        }

        int ind = 0;

        while(ind < commands.length){
            int act = commands[ind];
            ind++;
            //if turn left, we workaround variable di to + 1, without reaching negative integer
            if(act == -2){
                di = (di+1)%4;
            }//if turn right, we subtract the di, but without reaching negative integer
            else if(act == -1){
                di = (di+3)%4;
            }
            else{
                //if the command is other than -1 or -2, we move forward as many as the integer in the commands, and check the next step if there is an obstacle
                while(act > 0){
                    int ti = i+dir[di][0];
                    int tj = j+dir[di][ 1];
                    if(!obs.contains(ti+" "+tj)){
                        i += dir[di][0];
                        j += dir[di][1];
                    }
                    act--;
                }
            }

            //the maximum euclidean distance
            eDist = Math.max(eDist, i*i+j*j);
        }

        return eDist;
    }

    /********************************************************************/

    public int robotSim2(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle: obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int ans = 0;
        for (int cmd: commands) {
            if (cmd == -2)  //left
                di = (di + 3) % 4;
            else if (cmd == -1)  //right
                di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x*x + y*y);
                    }
                }
            }
        }

        return ans;
    }

}
