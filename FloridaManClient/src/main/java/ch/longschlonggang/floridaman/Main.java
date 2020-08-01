package ch.longschlonggang.floridaman;

import ch.longschlonggang.floridaman.utils.AnsiUtils;
import ch.longschlonggang.floridaman.utils.Colour;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    private static final String BANNER = "" +
            "$$$$$$$$\\ $$\\                     $$\\       $$\\                 $$\\      $$\\                     \n" +
            "$$  _____|$$ |                    \\__|      $$ |                $$$\\    $$$ |                    \n" +
            "$$ |      $$ | $$$$$$\\   $$$$$$\\  $$\\  $$$$$$$ | $$$$$$\\        $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\  \n" +
            "$$$$$\\    $$ |$$  __$$\\ $$  __$$\\ $$ |$$  __$$ | \\____$$\\       $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\ \n" +
            "$$  __|   $$ |$$ /  $$ |$$ |  \\__|$$ |$$ /  $$ | $$$$$$$ |      $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ |\n" +
            "$$ |      $$ |$$ |  $$ |$$ |      $$ |$$ |  $$ |$$  __$$ |      $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |\n" +
            "$$ |      $$ |\\$$$$$$  |$$ |      $$ |\\$$$$$$$ |\\$$$$$$$ |      $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\n" +
            "\\__|      \\__| \\______/ \\__|      \\__| \\_______| \\_______|      \\__|     \\__| \\_______|\\__|  \\__|";

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        // Main Menu
        boolean isRunning = true;
        while (isRunning) {
            AnsiUtils.clearScreen();
            AnsiUtils.setCursorColour(Colour.BRIGHT_BLUE, Colour.BLACK);
            AnsiUtils.printWithMargins(BANNER, 4, 2);
            AnsiUtils.setCursorColour(Colour.BRIGHT_RED, Colour.BLACK);
            AnsiUtils.printWithMargins("Version 1.0-SNAPSHOT\nMade by Long-Schlong-Gang", 4, 12);
            AnsiUtils.resetCursorColour();
            AnsiUtils.printWithMargins("" +
                            " Main Menu:\n" +
                            "------------\n" +
                            " [1] Get a random 'Florida Man' quote\n" +
                            " [2] Host a game\n" +
                            " [3] Join a game\n" +
                            " [4] Quit\n",
                    4, 16
            );
            System.out.print("\n > ");

            int opt;
            try {
                opt = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            if (opt < 1 || opt > 4) continue;

            switch (opt) {
                case 1:
                    getRandomQuote();
                    break;

                case 2:
                    hostGame();
                    break;

                case 3:
                    joinGame();
                    break;

                case 4:
                    isRunning = false;
                    break;
            }

        }

        // On Program Exit
        AnsiUtils.clearScreen();
        AnsiUtils.setCursorColour(Colour.BRIGHT_CYAN, Colour.BLACK);
        AnsiUtils.printWithMargins(" Thank you for playing!\n Press [ENTER] to leave.", 10, 5);
        input.nextLine();

        input.close();
        AnsiConsole.systemUninstall();
    }

    /*
        Menu Option Methods:
     */

    private static void getRandomQuote() {

    }

    private static void hostGame() {

    }

    private static void joinGame() {

    }

}
