package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/9/6.
 * <p>
 * dfs, with stack, and store the path
 */
public class MazeV3 extends MazeHelper {

    static class Position extends MazeHelper.Position {
        Position prev = null;

        public Position() {
        }

        public Position(int x, int y) {
            super(x, y);
        }

        public Position(int x, int y, Position prev) {
            super(x, y);
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Position position = (Position) o;

            return prev != null ? prev.equals(position.prev) : position.prev == null;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (prev != null ? prev.hashCode() : 0);
            return result;
        }
    }

    public static List<Position> go(int[][] maze, Position start, Position target) {
        boolean[][] mark = new boolean[maze.length][maze[0].length];
        mark[start.x][start.y] = true;
        int directions = move.length;

        Queue<Position> q = new LinkedList<>();
        q.offer(start);

        outer:
        while (!q.isEmpty()) {
            Position curr = q.poll();
            for (int i = 0; i < directions; i++) {
                int x = curr.x + move[i][0];
                int y = curr.y + move[i][1];

                if (canVisit(maze, mark, x, y)) {
                    mark[x][y] = true;
                    Position next = new Position(x, y, curr);
                    q.offer(next);

                    if (target.x == x && target.y == y) {
                        target.prev = curr;
                        break outer;
                    }
                }
            }
        }

        List<Position> path = new ArrayList<>();
        // visit from target to start. do not visit from the stack, it is incorrect
        if (!q.isEmpty()) {
            Position curr = target;
            while (curr != null) {
                Position prev = curr.prev;
                curr.prev = null;
                path.add(curr);
                curr = prev;
            }
            Collections.reverse(path);
        }

        return path;
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
