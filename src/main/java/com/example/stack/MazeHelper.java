package com.example.stack;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */
public class MazeHelper {


    protected static int[][] move = new int[4][];

    static {
        move[0] = new int[]{0, -1}; //
        move[1] = new int[]{1, 0};  //
        move[2] = new int[]{0, 1};  //
        move[3] = new int[]{-1, 0}; //
    }

    static class Position {
        int x = -1;
        int y = -1;

        public Position() {
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Maze.Position position = (Maze.Position) o;

            if (x != position.x) return false;
            return y == position.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }


    // for printing the path
    private static final int PATH_VISITED = -1;
    private static final int PATH_START = -2;
    private static final int PATH_TARGET = -3;

    protected int[][] makeMazeOne() {
        int[][] maze = new int[5][];
        maze[0] = new int[]{0, 0, 1, 0, 0};
        maze[1] = new int[]{0, 0, 0, 0, 0};
        maze[2] = new int[]{0, 0, 0, 1, 0};
        maze[3] = new int[]{1, 1, 0, 1, 1};
        maze[4] = new int[]{0, 0, 0, 0, 0};
        return maze;
    }

    protected int[][] makeMazeTwo() {
        int[][] maze = new int[5][];
        maze[0] = new int[]{0, 0, 1, 0, 0};
        maze[1] = new int[]{1, 0, 1, 0, 0};
        maze[2] = new int[]{1, 1, 1, 0, 0};
        maze[3] = new int[]{1, 1, 0, 1, 1};
        maze[4] = new int[]{0, 0, 0, 0, 0};
        return maze;
    }

    protected int[][] makeMazeThree() {
        int[][] maze = new int[6][];
        maze[0] = new int[]{0, 1, 1, 1, 0, 1, 1, 1};
        maze[1] = new int[]{1, 0, 1, 0, 1, 1, 1, 1};
        maze[2] = new int[]{0, 1, 0, 0, 0, 0, 0, 1};
        maze[3] = new int[]{0, 1, 1, 1, 0, 1, 1, 1};
        maze[4] = new int[]{1, 0, 0, 1, 1, 0, 0, 0};
        maze[5] = new int[]{0, 1, 1, 0, 0, 1, 1, 0};
        return maze;
    }

    public static boolean canVisit(int[][] maze, boolean[][] mark, int x, int y) {
        return (x >= 0 && y >= 0 && x < maze[0].length && y < maze.length) // range check
                && maze[x][y] == 0    // obstacle check
                && !mark[x][y];        // not yet visited
    }

    public static void printPath(int[][] maze, List<? extends Maze.Position> path, Position start, Position target) {

        for (Maze.Position curr : path) {
            maze[curr.x][curr.y] = PATH_VISITED;
        }

        maze[start.x][start.y] = PATH_START;
        maze[target.x][target.y] = PATH_TARGET;

        StringBuilder sb = new StringBuilder(128);
        sb.append("----------------\n");
        for (int[] outer : maze) {
            for (int i : outer) {
                char ch;
                switch (i) {
                    case PATH_START:
                        ch = 'S';
                        break;
                    case PATH_TARGET:
                        ch = 'T';
                        break;
                    case PATH_VISITED:
                        ch = 'X';
                        break;
                    default:
                        ch = (char) ('0' + i);
                }
                sb.append(ch + " ");
            }
            sb.append('\n');
        }
        sb.append("----------------\n");


        for (Position p : path) {
            sb.append("(").append(p.x).append(",").append(p.y).append(")->");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('\n');
        System.out.println(sb.toString());

    }

}
