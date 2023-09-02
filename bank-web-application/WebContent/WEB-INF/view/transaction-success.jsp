<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
  <head>
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
  </head>
    <style>
      body {
        text-align: center;
        padding: 40px 0;
        background-color: cyan;
      }
        h1 {
          color: rgb(5, 129, 5);
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-weight: 900;
          font-size: 40px;
          margin-bottom: 10px;
        }
        p {
          color: #404F5E;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-size:20px;
          margin: 0;
        }
      span {
        color: #9ABC66;
        font-size: 100px;
        line-height: 200px;
        margin-left:-15px;
      }
      .card {
        background: white;
        padding: 60px;
        border-radius: 4px;
        box-shadow: 0 2px 3px #C8D0D8;
        display: inline-block;
        margin: 0 auto;
      }
    </style>
    <body>
        <!--  <script>
            window.onload = function() {
             document.getElementById("my_audio_1").src = "success-1-6297.mp3";
            }
        </script>-->
      <div class="card">
      <div style="border-radius:200px; height:200px; width:200px; background: rgb(5, 129, 5); margin: 100px;">
            <span class="w3-animate-opacity">&#10004;</span>
        </div>
        <h1>Transaction Successful</h1>
        <form method="GET" action="securedCustDet" style="display:inline;">
        	<input type="hidden" name="customerId" id="customerId" value="${customerId}" />
			<button type="submit">YAY!</button>
		</form> 
        <audio id="my_audio_2" autoplay>
            <source src = "${pageContext.request.contextPath}/resources/success-1-6297.mp3" id="my_audio_1" type="audio/mpeg">
        </audio>
      </div>
    </body>
</html>