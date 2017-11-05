/**
 * Created by Asus on 2017/6/3.
 */
function changetype() {
    var keyword = document.Form1.keyword.value;
    location.href="/blog/systemddl?type=edit&keyword="+keyword;
}

function deleteUser(id) {
    if(confirm("您确定要注销吗？")) {
        location.href = "/blog/user?type=logout&user_id=" + id;
    }
}

function insertRows() {
    var tempRow = 0;
    var tbl = document.getElementById("dictTbl");
    tempRow = tbl.rows.length;
    var Rows = tbl.rows;
    var newRow = tbl.insertRow(tbl.rows.length);
    var Cells = newRow.cells;
    for(i=0;i<3;i++) {
        var newCell = Rows[newRow.rowIndex].insertCell(Cells.length);
        newCell.align = "center";
        switch (i) {
            case 0 :newCell.innerHTML = "<td class=\"ta_01\" align=\"center\"  width=\"15%\">"+tempRow+"</td>";break;
            case 1 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"60%\"><input name=\"itemname\" type=\"text\" id=\""+tempRow+"\" size=\"45\" maxlength=25></td>";break;
            case 2 : newCell.innerHTML="<td class=\"ta_01\" align=\"center\"  width=\"25%\"><a href='javascript:delTableRow(\""+tempRow+"\")'><img src=/blog/src/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a></td>";break;
        }
    }
}

function delTableRow(rowNum) {
    var tbl = document.getElementById("dictTbl");
    if(tbl.rows.length > rowNum) {
        tbl.deleteRow(rowNum);
        for(i=rowNum ; i<tbl.rows.length ; i++) {
            tbl.rows[i].cells[0].innerText=i;
            tbl.rows[i].cells[2].innerHTML="<a href='javascript:delTableRow(\""+i+"\")'><img src=/blog/src/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>";
        }
    }
}

$(function () {
    $("#saveitem").click(function () {
        if(saveDict()){
            this.type = "submit";
        }
    })
})
function saveDict() {
    if(document.Form1.keyword.value == "jerrynew") {
        if(Trim(document.Form1.keywordname.value)=="") {
            alert("请输入类型名称！");
            return false;
        }

        var allkeywords = document.Form1.keyword;
        for(var i=0 ; i<allkeywords.length ; i++) {
            if(allkeywords[i].value==Trim(document.Form1.keywordname.value)) {
                alert("已存在此类型名称，请重新输入！");
                return false;
            }
        }
    }
    var tbl = document.getElementById("dictTbl");
    for(i=1;i<tbl.rows.length;i++) {
        var name = tbl.rows[i].cells[1].children.item(0).value;
        if(Trim(name)==""){
            alert("名称不能为空！");
            return false;
        }
    }
    for(k=1;k<=tbl.rows.length-2;k++)
    {
        for(m=k+1;m<=tbl.rows.length-1;m++)
        {
            var name1 = tbl.rows[k].cells[1].children.item(0).value;
            var name2 = tbl.rows[m].cells[1].children.item(0).value;
            if(name1 == name2){
                alert("名称不能相同！");
                return false;
            }
        }
    }
    return true;
}