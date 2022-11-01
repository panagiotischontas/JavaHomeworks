<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="ro.uaic.info.laborator_2.GeneratedWords" %>
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
<table>
    <tr>
        <td>Index</td>
        <td>Word</td>
    </tr>
    <%
try {
        GeneratedWords words = (GeneratedWords)request.getAttribute("words");
        int index = 1;
        for (String word : words.getResults()) {
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