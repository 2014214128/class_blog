/**
 * Created by zhengguo on 2017/6/2.
 */
$(function () {

    $("#btnPublish").click(function () {
        if (checkSelType() && checkTitle() && checkEditor() && checkRadChl1() && chkForm()) {
            document.form.action = "/blog/article?type=publish";
            this.type = "submit";
        }
    })
    $("#btnDraft").click(function () {
        if (checkTitle()) {
            document.form.action = "/blog/draft?type=save";
            this.type = "submit";
        }
    })
    $("#btnCancel").click(function () {
        this.type = "submit";
    })
})

function refresh() {
    location.href = "/blog/jsps/background/homepage/publishArticle.jsp";
}
function deleteUser(id) {
    if(confirm("您确定要注销吗？")) {
        location.href = "/blog/user?type=logout";
    }
}
function chkForm() {
    var f = document.getElementById("file1").value;
    if (f) {
        var ext = f.substr(f.lastIndexOf(".") + 1).toLowerCase();
        if (".jpg.gif.png.bmp.".indexOf('.' + ext + '.') == -1) {
            alert("图片格式不正确。");
            return false;
        }
        return true;

    }
    return true;


}


function checkSelType() {
    var category = document.getElementById("selType");
    if (category.value === "0") {
        alert("请选择文章类型！");
        return false;
    }
    return true;
}

function checkTitle() {
    var title = document.getElementById("txtTitle");
    if (title.value === "") {
        alert("请输入文章标题！");
        return false;
    }
    return true;
}

function checkEditor() {
    var content = document.getElementById("editor");
    if (content.value === "") {
        alert("请输入文章内容！")
        return false;
    }
    return true;
}

function checkRadChl1() {

    var a = $('input:radio[name="second_category"]:checked').val();
    console.log(a);
    if (!a) {
        alert("请选择文章类别");
        return false;
    }
    return true;
    /*if (second_categoey.value === "") {
     alert("请选择文章类别！");
     return false;
     }
     return true;*/
}