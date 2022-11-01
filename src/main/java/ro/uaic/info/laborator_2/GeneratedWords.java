package ro.uaic.info.laborator_2;

import java.util.Set;
import java.util.TreeSet;

public class GeneratedWords {

    private final boolean[] letterUsage;
    private final Set<String> results = new TreeSet<>();
    private String currentBuffer;
    private final int wordSize;

    private final String input;

    private Dictionary dictionary = Dictionary.empty();

    private void permute (String letters, int step ) {
        if ( step == this.wordSize ) {
            if ( this.currentBuffer.length() == this.wordSize && this.dictionary.test(this.currentBuffer) )
                this.results.add(this.currentBuffer);
        } else {
            for ( int i = 0; i < letters.length(); ++ i ) {
                if ( ! this.letterUsage[i] ) {
                    this.letterUsage[i] = true;
                    this.currentBuffer += letters.charAt(i);
                    this.permute (letters, step + 1);
                    this.currentBuffer = this.currentBuffer.substring(0, this.currentBuffer.length() - 1);
                    this.letterUsage[i] = false;
                }
            }
        }
    }

    public GeneratedWords (String lettersAsWord, int size) {
        this.letterUsage    = new boolean[lettersAsWord.length()];
        this.wordSize       = size;
        this.currentBuffer  = "";
        this.input          = lettersAsWord;
    }

    public GeneratedWords (String lettersAsWord, int size, Dictionary dict) {
        this(lettersAsWord, size);
        this.dictionary = dict;
    }

    public void run () {
        if ( ! this.input.isEmpty() )
            this.permute(this.input, 0);
    }

    public Set<String> getResults() {
        return results;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
