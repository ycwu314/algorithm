package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

/**
 * Created by Administrator on 2017/9/6.
 * <p>
 * dfs, with stack, and store the path
 */
public class MazeV2 extends MazeHelper {

    static class Position extends MazeHelper.Position {
        // direction
        int d = -1;

        public Position(int x, int y, int d) {
            super(x, y);
            this.d = d;
        }

        public Position(int x, int y) {
            super(x, y);
        }

        public Position() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Position position = (Position) o;

            return d == position.d;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + d;
            return result;
        }
    }


    /**
     * @param maze
     * @param start
     * @param target
     * @return
     */
    public static List<Position> go(int[][] maze, Position start, Position target) {
        boolean[][] mark = new boolean[maze.length][maze[0].length];
        mark[start.x][start.y] = true;

        Stack<Position> stack = new Stack<>();
        stack.push(start);

        outer:
        while (!stack.isEmpty()) {
            Position curr = stack.pop();

            int d = curr.d + 1;
            while (d < move.length) {
                int x = curr.x + move[d][0];
                int y = curr.y + move[d][1];

                if (canVisit(maze, mark, x, y)) {
                    mark[x][y] = true;
                    // push current pos and direction into stack, so that can backward search from current pos and next direction
                    stack.push(new Position(curr.x, curr.y, d));

                    if (target.x == x && target.y == y) {
                        // now stack holds a path from start to target
                        stack.push(target);
                        break outer;
                    }

                    // use next step as curr in next round
                    Position next = new Position(x, y, 0);
                    curr = next;
                    d = 0;
                } else {
                    // cant reach or already searched, try next direction
                    d++;
                }
            }
        }

        return stack;
    }


    @Test
    public void testMaze() {
        int[][] maze = makeMazeOne();
        Position start = new Position(0, 4);
        Position target = new Position(4, 4);
        List<Position> path = go(maze, start, target);
        Assert.assertFalse(path.isEmpty());
        printPath(maze, path, start, target);

        maze = makeMazeTwo();
        path = go(maze, new Position(0, 0), new Position(4, 4));
        Assert.assertTrue(path.isEmpty());

    }


}
