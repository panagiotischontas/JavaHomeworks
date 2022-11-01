<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab 2</title>
</head>
<body>
<h1><%= "Lab 2" %>
</h1>
<form action="./result.jsp">
    <label for="iLetters">Letters: </label>
    <input type="text" id="iLetters" name="letters"> <br>
    <label for="iSize">Size: </label>
    <input type="number" id="iSize" name="size"> <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>