function $$(id) {
    return document.getElementById(id);
}
function checkLoginForm() {
    var un = document.getElementById("user");
    if (un.value === "") {
        layer.msg("用户名不能为空！")

    }
}
function checkPassword() {
    var pw = document.getElementById("password");
    if (pw.value === "") {
        layer.msg("密码不能为空！")
    }
}


function btnLogin_click() {
    var user = $$("user").value;
    var password = $$("password").value;
    localStorage.setItem("user", user);
    if ($$("chkSave").checked) {
        localStorage.setItem("password", password);
    } else {
        localStorage.removeItem("password");
    }
}

function login() {
    var user_phoneOrEmail = $("#user").val();
    var user_password = $("#password").val();
    var chkSave = $("#chkSave").attr("checked");
    if (user_password != null && user_password != undefined && user_password != "" && user_phoneOrEmail != null && user_phoneOrEmail != undefined && user_phoneOrEmail != "") {
        $.ajax({
            type: 'post',
            url: "/blog/user",
            data: {
                user_phoneOrEmail: user_phoneOrEmail,
                user_password: user_password,
                type: "login",
                chkSave: chkSave
            },
            success: function (data) {
                var result = JSON.parse(data);
                if (result.success) {
                    sessionStorage.setItem('success', result.success);
                    location.href = "/blog/jsps/background/homepage/publishArticle.jsp"
                } else {
                    layer.msg("账号或密码错误");
                }
            }, error: function () {
                layer.msg("登录失败")
            }
        });
    } else {
        layer.msg("用户名或密码不能为空")
    }
}

$(function () {
    /* $(document).keypress(function(e) {
     var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
     if (eCode == 13){
     alert('您按了回车键')
     //自己写判断操作
     }
     });*/
    /*$("#password").keypress(function(e){
     var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
     if (eCode == 13){
     alert('您按了回车键')
     //自己写判断函数
     }
     })*/
    if (localStorage.getItem("user") != null) {
        $$("user").value = localStorage.getItem("user");
    }
    if (localStorage.getItem("password")) {
        $$("password").value = localStorage.getItem("password");
    }
    $$("submitButton").addEventListener("click", function () {
        btnLogin_click();
    }, false);

    $("#user").blur(function () {
            checkLoginForm();
        }
    )
    $("#registerButton").click(function () {
            location.href = "/blog/jsps/background/register.jsp";
        }
    )
    $("#password").blur(function () {
        checkPassword();
    })
    $("#submitButton").click(function () {
        var user_phoneOrEmail = $("#user").val();
        var user_password = $("#password").val();
        var chkSave = $("#chkSave").attr("checked");
        if (user_password != null && user_password != undefined && user_password != "" && user_phoneOrEmail != null && user_phoneOrEmail != undefined && user_phoneOrEmail != "") {
            $.ajax({
                type: 'post',
                url: "/blog/login",
                data: {
                    user_phoneOrEmail: user_phoneOrEmail,
                    user_password: user_password,
                    type: "login",
                    chkSave: chkSave
                },
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success) {
                        sessionStorage.setItem('success', result.success);
                        location.href = "/blog/jsps/background/homepage/publishArticle.jsp"
                    } else {
                        layer.msg("账号或密码错误");
                    }
                }, error: function () {
                    layer.msg("登录失败")
                }
            });
        } else {
            layer.msg("用户名或密码不能为空")
        }

    })
})



