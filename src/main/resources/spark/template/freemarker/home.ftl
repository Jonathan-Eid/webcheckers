<!DOCTYPE html>
<!-- This is the code used to display the home page of the checkers game -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="3">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
      <a href="/">my home</a>
    </div>
    
    <div class="body">
      <p>Welcome to the world of online Checkers.</p>

      <#if message??>
        ${error} <br/>
      </#if>

      <#if userSignedIn??>
        Your name is ${user} <br/></br>
        Here are the players currently signed in </br>
        ${playerList} <br/>
        <a href="./signOut">Sign Out</a>

      <#else>
         <p> Number of Players Online: ${numPlayers}</p><br/>
         <a href="./signIn">Sign In</a>
      </#if>

    </div>
    
  </div>
</body>
</html>
