import java.util.concurrent.ThreadLocalRandom;

public class Model {
    public static Tile grid[][] = new Tile[3][3];
    public boolean player1Turn = true;

    public void level0(){
        int tileCode = 0;
        int min = 1;
        int max = 8;
        boolean found = false;

        while (!found){
            tileCode = ThreadLocalRandom.current().nextInt(min, max + 1); // generate number from 1-8

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j].getCode() == tileCode){
                        if (grid[i][j].getType().equalsIgnoreCase("blank")){
                            grid[i][j].drawO();
                            found = true;
                        }
                    }
                }
            }
        }
    }

    public void level2(){
        int move[] = bestMove();
        //grid[move[0]][move[1]].drawO();
    }

    public int checkWinner(){
        // X wins = 1, used 1 and 0 for it to bre reusable in all algo
        // O wins = -1
        // tie = 0
        for (int i = 0; i < 3; i++) {
            //check row
            if (grid[i][0].getType().equalsIgnoreCase(grid[i][1].getType()) && grid[i][0].getType().equalsIgnoreCase(grid[i][2].getType()) && grid[i][0].getType().equalsIgnoreCase("X")){
                return 1;
            } else if (grid[i][0].getType().equalsIgnoreCase(grid[i][1].getType()) && grid[i][0].getType().equalsIgnoreCase(grid[i][2].getType()) && grid[i][0].getType().equalsIgnoreCase("O")) {
                return -1;
            }
            //check col
            if (grid[0][i].getType().equalsIgnoreCase(grid[1][i].getType()) && grid[0][i].getType().equalsIgnoreCase(grid[2][i].getType()) && grid[0][i].getType().equalsIgnoreCase("X")){
                return 1; //check col
            } else if (grid[0][i].getType().equalsIgnoreCase(grid[1][i].getType()) && grid[0][i].getType().equalsIgnoreCase(grid[2][i].getType()) && grid[0][i].getType().equalsIgnoreCase("O")) {
                return -1; //check col
            }
        }

        // check \
        if (grid[0][0].getType().equalsIgnoreCase(grid[1][1].getType()) && grid[0][0].getType().equalsIgnoreCase(grid[2][2].getType()) && grid[0][0].getType().equalsIgnoreCase("X")){
            return 1;
        } else if (grid[0][0].getType().equalsIgnoreCase(grid[1][1].getType()) && grid[0][0].getType().equalsIgnoreCase(grid[2][2].getType()) && grid[0][0].getType().equalsIgnoreCase("O")){
            return -1;
        }
        // check /
        if(grid[2][0].getType().equalsIgnoreCase(grid[1][1].getType()) && grid[2][0].getType().equalsIgnoreCase(grid[0][2].getType()) && grid[2][0].getType().equalsIgnoreCase("X")){
            return 1;
        } else if(grid[2][0].getType().equalsIgnoreCase(grid[1][1].getType()) && grid[2][0].getType().equalsIgnoreCase(grid[0][2].getType()) && grid[2][0].getType().equalsIgnoreCase("O")){
            return -1;
        }
        return 0;
    }

    public int[] bestMove(){
        int bestVal = -1000;
        int moveVal;
        int[] move = new int[2];
        move[0] = -1; // row
        move[1] = -1;  // col

        // check each tile and evaluate minimax value for each.
        // return optimal value
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(grid[i][j].getType().equalsIgnoreCase("blank")){ //check if empty tile
                    grid[i][j].drawO(); // make move

                    //compute value
                    moveVal = minimax(0, false);
                    grid[i][j].setInitial(); //undo the move bc ur just checking but not really setting

                    if (moveVal > bestVal){ // update bestVal
                        bestVal = moveVal;
                        move[0] = i;
                        move[1] = j;

                    }
                }
            }
        }
        grid[move[0]][move[1]].drawO();
        player1Turn = true;
        return move;
    }



    public int minimax(int depth, boolean isMaximizing){
        int score = checkWinner();

        if (score == 1) // if max won
            return score;
        if (score == -1) // if min won
            return score;

        int bestScore;

        if (isMaximizing){
            bestScore = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(grid[i][j].getType().equalsIgnoreCase("blank")){
                        grid[i][j].drawO();
                        int tempScore =  minimax(depth+1, false);
                        bestScore = Math.max(bestScore, tempScore);
                        grid[i][j].setInitial(); //undo the move
                    }
                }
            }
            return bestScore;
        } else {
            bestScore = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(grid[i][j].getType().equalsIgnoreCase("blank")){
                        grid[i][j].drawX();
                        int tempScore = minimax(depth+1, true);
                        bestScore = Math.min(bestScore, tempScore);
                        grid[i][j].setInitial(); //undo
                    }
                }
            }
            return bestScore;
        }
    }

    public static Tile[][] getGrid() {
        return grid;
    }

    public static void setGrid(Tile[][] grid) {
        Model.grid = grid;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean bool) {
       player1Turn = bool;
    }
}
