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

    public boolean checkWinner(){
        for (int i = 0; i < 3; i++) {
            //check row
            if (grid[i][0].getType().equalsIgnoreCase(grid[i][1].getType()) && grid[i][0].getType().equalsIgnoreCase(grid[i][2].getType()) && !grid[i][0].getType().equalsIgnoreCase("blank")){
                return true;
            } else if (grid[0][i].getType().equalsIgnoreCase(grid[1][i].getType()) && grid[0][i].getType().equalsIgnoreCase(grid[2][i].getType()) && !grid[0][i].getType().equalsIgnoreCase("blank")){
                return true; //check col
            }
        }
        // check \
        if (grid[0][0].getType().equalsIgnoreCase(grid[1][1].getType()) && grid[0][0].getType().equalsIgnoreCase(grid[2][2].getType()) && !grid[0][0].getType().equalsIgnoreCase("blank")){
            return true;
        }
        // check /
        if(grid[2][0].getType().equalsIgnoreCase(grid[1][1].getType()) && grid[2][0].getType().equalsIgnoreCase(grid[0][2].getType()) && !grid[2][0].getType().equalsIgnoreCase("blank")){
            return true;
        }
        return false;
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
