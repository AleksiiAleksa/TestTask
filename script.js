function permit()
{
	if (document.calc.date.value =='' || document.calc.deposit.value =='' || (document.calc.rad[1].checked && document.calc.fill.value ==''))
	{
		document.calc.bt.disabled = true; 
	}
	else
	{
		document.calc.bt.disabled = false; 
	}
	if(document.calc.deposit.value<1000 ||document.calc.deposit.value>3000000)
	{
		document.calc.deposit.value ='';
	}
	if(document.calc.fill.value<1000 ||document.calc.fill.value>3000000)
	{
		document.calc.fill.value ='';
	}
}
function locking()
{
	if (document.calc.rad[0].checked)
	{
		document.calc.fill.disabled = 1;
		document.calc.fill.value ='';
	}
	else
	{
		document.calc.fill.disabled = 0;
		document.calc.bt.disabled = true; 
	}
}
function execute()
{
	var helper = 1;
	if (document.calc.rad[0].checked)
	{
		helper=0;
	}
	var i = document.getElementById('select').options.selectedIndex; 
	var date = document.getElementById('date').value;
	var sumDeposit = document.getElementById('sumDeposit').value;
	var sumRefill = document.getElementById('sumRefill').value;
    $.ajax({
		type: "POST",
		url:'calc.php',
		data: {date: date,sumDeposit: sumDeposit,index: i,rad: helper,sumRefill: sumRefill},
		success: function(result){
			document.getElementById('res').innerText = result;
		}
    });
}