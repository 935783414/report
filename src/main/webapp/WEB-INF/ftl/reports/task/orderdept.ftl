<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>合同执行周期分析</title>
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
    <link rel="stylesheet" href="${ctx}/ftl/common/ladda/ladda-themeless.min.css">
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/spin.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/ladda.min.js"></script>
    <style type="text/css">
        .fixed-table-container {
            height: 500px;
        }
    </style>


</head>
<body id="wrapper" style="overflow: hidden">
<div>
    <h2  align="center">年执行合同周期分析(截至xx年xx月xx日纳入项目计划)</h2>
</div>
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
</div>
<script type="text/javascript" src="${ctx}/ftl/report/avplan/deptexamreport/task/ordercolumns.js"></script>
<script type="text/javascript">
    var defaultColumns = Columns.getColumns();
    var year=date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var time  =  year+'年'+month+'月'+day+'日 ';


    var deptExamParams = {
        classes : "table table-bordered table-hover",

        url : "${ctx}/report/task/orderList.z",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        cache : false,
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

    
        onLoadSuccess:function(data){
            if(data && data.length >0){
                var first = data[0];
                $("#showTotal").text(first.total+"");
                $("#showOrder").text(first.orderNum+"");
            }else{
                $("#showTotal").text("0");
                $("#showOrder").text("0");
            }
        }
    }

    deptExamParams.height = function(){
        return $(window).height();
    }
    
    var trueHeight = document.documentElement.clientHeight;

    var startHeight = document.body.offsetHeight;

    deptExamParams.height = trueHeight - startHeight;
    console.log(deptExamParams.height);
    
    $('#message-deptexam-table').bootstrapTable(deptExamParams);

    var exportL = Ladda.create( document.querySelector( '#exportList' ) );
    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );
</script>
</body>
</html>