function user_login(){
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {qzUsername:"abc123",qzPassword:"asdf123"},
        url: "/user/doLogin",
        success: function (result) {
            console.log(result)
            if(result.status == 200){
                alert("登陆成功")
            }else{
                alert("登陆失败")
            }
        }
    })
}