
///////////操作///////////
function GoTo(Url)//转到页面 
{
    window.location.href = Url;
    return false;
}

//弹出框中加载iframe
///frameSrc:url地址，otitle：标题，cssobj：弹出框大小样式设置 默认800*620，cssifm:iframe样式
function showtip(frameSrc, otitle, cssobj, cssifm) {
    $("#NoPermissioniframe").attr("src", frameSrc);//模态框中iframe
    $('#NoPermissionModal').modal({ show: true, backdrop: 'static' });//模态框div
    var _scrollHeight = $(document).scrollTop();
    var wHeight = $(window).height();
    var this_height;
    if (cssobj && cssobj["height"])
        this_height = cssobj["height"];
    else
        this_height = "620";
    var this_top = (wHeight - this_height) / 2 + _scrollHeight + "px";
    var this_top = (wHeight - this_height) / 2 + "px";

    var mycss = cssobj || { "width": "84%", "height": "90%", "top": this_top };
    var myifmcss = cssifm || {};//iframe样式
    $('#NoPermissionModal .modal-dialog').css(mycss).find('.modal-content').css({ height: '100%', width: '100%' }).find('h4').html(otitle || "").end().find('.modal-body').css({ height: '88%' }).find("#NoPermissioniframe").css(myifmcss);
}

//iframe选择产品事件
function ProductSelect(id, name) {
    //调用主页函数
    window.parent.GetProductSelectInfo(id, name);
}

//iframe选择设备事件
function DeviceSelect(id,code,name) {
    //调用主页函数
    window.parent.GetDeviceSelectInfo(id,code,name);
}

//iframe选择生产任务事件
function ProtionTaskSelect(id, name) {
    //调用主页函数
    window.parent.GetProtionTaskSelectInfo(id, name);
}

//iframe选择终端事件
function TerminalSelect(id, name) {
    //调用主页函数
    window.parent.GetTerminalSelectInfo(id, name);
}




