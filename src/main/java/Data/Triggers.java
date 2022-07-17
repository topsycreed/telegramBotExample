package Data;

import java.util.Arrays;
import java.util.List;

import static Data.Dictionary.*;

public class Triggers {

    public static boolean isDirectQuestion(String message) {
        return message.contains(DIRECT_QUESTION);
    }

    public static boolean isMen(String message) {
        return checkCondition(message, MENS);
    }

    public static boolean isPositive(String message) {
        return checkCondition(message, POSITIVE);
    }

    public static boolean isFact(String message) {
        return checkCondition(message, FACTS);
    }

    public static boolean isBot(String message) {
        return checkCondition(message, BOTS);
    }

    private static boolean checkCondition(String message, String[] triggers) {
        List<String> triggerWords = Arrays.stream(triggers).toList();
        List<String> words = Arrays.stream(message.split(" ")).toList();
        return words.stream().anyMatch(triggerWords::contains);
    }
}
