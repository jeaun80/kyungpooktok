'use strict';

(function () {
    window.addEventListener("load", function () {
        let form = this.document.querySelector("#signupvalue");
        let btnSave = this.document.querySelector("#savebtn");
        //alert("jingwon");
        //console.log("hihihihih");
        btnSave.addEventListener("click", function (event) {
            if (form.checkValidity() == false) {
                event.preventDefault();
                event.stopPropagation();
                form.classList.add("was-validated");
            }
        }, false);
    }, false);
})();