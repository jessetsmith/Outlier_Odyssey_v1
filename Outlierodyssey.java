import java.util.Scanner;

public class OutlierOdyssey {

    public static void main(String[] args) {
        // Scanner incantation to initialize game
        Scanner input = new Scanner(System.in);
        Game b = new Game(10, 10);

        // Call and run programs to test

        // Run game
        b.runGame(input);

        // Close Scanner incantation
        input.close();

    }

}
