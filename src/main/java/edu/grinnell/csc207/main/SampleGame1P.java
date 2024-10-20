package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Random;

/**
 * A sample one-player game (is that a puzzle?). Intended as a potential
 * use of our Matrix interface.
 *
 * @author Samuel A. Rebelsky
 */
public class SampleGame1P {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default width.
   */
  static final int DEFAULT_WIDTH = 10;

  /**
   * The default number of rows.
   */
  static final int DEFAULT_HEIGHT = 12;

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Print the insturctions.
   *
   * @param pen
   *  The printwriter used to print the instructions.
   */
  public static void printInstructions(PrintWriter pen) {
    pen.println("""
                Welcome to the sample one-player game.

                Your game board is a grid of X's, O's and blanks.

                Your goal is to eliminate as many X's as possible while
                keeping as many O's as possible.

                You have four basic moves. You can do each three times.

                * RR: remove a row
                * RC: remove a column
                * IR: insert a blank row
                * IC: insert a blank column

                After each move, any neighboring X's eliminate each other
                and any neighboring O's also eliminate each other.

                Command-line arguments:

                * -width W - set up the width of the board
                * -height H - set up the height of the board
                * -game N - choose setup number N (useful if you want to
                  play the same setup multiple times).
                """);
  } // printInstructions(PrintWriter)

  /**
   * Set up a new board.
   *
   * @param width
   *   The width of the board.
   * @param height
   *   The height of the board.
   * @param game
   *   The game number.
   *
   * @return the newly created board
   */
  static Matrix<String> setupBoard(int width, int height, int game) {
    Random setup = new Random(game);
    Matrix<String> board = new MatrixV0<String>(width, height, " ");
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        double rand = setup.nextDouble();
        if (rand < 0.15) {
          board.set(row, col, "X");
        } else if (rand < 0.3) {
          board.set(row, col, "O");
        } // if/else
      } // for col
    } // for row
    return board;
  } // setupBoard(int, int, int)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the game.
   *
   * @param args
   *   Command-line arguments.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    int rrRemaining = 3;
    int rcRemaining = 3;
    int icRemaining = 3;
    int irRemaining = 3;
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;
    Random rand = new Random();
    int game = rand.nextInt();

    // Process the command line

    printInstructions(pen);

    // Set up the board
    Matrix<String> board = setupBoard(width, height, game);

    // Run the game
    pen.println("Game number " + game);
    pen.println();

    String[] commands = new String[] {"RR", "RC", "IR", "IC", "DONE"};
    String command = "";
    do {
      Matrix.print(pen, board);
      command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
      switch (command) {
        case "RR":
          break;
        case "RC":
          break;
        case "IR":
          break;
        case "IC":
          break;
        default:
          pen.printf("Unexpected command: '%s'. Please try again.\n", command);
          break;
      } // switch
    } while (!"DONE".equals(command));

    // Print final results

    // And we're done
    pen.close();
  } // main(String[])
} // class SampleGame1P
