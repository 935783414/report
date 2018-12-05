<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>吴忠仪表考勤汇总表</title>
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
    <link rel="stylesheet" href="${ctx}/css/table/bootstrap-datepicker3.standalone.css">
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/spin.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/ladda.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-select.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-datepicker.zh-CN.min.js"></script>
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
        <div class="btn-group" style="width: 200px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                    年份选择
                </span>
                <select class="selectpicker" id="Year" title="-请选择-">

                </select>

            </div>
        </div>

        <div class="btn-group" style="width: 200px">
            <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon " style="background-color:rgba(51,122,183,0.8);color: #fff">
                    月份选择
                </span>
                <select class="selectpicker" title="-请选择-">
                    <option value="1">1月</option>
                    <option value="2">2月</option>
                    <option value="3">3月</option>
                    <option value="4">4月</option>
                    <option value="5">5月</option>
                    <option value="6">6月</option>
                    <option value="7">7月</option>
                    <option value="8">8月</option>
                    <option value="9">9月</option>
                    <option value="10">10月</option>
                    <option value="11">11月</option>
                    <option value="12">12月</option>
                </select>
            </div>
        </div>
         <div class="btn-group" style="width: 150px">
              <div class="input-group input-group-toolbar">
                <span class="form-group input-group-addon" style="background-color:rgba(51,122,183,0.8);color: #fff">
                    <a href="javascript:;" type="button" id="dept"><font style="color: #fbfbfb">部门</font></a>
                </span>
                  <input class="form-control " type="text" id="bumen" />
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
                    类别
                </span>
                  <select id="roleSelect" class="selectpicker" title="-请选择-">

                  </select>
              </div>
          </div>
          <button type="button" class="btn btn-primary" id="queryList">查询</button>
          <button type="button" class="btn btn-primary"  id="exportAllList">导出EXL</button>

        </div>
    </div>
<script type="text/javascript" src="${ctx}/ftl/report/avplan/report/statistics.js"></script>
<script type="text/javascript">
    /**
     * 查询表数据；
     * 进Controller获取数据返回
     * 分页查询
     */
    var defaultColumns = Columns.getColumns();
     
    var deptExamParams = {
        classes : "table table-bordered table-hover",

        url : "${ctx}/report/huizong/statsList.z",
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


    /*var exportL = Ladda.create( document.querySelector( '#exportList' ) );
    var saveL = Ladda.create( document.querySelector( '#saveExam' ) );*/
    /**
     * 获取年度时间
     */
    $(function() {
//设置年份的选择
        var myDate= new Date();
        var startYear=myDate.getFullYear()-3;//起始年份
        var endYear=myDate.getFullYear()+3;//结束年份
        var obj=document.getElementById('Year')
        for (var i=startYear;i<=endYear;i++)
        {
            obj.options.add(new Option(i,i));
        }
        obj.options[obj.options.length-4].selected=1;
    });

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

    /**
     * 点击部门选择
     */

    $("#dept").on("click",function () {
        var l = layer.open({
            type: 2,
            title: '部门选择',
            shadeClose: true,
            shade: 0.5,
            maxmin: true, //开启最大化最小化按钮
            area: ['500px', '600px'],
            content: "${ctx}/reports/task/twoBM.z"
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
    /**
     * 分类数据获取
     */
    $(function(){
        var content = "";
        $.post(
                "${ctx}/response/roleList",
                function(data){
                    for(var i=0;i<data.length;i++){
                        content+="<option>"+data[i].Base_RoleName+"</option>";
                    }
                    //将拼接好的option放置到select中
                    $("#roleSelect").html(content);
                },
                "json"
        );
    });
</script>
</body>
</html>