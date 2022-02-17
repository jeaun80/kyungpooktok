'use strict';

(function () {
    window.addEventListener("load", function () {
        let form = this.document.querySelector("#deletevalue");
        let btndel = this.document.querySelector("#deletebtn");

        btndel.addEventListener("click", function (event) {
            if (form.checkValidity() == false) {
                event.preventDefault();
                event.stopPropagation();
                form.classList.add("was-validated");
            }
        }, false);
    }, false);
})();