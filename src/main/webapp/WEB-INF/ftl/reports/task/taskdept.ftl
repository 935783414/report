<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>任务完成状态表</title>

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
                <table id="message-deptexam-table">

                </table>
            </div>
        </div>
    </div>
<#--toolbar-->
    <div id="toolbar" >
        <div class="btn-group ">
            <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary" id="selectKey">任务选择<span class="caret"></span></button>
            <ul class="dropdown-menu">
                <#list depts as children>
                    <li><a href="javascript:;" onclick="changeDept('${children.ownername}')">${children.ownername}</a></li>
                </#list>
            </ul>
        </div>
        <div class="btn-group" style="width: 300px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                    填写任务计划起止时间
                </span>
                <input class="form-control " type="text" id="reportdate" value="${time}"/>
            </div>
        </div>
          <span>—</span>
        <div class="btn-group" style="width: 200px">
            <div class="input-group input-group-toolbar">
                <input class="form-control " type="text" id="reportdate1" value="${time1}"/>
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
<script type="text/javascript" src="${ctx}/ftl/report/avplan/deptexamreport/task/taskcolumns.js"></script>
<script type="text/javascript">
    var defaultUnit = "产品研发";
    var defaultColumns = Columns.getColumns(defaultUnit);
    var deptExamParams = {
        classes : "table table-bordered",

        url : "${ctx}/report/task/taskdept/dataList.z",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        cache : false,
        queryParams :  function (param) {
            return {
                ownername: defaultUnit,
                reportdate: $("#reportdate").val()
            };
        },
        pagination : true,
        sidePagination : 'client',
        pageNumber :1,
        pageSize : 10,
        pageList: [10,20, 30, 60,'ALL'],

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
            if(row.isExam == '不考核')return  { classes: 'active' }  ;
            return {};
        },
            // .zreport/deptexam/detail/2302934
        onEditableSave :function(field, row, oldValue, $el) {
            $.ajax({
                type: 'post',
                url: '${ctx}/report/task/taskdept/editData.z',
                data: row,
                contentType: "application/x-www-form-urlencoded",
                dataType: 'json',
                success: function (data) {
                    alert('成功');
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

    deptExamParams.height = function(){
        return $(window).height();
    }
    
    var trueHeight = document.documentElement.clientHeight;

    var startHeight = document.body.offsetHeight;

    deptExamParams.height = trueHeight - startHeight;
    console.log(deptExamParams.height);
    
    $('#message-deptexam-table').bootstrapTable(deptExamParams);
    
    function changeDept(ownername) {
        defaultUnit = ownername;
        var columns = Columns.getColumns(ownername);
        $("#message-deptexam-table").bootstrapTable('refreshOptions',{
            columns:columns,
        });
        $("#queryList").click();
    }

    $("#queryList").click(function () {
        $("#selectKey").text(defaultUnit);
        $("#message-deptexam-table").bootstrapTable('refresh', {
            query: {
                unitname: defaultUnit,
                reportdate: $("#reportdate").val(),
                reportdate1: $("#reportdate1").val()
            }
        });
    });

    $("#saveExam").click(function () {
        console.log("SAVE");
        saveL.start();
        $.ajax({
            type: 'post',
            url: '${ctx}/report/task/taskdept/saveDatas.z',
            contentType: "application/x-www-form-urlencoded",
            dataType: 'json',
            success: function (data) {
                if(data.success){
                    alert('成功');
                }
                saveL.stop();
            }
        })
    });

    $("#message-deptexam-table").on('click','.btn-toDetail',function () {
        var orderno = $(this).attr("data-orderno");
        var url = '${ctx}/report/task/taskdept/detail/'+orderno+'.z';
        // console.log(url);
        window.location.href = url;
    });



    $("#exportList").click(function () {
        var form = $("<form ></form>");
        var input = $("<input />");
        input.attr({"type":"hidden","name":"reportdate"});
        input.val($("#reportdate").val());
        form.append(input);
        form.attr({"method":"post","action":'${ctx}/report/task/taskdept/exportDatas.z'})
        var options = {
            url: '${ctx}/report/task/taskdept/exportDatas.z',
            type:'post',
            beforeSubmit : function(e){
                exportL.start();
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
                    form2.attr({"method":"post","action":'${ctx}/report/task/taskdept/download.z'})
                    form2.submit();
                    document.body.removeChild(form2[0]);
                }
                exportL.stop();
            }
        }
        form.ajaxSubmit(options);
    });

    var exportL = Ladda.create( document.querySelector( '#exportList' ) );
    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );
</script>
</body>
</html>