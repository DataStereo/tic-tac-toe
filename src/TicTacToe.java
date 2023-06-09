import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPossition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPossition = new ArrayList<Integer>();

    public static void main(String[] args) {

    // instantiate and print the board
        char [] [] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);

        Scanner scanner = new Scanner(System.in);

    // run the game
        while (true){
            System.out.println("Enter your placement 1-9:");
            int playerPos = scanner.nextInt();

            placePiece(gameBoard, playerPos, "player");

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;

            while (playerPossition.contains(playerPos) || cpuPossition.contains(playerPossition)){
                System.out.println("Position is taken, enter a possition");
                playerPos = scanner.nextInt();
            }

            placePiece(gameBoard, cpuPos, "cpu");

            while (playerPossition.contains(cpuPos) || cpuPossition.contains(cpuPos)){
                cpuPos = random.nextInt(9) + 1;
            }


            printGameBoard(gameBoard);
            String result = checkWinner();
            System.out.println(result);
        }
    }

    // print game board with the result
    public static void printGameBoard(char [] [] gameBoard){
        for(char [] row: gameBoard){
            for (char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // add a sign to the board
    public static void placePiece(char[][] gameBoard, int pos, String user){

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPossition.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPossition.add(pos);
        }

        switch (pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cros1 = Arrays.asList(1,5,9);
        List cros2 = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(cros1);
        winning.add(cros2);

        for (List l: winning){
            if (playerPossition.containsAll(l)) {
                return "Congratulations you won!";
                } else if (cpuPossition.containsAll(l)) {
                return  "CPU wins sorry!";
            }else if (playerPossition.size() + cpuPossition.size() == 9){
                return "DRAW";
            }
        }

        return "";
    }
}