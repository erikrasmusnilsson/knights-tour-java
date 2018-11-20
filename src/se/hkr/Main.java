package se.hkr;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final int BOARD_SIZE = 8;
    public static final int SIMULATIONS_TO_RUN = 10000;

    private SecureRandom rand = new SecureRandom();
    private int counter = 0;

    public static void main(String[] args) {
        Main app = new Main();
        //int[] start = {2, 3}; fixed start position for debugging

        Instant startTime = Instant.now();
        for (int i = 0; i < SIMULATIONS_TO_RUN; i++) { // simulate game n times
            int[] start = { app.rand.nextInt(BOARD_SIZE),
                    app.rand.nextInt(BOARD_SIZE)};

            Board board = new Board(BOARD_SIZE, BOARD_SIZE, start);

            while (true) {
                // get all the possible moves and select a random one
                ArrayList<ArrayList<Integer>> possibleMoves = board.calculatePossibleMoves(board.getCurrentPosition());
                if (possibleMoves.size() <= 0) {
                    if (board.isClosed()) {
                        app.counter++; // all the tiles were visited
                    }
                    break;
                }

                // choose the best move to make and store result in array
                int[] nextMove = board.chooseBestMove(possibleMoves);
                board.makeMove(nextMove);

                /*
                ----------------------------- remove comment brackets too see in action*/
                /*System.out.println(board);
                waitForInput();*/
            }
        }

        Instant stopTime = Instant.now();
        System.out.printf(
                        "Simulation ended!%n" +
                        "Blue: starting point <-- only visible when 'in action'%n" +
                        "Red: ending point <-- only visible when 'in action'%n" +
                        "Simulation ran: %d%n" +
                        "Simulation succesfully closed: %d%n" +
                        "Success rate: ~%.1f%% %n" +
                        "Time elapsed: %d ms",
                SIMULATIONS_TO_RUN,
                app.counter,
                ((double)app.counter / (double)SIMULATIONS_TO_RUN) * 100,
                Duration.between(startTime, stopTime).toMillis());
    }

    public static void waitForInput () {
        Scanner input = new Scanner(System.in);
        System.out.print("[ENTER] >> ");
        input.nextLine();
    }
}
