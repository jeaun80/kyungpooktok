'use strict';

(function () {
    window.addEventListener("load", function () {
        let form = this.document.querySelector("#updatevalue");
        let btnSave = this.document.querySelector("#updatebtn");

        btnSave.addEventListener("click", function (event) {
            if (form.checkValidity() == false) {
                event.preventDefault();
                event.stopPropagation();
                form.classList.add("was-validated");
            }
        }, false);
    }, false);
})();