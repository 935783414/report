<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>日考核报表</title>

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


</head>
<body id="wrapper" style="overflow: hidden">
<div id="page-wrapper" style="padding-top: 10px;margin: 15px;">
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-head-line" id="scaleName">部门考核管理</h1>
            <!--<h1 class="page-subhead-line">This is dummy text , you can replace it with your original text. </h1>-->
        </div>
    </div>
    <div class="row" id="table_row">
        <div class="panel panel-default">
            <div class="panel-body" style="margin-left: 10px;margin-right: 10px;padding-top: 3px;"
                 id="deptexam-panel-body">
                <table id="manager-deptexam-table">

                </table>
            </div>
        </div>
    </div>
<#--toolbar-->
    <div id="toolbar" >
        <button type="button" class="btn btn-warning ladda-button" data-style="contract"  id="saveExam" style="padding-right: 5px">生成当天考核</button>
    </div>
    <#--end toolbar-->
</div>
<script type="text/javascript">
    // manager-deptexam-table
    var tableParam = {
        classes : "table table-bordered table-hover",

        url : "${ctx}/exam/deptexam/examList.z",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        cache : false,

        pagination : false,
        height : 500,

        columns :[
            {title: '序&nbsp;&nbsp;&nbsp;号', formatter: function (value, row, index) {return index + 1;},align:'center'},
            // {checkbox:true},
            {field: 'reportdate', title: '上报日期',align:'center'},
            {title: '操&nbsp;&nbsp;&nbsp;作',align:'center', formatter: formatterOperator} // function (value, row, index) {return index + 1;}}
        ],
        cardView:(function(){
            // if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
            //     return true;
            // } else {
            //     return false;
            // }
            return false;
        })(),

        search :true,
        searchOnEnterKey :true,
        trimOnSearch : true,
        toolbar : "#toolbar",
        
        onLoadSuccess : function (data) {

        }
    }
    
    function formatterOperator(value, row, index) {
        //row.reportdate
        var placement = 'left';
        if(index<2 )placement = 'bottom';
        if(index>10) placement = 'top';
        var deleteBtn = "<div class='col-md-4'><a tabindex='0'  role='button' class='btn btn-sm btn-danger operator-delete ' data-html='true' " +
                " data-toggle='popover' data-trigger='focus' data-placement='"+placement+"'" +
                " data-content='<div class=\"alert alert-danger text-center\" style=\"margin-bottom:5px;\" ><p>确认要删除该当天考核数据？</p><hr />" +
                "<button class=\"btn btn-danger  delete-enter \" data-reportdate=\""+row.reportdate+"\" >确 认</button>&nbsp;&nbsp;&nbsp;&nbsp;" +
                "<button class=\"btn btn-default delete-cancel\">取 消</button></div>' " +
                " data-reportdate='"+row.reportdate+"'>&nbsp;删&nbsp;除&nbsp;考&nbsp;核&nbsp;</a></div>";
        var detailBtn = "<div class='col-md-4'><button type='button' class='btn btn-sm btn-primary operator-detail ' data-reportdate='"+row.reportdate+"'>&nbsp;查&nbsp;看&nbsp;详&nbsp;情&nbsp;</button></div>";
        var exportBtn = "<div class='col-md-4'><button type='button' class='btn btn-sm btn-info operator-export ladda-button'  data-style='contract' data-reportdate='"+row.reportdate+"'>&nbsp;导&nbsp;出&nbsp;考&nbsp;核&nbsp;</button></div>";

        return deleteBtn+detailBtn+exportBtn;
    }

    // $("#btn_del").popover('show');


    $("#saveExam").click(function () {
        console.log("SAVE");
        saveL.start();
        $.ajax({
            type: 'post',
            url: '${ctx}/exam/deptexam/saveDatas.z',
            contentType: "application/x-www-form-urlencoded",
            dataType: 'json',
            success: function (data) {
                if(data.success){
                    alert('成功');
                    $("#manager-deptexam-table").bootstrapTable("refresh");
                }
                saveL.stop();
            }
        })
    });

    $("#manager-deptexam-table").on('click','.operator-delete',function (e) {
        // $(".operator-delete").popover("hide");
        var reportDate = $(this).attr("data-reportdate");
        $(this).popover("toggle");
    });
    $("#manager-deptexam-table").on('click','.delete-cancel',function (e) {
        $(".operator-delete").popover("hide");
    });
    $("#manager-deptexam-table").on('click','.delete-enter',function (e) {
        $(".operator-delete").popover("hide");
        var reportdate = $(this).attr("data-reportdate");
        $.ajax({
            type: 'post',
            url: '${ctx}/exam/deptexam/deleteData.z',
            data: {reportdate:reportdate},
            contentType: "application/x-www-form-urlencoded",
            dataType: 'json',
            success: function (data) {
                alert('删除成功');
                $("#manager-deptexam-table").bootstrapTable("refresh");
            }
        });
    });

    $("#manager-deptexam-table").on('click','.operator-detail',function (e) {
        var reportDate = $(this).attr("data-reportdate");
        var form2 = $("<form ></form>");
        var input2 = $("<input />");
        input2.attr({"type":"hidden","name":"reportdate"});
        input2.val(reportDate);
        form2.append(input2);
        form2.appendTo(document.body);
        form2.attr({"method":"post","action":'${ctx}/exam/deptexam/admin.z'})
        form2.submit();
        document.body.removeChild(form2[0]);
    });



    $("#manager-deptexam-table").on('click','.operator-export',function (e) {
        var l = Ladda.create(this);
        var reportDate = $(this).attr("data-reportdate");

        var form = $("<form ></form>");
        var input = $("<input />");
        input.attr({"type":"hidden","name":"reportdate"});
        input.val(reportDate);
        form.append(input);
        form.attr({"method":"post","action":'${ctx}/exam/deptexam/exportDatas.z'})
        var options = {
            url: '${ctx}/exam/deptexam/exportDatas.z',
            type:'post',
            beforeSubmit : function(e){
                l.start();
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
                l.stop();
            }
        }
        form.ajaxSubmit(options);
    });


    $("#manager-deptexam-table").bootstrapTable(tableParam);
    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );

</script>
</body>
</html>