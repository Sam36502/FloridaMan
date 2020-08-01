package ch.longschlonggang.floridaman.utils;

import ch.longschlonggang.floridaman.Main;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/*

    Provides static methods for connecting to the server

 */
public class HttpClient {

    // Sends a POST request and returns the status code
    private static int sendPostRequest(URI uri) {
        HttpPost post = new HttpPost(uri);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            Main.errorMsg("Unable to send POST request.", e);
        }

        return response.getStatusLine().getStatusCode();
    }

    // Sends a DELETE request and returns the status code
    private static int sendDeleteRequest(URI uri) {
        HttpDelete post = new HttpDelete(uri);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            Main.errorMsg("Unable to send DELETE request.", e);
        }

        return response.getStatusLine().getStatusCode();
    }

    //

    // Registers a user with the server
    public static void registerUser(String url, String name) {
        URIBuilder builder = null;
        URI uri = null;
        try {
            builder = new URIBuilder(url)
                    .setPath("/connect")
                    .addParameter("name", name);
            uri = builder.build();
        } catch (URISyntaxException e) {
            Main.errorMsg("Unable to register player with server.", e);
        }

        // Handle Statuses
        switch (sendPostRequest(uri)) {
            case 403:
                Main.errorMsg("That name is already taken");
                break;

            case 400:
                Main.errorMsg("An error occurred while registering the user on the server.");
        }
    }

    // Submits a word to the server
    public static void submitWord(String url, String name, String word) {
        URIBuilder builder = null;
        URI uri = null;
        try {
            builder = new URIBuilder(url)
                    .setPath("/floridaman")
                    .addParameter("name", name)
                    .addParameter("word", word);
            uri = builder.build();
        } catch (URISyntaxException e) {
            Main.errorMsg("Unable to submit word to server.", e);
        }

        // Handle Statuses
        switch (sendPostRequest(uri)) {
            case 403:
                Main.errorMsg("It's not currently your turn.\nBe Patient.");
                break;

            case 400:
                Main.errorMsg("An error occurred while submitting your word the server.");
        }
    }

    // De-Registers a player from the server
    public static void deregisterUser(String url, String name) {
        URIBuilder builder = null;
        URI uri = null;
        try {
            builder = new URIBuilder(url)
                    .setPath("/connect")
                    .addParameter("name", name);
            uri = builder.build();
        } catch (URISyntaxException e) {
            Main.errorMsg("Unable to De-Register player from the server.", e);
        }

        // Handle Statuses
        switch (sendDeleteRequest(uri)) {
            case 400:
                Main.errorMsg("Attempted to disconnect '" + name + "' from server, but failed.");
        }
    }

}
