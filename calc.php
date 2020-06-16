 <?php
 $date = $_POST['date'];
 $sumDeposit = $_POST['sumDeposit'];
 $index = $_POST['index'];
 $rad = $_POST['rad'];
if($rad==0)
{
	$sumRefill=0;
}
else
{
	$sumRefill = $_POST['sumRefill'];
}
 $start = strtotime($date);
 $end = strtotime('+'.($index+1).' years', $start);
 
 $percent = 0.1;
 while ($start!= $end)
 {
	  $year = substr($start,6); 
	  $days = date('t', strtotime($start));
	  if($year%4!=0 || $year%100==0 && $year%400!=0)
	  {
		  $sumDeposit = $sumDeposit+($sumDeposit+$sumRefill)*$days*($percent/365);
	  }
	  else
	  {
		  $sumDeposit = $sumDeposit+($sumDeposit+$sumRefill)*$days*($percent/366);
	  }
	  $start = strtotime('+1 month', $start);
 }
 echo $sumDeposit;
?>