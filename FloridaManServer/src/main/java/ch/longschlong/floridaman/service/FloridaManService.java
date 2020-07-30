package ch.longschlong.floridaman.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("floridaman")
public class FloridaManService {

    private static final String BEGINNING = "Florida Man ";


    private List<String> playerNames = new ArrayList<>();

    private int currPlayerIndex = -1;

    private StringBuilder currentSentence = new StringBuilder(BEGINNING);


    // Registers a new player in the list
    public void registerPlayer(final String name) {

        // TODO[DEBUG]:
        System.out.println("Player '" + name + "' has joined the game!");

        playerNames.add(name);
        // TODO[DEBUG]:
        System.out.println("NumPlayers: " + playerNames.size() + "; Player[0]: " + playerNames.get(0));

        if (currPlayerIndex < 0) {
            currPlayerIndex = 0;
        }
    }

    // De-Registers a player from the list
    public void deregisterPlayer(final String name) {
        // TODO[DEBUG]:
        System.out.println("Player '" + name + "' has left the game!");

        playerNames.remove(name);

        if (playerNames.size() < 1) {
            currPlayerIndex = -1;
        }
    }

    // Submits a word for the given user
    public void submitWord(final String name, final String word) {
        String trimmedWord = word.trim();

        // Check if round terminated
        if (".".equals(trimmedWord)) {

            // TODO[DEBUG]:
            System.out.println("Player '" + name + "' has terminated the round.");
            System.out.println("Final Sentence: " + currentSentence);

            currentSentence.append('.');
            return;
        }

        // Append word to current Sentence
        // TODO[DEBUG]:
        System.out.println("Player '" + name + "' submitted '" + trimmedWord + "'.");
        currentSentence.append(trimmedWord).append(' ');
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
