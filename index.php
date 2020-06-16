<html>
<head>
 <title> Тестовое задание </title>
 <link rel=stylesheet href="styles.css">
 <script src="script.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
   <meta charset="utf-8">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#date" ).datepicker();
  } );
  $( function() {
    $( "#slider" ).slider({
      value:1500000,
      min: 1000,
      max: 3000000,
      step: 100,
      slide: function( event, ui ) {
        $( "#sumDeposit" ).val(ui.value );
      }
    });
    $( "#sumDeposit" ).val( $( "#slider" ).slider( "value" ) );
  } );
   $( function() {
    $( "#slider2" ).slider({
      value:1500000,
      min: 1000,
      max: 3000000,
      step: 100,
      slide: function( event, ui ) {
		  if (document.calc.rad[1].checked)
		  {
			$( "#sumRefill" ).val(ui.value );
		  }
      }
    });
	if (document.calc.rad[1].checked)
		  {
			$( "#sumRefill" ).val( $( "#slider2" ).slider( "value" ) );
		  }
   });
  </script>
  </script>
   </head>
   <body link="white" vlink="#FFFACD">
		<header>
			<img src="log.png" width=130>
			<div id="headerInfo">
			8-800-100-5005<br>+7(3452)522-000
			</div>
		</header>  
<div id="main">		
	<div class="header">
		<div>Кредитные карты</div>
		<div>Вклады</div>
		<div>Дебетовая карта</div>
		<div>Страхование</div>
		<div>Друзья</div>
		<div id="last">Интернет-банк</div>
	</div>
	<div id="content">
	<div class="bread">
		<ul>
			<li><a href="#">Главная</a></li>
			<li><a href="#">Вклады</a></li>
			<li><a class="active">Калькулятор</a></li>
		</ul>
	</div>
	<form method="POST" name="calc">
	<h1>Калькулятор</h1>
	<div id="left">
		<p>Дата оформления вклада 
		<p>Сумма вклада
		<p>Срок вклада 
		<p>Пополнение вклада
		<p>Сумма пополнения вклада 
	</div>
	<div id="center">
		<p><input type="text" class="update" id="date" name="date" onchange="permit()">
		<p><input type="text" class="update" id="sumDeposit" name="deposit" onchange="permit()" onKeyPress="if ((event.keyCode < 48)||(event.keyCode > 57)) event.returnValue = false">
		<p><select class="update" id="select" name="list" onchange="permit()">
			<option>1 год</option>
			<option>2 года</option>
			<option>3 года</option>
			<option>4 года</option>
			<option>5 лет</option>
		</select>
		<p><input type="radio" name="rad" value="no" checked onchange="locking()"> нет
		<input type="radio" name="rad" value="yes" onchange="locking()"> да
		<p><input type="text" class="update" id="sumRefill"  name="fill" disabled onchange="permit()" onKeyPress="if ((event.keyCode < 48)||(event.keyCode > 57)) event.returnValue = false">
	</div>
	<div id="right">
      <div id="slider"><p>1000 <p id="rightFloat">3000000</div>
	   <div id="slider2"><p>1000 <p id="rightFloat">3000000</div>
	</div>
	<div id="bottom">
		<p><input type="button" value="Рассчитать" name="bt" id="button" onclick="execute()" disabled>
		<span class="result">Результат:</span><label id="res"></label>
	</div>
	</form>
	</div>
	</div>
	<footer>
	   
	</footer>
</body>
</html>