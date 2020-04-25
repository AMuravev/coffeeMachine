package machine;

public enum Command {
    BUY("buy"),
    FILL("fill"),
    TAKE("take"),
    REMAINING("remaining"),
    BACK("back"),
    ESPRESSO("1"),
    LATTE("2"),
    CAPPUCCINO("3"),
    INPUT_COUNT(""),
    EXIT("exit");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Command findCommand(String name) {

        for (Command command : Command.values()) {
            if (command.getCommand().equals(name)) {
                return command;
            }
        }
        return Command.INPUT_COUNT;
    }
}
