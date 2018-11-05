layui.use(['element','layer'],function () {
    var element = layui.element;
    var layer = layui.layer;

    //触发事件
    var active = {
        tabAdd: function (url,id,name) {
            element.tabAdd('demo',{
                title:name,
                content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="/admin/'+url+'" style="width:100%;height:99%;"></iframe>',
                id:id
            });
            // CustomRightClick(id);
            // FrameWH();//计算iframe层的大小
        },
        tabChange:function (id) {
            element.tabChange('demo',id);
        },
        tabDelete:function (id) {
            element.tabDelete('demo',id);
        }
    };


    //当点击site-demo-active属性的标签时，即左侧菜单栏中的内容，触发事件
    $(".site-demo-active").on('click',function () {
        console.log("11111111");
        var dataid = $(this);

        //这时候要判断右侧的.layui-tab-title属性下的lay-id的li的数目，已经打开的tab的数量
        if($(".layui-tab-title li[lay-id]").length<= 0){
            //比零小，则直接打开新的tab项
            active.tabAdd(dataid.attr("data-url"),dataid.attr("data-id"),dataid.attr("data-title"));
        }else{
            //判断该tab项是否以及存在
            var isData = false;//初始化一个标志，为false说明未打开该tab项
            $.each($(".layui-tab-title li[lay-id]"),function () {
                if($(this).attr("lay-id") === dataid.attr("data-id")){
                    isData = true;
                }
            });
            if (isData === false){
                //标志为false，新增一个tab
                active.tabAdd(dataid.attr("data-url"),dataid.attr("data-id"),dataid.attr("data-title"));
            }
        }
        active.tabChange(dataid.attr("data-id"));
    });




});