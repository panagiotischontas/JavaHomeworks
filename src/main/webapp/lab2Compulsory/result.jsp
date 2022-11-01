<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.TreeSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab 2</title>
</head>
<body>
<h1><%= "Lab 2" %>
</h1>
</body>
<%
    try {
        Set<String> validWords = new HashSet<>();

        InputStream file = application.getClassLoader().getResourceAsStream("dict.txt");
        InputStreamReader reader = new InputStreamReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        while (true) {
            try {
                String line;
                if ((line = bufferedReader.readLine()) == null)
                    break;

                validWords.add(line.toLowerCase());
            } catch (IOException e) {
                break;
            }

        }

        class Permute {
            private Set<String> validWords = null;
            public Permute (Set<String> words) { this.validWords = words; }

            void apply (String word, Set< String > results, boolean[] used, int l, String curr, int k) {
                if ( k == l ) {
                    if ( curr.length() == l && this.validWords.contains(curr) )
                        results.add(curr);
                } else {
                    for ( int i = 0; i < word.length(); ++ i ) {
                        if ( ! used[i] ) {
                            used[i] = true;
                            curr += word.charAt(i);
                            this.apply (word, results, used, l, curr, k + 1);
                            curr = curr.substring(0, curr.length() - 1);
                            used[i] = false;
                        }
                    }
                }
            }
        }

        String letters = request.getParameter("letters");
        String sizeStr = request.getParameter("size");

        Set < String > wordList = new TreeSet<>();

        if ( letters != null && sizeStr != null ) {
            int size = Integer.parseInt(sizeStr);

            Permute permuter = new Permute(validWords);
            boolean[] used = new boolean[letters.length()];
            permuter.apply(letters, wordList, used, size, "", 0);
        }
%>
<table>
    <tr>
        <td>Index</td>
        <td>Word</td>
    </tr>
    <%
        int index = 1;
        for (String word : wordList) {
    %>
            <tr>
                <td><%=index++%></td>
                <td><%=word%></td>
            </tr>
    <%
        }
    %>
</table>
<%
} catch (Exception e) {
%>
    <h1>Error : <%=e.toString()%></h1>
<%
}
%>
</html>