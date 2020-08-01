package ch.longschlong.floridaman.utils;

import org.fusesource.jansi.Ansi;

import java.util.List;

/*

    Handles displaying things on screen

 */
public class Screen {

    // Style Constants
    private static final int FINISHED_SENTENCE_PAUSE = 5000; // Milliseconds
    private static final int LEFT_MARGIN = 8;
    private static final int SENTENCE_TOP_MARGIN = 4;
    private static final int SENTENCE_MAX_HEIGHT = 3;
    private static final int PLAYERS_TOP_MARGIN = 5;
    private static final Ansi.Color TEXT_FG = Ansi.Color.WHITE;
    private static final Ansi.Color TEXT_BG = Ansi.Color.BLACK;
    private static final Ansi.Color SENTENCE_FG = Ansi.Color.WHITE;
    private static final Ansi.Color SENTENCE_BG = Ansi.Color.BLACK;
    private static final Ansi.Color SELECTED_FG = Ansi.Color.BLACK;
    private static final Ansi.Color SELECTED_BG = Ansi.Color.WHITE;

    public static void display (String sentence, List<String> names, int nameIndex, String oldSentence) {
        clearScreen();

        // Display Current Sentence
        setColour(SENTENCE_FG, SENTENCE_BG);
        setCursorPos(LEFT_MARGIN, SENTENCE_TOP_MARGIN);
        System.out.print("Current Sentence:");
        setCursorPos(LEFT_MARGIN, SENTENCE_TOP_MARGIN + 1);
        System.out.print(sentence);
        if (oldSentence != null) {

            setCursorPos(LEFT_MARGIN, SENTENCE_TOP_MARGIN - 2);
            setColour(SENTENCE_FG, SENTENCE_BG);
            System.out.print("Completed Sentence: " + oldSentence);

        }
        setColour(TEXT_FG, TEXT_BG);

        // Display List of players
        setCursorPos(LEFT_MARGIN, SENTENCE_TOP_MARGIN + SENTENCE_MAX_HEIGHT + PLAYERS_TOP_MARGIN);
        setColour(TEXT_FG, TEXT_BG);
        for (int i=0; i<names.size(); i++) {
            // Highlight current player
            if (i == nameIndex) {
                setColour(SELECTED_FG, SELECTED_BG);
                System.out.print(names.get(i));
                setColour(TEXT_FG, TEXT_BG);
            } else {
                System.out.print(names.get(i));
            }
            // Move to next line
            System.out.print(Ansi.ansi().cursorDownLine().cursorToColumn(LEFT_MARGIN));
        }

    }

    // Default Screen draw method
    public static void display (String sentence, List<String> names, int nameIndex) {
        display(sentence, names, nameIndex, null);
    }

    private static void setCursorPos(int x, int y) {
        System.out.print(Ansi.ansi().cursor(y, x));
    }

    private static void setColour(Ansi.Color fg, Ansi.Color bg) {
        System.out.print(Ansi.ansi().fg(fg).bg(bg));
    }

    private static void clearScreen() {
        setColour(TEXT_FG, TEXT_BG);
        System.out.print(Ansi.ansi().eraseScreen(Ansi.Erase.ALL));
    }

}
