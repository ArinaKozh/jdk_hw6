package MontyHallProblem;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
public class ConsoleUtils{
    public static final String PLEASE_REPEAT = "Пожалуйста попробуйте снова.";
    private static final String PROMPT_ENTER = "Нажмите Ввод чтобы продолжить...";
    private static final String WARN_WRONG_MENU_ITEM = "Некорректный ввод: требуется выбрать пункт меню. "
            + PLEASE_REPEAT;
    private static final String ERR_NOT_INT = "Некорректный ввод: Требуется целое число. " + PLEASE_REPEAT;
    private static final String ERR_INT_MUST_BE_IN_RANGE = "Число должно быть в интервале от %d до %d! "
            + PLEASE_REPEAT;
    private static final String ERR_INT_TOO_LOW = "Число не должно быть меньше %d! " + PLEASE_REPEAT;
    private static final String ERR_INT_TOO_HIGH = "Число не должно быть больше %d! " + PLEASE_REPEAT;
    private static final String ERR_WRONG_DATE_FORMAT = "Некорректный ввод: не соответствует формату YYYY-MM-DD! "
            + PLEASE_REPEAT;
    public static final Scanner scanner = new Scanner(System.in, Charset.forName("UTF-8"));
    public static void println(Object view){
        System.out.println(view.toString());
    }
    public static boolean askYesNo(String prompt, boolean isYesDefault){
        System.out.print(prompt);
        var answer = scanner.nextLine();
        return answer.equalsIgnoreCase("y");
    }

    public static Integer askInteger(String prompt, Integer min, Integer max){
        Integer answer = scanner.nextInt();
        if (answer != null) {
            return answer;
        } else {
            return null;
        }
    }


    public static String askString(String prompt, Function<String, Boolean> checkValidity, String wrongWarn){
        String answer = scanner.nextLine();
        if (answer != null) {
            return answer;
        } else {
            return null;
        }
    }

}
