function  checkqx() {
 let qx=  getCookie("qxid");
   if(qx=="3"){
       location.href="employeeindex.html";
   }else if(qx=="4"){
       location.href="userindex.html";
   }
}

function  checkEmpqx() {
    let qx=  getCookie("qxid");
    if(qx=="1"||qx=="2"){
        location.href="index.html";
    }else if(qx=="4"){
        location.href="userindex.html";
    }
}

function  checkvipqx() {
    let qx=  getCookie("qxid");
    if(qx=="1"||qx=="2"){
        location.href="index.html";
    }else if(qx=="3"){
        location.href="employeeindex.html";
    }
}