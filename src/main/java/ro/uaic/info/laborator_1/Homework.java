package ro.uaic.info.laborator_1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet(name = "Homework", value = "/homework")
public class Homework extends HttpServlet {

    private Set<String> validWords = new HashSet<>();

    @Override
    public void init() throws ServletException {
        super.init();

        InputStream file = getClass().getClassLoader().getResourceAsStream("dict.txt");
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

    public void permute (String word, Set< String > results, boolean[] used, int l, String curr, int k ) {
        if ( k == l ) {
            if ( curr.length() == l && this.validWords.contains(curr) )
                results.add(curr);
        } else {
            for ( int i = 0; i < word.length(); ++ i ) {
                if ( ! used[i] ) {
                    used[i] = true;
                    curr += word.charAt(i);
                    permute (word, results, used, l, curr, k + 1);
                    curr = curr.substring(0, curr.length() - 1);
                    used[i] = false;
                }
            }
        }
    }

    public Set <String> getSeq (String word) {

        boolean[] used = new boolean[word.length()];
        Set <String> results = new TreeSet<>();
        for ( int i = 1; i <= word.length(); ++ i ) {
            permute ( word, results, used, i, "", 0 );
        }

        return results;
    }

    public Set <String> getPerm (String word, int size) {


        boolean[] used = new boolean[word.length()];
        Set <String> results = new TreeSet<>();
        permute ( word, results, used, size, "", 0 );

        return results;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String word = request.getParameter("word");
        String size = request.getParameter("size");
        Set <String> formedWords = new TreeSet<>();
        List <Character> orderedChars = new ArrayList<>();

        if ( word != null ) {

            for ( int i = 0; i < word.toCharArray().length; ++ i ) {
                orderedChars.add ( word.toCharArray()[i] );
            }

            orderedChars.sort(Comparator.naturalOrder());
            StringBuilder sortedWord = new StringBuilder();
            for ( Character c : orderedChars ) {
                sortedWord.append(c);
            }

            if ( size == null ) {
                formedWords = this.getSeq (sortedWord.toString());
            } else {

                int castedSize = Integer.parseInt(size);
                if ( castedSize > 0 )
                    formedWords = this.getPerm (sortedWord.toString(), castedSize);
                else
                    formedWords = this.getSeq(sortedWord.toString());
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + formedWords + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}