//统计所有选中的报刊金额
function calculateMoney() {
  var sum = 0;
  var ids = [];
  var chkItems = document.getElementsByName("one");
  var idItems = document.getElementsByName("hidden");
  for (var i = 0; i < chkItems.length; i++) {
    if (chkItems[i].checked) {
      sum += parseInt(chkItems[i].value);
      ids.push(idItems[i].value);
    }
  }
  var sumMoneyObj = document.getElementById("sumMoney");
  var list = document.getElementById("ids");
  list.innerHTML = ids;
  sumMoneyObj.innerHTML = sum +" ￥";
}

//给每个报刊复选框加上自动统计功能
function iniEvent() {
  var chkItems = document.getElementsByName("one");
  for (var i = 0; i < chkItems.length; i++) {
    	chkItems[i].onclick = calculateMoney;
 	 }      
}