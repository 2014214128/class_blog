/**
 * Created by Asus on 2017/6/6.
 */
function deleteUser(id) {
    if (confirm("您确定要注销吗？")) {
        location.href = "/blog/user?type=logout&user_id=" + id;
    }
}

function changePage(pageNow) {
    location.href = "/blog/article?type=show&pageNow=" + pageNow;
}

function pass(id,num) {
    var s = "selType"+num;
    var part = document.getElementById(s).value;
    if(part != '0') {
        location.href = "/blog/article?type=pass&id=" + id + "&part=" + part;
    } else {
        alert("请选择分类类别！");
    }
}