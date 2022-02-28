'use strict';

let index = {
    init: function() {
        $("#savebtn").on("click", () => { //this를 바인딩하기 위해 화샬표 함수 사용
            let form = document.querySelector("#signupvalue");
            //let idval = document.querySelector("#username");
            //if(idval.checkValidity()==false){
            //alert("id는 4~20자리로 생성하세요");
            //}
            if(form.username.checkValidity()==false){
                console.log("id안됨")
                alert("id는 4~20자리로 생성하세요");
            }
            else if(form.password.checkValidity()==false){
                console.log("비밀번호안됨");
                alert("password는 8~20자리로 생성하세요");
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
                alert("hihihihi");
            }
            else{
                console.log("회탈 됨");
                alert("kkkkkk");

                this.delete();
            }
        });


        $("#emailsubbtn").on("click",() => {
            let form = document.querySelector("#email1");
            if(form.checkValidity()==false){
                console.log("안되는 이메일");
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
            let form =document.querySelector("#email1");
            if(form.checkValidity()==false){
                console.log("형식이 틀림");
            }
            else{
                console.log("검사해볼게");
                this.mailduplcate();
            }
        })

        $("#idsvaluebtn").on("click",() =>{
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
            email: $("#email1").val()+$("#email2").val(),
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
            if(res==true){
                console.log("hhhhhhhh");
                console.log(res);
                console.log(res[0]);
                alert("회원탈퇴 완료했습니다.");
                location.href = "/auth/user/login";
            }
            else{
                console.log("hihi");
                console.log(res);
                alert("비밀번호가 틀렷습니다");
            }
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    mailduplcate:function (){
        const data={
            //email:$("#email1").val()
            email:$("#email1").val()+$("#email2").val()
        }
        $.ajax({
            url:"/auth/mailduplicate",
            type:"POST",
            data:JSON.stringify(data),
            dataType:"json",
            async:false,
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                alert("사용가능한 이메일입니다.");
                $("#emailsubbtn").attr("disabled",null);
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
            email:$("#email1").val()+$("#email2").val()
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
            email: $("#email1").val()+$("#email2").val(),
            num: $("#checkvalue").val()
        }

        $.ajax({
            url:"/auth/checkmail",
            type:"POST",
            data:JSON.stringify({
                email: $("#email1").val()+$("#email2").val(),
                num: $("#checkvalue").val()
            }),
            datatype:"json",
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $("#checkvalue").attr("disabled",null);
                $("#checkemailbtn").attr("disabled",null);
                $("#idcon").attr("disabled",null);
                alert("이메일 인증에 성공했습니다!");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("이메일 인증에 실패했습니다. 번호를 확인하세요");
            }
        }).done(function (){
            $("#savebtn").attr("disabled",null);
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