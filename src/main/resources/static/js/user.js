'use strict';

let index = {
    init: function() {
        $("#savebtn").on("click", () => { //this를 바인딩하기 위해 화샬표 함수 사용
            let form = document.querySelector("#signupvalue");
            if(form.checkValidity()==false){
                console.log("회원가입 안됨");
            }
            else{
                console.log("회원가입 됨");
                this.save();
            }
        });
    },

    save: function() {
        let data = { //JavaScript Object
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            kakaotalkid: $("#kakaotalkid").val(),
            instarid: $("#instarid").val()
        }

        $.ajax({
            type: "POST", //Http method
            url: "/auth/api/signup", //API 주소
            data: JSON.stringify(data), //JSON으로 변환
            contentType: "application/json; charset=utf-8", //MIME 타입
            dataType: "json" //응답 데이터
        }).done(function(res) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/auth/login";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }
}
index.init();