package ch.longschlonggang.floridaman.utils;

import org.fusesource.jansi.Ansi;

public class AnsiUtils {

    public static void clearScreen() {
        System.out.print(Ansi.ansi().eraseScreen().cursor(1, 1));
    }

    public static void setCursorPos(int row, int col) {
        System.out.print(Ansi.ansi().cursor(row, col));
    }

    public static void printWithMargins(String str, int leftMargin, int topMargin) {
        String[] strs = str.split("\\n");
        setCursorPos(topMargin, leftMargin);
        for (String s: strs) {
            System.out.print(s);
            System.out.print(Ansi.ansi().cursorDownLine().cursorToColumn(leftMargin));
        }
    }

    public static void resetCursorColour() {
        System.out.print(Ansi.ansi().bgDefault().fgDefault());
    }

    public static void setCursorColour(Colour fg, Colour bg) {
        if (fg.isBright()) {
            System.out.print(Ansi.ansi().fgDefault().fgBright(fg.getAnsiColour()));
        } else {
            System.out.print(Ansi.ansi().fgDefault().fg(fg.getAnsiColour()));
        }

        if (bg.isBright()) {
            System.out.print(Ansi.ansi().bgDefault().bgBright(bg.getAnsiColour()));
        } else {
            System.out.print(Ansi.ansi().bgDefault().bg(bg.getAnsiColour()));
        }
    }

}
