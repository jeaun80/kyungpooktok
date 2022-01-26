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


        $("#emailsubbtn").on("click",() => {
            let form = document.querySelector("#email");
            if(form.checkValidity()==false){
                console.log("안되는 이메일");
                alert("dfjaksfd");
            }
            else{
                console.log("메일발송");
                this.sendmail();
                alert($("#email.value"));
            }
        })
        $("#checkemailbtn").on("click",() =>{
            let form =document.querySelector("#checkvalue");
            if(form.checkValidity()==false){
                console.log("형식이 틀림");
            }
            else{
                console.log("검사해볼게");
                alert("되냐");
                this.checkmail();
            }
        })

        $("#emailduplicatebtn").on("click",() =>{
            let form =document.querySelector("#email");
            if(form.checkValidity()==false){
                console.log("형식이 틀림");
            }
            else{
                console.log("검사해볼게");
                alert("되냐");
                this.mailduplcate();
            }
        })

        $("#idvaluebtn").on("click",() =>{
            let form = document.querySelector("#idemailvalue");
            if(form.checkValidity()==false){
                console.log("형식 또는 입력없음");

            }
            else{
                this.idcheckemail();
            }
        })
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
    },
    mailduplcate:function (){
        const data={
            email:$("#email").val()
        }
        $.ajax({
            url:"/auth/mailduplicate",
            type:"POST",
            data:JSON.stringify(data),
            dataType:"json",
            async:false,
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                if(data){
                    alert("사용가능한 이메일입니다.")
                }
                else{
                    alert("사용불가");
                }

            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("중복되는 이메일입니다.");
            }
        }).done(function (res){
        }).fail(function (err){
            alert(JSON.stringify(err));
        })
    },
    sendmail:function (){
        const data={
            email:$("#email").val()
        }

        $.ajax({
            url:"/auth/mail",
            type:"POST",
            data:JSON.stringify(data),
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $("#checkvalue").attr("disabled",null);
                $("#checkemailbtn").attr("disabled",null);
                alert("이메일 발송에 성공했습니다!");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("이메일 발송에 실패했습니다.");
            }
        }).done(function (res){
            alert("인증번호 발송");
        }).fail(function (err){
            alert(JSON.stringify(err));
        })
    },
    checkmail: function (){
        const data= {
            email: $("#email").val(),
            num: $("#checkvalue").val()
        }

        $.ajax({
            url:"/auth/checkmail",
            type:"POST",
            data:JSON.stringify({
                email: $("#email").val(),
                num: $("#checkvalue").val()
            }),
            datatype:"json",
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $("#checkvalue").attr("disabled",null);
                $("#checkemailbtn").attr("disabled",null);
                alert("이메일 인증에 성공했습니다!");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("이메일 발송에 실패했습니다.");
            }
        }).done(function (){
            $("#savebtn").attr("disabled",null);
            alert("됫다?");
        })
    },
    idcheckemail: function (){
        const data={
            username:$("idvalue").val(),
            email:$("emailvalue").val()
        }
        $.ajax({
            url:"/auth/api/cheke",
            type:"GET",
            data:JSON.stringify(data),
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            success:function (res){
            }
        }).done(function (){
            alert("ㄲ트");
        })
    }

}
index.init();