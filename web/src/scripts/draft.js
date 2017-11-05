/**
 * Created by zhengguo on 2017/6/6.
 */
function deleteUser(id) {
    if(confirm("您确定要注销吗？")) {
        location.href = "/blog/user?type=logout&user_id=" + id;
    }
}
function changePage(pageNow){
    location.href = "/blog/draft?type=show&pageNow=" + pageNow;
}