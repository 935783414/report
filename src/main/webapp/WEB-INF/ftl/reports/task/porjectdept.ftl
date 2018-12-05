<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>准时率分析表</title>
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
      <#--  <div class="btn-group ">
            <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary" id="selectKey">任务选择<span class="caret"></span></button>
            <ul class="dropdown-menu">
                <#list depts as children>
                    <li><a href="javascript:;" onclick="changeDept('${children.ownername}')">${children.ownername}</a></li>
                </#list>
            </ul>
        </div>-->
        <div class="btn-group" style="width: 300px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                    合同实际起止时间
                </span>
                <input class="form-control " type="text" id="reportdate" value="${time}" data-provide="datepicker"/>
            </div>
        </div>
          <span>—</span>
        <div class="btn-group" style="width: 200px">
            <div class="input-group input-group-toolbar">
                <input class="form-control " type="text" id="reportdate1" value="${time1}" data-provide="datepicker"/>
            </div>
        </div>
        <button type="button" class="btn btn-primary " id="queryList">查询</button>
  <#--      <button type="button" class="btn btn-warning ladda-button" data-style="zoom-out" style="display: none"  id="saveExam">生成考核</button>
        <button type="button" class="btn btn-info ladda-button" data-style="zoom-out" id="exportList"><span class="ladda-label">导出考核</span></button>

        <div class="btn-group text-center" style="vertical-align:middle">
            <h2 style="vertical-align:middle;margin: 0px;">
                <span   class="label  label-danger "  style="vertical-align:middle;margin: 2px;padding: 3px 10px" id="showTotal">0</span>项
                <span   class="label  label-warning " style="vertical-align:middle;margin: 2px;padding: 3px 10px" id="showOrder">0</span>份
            </h2>
            <#--<h3>项/<span class="label  label-danger " id="showOrder">35</span>份</h3>--> 
        </div>
    </div>
    <#--end toolbar-->

</div>

<script type="text/javascript" src="${ctx}/ftl/report/avplan/deptexamreport/task/projectcolumns.js"></script>
<script type="text/javascript">
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
                 reportdate1: $("#reportdate1").val(),
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
    
    $("#queryList").click(function () {
        $("#message-deptexam-table").bootstrapTable('refresh', {
            query: {
                reportdate: $("#reportdate").val(),
                reportdate1: $("#reportdate1").val()
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
};
    var exportL = Ladda.create( document.querySelector( '#exportList' ) );
    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );
</script>
</body>
</html>