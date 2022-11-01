package ro.uaic.info.laborator_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    public static Dictionary empty () {
        return new Dictionary (null);
    }

    private final Set<String> validWords = new HashSet<>();

    public Dictionary (String path) {

        if ( path != null ) {
            InputStream file = getClass().getClassLoader().getResourceAsStream(path);
            InputStreamReader reader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while (true) {
                try {
                    String line;
                    if ((line = bufferedReader.readLine()) == null)
                        break;

                    this.validWords.add(line.toLowerCase());
                } catch (IOException e) {
                    break;
                }
            }
        }
    }

    public boolean test (String word) {
        return this.validWords.isEmpty() || this.validWords.contains(word);
    }
}
