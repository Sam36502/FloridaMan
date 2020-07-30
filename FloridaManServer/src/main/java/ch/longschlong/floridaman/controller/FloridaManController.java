package ch.longschlong.floridaman.controller;

import ch.longschlong.floridaman.service.FloridaManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public HttpStatus registerUser(@RequestParam(name="name") String name) {

        if (floridaManService.playerExists(name)) {
            return HttpStatus.FORBIDDEN;
        }

        floridaManService.registerPlayer(name);
        return HttpStatus.OK;
    }

    // Client calls this to submit their next word
    // Returns 'HTTP OK' when word accepted
    // Returns 'HTTP FORBIDDEN' if it's not the player's turn
    // Returns 'HTTP BAD_REQUEST' on an invalid request
    @PostMapping("/floridaman")
    public HttpStatus submitWord(
            @RequestParam(name="name") String name,
            @RequestParam(name="word") String word
    ) {

        return HttpStatus.OK;
    }


}
