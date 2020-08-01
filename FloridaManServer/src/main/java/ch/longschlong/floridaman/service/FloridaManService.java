package ch.longschlong.floridaman.service;

import ch.longschlong.floridaman.utils.Screen;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("floridaman")
public class FloridaManService {

    private static final String BEGINNING = "Florida Man";
    private static final List<String> EMPTY_LIST = List.of("No Players");

    private List<String> playerNames = new ArrayList<>();

    private int currPlayerIndex = -1;

    private StringBuilder currentSentence = new StringBuilder(BEGINNING);


    // Registers a new player in the list
    public void registerPlayer(final String name) {
        if (currPlayerIndex < 0) {
            currPlayerIndex = 0;
            playerNames = new ArrayList<>();
        }

        playerNames.add(name);
        // Draw Screen
        Screen.display(currentSentence.toString(), playerNames, currPlayerIndex);
    }

    // De-Registers a player from the list
    public void deregisterPlayer(final String name) {
        playerNames.remove(name);

        if (playerNames.size() < 1) {
            currPlayerIndex = -1;
            playerNames = EMPTY_LIST;
        }

        // Draw Screen
        Screen.display(currentSentence.toString(), playerNames, currPlayerIndex);
    }

    // Submits a word for the given user
    public void submitWord(final String name, final String word) {
        String trimmedWord = word.trim();

        // Check if round terminated
        String oldSentence = null;
        if (".".equals(trimmedWord)) {
            currentSentence.append('.');
            oldSentence = currentSentence.toString();

            // TODO: Send finished sentence to archive API

            currentSentence = new StringBuilder(BEGINNING);

        // Append word to sentence
        } else {
            currentSentence.append(' ').append(trimmedWord);
        }

        // Move to next player
        currPlayerIndex++;
        if (currPlayerIndex == playerNames.size()) {
            currPlayerIndex = 0;
        }

        // Draw Screen
        Screen.display(currentSentence.toString(), playerNames, currPlayerIndex, oldSentence);
    }

    // Checks if a name has already been registered
    public boolean playerExists(final String name) {
        return playerNames.contains(name);
    }

    // Returns the name of the current player whose turn it is
    public String whoseTurn() {
        if (currPlayerIndex < 0 || currPlayerIndex >= playerNames.size()) {
            return "nobody";
        } else {
            return playerNames.get(currPlayerIndex);
        }
    }

}
