package ch.longschlong.floridaman.controller;

import ch.longschlong.floridaman.service.FloridaManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *      FOOL!
 *      Nobody can control Florida Man!
 */
@RestController
public class FloridaManController {

    @Autowired
    private FloridaManService floridaManService;

    // Client calls this to register with the server
    // Returns 'HTTP OK' when registered successfully
    // Returns 'HTTP FORBIDDEN' if the player's name has already been registered
    // Returns 'HTTP BAD_REQUEST' on an invalid request
    @PostMapping("/connect")
    public ResponseEntity registerUser(@RequestParam(name="name") String name) {

        if (floridaManService.playerExists(name)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        floridaManService.registerPlayer(name);
        return new ResponseEntity(HttpStatus.OK);
    }

    // De-Registers a player from the server
    // Returns 'HTTP OK' if the player was successfully removed
    // Returns 'HTTP BAD_REQUEST' if the player isn't present or the request is invalid
    @DeleteMapping("/connect")
    public ResponseEntity deregisterUser(@RequestParam(name="name") String name) {
        if (!floridaManService.playerExists(name)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        floridaManService.deregisterPlayer(name);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Client calls this to submit their next word
    // Returns 'HTTP OK' when word accepted
    // Returns 'HTTP FORBIDDEN' if it's not the player's turn
    // Returns 'HTTP BAD_REQUEST' on an invalid request
    @PostMapping("/floridaman")
    public ResponseEntity submitWord(
            @RequestParam(name="name") String name,
            @RequestParam(name="word") String word
    ) {

        if (! floridaManService.playerExists(name)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (! floridaManService.whoseTurn().equals(name)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        floridaManService.submitWord(name, word);
        return new ResponseEntity(HttpStatus.OK);
    }


}
