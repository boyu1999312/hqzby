$("#top-login").on("click",function () {
    $("#myModal").modal("show")
})

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
                alert("登陆成功!")
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