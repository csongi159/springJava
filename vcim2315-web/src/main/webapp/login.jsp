<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Login</h1>
<form action="login" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br>
  <button type="submit">Login</button>
</form>
</body>
</html>
