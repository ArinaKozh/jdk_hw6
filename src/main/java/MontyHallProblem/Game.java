package MontyHallProblem;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
public class Game {
    static final int N_DOORS = 3;
    private final int id;
    private final int winDoor;
    private int playerFirstChoice;
    private int openDoor;
    private int playerFinalChoice;

    public Game(int gameId) {
        id = gameId;
        // Случайным образом выбирается индекс двери (от 0 до N-1)
        // за которой выигрыш - автомобиль:
        winDoor = getRandomDoor();
        playerFirstChoice = -1;
        openDoor = -1;
        playerFinalChoice = -1;
    }

    /**
     * Игрок делает первый выбор двери.
     *
     * @throws RuntimeException Если игрок уже делал свой первый выбор двери.
     */
    public void playerMakeFirstChoice() {
        if (isValidByRange(playerFirstChoice)) {
            throw new RuntimeException("Игрок уже делал первый выбор.");
        }
        playerFirstChoice = getRandomDoor();
    }

    /**
     * Ведущий открывает одну из оставшихся дверей, которая не выбрана игроком и не
     * является выигрышной.
     *
     * @throws RuntimeException Если игрок ещё не сделал свой первый выбор двери.
     */
    public void masterOpensABadDoor() {
        if (!isValidByRange(playerFirstChoice)) {
            throw new RuntimeException("Игрок ещё не сделал первый выбор.");
        }
        openDoor = getRandomDoor(new int[]{winDoor, playerFirstChoice});
    }

    /**
     * Игроку предоставляется сделать итоговый выбор: поменять первоначально
     * выбранную дверь на оставшуюся (любую из оставшихся, при числе дверей более
     * 3-х), либо оставить свой первоначальный выбор неизменным.
     *
     * @param swapDoor true -- поменять первоначальный выбор; false -- открыть
     *                 первоначально выбранную дверь.
     * @throws RuntimeException Если игрок ещё не сделал свой первый выбор двери, а
     *                          ведущий ещё не открыл одну из проигрышных дверей.
     */
    public void playerMakeSecondChoice(boolean swapDoor) {
        if (!isValidByRange(playerFirstChoice)) {
            throw new RuntimeException("Игрок ещё не сделал первый выбор.");
        }
        if (!isValidByRange(openDoor)) {
            throw new RuntimeException("Ведущий ещё не открыл проигрышную дверь.");
        }
        if (swapDoor) {
            playerFinalChoice = getRandomDoor(new int[]{playerFirstChoice, openDoor});
        } else {
            playerFinalChoice = playerFirstChoice;
        }
    }

    /**
     * Статус игрока по окончании игры.
     *
     * @return true, если игрок выбрал выигрышную дверь.
     * @throws RuntimeException Если ещё не пройдены все этапы игры.
     */
    public boolean isPlayerWinner() {
        if (!isValidByRange(playerFinalChoice)) {
            throw new RuntimeException("Ещё не пройдены все этапы игры.");
        }
        return playerFinalChoice == winDoor;
    }
    public GameDescriptor getGameDescriptor() {
        if (!isValidByRange(playerFinalChoice)) {
            throw new RuntimeException("Ещё не пройдены все этапы игры.");
        }
        return new GameDescriptor(id, winDoor, playerFirstChoice, openDoor, playerFinalChoice);
    }

    private static int getRandomDoor(int[] exclusions) {
        exclusions = Arrays.stream(exclusions).sorted().distinct().toArray();
        int doorsRemaining = N_DOORS - exclusions.length;
        int indexAmongRemaining = ThreadLocalRandom.current().nextInt(doorsRemaining);

        for (int exclDoorIndex : exclusions) {
            if (indexAmongRemaining >= exclDoorIndex) {
                ++indexAmongRemaining;
            }
        }
        return indexAmongRemaining;
    }

    private static int getRandomDoor() {
        return ThreadLocalRandom.current().nextInt(N_DOORS);
    }

    public static boolean isValidByRange(int door) {
        return door >= 0 && door < N_DOORS;
    }
}