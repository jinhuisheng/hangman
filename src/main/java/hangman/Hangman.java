package hangman;

import java.util.stream.IntStream;

/**
 * @author huisheng.jin
 * @date 2020/9/19.
 */
public class Hangman {
    private static final int TRIES_COUNT = 12;
    private final String answer;
    private String used = "aeiou";
    private String discovered = "";
    private Integer tries = TRIES_COUNT;
    private static final String VOWELS = "aeiou";

    public Hangman(String answer) {
        this.answer = answer;
        initDiscovered(answer);
    }

    private void initDiscovered(String word) {
        for (String s : word.split("")) {
            discovered += VOWELS.contains(s) ? s : "_";
        }
    }

    public void type(char c) {
        if (isAnswerContains(c)) {
            if (isDiscoveredContains(c)) {
                decreaseTries();
            } else {
                replaceDiscovered(c);
            }
        } else {
            decreaseTries();
        }
        appendUsed(c);
    }

    public Integer getLength() {
        return answer.length();
    }

    public String getUsed() {
        return used;
    }

    public String getDiscovered() {
        return discovered;
    }

    public Integer getTries() {
        return tries;
    }

    private boolean isDiscoveredContains(char c) {
        return discovered.indexOf(c) != -1;
    }

    private void decreaseTries() {
        tries -= 1;
    }

    private boolean isAnswerContains(char c) {
        return answer.indexOf(c) != -1;
    }

    private void appendUsed(char c) {
        if (!isUsedContains(c)) {
            used += c;
        }
    }

    private boolean isUsedContains(char c) {
        return used.indexOf(c) != -1;
    }

    private void replaceDiscovered(char c) {
        StringBuilder builder = new StringBuilder(discovered);
        String[] answerArray = answer.split("");
        IntStream.range(0, answerArray.length)
                .filter(i -> answerArray[i].equals(String.valueOf(c)))
                .forEach(i -> builder.setCharAt(i, c));
        this.discovered = builder.toString();
    }

    public Boolean isWin() {
        return discovered.equals(answer);
    }
}
