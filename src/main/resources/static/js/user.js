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

        $("#updatebtn").on("click",() => {
            let form = document.querySelector("#updatevalue");
            if(form.checkValidity()==false){
                console.log("정보수정 안됨");
            }
            else{
                console.log("정보수정 됨");
                this.update();
            }
        });

        $("#deletebtn").on("click",() => {
            let form = document.querySelector("#deletevalue");
            if(form.checkValidity()==false){
                console.log("횡원탈퇴 안됨");
            }
            else{
                console.log("회탈 됨");
                this.delete();
            }
        });
    },

    save: function() {
        let data = { //JavaScript Object
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            instarid: $("#instarid").val(),
            kakaotalkid: $("#kakaotalkid").val()

        }

        $.ajax({
            type: "POST", //Http method
            url: "/auth/api/signup", //API 주소
            data: JSON.stringify(data), //JSON으로 변환
            contentType: "application/json; charset=utf-8", //MIME 타입
            dataType: "json" //응답 데이터
        }).done(function(res) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/auth/user/login";
        }).fail(function(err) {
            alert(data.kakaotalkid);
            alert(JSON.stringify(err));
        });
    },
    update: function() {
        let data = { //JavaScript Object
            id: $("#id").val(),
            password: $("#password").val(),
            kakaotalkid: $("#kakaotalkid").val(),
            instarid: $("#instarid").val()
        }

        $.ajax({
            type: "PUT", //Http method
            url: "/api/update", //API 주소
            data: JSON.stringify(data), //JSON으로 변환
            contentType: "application/json; charset=utf-8", //MIME 타입
            dataType: "json" //응답 데이터
        }).done(function(res) {
            alert("정보변경이 완료되었습니다.");
            location.href = "/";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },
    delete: function () {
        let data = {
            id: $("#id").val(),
            password: $("#password").val()
        }

        $.ajax({
            type: "DELETE",
            url: "/api/delete",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("탈퇴가 완료된슴다.");
            location.href = "/auth/user/login";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }

}
index.init();