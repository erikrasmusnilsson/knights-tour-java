package se.hkr;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {

    private Tile[][] board;
    private int[] start;
    private int[] currentPosition;

    public Board (int boardLength, int boardHeight, int[] start) {
        // Initiate the board
        board = new Tile[boardLength][boardHeight];
        for (int i = 0; i < board.length; i++) {
            // Fill with 'Tile' objects
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Tile();
            }
        }

        // Set starting point and current position to whatever the user decides
        this.start = start;
        makeMove(start);
    }

    public void makeMove (int[] move) {
        int y = move[0], x = move[1];
        if (y < 0 || x < 0) {
            throw new IllegalArgumentException("Coordinates does not fit array.");
        }

        board[y][x].setVisited(true);
        currentPosition = move;
    }

    public boolean isClosed () {
        boolean closed = false;
        for (Tile[] tileArray : board) {
            for (Tile tile : tileArray) {
                if (!tile.getVisited()) {
                    closed = false;
                    return closed;
                }

                closed = true;
            }
        }

        return closed;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleMoves (int[] currentPosition) {
        int y = currentPosition[0], x = currentPosition[1];
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        /*
        *       Up and down moves
        * */
        if (x > 0) {
            if (y > 1) {
                // add coordinates to temporary list
                temp.add(y - 2);
                temp.add(x - 1);

                // check if the tile has already been visited
                addToMoves(moves, temp);
            }
            if (y < board.length - 2) {
                temp.add(y + 2);
                temp.add(x - 1);

                addToMoves(moves, temp);
            }
        }

        if (x < board[0].length - 1) {
            if (y > 1) {
                temp.add(y - 2);
                temp.add(x + 1);

                addToMoves(moves, temp);
            }
            if (y < board.length - 2) {
                temp.add(y + 2);
                temp.add(x + 1);

                addToMoves(moves, temp);
            }
        }
        /*
        *       -------[STOP]Up and down moves---------
        * */
        /*
        *       Side to side moves
        * */
        if (x > 1) {
            if (y > 0) {
                temp.add(y - 1);
                temp.add(x - 2);

                addToMoves(moves, temp);
            }
            if (y < board.length - 1) {
                temp.add(y + 1);
                temp.add(x - 2);

                addToMoves(moves, temp);
            }
        }
        if (x < board[0].length - 2) {
            if (y > 0) {
                temp.add(y - 1);
                temp.add(x + 2);

                addToMoves(moves, temp);
            }
            if (y < board.length - 1) {
                temp.add(y + 1);
                temp.add(x + 2);

                addToMoves(moves, temp);
            }
        }

        // return all the moves collected in ArrayList
        return moves;
    }

    private void addToMoves (ArrayList<ArrayList<Integer>> m, ArrayList<Integer> t) {
        boolean visited = board[t.get(0)][t.get(1)].getVisited();
        if (!visited) {
            m.add(new ArrayList<Integer>(t));
        }

        t.clear();
    }

    public int[] getCurrentPosition () {
        return currentPosition;
    }

    @Override
    public String toString () {
        String s = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (currentPosition[0] == i && currentPosition[1] == j) {
                    // make current position appear in red
                    s += "\u001B[31m";
                    s += board[i][j];
                    s += "\u001B[0m";
                } else if (start[0] == i && start[1] == j) {
                    s += "\u001B[34m";
                    s += board[i][j];
                    s += "\u001B[0m";
                } else {
                    s += board[i][j];
                }
            }
            s += "\n";
        }

        return s;
    }
}
