<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>异常分析</title>
    <script type="text/javascript" src="${ctx}/ftl/common/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/jquery.form.js"></script>
    <#--bootstrap-->
    <link rel="stylesheet" href="${ctx}/css/bootstrap.css" >
    <script type="text/javascript" src="${ctx}/ftl/common/bootstrap.js"></script>
    <#--bootstrap table-->
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-table.js"></script>
    <link href="${ctx}/ftl/common/table/bootstrap-table.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/ftl/common/table/locale/bootstrap-table-zh-CN.js"></script>

    <#--table editable -->
    <link rel="stylesheet" href="${ctx}/ftl/common/editable/css/bootstrap-editable.css">
    <script type="text/javascript" src="${ctx}/ftl/common/editable/js/bootstrap-editable.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/extensions/editable/bootstrap-table-editable.js"></script>
    <link rel="stylesheet" href="${ctx}/ftl/common/table/extensions/fixColumns/bootstrap-table-fixed-columns.css">
    <script type="text/javascript" src="${ctx}/ftl/common/table/extensions/fixColumns/bootstrap-table-fixed-columns.js"></script>
   
    <#--<script type="text/javascript" src="${ctx}/ftl/common/table/extensions/resizable/bootstrap-table-resizable.js "></script>-->
    <#--loading -->
    <link rel="stylesheet" href="${ctx}/css/table/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/table/bootstrap-select.css">
    <link rel="stylesheet" href="${ctx}/ftl/common/ladda/ladda-themeless.min.css">
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/spin.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/ladda.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-select.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/layer/layer.js"></script>
    <style type="text/css">
        .fixed-table-container {
            height: 500px;
        }
        a:link,a:visited{
            text-decoration:none;  /*超链接无下划线*/
        }
        a:hover{
            text-decoration:underline;  /*鼠标放上去有下划线*/
        }
    </style>


</head>
<body id="wrapper" style="overflow: hidden">
<div id="page-wrapper" style="padding-top: 10px;">
    <div class="row" id="table_row">
        <div class="panel panel-default">
            <div class="panel-body" style="margin-left: 10px;margin-right: 10px;padding-top: 3px;"
                 id="deptexam-panel-body">
                <table id="message-deptexam-table" >
              
                </table>
            </div>
        </div>
    </div>
<#--toolbar-->

    <div id="toolbar" >

        <div class="btn-group" style="width: 300px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                    日期
                </span>
                <input class="form-control " type="text" id="reportdate" value="${time}" data-provide="datepicker"/>
            </div>
        </div>
 <#--       <div class="btn-group" style="width: 150px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon" style="background-color:rgba(51,122,183,0.8);color: #fff">
                    <a href="javascript:;" type="button" id="dept"><font style="color: #fbfbfb">部门</font></a>
                </span>
                <input class="form-control " type="text" id="bumen" />
            </div>
        </div>-->
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
            部门选择
        </button>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h3 class="modal-title" id="myModalLabel">
                            部门选择
                        </h3>
                    </div>
                        <div class="tree well">
                            <ul>
                                <li>
                                    <span><i class="icon-folder-open"></i> 顶级节点1</span> <a href="">Goes somewhere</a>
                                    <ul>
                                        <li>
                                            <span><i class="icon-minus-sign"></i> 一级节点1</span> <a href=""></a>
                                            <ul>
                                                <li>
                                                    <span><i class="icon-leaf"></i> 二级节点1_1</span> <a href=""></a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li>
                                            <span><i class="icon-minus-sign"></i> 一级节点2</span> <a href=""></a>
                                            <ul>
                                                <li>
                                                    <span><i class="icon-leaf"></i>二级节点2_1</span> <a href=""></a>
                                                </li>
                                                <li>
                                                    <span><i class="icon-minus-sign"></i> 二级节点2_2</span> <a href=""></a>
                                                    <ul>
                                                        <li>
                                                            <span><i class="icon-minus-sign"></i>三级节点2_1</span> <a href=""></a>
                                                            <ul>
                                                                <li>
                                                                    <span><i class="icon-leaf"></i>四级节点2_1</span> <a href=""></a>
                                                                </li>
                                                                <li>
                                                                    <span><i class="icon-leaf"></i> 四级节点2_2</span> <a href=""></a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li>
                                                            <span><i class="icon-leaf"></i>三级节点2_2</span> <a href=""></a>
                                                        </li>
                                                        <li>
                                                            <span><i class="icon-leaf"></i> 三级节点2_3</span> <a href=""></a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li>
                                                    <span><i class="icon-leaf"></i>二级节点2_3</span> <a href=""></a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <span><i class="icon-folder-open"></i> 顶级节点2</span> <a href=""></a>
                                    <ul>
                                        <li>
                                            <span><i class="icon-leaf"></i> 一级节点2_1</span> <a href=""></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" class="btn btn-primary">
                            提交更改
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="btn-group" style="width: 150px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                    <a href="javascript:;" type="button" id="username"><font style="color: #fbfbfb">姓名</font></a>
                </span>
                <input class="form-control " type="text" id="username" />
            </div>
        </div>
        <div class="btn-group" style="width: 150px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                   卡类别
                </span>
                <select class="selectpicker" title="-请选择-">
                    <option value="1">正式员工</option>
                    <option value="2">临时员工</option>
                    <option value="3">外包人员</option>
                    <option value="4">顶岗实习</option>
                </select>
            </div>
        </div>
        <button type="button" class="btn btn-primary" id="queryList">查询</button>
        <button type="button" class="btn btn-primary"  id="exportAllList">导出EXL</button>
        </div>
    </div>
    <#--end toolbar-->



<script type="text/javascript" src="${ctx}/ftl/report/avplan/report/exceptioncolumns.js"></script>
<script type="text/javascript">
    var defaultColumns = Columns.getColumns();
    var defaultUnit = "门卫";
    var deptExamParams = {
        classes : "table table-bordered table-hover",

        url : "${ctx}/report/yichang/accesssList.z",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        cache : false,
        queryParams :  function (param) {
            return {
                unitname: defaultUnit,
                reportdate: $("#reportdate").val()
            };
        },
        pagination : true,
        sidePagination : 'client',
        pageNumber :1,
        pageSize : 10,
        pageList: [10,20, 30, 60,'ALL'],
       
       fixedColumns:true,
       fixedNumber:1,
        
        columns :defaultColumns,
       
        cardView:(function(){
            if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
                return true;
            } else {
                return false;
            }
        })(),

        search :true,
        searchOnEnterKey :true,
        trimOnSearch : true,
        idField : 'innerid',
        uniqueId :'innerid',
        toolbar : "#toolbar",

    }

    deptExamParams.height = function(){
        return $(window).height();
    }
    
    var trueHeight = document.documentElement.clientHeight;

    var startHeight = document.body.offsetHeight;

    deptExamParams.height = trueHeight - startHeight;


    $('#message-deptexam-table').bootstrapTable(deptExamParams);
    
    $("#queryList").click(function () {
        $("#message-deptexam-table").bootstrapTable('refresh', {
            query: {
                reportdate: $("#reportdate").val(),
               /* reportdate1: $("#reportdate1").val()*/
            }
        });
    });

$.fn.datepicker.dates['en'] = {
    days: ['星期天', "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
    daysShort: ['星期天', "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
    daysMin: ["日", "一", "二", "三", "四", "五", "六"],
    months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    today: "Today",
    clear: "Clear",
    format: "yyyy-mm-dd",
    titleFormat: "yyyy-MM", 
    weekStart: 0
}
    /*var exportL = Ladda.create( document.querySelector( '#exportList' ) );
    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );*/
    /**
     * 导出EXL文件
     */
    $("#exportAllList").click(function () {
        exportDataList("all");
    });

    var exportAll ;
    if(document.getElementById("exportAllList")){
        exportAll = Ladda.create( document.querySelector( '#exportAllList' ) );
    }

    $("body").height(document.documentElement.clientHeight);

    //导出数据
    function exportDataList(unitName) {
        var form = $("<form ></form>");
        var input = $("<input />");
        input.attr({"type":"hidden","name":"reportdate"});
        input.val($("#reportdate").val());
        form.append(input);

        var input2 = $("<input />");
        input2.attr({"type":"hidden","name":"unitname"});
        input2.val(unitName);
        form.append(input2);

        form.attr({"method":"post","action":'${ctx}/warn/exportDatas.z'})
        var options = {
            url: '${ctx}/warn/exportDatas.z',
            type:'post',
            beforeSubmit : function(e){

                if (unitName==="all") {
                    exportAll.start();
                } else {
                    exportL.start();
                }
                console.log("EXPORT");
                return true;
            },
            success:function (data) {
                if(data.success){
                    console.log("success..");
                    var form2 = $("<form ></form>");
                    var input2 = $("<input />");
                    input2.attr({"type":"hidden","name":"path"});
                    input2.val(data.path);
                    form2.append(input2);
                    form2.appendTo(document.body);
                    form2.attr({"method":"post","action":'${ctx}/exam/deptexam/download.z'})
                    form2.submit();
                    document.body.removeChild(form2[0]);
                }
                if (unitName==="all") {
                    exportAll.stop();
                } else {
                    exportL.stop();
                }
            }
        }
        form.ajaxSubmit(options);
    }

    /*组织树*/
    $(function(){
        $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
        $('.tree li.parent_li > span').on('click', function (e) {
            var children = $(this).parent('li.parent_li').find(' > ul > li');
            if (children.is(":visible")) {
                children.hide('fast');
                $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
            } else {
                children.show('fast');
                $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
            }
            e.stopPropagation();
        });
    });
    /**
     * 点击姓名选择
     */
    $("#username").on("click",function () {
        var l = layer.open({
            type: 2,
            title: '人员选择',
            shadeClose: true,
            shade: 0.5,
            maxmin: true, //开启最大化最小化按钮
            area: ['1300px', '600px'],
            content: "${ctx}/reports/task/threeXM.z"
        });
    });
</script>
</body>
</html>