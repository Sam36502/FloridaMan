package ch.longschlong.floridaman.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("floridaman")
public class FloridaManService {

    private static final String BEGINNING = "Florida Man ";


    private List<String> playerNames = new ArrayList<>();

    private String currentSentence = BEGINNING;


    // Registers a new player in the list
    public void registerPlayer(String name) {

    }

    // Submits a word for the given user
    public void submitWord(String name, String word) {

    }

}
