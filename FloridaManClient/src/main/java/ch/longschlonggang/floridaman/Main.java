package ch.longschlonggang.floridaman;

import ch.longschlonggang.floridaman.utils.AnsiUtils;
import ch.longschlonggang.floridaman.utils.Colour;
import ch.longschlonggang.floridaman.utils.HttpClient;
import org.fusesource.jansi.AnsiConsole;

import java.util.Arrays;
import java.util.Comparator;
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
            System.out.print("\n     > ");

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

        // Find Server
        String url = null;
        while (url == null) {
            AnsiUtils.clearScreen();
            AnsiUtils.setCursorColour(Colour.BRIGHT_BLUE, Colour.BLACK);
            AnsiUtils.printWithMargins("Enter the Server URL or 'exit' to return to main menu:", 4, 2);
            AnsiUtils.resetCursorColour();
            System.out.print(" > ");

            url = "http://" + input.nextLine() + ":8080";
            if ("http://exit:8080".equals(url)) return;
            if (! HttpClient.serverReachable(url)) url = null;

            if (url == null) {
                errorMsg("Unable connect to '" + url + "'.");
            }
        }

        // Register Username
        String name = null;
        while (name == null) {
            AnsiUtils.clearScreen();
            AnsiUtils.setCursorColour(Colour.BRIGHT_BLUE, Colour.BLACK);
            AnsiUtils.printWithMargins("Enter your username or 'exit' to return to main menu:", 4, 2);
            AnsiUtils.resetCursorColour();
            System.out.print(" > ");

            name = input.nextLine();
            if ("exit".equals(name)) return;

            int status = HttpClient.registerUser(url, name);
            if (status == 400 || status == 403) name = null;
        }

        // Submit words
        boolean stillPlaying = true;
        while (stillPlaying) {
            AnsiUtils.clearScreen();
            AnsiUtils.setCursorColour(Colour.BRIGHT_BLUE, Colour.BLACK);
            AnsiUtils.printWithMargins("Enter your word to submit or 'exit' to return to main menu:", 4, 2);
            AnsiUtils.resetCursorColour();
            System.out.print(" > ");

            String word = input.nextLine();
            if ("exit".equals(word)) {
                HttpClient.deregisterUser(url, name);
                stillPlaying = false;
                continue;
            }

            HttpClient.submitWord(url, name, word);
        }

    }

    // Displays an error message
    public static void errorMsg(String msg) {
        errorMsg(msg, null);
    }

    // Displays an error message with exception information
    public static void errorMsg(String msg, Exception e) {
        // Break up message if it's too long
        if (msg.length() > 100) {
            String newMsg = "";
            for (int i=0; i<msg.length(); i++) {
                newMsg += msg.charAt(i);
                if (i > 0 && i % 100 == 0) newMsg += "\n";
            }
            msg = newMsg;
            newMsg = null;
        }

        // Check if an exception was passed
        if (e != null) {
            String exc = e.toString();

            // Break up exception message if it's too long
            if (exc.length() > 100) {
                String newExc = "";
                for (int i=0; i<exc.length(); i++) {
                    newExc += exc.charAt(i);
                    if (i > 0 && i % 100 == 0) newExc += "\n";
                }
                exc = newExc;
                newExc = null;
            }

            msg += "" +
                    "\n" +
                    "\n" + exc +
                    "\n" +
                    "\n  Please notify the developers if you see this.  " +
                    "\n      Press [Enter] to close error message.";
        } else {
            msg += "" +
                    "\n" +
                    "\n  Press [Enter] to close error message.  ";
        }

        String[] lines = msg.split("\\n");
        int width = Arrays.stream(lines).max(Comparator.comparing(String::length)).get().length();

        // Put error message in an ASCII-art box
        String formattedMsg = "+--[ ERROR ]";
        for (int i=0;i<width-9;i++) formattedMsg += "-";
        formattedMsg += "+\n";
        for (String s: lines) {
            formattedMsg += "| " + s;
            for (int x=0;x<width-s.length();x++) formattedMsg += " ";
            formattedMsg += " |\n";
        }
        formattedMsg += "+";
        for (int i=0;i<width+2;i++) formattedMsg += "-";
        formattedMsg += "+";

        AnsiUtils.setCursorColour(Colour.BRIGHT_RED, Colour.BLACK);
        AnsiUtils.printWithMargins(formattedMsg, 10, 5);
        AnsiUtils.resetCursorColour();

        input.nextLine();
    }

}
