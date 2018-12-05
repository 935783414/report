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
                <form>
                <table id="message-deptexam-table" >

                </table>
            </div>
        </div>
    </div>
<#--toolbar-->

    <div id="toolbar" >
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-primary" id="queryList" style="width: 120px">查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;询</button>
    <button type="submit" class="btn btn-primary" id="submit" style="width: 120px" onsubmit="submit()">保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存</button>
    </form>
    </div>
</div>
<#--end toolbar-->

</div>

<script type="text/javascript" src="${ctx}/ftl/report/avplan/report/depNamecolumns.js"></script>
<script type="text/javascript">
    /**
     * 获取表格数据
     */

    var defaultColumns = Columns.getColumns();

    var deptExamParams = {
        classes : "table table-bordered table-hover",

        url : "${ctx}/report/task/pro/proList.z",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        cache : false,
        queryParams :  function (param) {
            return {
                /* reportdate1: $("#reportdate1").val(),*/
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
    /**
     * 提交人员表单到statistics中
     */


</script>
</body>
</html>