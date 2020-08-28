import java.util.*;
import java.util.concurrent.TimeUnit;
public class GameofLife {
    public static void main(String[] args) {
        int [][] world = new int[10][10];
        // int[][]world = new int[][]{{0,0,0,0,0},{0,0,0,0,0},{0,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}};
        initializeWorld(world);
        while(true) {
        printWorld(world);
        try {
            TimeUnit.SECONDS.sleep(1);
        }
            catch(Exception e) {
         }
        world = step(world);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        }
        // System.out.println();
        // printWorld(world);
    }
    public static void printWorld(int [][] world) {
        for(int r = 0; r< world.length; r ++) {
            for(int c = 0;  c< world[0].length; c ++) {
                System.out.print(world[r][c]);
            }
            System.out.println();
        }
    }
    public static int[][] initializeWorld(int [][]world) {
        for(int r = 0; r< world.length; r ++) {
            for(int c = 0;  c< world[0].length; c ++) {
                double rand  =  Math.random();
                if (rand <=0.3) {
                    world[r][c] = 1;
                }
               else {
                   world[r][c] = 0;
               }
            }
        }
        return world;
    }
    public static int[][] step(int[][]world) {
        int [][] bufferWorld = deepCopy(world);
        // printWorld(bufferWorld);
        for(int r = 0; r< world.length; r ++) {
            for(int c = 0;  c< world[0].length; c ++) {
                int livingNeighbors =0;
                if((c- 1) > 0 && world[r][c-1] == 1 ) {
                    livingNeighbors ++;
                }
                if((c+1) < world[0].length && world[r][c+1] == 1 ) {
                    livingNeighbors ++;
                }
                if((r- 1) > 0 && world[r-1][c] == 1 ) {
                    livingNeighbors ++;
                }
                if((r+1) < world.length && world[r+1][c] == 1 ) {
                    livingNeighbors ++;
                }

                if((c- 1) > 0 && (r- 1) > 0 && world[r-1][c-1] == 1 ) {
                    livingNeighbors ++;
                }
                if((c+1) < world[0].length && (r+1) < world.length && world[r+1][c+1] == 1 ) {
                    livingNeighbors ++;
                }
                if((c+1) < world[0].length && (r- 1) > 0 && world[r-1][c+1] == 1 ) {
                    livingNeighbors ++;
                }
                if((c-1) >0 && (r+1) < world.length && world[r+1][c-1] == 1 ) {
                    livingNeighbors ++;
                }
                if(livingNeighbors < 2) {
                    bufferWorld[r][c] = 0;
                }
                if(livingNeighbors > 3) {
                    bufferWorld[r][c] = 0;
                }
                if(livingNeighbors == 3) {
                    bufferWorld[r][c] = 1;
                }



            }
        }
        return bufferWorld;
    }

    public static int[][] deepCopy(int [][]world) {
        int[][]copy = new int[world.length][world[0].length];
        for(int r = 0; r < world.length; r ++) {
            for(int c = 0; c< world[0].length; c ++) {
                copy[r][c] = world[r][c];
            }
        }
        return copy;
    }
}