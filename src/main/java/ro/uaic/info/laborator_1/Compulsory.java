package ro.uaic.info.laborator_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Compulsory", value = "/compulsory")
public class Compulsory extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String word = request.getParameter("word");
        List <Character> orderedChars = new ArrayList<>();

        if ( word != null ) {
            for ( int i = 0; i < word.toCharArray().length; ++ i ) {
                orderedChars.add ( word.toCharArray()[i] );
            }

            orderedChars.sort(Comparator.naturalOrder());
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + orderedChars + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}