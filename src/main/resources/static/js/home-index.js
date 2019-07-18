$("#top-login").on("click",function () {
    $("#myModal").modal("show")
})

$("#top-register").on("click",function () {
    $(":input","#register-form")
        .not(":button",":reset",":hidden",":submit")
        .val("")
        .removeAttr("checked")
        .removeAttr("selected");
    $("#myRegister").modal("show")
})



//登录检测
function doQueryUser(){
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/user/doQueryUser?timestamp=" + new Date().getTime(),
        success: function (result) {
            if(result.status == 200){
                // alert("登陆成功!")
                $("#user-name-a").text(result.data.qzUsername)
                    .attr("data-toggle", "dropdown")
                    .append("<span class=\"caret\"></span>")
                $("#register-li").remove()
                $("#login-li").remove()
            }
        }
    })
}

//退出登录
function doLoginout(){
    $.post("/user/doLoginout", function (result) {
        if(result.status == 200)
            location.reload()
    })
}
/*
$('#myModal').on('shown.bs.modal', function () {
    var $this = $(this);
    var dialog = $this.find('.modal-dialog');

    //此种方式，在使用动画第一次显示时有问题
    //解决方案，去掉动画fade样式
    var top = ($(window).height() - dialog.height()) / 2;
    dialog.css({
        marginTop:top
    });
});*/
//登陆按钮点击事件
$("#btn-login").on("click",function () {
    doLogin()
})
function doLogin(){
    var params = $("#login-form").serialize()
    console.log(params)
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/user/doLogin?timestamp=" + new Date().getTime(),
        data: params,
        success: function (result) {
            if(result.status == 200){
                // alert("登陆成功!")
                location.reload()
            } else
                alert("登陆失败!")
        }
    })
}
//登陆表单输入框回车监听
$(".login-text").keydown(function (event) {
    if(event.keyCode == 13){
        doLogin()
    }
})

//注册表单输入框回车监听
$(".register-text").keydown(function (event) {
    if(event.keyCode == 13){
        doRegister()
    }
})
$("#btn-sure").on("click",function () {
    doRegister()
})
function doRegister() {
    var params = $("#register-form").serialize()
    console.log(params)
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/user/doRegister?timestamp=" + new Date().getTime(),
        data: params,
        success: function (result) {
            if(result.status == 200){
                alert("注册成功!")
                location.reload()
            } else
                alert("注册失败!")
        }
    })
}

function doYanZheng() {

    //用户名校验
    $("#qzUsername-input").blur(function () {
        var params = {qzUsername:$(this).val()}
        if(params.qzUsername == null || params.qzUsername == "")
            return false
        $.post("/user/doqzUsername" ,params ,function (result) {
            if(result.status == 200)
                $("#qzUsername-span").empty().append("<span class='label label-success'><span class='glyphicon glyphicon-ok'></span>&nbsp;用户名可以使用</span>")
            else
                $("#qzUsername-span").empty().append("<span class='label label-danger'><span class='glyphicon glyphicon-remove'></span>&nbsp;用户名已经被注册了</span>")
        })
    })


    //密码校验
    $("#sure-password").blur(function () {
        jiaoyan()
    })
    $("#password-input").blur(function () {
        jiaoyan()
    })
    function jiaoyan() {
        if($("#password-input").val() == "" || $("#sure-password").val() == "")
            return false
        if($("#password-input").val() == $("#sure-password").val())
            $("#surePassword-span").empty().append("<span class='label label-success'><span class='glyphicon glyphicon-ok'></span>&nbsp;密码正确</span>")
        else
            $("#surePassword-span").empty().append("<span class='label label-danger'><span class='glyphicon glyphicon-remove'></span>&nbsp;密码不一致</span>")
    }

    //验证码校验
    $("#regCode").blur(function () {
        var params = {regCode:$(this).val()}
        $.post("/user/doRegCode", params, function (result) {
            if(result.status == 200)
                $("#sureRegCode-span").empty().append("<span class='label label-success'><span class='glyphicon glyphicon-ok'></span>&nbsp;验证码正确</span>")
            else
                $("#sureRegCode-span").empty().append("<span class='label label-danger'><span class='glyphicon glyphicon-remove'></span>&nbsp;验证码错误或失效</span>")
        })
    })


    $("#sendRegCode").on("click",function () {
        var params = {regEmail:$("#reg-email").val()}
        if(!params.regEmail.match(/^\w+([._-]\w+)*@(\w+\.)+\w+$/))
            return false
        $("#sendRegCode").attr("disabled",true)
        time(this,60)
        $("#email-span").append("<span class='label label-info'>如果没有收到，试试在垃圾邮件里找一下内！</span>")
        $.post("/user/doSendEmail", params, function (result) {
            if(result.status != 200)
                alert("验证码发送失败")
        })
    })
    //发送验证码按钮倒计时
    function time(obj, wait) {
        var $this = $(obj)
        if(wait == 0){
            $("#sendRegCode").attr("disabled",false)
            $("#sendRegCode").text("发送验证码")
            $("#email-span").empty()
        }else{
            $("#sendRegCode").text(wait + " s")
            wait--
            setTimeout(function () {
                time($this, wait)
            },1000)
        }
    }
}
doYanZheng()
doQueryUser()