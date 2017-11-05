/**
 * Created by Asus on 2017/6/4.
 */
function changetype() {
    var role = document.getElementById("role").value;
    if(role == "0") {
        location.href = "/blog/jsps/background/roleManage.jsp";
    }else{
        location.href="/blog/role?type=edit&grade="+role;
    }
}

function checkUserId() {
    var user_id = document.getElementsByName("user_id");
    var count = 0;
    for(var i=0 ; i<user_id.length ; i++) {
        if(user_id[i].checked) {
            count++;
        }
    }
    if(count == 0) {
        alert("没有用户被选中！");
        return false;
    }
    return true;
}

function deleteUser(id) {
    if(confirm("您确定要注销吗？")) {
        location.href = "/blog/user?type=logout&user_id=" + id;
    }
}

$(function () {
    $("#addRole").click(function () {
        if(checkUserId()) {
            this.type = "submit";
        }
    })
})