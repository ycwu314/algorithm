package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/6.
 * <p>
 * dfs with recursion, dont store the path
 */
public class Maze extends MazeHelper {

    public static boolean go(int[][] maze, Position start, Position target) {
        boolean[][] mark = new boolean[maze.length][maze[0].length];
        mark[start.x][start.y] = true;

        boolean[] found = new boolean[1];
        goRecursive(maze, mark, start, target, found);

        return found[0];
    }

    private static void goRecursive(int[][] maze, boolean[][] mark, Position curr, Position target, boolean[] found) {
        if (target.equals(curr)) {
            found[0] = true;
            return;
        }

        for (int i = 0; i < move.length; i++) {
            int x = curr.x + move[i][0];
            int y = curr.y + move[i][1];

            if (canVisit(maze, mark, x, y)) {
                mark[x][y] = true;
                Position next = new Position(x, y);
                goRecursive(maze, mark, next, target, found);
            }
        }
    }

    @Test
    public void testMaze() {
        Assert.assertTrue(go(makeMazeOne(), new Position(0, 4), new Position(4, 4)));
        Assert.assertFalse(go(makeMazeTwo(), new Position(0, 0), new Position(4, 4)));
    }


}
