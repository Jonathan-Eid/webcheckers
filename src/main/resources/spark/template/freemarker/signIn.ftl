<!DOCTYPE html>
<!-- This is the code used to display the sign in page of the checkers game -->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <meta http-equiv="refresh" content="10">
        <title> Web Checkers</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
    <body>
      <div class="page">

        <h1>Web Checkers</h1>

        <div class="navigation">
          <a href="/">my home</a>
        </div>

        <div class="body">
          <p>
            <#if invalidSignInMessage??>
                <div>${invalidSignInMessage}</div>
            </#if>
          </p>
          <p>${signInMessage}</p>

          <form action="./signingIn" method="POST">
              <label for="user">username:</label>
              <input type="text" id="user" name="username">
              <label for="password">password:</label>
              <input type="text" id = "password" name = "password">
              <button type="submit">Sign In</button>
          </form>

        </div>

      </div>
    </body>
</html>
