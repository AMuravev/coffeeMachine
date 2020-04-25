package machine;

import static machine.Command.*;

public class Machine {

    public Machine() {
        welcome();
        currentState = State.START;
        watter = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
    }

    private State currentState;
    private int watter;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public void command(String str) {

        Command command = Command.findCommand(str);

        switch (getCurrentState()) {
            case START:
                start(command);
                break;
            case FILLING_WATER:
            case FILLING_MILK:
            case FILLING_CUPS:
            case FILLING_BEANS:
                fillProcess(str);
                break;
            case PURCHASE:
                purchase(command);
                break;
            case EXIT:
                exit();
                break;
            default:
                break;

        }

    }

    private void start(Command command) {

        switch (command) {
            case BUY:
                buy();
                break;
            case FILL:
                fill();
                break;
            case TAKE:
                take();
                break;
            case REMAINING:
                remaining();
                break;
            case EXIT:
                exit();
                break;
            default:
                break;
        }

    }

    private boolean buy() {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");

        setCurrentState(State.PURCHASE);

        return true;

    }

    private boolean purchase(Command command) {

        switch (command) {
            case ESPRESSO:
                updateSupply(Coffee.ESPRESSO);
                break;
            case LATTE:
                updateSupply(Coffee.LATTE);
                break;
            case CAPPUCCINO:
                updateSupply(Coffee.CAPPUCCINO);
                break;
            case BACK:
                welcome();
                break;
            default:
                error();
                break;
        }

        setCurrentState(State.START);
        welcome();

        return true;
    }

    private boolean fill() {

        System.out.println("Write how many ml of water do you want to add:");
        setCurrentState(State.FILLING_WATER);

        return true;
    }

    private boolean fillProcess(String count) {

        int countInt = Integer.parseInt(count);

        switch (getCurrentState()) {
            case FILLING_WATER:
                setWatter(getWatter() + countInt);
                System.out.println("Write how many ml of milk do you want to add");
                setCurrentState(State.FILLING_MILK);
                break;
            case FILLING_MILK:
                setMilk(getMilk() + countInt);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                setCurrentState(State.FILLING_BEANS);
                break;
            case FILLING_BEANS:
                setBeans(getBeans() + countInt);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                setCurrentState(State.FILLING_CUPS);
                break;
            case FILLING_CUPS:
                setCups(getCups() + countInt);
                setCurrentState(State.START);
                welcome();
                break;
            default:
                setCurrentState(State.START);
                break;
        }

        return true;

    }

    private boolean take() {

        System.out.println("I gave you $" + getMoney());
        setMoney(0);
        welcome();

        return true;
    }

    private boolean remaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(getWatter() + " of water");
        System.out.println(getMilk() + " of milk");
        System.out.println(getBeans() + " of coffee beans");
        System.out.println(getCups() + " of disposable cups");
        System.out.println(getMoney() + " of money");
        welcome();

        return true;
    }

    private boolean welcome() {

        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");

        return true;
    }

    private boolean exit() {

        setCurrentState(State.EXIT);

        return true;
    }

    private boolean error() {

        System.out.println("Command not found!");

        return true;
    }

    private boolean updateSupply(Coffee coffee) {

        int limit = Math.min(Math.min(getWatter() - coffee.getWater(), getMilk() - coffee.getMilk()), getBeans() - coffee.getBeans());

        if (limit <= 0 || getCups() - 1 <= 0) {
            System.out.println("Sorry, not enough supply!");
        } else {
            setWatter(getWatter() - coffee.getWater());
            setMilk(getMilk() - coffee.getMilk());
            setBeans(getBeans() - coffee.getBeans());
            setCups(getCups() - 1);
            setMoney(getMoney() + coffee.getCost());

            System.out.println("I have enough resources, making you a coffee!");
        }

        return true;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public int getWatter() {
        return watter;
    }

    public void setWatter(int watter) {
        this.watter = watter;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
