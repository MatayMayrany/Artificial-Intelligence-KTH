import java.util.*;

class Player {

   private int[][] POINTS = {
      { 0, 1, 10, 100, 1000 },
      { -1, 0, 0, 0, 0 },
      { -10, 0, 0, 0, 0 },
      { -100, 0, 0, 0, 0 },
      { -1000, 0, 0, 0, 0 },
      };

   /**
    * Performs a move
    *
    * @param gameState the current state of the board
    * @param deadline  time before which we must have returned
    *
    * @return the next state the board is in after our move
    */
   GameState play(final GameState gameState, final Deadline deadline) {
      Vector<GameState> nextStates = new Vector<>();
      gameState.findPossibleMoves(nextStates);

      if (nextStates.size() == 0) {
         // Must play "pass" move if there are no other moves possible.
         return new GameState(gameState, new Move());
      }

      /**
       * Here you should write your algorithms to get the best next move, i.e.
       * the best next state. This skeleton returns a random move instead.
       */
      int alpha = Integer.MIN_VALUE;
      int beta = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      int id = 0;
      int maxDepth = 5;

      for (int i = 0; i < nextStates.size(); i++) {
         int bestValue = 0;
         bestValue = alphaBeta(nextStates.get(i), maxDepth, alpha, beta, gameState.getNextPlayer());
         if (bestValue >= max) {
            id = i;
            max = bestValue;
         }
      }

      return nextStates.get(id);
   }

   private int alphaBeta(GameState gameState, int depth, int alpha, int beta, int player) {
      Vector<GameState> nextStates = new Vector<>();
      gameState.findPossibleMoves(nextStates);
      int bestValue = 0;
      if (depth == 0 || nextStates.size() == 0) {
         bestValue = eval(gameState);
      } else if (player == Constants.CELL_X) {
         bestValue = Integer.MIN_VALUE;
         for (GameState state : nextStates) {
            //traverse the tree recursively
            bestValue = Math.max(bestValue, alphaBeta(state, depth - 1, alpha, beta, Constants.CELL_O));
            alpha = Math.max(alpha, bestValue);
            // prune because there is no point of looking further as opponent will never allow us to come here
            if (beta <= alpha) {
               break;
            }
         }
      } else {
         bestValue = Integer.MAX_VALUE;
         for (GameState state : nextStates) {
            bestValue = Math.min(bestValue, alphaBeta(state, depth - 1, alpha, beta, Constants.CELL_X));
            beta = Math.min(bestValue, beta);
            if (beta <= alpha) {
               break;
            }
         }
      }
      return bestValue;
   }

   private int eval(GameState gameState) {
      int xRowSum;
      int oRowSum;
      int xColSum;
      int oColSum;
      int rowScore = 0;
      int colScore = 0;
      int diagScore = 0;
      int xMainDiagSum = 0;
      int oMainDiagSum = 0;
      int xAntiDiagSum = 0;
      int oAntiDiagSum = 0;
      for (int i = 0; i < 4; i++) { // iterate over rows
         if (gameState.at(i, i) == Constants.CELL_X) {
            xMainDiagSum++;
         } else if (gameState.at(i, i) == Constants.CELL_O) {
            oMainDiagSum++;
         }
         if (gameState.at(3 - i, i) == Constants.CELL_X) {
            xAntiDiagSum++;
         } else if (gameState.at(3 - i, i) == Constants.CELL_O) {
            oAntiDiagSum++;
         }
         // we set the variables to zero so we can start
         // with a new row/column
         xRowSum = 0;
         oRowSum = 0;
         xColSum = 0;
         oColSum = 0;
         for (int j = 0; j < 4; j++) {  // iterate over columns
            if (gameState.at(i, j) == Constants.CELL_X) {
               xRowSum++;
            } else if (gameState.at(i, j) == Constants.CELL_O) {
               oRowSum++;
            }
            if (gameState.at(j, i) == Constants.CELL_X) {
               xColSum++;
            } else if (gameState.at(j, i) == Constants.CELL_O) {
               oColSum++;
            }
         }
         rowScore += POINTS[oRowSum][xRowSum];
         colScore += POINTS[oColSum][xColSum];
      }
      diagScore += POINTS[oMainDiagSum][xMainDiagSum] + POINTS[oAntiDiagSum][xAntiDiagSum];
      return rowScore + colScore + diagScore;
   }

}