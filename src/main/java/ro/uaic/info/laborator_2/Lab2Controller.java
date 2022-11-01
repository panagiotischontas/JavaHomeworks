package ro.uaic.info.laborator_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/lab2Homework/Controller")
public class Lab2Controller extends HttpServlet {

    private Dictionary dictionary = null;

    @Override
    public void init() throws ServletException {
        super.init();

        this.dictionary = new Dictionary("dict.txt");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String word = request.getParameter("letters");
        String sizeStr = request.getParameter("size");
        String nextPage = (word != null && ! word.isEmpty()) ? "/lab2Homework/result.jsp" : "/lab2Homework/input.jsp";

        if ( word == null ) {
            word = "";
        }

        int size;
        if ( sizeStr == null ) {
            size = word.length();
        } else {
            size = Integer.parseInt(sizeStr);
        }

        GeneratedWords words = new GeneratedWords ( word, size, this.dictionary );
        words.run();

        request.setAttribute("words", words);
        getServletContext().getRequestDispatcher(nextPage).forward(request, response);
    }

    public void destroy() {
    }
}