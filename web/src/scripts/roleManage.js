/**
 * Created by Asus on 2017/6/4.
 */
function changetype() {
    var role = document.getElementById("role").value;
    location.href = "/blog/role?type=edit&grade="+role;
}
function deleteUser(id) {
    if(confirm("您确定要注销吗？")) {
        location.href = "/blog/user?type=logout&user_id=" + id;
    }
}