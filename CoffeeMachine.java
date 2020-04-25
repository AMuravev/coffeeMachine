package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine();

        while (machine.getCurrentState() != State.EXIT) {

            String command = scanner.next();

            machine.command(command);

        }

    }

}
