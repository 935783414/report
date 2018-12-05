<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>异常反馈日报</title>

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
    <#--<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>-->
    <style type="text/css">

        th {
            text-align: center;
        }
         .modal-content {
         /*// 宽度自适应内容*/
         width: fit-content;
         /*// 流出间隙*/
         padding: 0.2rem 0.4rem;
         }

        .table-bordered > thead > tr > th,
        .table-bordered > tbody > tr > th,
        .table-bordered > tfoot > tr > th,
        .table-bordered > thead > tr > td,
        .table-bordered > tbody > tr > td,
        .table-bordered > tfoot > tr > td {
            border: 1px solid #b1b1b1;
            border-right-color: #ddd;
            border-left-color: #ddd;
        }
    </style>


</head>
<body id="wrapper" style="overflow: hidden">
<div id="page-wrapper" style="padding-top: 10px;">
    <div class="row" id="table_row">
        <div class="panel panel-default">
            <div class="panel-body" style="margin-left: 10px;margin-right: 10px;padding-top: 3px;"
                 id="deptexam-panel-body">
                <table id="message-deptexam-table">

                </table>
            </div>
        </div>
    </div>
<#--toolbar-->
    <div id="logobar"  style="position: absolute;right: 240px;top: 15px;width: 400px;height: 60px">
        <h3 ><strong>异常反馈日报&nbsp;&nbsp;</strong><small style="font-size: 70%" id="reportDateTitle"></small></h3>
    </div>
    <div id="toolbar" >
        <div class="btn-group ">
            <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary" id="selectKey">采购部<span class="caret"></span></button>
            <ul class="dropdown-menu">
                <#list depts as children>
                    <li><a href="javascript:;" onclick="changeDept('${children.unitname}')">${children.unitname}</a></li>
                </#list>
            </ul>
        </div>
        <div class="btn-group" style="width: 200px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff;border: 1px;border-color:rgba(51,122,183,0.8); ">
                    报表时间
                </span>
                <input class="form-control " style="border-color: rgba(51,122,183,0.8)" type="text" id="reportdate" value="${time!}" data-provide="datepicker"/>
            </div>
        </div>
        <button type="button" class="btn btn-primary " id="queryList">查询</button>
        <button type="button" class="btn btn-warning ladda-button" data-style="zoom-out" style="display: none"  id="saveExam">生成考核</button>
        <button type="button" class="btn btn-info ladda-button" data-style="zoom-out" id="exportList"><span class="ladda-label">导出考核</span></button>
        <#if user?? && user = 'admin' >
            <button type="button" class="btn btn-info ladda-button" data-style="zoom-out" id="allExportList"><span class="ladda-label">全部导出</span></button>
         </#if>


        <div class="btn-group text-center" style="vertical-align:middle">
            <h2 style="vertical-align:middle;margin: 0px;">
                <span   class="label  label-danger "  style="vertical-align:middle;margin: 2px;padding: 3px 10px;line-height: 2" id="showTotal">0</span>项
                <span   class="label  label-warning " style="vertical-align:middle;margin: 2px;padding: 3px 10px;line-height: 2" id="showOrder">0</span>份
            </h2>
            <#--<h3>项/<span class="label  label-danger " id="showOrder">35</span>份</h3>-->
        </div>
    </div>
    <#--end toolbar-->
</div>

<div class="modal fade bd-example-modal-sm"  id="myModal" role="dialog" data-backdrop="false"  aria-hidden="true">
    <div class="modal-dialog modal-sm" style="margin-top: 120px;background-color: rgba(255,255,255,0.6)">
        <div class="modal-content">
            <p class="text-center mb-0" style="margin: 5px;">
                <i class="glyphicon glyphicon-ok-circle" style="color: green"></i>&nbsp;&nbsp;&nbsp;&nbsp;保存成功
            </p>
        </div>
    </div>
</div>

<#if user?? && user = 'admin' >
    <script type="text/javascript" src="${ctx}/ftl/report/avplan/deptexamreport/exam/columns.js"></script>
<#else>
    <script type="text/javascript" src="${ctx}/ftl/report/avplan/deptexamreport/exam/guessColumns.js"></script>
</#if>

<script type="text/javascript">

    function book(){
        $('#myModal').modal('show');
        setTimeout(function(){
            $("#myModal").modal("hide");
        },1000);
    }
    var defaultUnit = "采购部";
    var defaultColumns = Columns.getColumns(defaultUnit);
    var deptExamParams = {
        classes : "table table-bordered",

        url : "${ctx}/exam/deptexam/dataList.z",
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

        fixedColumns: true,
        fixedNumber: 2,

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

        rowStyle:function(row,index){
            if(row.isExam == '暂不考核')return { classes: 'warning' }  ;
            if(row.isExam == '不考核')  return { classes: 'active'  }  ;
            if(row.isExam == '考核')    return { classes: 'danger'  }  ;
            return {};
        },
        onEditableSave :function(field, row, oldValue, $el) {
            $.ajax({
                type: 'post',
                url: '${ctx}/exam/deptexam/editData.z',
                data: row,
                contentType: "application/x-www-form-urlencoded",
                dataType: 'json',
                success: function (data) {
                    // alert('成功');
                    book();
                }
            })
            return false;
        },
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


    var trueHeight = document.documentElement.clientHeight;

    var startHeight = document.body.offsetHeight;

    deptExamParams.height = trueHeight - startHeight;
    // console.log(deptExamParams.height);

    $('#message-deptexam-table').bootstrapTable(deptExamParams);
    
    function changeDept(unitname) {
        defaultUnit = unitname;
        var columns = Columns.getColumns(unitname);
        var fixedNumber = Columns.getFixedColumnNumber(unitname);

        $("#message-deptexam-table").bootstrapTable('refreshOptions',{
            columns:columns,
            fixedNumber:fixedNumber
        });
        $("#queryList").click();
    }

    $("#queryList").click(function () {
        setDateTitle();
        $("#selectKey").text(defaultUnit);
        $("#message-deptexam-table").bootstrapTable('refresh', {
            query: {
                unitname: defaultUnit,
                reportdate: $("#reportdate").val()
            }
        });
    });

    $("#saveExam").click(function () {
        saveL.start();
        $.ajax({
            type: 'post',
            url: '${ctx}/exam/deptexam/saveDatas.z',
            contentType: "application/x-www-form-urlencoded",
            dataType: 'json',
            success: function (data) {
                if(data.success){
                    book();
                }
                saveL.stop();
            }
        })
    });

    $("#deptexam-panel-body").on('click','.btn-toDetail',function () {
        var innerid = $(this).attr("data-innerid");
        var url = '${ctx}/exam/deptexam/detail.z?innerid='+innerid;
        window.open(url,"_blank");
    });

    $("#exportList").click(function () {
        var form = $("<form ></form>");

        var input = $("<input />");
        input.attr({"type":"hidden","name":"reportdate"});
        input.val($("#reportdate").val());
        form.append(input);

        var input2 = $("<input />");
        input2.attr({"type":"hidden","name":"dept"});
        input2.val(defaultUnit);
        form.append(input2);

        form.attr({"method":"post","action":'${ctx}/exam/deptexam/exportDatas.z'})
        var options = {
            url: '${ctx}/exam/deptexam/exportDatas.z',
            type:'post',
            beforeSubmit : function(e){
                exportL.start();
                return true;
            },
            success:function (data) {
                if(data.success){
                    // console.log("success..");
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
                exportL.stop();
            }
        }
        form.ajaxSubmit(options);
    });

    $("#allExportList").click(function () {
        var form = $("<form ></form>");

        var input = $("<input />");
        input.attr({"type":"hidden","name":"reportdate"});
        input.val($("#reportdate").val());
        form.append(input);

        form.attr({"method":"post","action":'${ctx}/exam/deptexam/exportDatas.z'})
        var options = {
            url: '${ctx}/exam/deptexam/exportDatas.z',
            type:'post',
            beforeSubmit : function(e){
                allExportL.start();
                return true;
            },
            success:function (data) {
                if(data.success){
                    // console.log("success..");
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
                allExportL.stop();
            }
        }
        form.ajaxSubmit(options);
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

    var allExportL = null;
    if(document.getElementById("exportAllList")){
        allExportL = Ladda.create( document.querySelector( '#exportAllList' ) );
    }

    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );

    $("body").height(document.documentElement.clientHeight);

    // $("#reportDateTitle")

    function setDateTitle() {
        var date = $("#reportdate").val();
        var dateAry = date.split("-");
        $("#reportDateTitle").text(dateAry[0]+"年"+dateAry[1]+"月"+dateAry[2]+"日前");
    }
    setDateTitle();
</script>
</body>
</html>