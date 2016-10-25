<html>
  <head>
<!-- <script type="text/javascript">  -->    
<script>
      function replyOne () 
      {
          document.getElementById("input1").value = 
            document.getElementById("username").innerHTML;
      }
    </script>
  </head>
  <body>
    <p id="username">Jack</p>
    <input id="input1" ></input>
    <button onclick="replyOne()">Copy Text</button>
  </body>
</html>