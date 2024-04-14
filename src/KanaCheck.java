import java.util.Arrays; // Used for Debug only
import java.util.HashMap;
import java.util.Scanner;

public class KanaCheck {

    /**
     * Run main to use test cases, otherwise use KanaCheck.main and provide it input from the terminal
     * If only running KanaCheck.java you may provide input from the terminal or as args when runnign the program
     * @param args strings to be checked
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Test case variables
        int fails = 0;
        int successes = 0;

        // Get input from args or the user
        if (args.length > 0) {
            System.out.println("Using args");
            for (String arg : args) {
//                System.out.println(arg); // Debug
                if (runChecker(arg)) {
                    System.out.println("Success");
                    successes++;
                } else {
                    System.out.println("Failure");
                    fails++;
                }
            }
            System.out.println("Successes: " + successes + " Fails: " + fails);

        } else {
            System.out.println("Using user input");
            System.out.println("Enter a string of Romanji and Hiragana characters separated by commas:");
            String input = in.nextLine();
            runChecker(input);
        }
    }

    /**
     * Run the checker
     *
     * @param input the string to be tested
     * @return true if the input is valid, false if the input is invalid and an error occurs
     */
    private static boolean runChecker(String input) {
        HashMap<String, String> RomanjiHiragana = getRomanjiHiragana();

        String hiraganaInput; // Hiragana input string from user, comma delimited
        String[] hiragana = new String[48]; // Array to hold Hiragana input, split by commas
        String romanjiInput; // Romanji input string from user, comma delimited
        String[] romanji = new String[48]; // Array to hold Romanji input, split by commas

        int switchIndex;
        char firstChar;

        int correct = 0;
        int incorrect = 0;
        int typo = 0;
        float percentage = 0;
        int total = 0;

        input = input.trim();
        input = input.startsWith(",") ? input.substring(1) : input;

        firstChar = input.charAt(0);

        if (Character.UnicodeBlock.of(firstChar) == Character.UnicodeBlock.BASIC_LATIN) {
            System.out.println("First half is Romanji");
            switchIndex = findLatinSwitchIndex(input, false);
            romanjiInput = input.substring(0, switchIndex);
            hiraganaInput = input.substring(switchIndex);
        } else {
            System.out.println("First half is Hiragana");
            switchIndex = findLatinSwitchIndex(input, true); // is finding a comma and says that is where it switched
            hiraganaInput = input.substring(0, switchIndex);
            romanjiInput = input.substring(switchIndex);
        }

        // Debug
//        System.out.println("Romanji: " + romanjiInput);
//        System.out.println("Hiragana: " + hiraganaInput);

        // Make sure there is no whitespace at the beginning or end
        romanjiInput = romanjiInput.trim();
        hiraganaInput = hiraganaInput.trim();

        // Remove a possible trailing or leading commas
        romanjiInput = romanjiInput.endsWith(",") ? romanjiInput.substring(0, romanjiInput.length() - 1) : romanjiInput;
        hiraganaInput = hiraganaInput.endsWith(",") ? hiraganaInput.substring(0, hiraganaInput.length() - 1) : hiraganaInput;
        romanjiInput = romanjiInput.startsWith(",") ? romanjiInput.substring(1) : romanjiInput;
        hiraganaInput = hiraganaInput.startsWith(",") ? hiraganaInput.substring(1) : hiraganaInput;

        // Make sure there is no whitespace at the beginning or end (again - removing the commas may have revealed whitespace)
        romanjiInput = romanjiInput.trim();
        hiraganaInput = hiraganaInput.trim();

        // Debug
//        System.out.println("Trimmed");
//        System.out.println(romanjiInput);
//        System.out.println(hiraganaInput);

        //Split the input strings by commas and save it to the arrays
        romanji = romanjiInput.split(",");
        hiragana = hiraganaInput.split(",");

        // Debug
//        System.out.println("Romanji Length: " + romanji.length);
//        System.out.println("Romanji: " + Arrays.toString(romanji));
//        System.out.println("Hiragana Length: " + hiragana.length);
//        System.out.println("Hiragana:" + Arrays.toString(hiragana));

        // Iterate through the arrays and check if the Romanji is a correct translation of the Hiragana
        if (romanji.length != hiragana.length) { // Make sure the user gave lists of the same length
            System.out.println("ERROR: The number of Romanji and Hiragana characters do not match; ensure that all Romanji entries have a corresponding Hiragana entry and vice versa.");
            return false;
        } else {
            for (int i = 0; i < romanji.length; i++) {
                total++;
                if (RomanjiHiragana.containsKey(romanji[i].toLowerCase())) {
                    if (RomanjiHiragana.get(romanji[i].toLowerCase()).equals(hiragana[i])) {
                        System.out.println("Correct: " + romanji[i].toUpperCase() + " " + hiragana[i]);
                        correct++;
                    } else {
                        System.out.println("Incorrect: " + romanji[i].toUpperCase() + " " + hiragana[i]);
                        incorrect++;
                    }
                } else {
                    System.out.println("Typo: " + romanji[i].toUpperCase() + " " + hiragana[i]);
                    typo++;
                }
            }
        }


        // Let the user know how they did
        percentage = (float) correct / total * 100;
        System.out.println("\n" + percentage + "%");
        System.out.println(correct + " Correct\n" + incorrect + " Incorrect\n" + typo + " Typos\n");
        return true;
    }


    /**
     * Find the index where String switches from Latin to Hiragana characters or vice versa
     *
     * @param input        the input string
     * @param becomesLatin true if the string becomes Latin characters, false if it becomes Hiragana characters
     * @return the index where the switch occurs or the length of the string if no switch occurs
     */
    public static int findLatinSwitchIndex(String input, boolean becomesLatin) { // Find where the Latin characters start
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (becomesLatin) {
                if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN && c != ',') {
//                    System.out.println("Is Latin Index: " + i); // Debug
                    return i;
                }
            } else {
                if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA) {
//                    System.out.println("Hiragana Index: " + i); // Debug
                    return i;
                }
            }
        }
        return input.length(); // Return the length of the string if all characters are Latin or Hiragana
    }

    /**
     * Create a HashMap object called RomanjiHiragana and populate it
     * Put in a method for cleanliness
     *
     * @return RomanjiHiragana HashMap of Romanji to Hiragana characters
     */
    private static HashMap<String, String> getRomanjiHiragana() {
        HashMap<String, String> RomanjiHiragana = new HashMap<String, String>();
        RomanjiHiragana.put("a", "あ");
        RomanjiHiragana.put("i", "い");
        RomanjiHiragana.put("u", "う");
        RomanjiHiragana.put("e", "え");
        RomanjiHiragana.put("o", "お");
        RomanjiHiragana.put("ka", "か");
        RomanjiHiragana.put("ki", "き");
        RomanjiHiragana.put("ku", "く");
        RomanjiHiragana.put("ke", "け");
        RomanjiHiragana.put("ko", "こ");
        RomanjiHiragana.put("sa", "さ");
        RomanjiHiragana.put("shi", "し");
        RomanjiHiragana.put("su", "す");
        RomanjiHiragana.put("se", "せ");
        RomanjiHiragana.put("so", "そ");
        RomanjiHiragana.put("ta", "た");
        RomanjiHiragana.put("chi", "ち");
        RomanjiHiragana.put("tsu", "つ");
        RomanjiHiragana.put("te", "て");
        RomanjiHiragana.put("to", "と");
        RomanjiHiragana.put("na", "な");
        RomanjiHiragana.put("ni", "に");
        RomanjiHiragana.put("nu", "ぬ");
        RomanjiHiragana.put("ne", "ね");
        RomanjiHiragana.put("no", "の");
        RomanjiHiragana.put("ha", "は");
        RomanjiHiragana.put("hi", "ひ");
        RomanjiHiragana.put("fu", "ふ");
        RomanjiHiragana.put("hu", "ふ");
        RomanjiHiragana.put("he", "へ");
        RomanjiHiragana.put("ho", "ほ");
        RomanjiHiragana.put("ma", "ま");
        RomanjiHiragana.put("mi", "み");
        RomanjiHiragana.put("mu", "む");
        RomanjiHiragana.put("me", "め");
        RomanjiHiragana.put("mo", "も");
        RomanjiHiragana.put("ya", "や");
        RomanjiHiragana.put("yu", "ゆ");
        RomanjiHiragana.put("yo", "よ");
        RomanjiHiragana.put("ra", "ら");
        RomanjiHiragana.put("ri", "り");
        RomanjiHiragana.put("ru", "る");
        RomanjiHiragana.put("re", "れ");
        RomanjiHiragana.put("ro", "ろ");
        RomanjiHiragana.put("wa", "わ");
        RomanjiHiragana.put("wo", "を");
        RomanjiHiragana.put("n", "ん");

        return RomanjiHiragana;
    }
}
