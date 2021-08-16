import java.util.Scanner;

public class Bee3 {
   private static int rowBee,colBee;
   private static int flowers=0;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        char[][] field = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            if (line.contains("B")) {
                rowBee = i;
                colBee = line.indexOf("B");//намирам си къде ми е стартовата позиция
            }
            field[i] = line.toCharArray();
        }
        String command=scanner.nextLine();
        while (!command.equals("End")){
            int currentRow=rowBee;
            int currentCol=colBee;
            if (command.equals("up")) {
                //roww--
                currentRow--;
            } else if (command.equals("down")) {
                currentRow++;
            } else if (command.equals("left")) {
                currentCol--;
            } else if (command.equals("right")) {
                currentCol++;
            }
            if(isOutOfBounds(currentRow,currentCol,field)){
                field[rowBee][colBee]='.';
                System.out.println("The bee got lost!");

                break;
            }
            field[rowBee][colBee]='.';
            if(field[currentRow][currentCol]=='f'){
                flowers++;
                field[currentRow][currentCol]='B';
                rowBee=currentRow;
                colBee=currentCol;
            }else if(field[currentRow][currentCol]=='O'){
                field[currentRow][currentCol]='.';
                if (command.equals("up")) {
                    //roww--
                    currentRow--;
                } else if (command.equals("down")) {
                    currentRow++;
                } else if (command.equals("left")) {
                    currentCol--;
                } else if (command.equals("right")) {
                    currentCol++;
                }
                if(isOutOfBounds(currentRow,currentCol,field)){
                    field[rowBee][colBee]='.';
                    System.out.println("The bee got lost!");
                    break;
                }
                if(field[currentRow][currentCol]=='f'){
                    flowers++;
                    field[currentRow][currentCol]='B';
                    rowBee=currentRow;
                    colBee=currentCol;
                }else {
                    field[currentRow][currentCol]='B';
                    rowBee=currentRow;
                    colBee=currentCol;
                }
            }else {
                field[currentRow][currentCol]='B';
                rowBee=currentRow;
                colBee=currentCol;
            }
            command=scanner.nextLine();

        }
        if(flowers>=5){
            System.out.printf("Great job, the bee manage to pollinate %s flowers!",flowers);
        }else {
            int needed=5-flowers;
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more",needed);
        }
        System.out.println();
        printMatrix(field);
    }

    private static void moveBee(String command) {
        if (command.equals("up")) {
            //roww--
            rowBee--;
        } else if (command.equals("down")) {
            rowBee++;
        } else if (command.equals("left")) {
            colBee--;
        } else if (command.equals("right")) {
            colBee++;
        }
    }

    public static void printMatrix(char[][]field){
        for (int r = 0; r <field.length ; r++) {
            for (int c = 0; c <field[r].length ; c++) {
                System.out.print(field[r][c]);//печатам си матрицата
            }
            System.out.println();
        }
    }
    private static boolean isOutOfBounds(int row, int col, char[][] field) {
        return row<0||row>=field.length||col<0||col>=field[row].length;
    }
}
