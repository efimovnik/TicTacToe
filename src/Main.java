import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        printGrid(grid);
        Scanner scanner = new Scanner(System.in);
        boolean xTurn = true;
        boolean finished = false;
        while (!finished) {
            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("You should enter numbers!");
            } else {
                int moveX = scanner.nextInt() - 1;
                int moveY = scanner.nextInt() - 1;
                if (moveX < 0 || moveX > 2 || moveY < 0 || moveY > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    switch (grid[moveX][moveY]) {
                        case 'X', 'O' -> System.out.println("This cell is occupied! Choose another one!");
                        case '\u0000' -> {
                            grid[moveX][moveY] = xTurn ? 'X' : 'O';
                            xTurn = !xTurn;
                            System.out.println("> " + (moveX + 1) + " " + (moveY + 1));
                            printGrid(grid);
                            finished = calculateResult(grid);
                        }
                    }
                }
            }
        }
    }


    private static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (char[] column : grid) {
            System.out.print("| ");
            for (char row : column) {
                if (row == '\u0000') {
                    row = ' ';
                }
                System.out.print(row + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    private static boolean calculateResult(char[][] grid) {
        boolean finished = false;
        int xCounter = 0;
        int oCounter = 0;
        boolean xWins = false;
        boolean oWins = false;
        for (char[] row : grid) {
            int xRowCounter = 0;
            int oRowCounter = 0;
            for (char element : row) {
                switch (element) {
                    case 'X' -> {
                        xCounter += 1;
                        xRowCounter += 1;
                        xWins = xRowCounter == 3 || xWins;
                        break;
                    }
                    case 'O' -> {
                        oCounter += 1;
                        oRowCounter += 1;
                        oWins = oRowCounter == 3 || oWins;
                        break;
                    }
                }
            }
        }
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] ||
                grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0]) {
            switch (grid[0][0]) {
                case 'X' -> xWins = true;
                case 'O' -> oWins = true;
            }
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] ||
                grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2]) {
            switch (grid[0][2]) {
                case 'X' -> xWins = true;
                case 'O' -> oWins = true;
            }
        }
        if (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1]) {
            switch (grid[0][1]) {
                case 'X' -> xWins = true;
                case 'O' -> oWins = true;
            }
        }
        if (xCounter - oCounter > 1 || oCounter - xCounter > 1 || oWins && xWins) {
            System.out.println("Impossible");
            finished = true;
        } else if (oWins) {
            System.out.println("O wins");
            finished = true;
        } else if (xWins) {
            System.out.println("X wins");
            finished = true;
        } else if (oCounter + xCounter == 9) {
            System.out.println("Draw");
            finished = true;
        } return finished;
    }
}




