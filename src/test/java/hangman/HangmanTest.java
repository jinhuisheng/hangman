package hangman;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/9/19.
 */
public class HangmanTest {

    private Hangman hangman = new Hangman("word");

    @Test
    void start_game() {
        assertThat(hangman.getTries()).isEqualTo(12);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiou");
        assertThat(hangman.getDiscovered()).isEqualTo("_o__");
    }

    @Test
    void type_a_vowel() {
        hangman.type('o');
        assertThat(hangman.getTries()).isEqualTo(11);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiou");
        assertThat(hangman.getDiscovered()).isEqualTo("_o__");
    }

    @Test
    void type_a_wrong_consonant() {
        hangman.type('t');
        assertThat(hangman.getTries()).isEqualTo(11);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiout");
        assertThat(hangman.getDiscovered()).isEqualTo("_o__");
    }

    @Test
    void type_a_wrong_consonant_twice() {
        hangman.type('t');
        hangman.type('t');
        assertThat(hangman.getTries()).isEqualTo(10);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiout");
        assertThat(hangman.getDiscovered()).isEqualTo("_o__");
    }

    @Test
    void should_game_over_type_a_wrong_consonant_12_times() {
        IntStream.range(0, 12).forEach(item -> hangman.type('t'));
        assertThat(hangman.getTries()).isEqualTo(0);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiout");
        assertThat(hangman.getDiscovered()).isEqualTo("_o__");
    }

    @Test
    void type_a_right_consonant() {
        hangman.type('w');
        assertThat(hangman.getTries()).isEqualTo(12);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiouw");
        assertThat(hangman.getDiscovered()).isEqualTo("wo__");
    }

    @Test
    void type_a_right_consonant_when_answer_has_duplicate_char() {
        Hangman hangman = new Hangman("everybody");
        hangman.type('y');
        assertThat(hangman.getTries()).isEqualTo(12);
        assertThat(hangman.getLength()).isEqualTo(9);
        assertThat(hangman.getUsed()).isEqualTo("aeiouy");
        assertThat(hangman.getDiscovered()).isEqualTo("e_e_y_o_y");
    }

    @Test
    void type_a_same_right_consonant_twice() {
        hangman.type('w');
        hangman.type('w');
        assertThat(hangman.getTries()).isEqualTo(11);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiouw");
        assertThat(hangman.getDiscovered()).isEqualTo("wo__");
    }

    @Test
    void should_win_the_game_when_type_right_to_over() {
        hangman.type('w');
        hangman.type('r');
        hangman.type('d');
        assertThat(hangman.getTries()).isEqualTo(12);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiouwrd");
        assertThat(hangman.getDiscovered()).isEqualTo("word");
        assertThat(hangman.isWin()).isEqualTo(true);
    }

    @Test
    void should_fail_the_game_type_wrong_chars_12_times() {
        IntStream.range(0, 12).forEach(item -> hangman.type('t'));
        assertThat(hangman.getTries()).isEqualTo(0);
        assertThat(hangman.getLength()).isEqualTo(4);
        assertThat(hangman.getUsed()).isEqualTo("aeiout");
        assertThat(hangman.getDiscovered()).isEqualTo("_o__");
        assertThat(hangman.isWin()).isEqualTo(false);
    }

}
