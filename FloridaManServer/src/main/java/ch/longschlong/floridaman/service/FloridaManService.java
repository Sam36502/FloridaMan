package ch.longschlong.floridaman.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("floridaman")
public class FloridaManService {

    private static final String BEGINNING = "Florida Man ";


    private List<String> playerNames = new ArrayList<>();

    private StringBuilder currentSentence = new StringBuilder(BEGINNING);


    // Registers a new player in the list
    public void registerPlayer(final String name) {
        playerNames.add(name);
    }

    // Submits a word for the given user
    public void submitWord(final String name, final String word) {

    }

    // Checks if a name has already been registered
    public boolean playerExists(final String name) {
        return playerNames.contains(name);
    }

}
