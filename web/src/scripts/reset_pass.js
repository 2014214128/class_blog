function checkPhone() {
    var str1 = document.getElementById("phone").value;
    var pattern = /^1[34578]\d{9}$/;
    if (str1 == "") {
        layer.msg("输入的内容不能为空！");
        return false;
    }
    else if (str1 != "" && !pattern.test(str1)) {
        layer.msg("手机号码输入有误！");
        return false;
    }
    else {
        var user_phone = $("#phone").val();
        console.log(user_phone);
        $.ajax({
            type: 'post',
            url: "/blog/user",
            data: {
                user_phone: user_phone,
                type: "checkUserPhone"
            },
            success: function (data) {
                var result = JSON.parse(data);
                console.log(result);
                if (result.success) {
                    sessionStorage.setItem('success', result.success);
                    layer.msg("该手机号未注册！");
                    return true;
                }
                else {
                    return false;
                }
            }
        })
    }
}//判断手机号码格式是否有误
function checkPassword(){
    var str4=document.getElementById("password").value;
    var pattern = /^[a-zA-Z\d]{6,16}$/;
    if(str4==""){
        layer.msg("输入的内容不能为空！");
        return false;
    }
    else if(str4!=""&&!pattern.test(str4)){
        layer.msg("密码格式有误！请输入6-16位英文或数字");
        return false;
    }
    else {return true;}
}//判断密码格式是否有误
function checkPasswordAgain(){
    var str4=document.getElementById("password").value;
    var str5=document.getElementById("conf").value;
    if(str5==""){
        layer.msg("输入的内容不能为空！");
        return false;
    }
    else if(str5!=str4){
        layer.msg("确认密码输入有误！");
        return false;
    }
    else {return true;}
}//判断两次输入密码是否一致
$(function () {
	$("#phone").blur(function () {
	    checkPhone();
	});
    $("#password").blur(function () {
        checkPassword();
    });
    $("#conf").blur(function () {
        checkPasswordAgain();
    });
    $("#subBut").click(function () {
            var user_phone = $("#phone").val();
            var user_email = $("#email").val();
            var user_loginname = $("#userName").val();
            var user_password = $("#password").val();
            var checkPassword=$("#conf").val();
            if(user_phone!=""){
                        if(user_password!=""){
                            if(checkPassword!=""){
                                    $.ajax({
                                        type: 'post',
                                        url: "/blog/user",
                                        data: {
                                            phone: user_phone,
                                            password: user_password,
                                            type: "resetPwd"
                                        },
                                        success: function (data) {
                                            var result = JSON.parse(data);
                                            console.log(result);
                                            if (result.success) {
                                                sessionStorage.setItem('success', result.success);
                                                layer.msg("找回密码成功！");
                                                location.href = "/blog/jsps/background/reset_success.jsp"
                                            }
                                            else {
                                                layer.msg(result.message);
                                            }
                                        }
                                    })
                            }
                            else {
                                layer.msg("确认密码不能为空！");
                            }
                        }
                        else {
                            layer.msg("密码不能为空！")
                        }
            }
            else {
                layer.msg("手机号不能为空！");
            }
    });
});
