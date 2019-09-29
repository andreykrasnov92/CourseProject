/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function start() {
    if (document.getElementById("timer") != null) {
        timer();
    } else {
        window.setTimeout("start();", 10);
    }
}

start();

function timer() {
    try {
        s = document.getElementById("timer").textContent.toString();
        i = parseInt(s, 0);
        if (!isNaN(i)) {
            if (i-- > 0) {
                document.getElementById("timer").textContent = i;
                window.setTimeout("timer();", 1000);
            }
        }
    } catch (exception) {
    }
}
