<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>David测试 -新建待办事项</title>
  <#assign ctx = request.contextPath />
<#--<script type="text/javascript" src="../ftl/common/pc.js"></script>-->
<#--document.write("<link href=\"../ftl/pc/sui/css/sui.min.css\" rel=\"stylesheet\">")-->
<#--document.write("<script type=\"text/javascript\" src=\"../ftl/common/jquery.min.js\"></script>")-->
<#--document.write("<script type=\"text/javascript\" src=\"../ftl/pc/sui/js/sui.min.js\"></script>")-->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/table/bootstrap-editable.css" rel="stylesheet">
    <link href="${ctx}/css/table/bootstrap-table.css" rel="stylesheet">
    <style type="text/css">
        #shelter{
            box-shadow: 0px 3px 5px #888888;
            background-color: rgba(255,255,255,1);
            margin-left: 10px;
            margin-right: 10px;
        }
        #fixed_table{
            position: absolute;
            top: 0px;
            left: 0px;
            table-layout:fixed;
        }
        /*.scroll_table_content{*/
            /*width:100%;*/
            /*overflow:auto;*/
        /*}*/

    </style>
    <script type="text/javascript" src="${ctx}/ftl/common/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/bootstrap.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-editable.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-table.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-table-locale-all.js"></script>
</head>
<body id="wrapper" style="overflow: hidden">
<#--<nav class="navbar navbar-default navbar-fixed-top " role="navigation" style="margin-bottom: 0;background: #00CA79;">-->
    <#--<div class="header-right">-->
    <#--<button class="btn btn-info" id="btn_add_sibling"><i class="glyphicon glyphicon-plus"></i> 新增同级</button>-->
    <#--<button class="btn btn-info" id="btn_add_children"><i class="glyphicon glyphicon-plus"></i> 新增子级</button>-->
    <#--<button class="btn btn-info" id="btn_edit"><i class="glyphicon glyphicon-edit"></i> 编 辑</button>-->
    <#--<button class="btn btn-danger" id="btn_del" role="button" ><i class="glyphicon glyphicon-remove"></i> 删 除</button>-->
        <#--<!--<button class="btn btn-info"><i class="glyphicon glyphicon-repeat"></i> 重 置</button>&ndash;&gt;-->
    <#--</div>-->
<#--</nav>-->

<div id="page-wrapper" style="padding-top: 10px;">
    <div id="page-inner">
        <div class="row" id="title_row">
            <div class="col-md-12">
                <div class="form-group">
                    <div class="col-md-4">
                        <h1 class="page-head-line" id="title">采购部</h1>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>选择部门</label>
                            <select class="form-control" id="unitname">
                                <#list depts as children>
                                    <option value="${children.unitname}">${children.unitname}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>填写时间(yyyy-MM-dd，默认为今天)</label>
                            <input class="form-control" type="text" id="reportdate" value="${time}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>合同号</label>
                            <input class="form-control" type="text" id="orderno">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <button type="button" id="queryList" class="btn btn-info">查询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="table_row" >
            <div class="panel panel-default" >
                <div class="panel-body" style="width: auto;margin-left: 10px;margin-right: 10px;" id="deptexam-panel-body">
                    <div class="scroll_table_content" id="scroll_wrap" style="overflow: auto;height: 400px;" >
                        <table id="message-deptexam-table"  class="table  table-bordered table-hover"  >

                        </table>
                        <div id="test_width"></div>
                    </div>
                </div>
            </div>
            <!-- End  Kitchen Sink -->
        </div>
    </div>
</div>

<script>
    var report;
    var baseBeginColumns = [{title: '序号', formatter: function (value, row, index) {return index + 1;}}
        ,{field: 'orderno', title: '合同号'}];
    var baseEndColumns = [
        {
            field: 'reason', title: '原因',
            formatter: function (value, row, index) {
                var val = (!value || value == null || value == "null") ? "请填写" : value;
                return "<a href='#' name='reason' data-type='textarea' data-mode='inline' data-pk='" + row.innerid + "' data-title='考核原因'>" + val + "</a>";
                // return "asdw"+value;
            },
            width:'500px'
        }
        , {
            field: 'isExam', title: '考核情况',
            formatter: function (value, row, index) {
                var val = (!value || value == null || value == "null") ? "请选择" : value;
                return "<a href='#' name='isExam' data-type='select' data-mode='inline' data-source='[{value:\"考核\",text:\"已考核\"},{value:\"不考核\",text:\"不考核\"},{value:\"暂不考核\",text:\"暂不考核\"}]' data-pk='" + row.innerid + "' data-title='考核情况'>" + val + "</a>";
                // return "asdw"+value;
            }
        }
        , {
            field: 'appeal', title: '申诉情况',
            formatter: function (value, row, index) {
                var val = (!value || value == null || value == "null") ? "请填写" : value;
                return "<a href='#' name='appeal' data-type='textarea' data-mode='inline' data-source='' data-pk='" + row.innerid + "' data-title='考核原因'>" + val + "</a>";
                // return "asdw"+value;
            }
        }
    ];



    $("#queryList").click(function () {
        $("#message-deptexam-table").bootstrapTable('refresh', {
            query: {
                unitname: $("#unitname").val(),
                reportdate: $("#reportdate").val(),
                orderno: $("#orderno").val()
            }
        });
        $("#title").text($("#unitname").val());
    });

    $("#unitname").change(function (e) {
        var columns = getColumns($("#unitname").val());
        $("#message-deptexam-table").bootstrapTable('refreshOptions',{
            columns:columns,
        });
        $("#queryList").click();
    });

    function getColumns(unit) {
        if (unit == "研发部" || unit == "工艺部") {

        } else if (unit == "砂铸部" || unit == "精铸部") {

        } else if (unit == "采购部") {

        } else if (unit.indexOf("事业部") > -1) {

        }
        ;
        var columns = new Array();
        columns=columns.concat(baseBeginColumns).concat(middleColumns).concat(baseEndColumns);
        return columns;
    }


    var defaultUnit = $("#unitname").val();
    var defaultColumns = getColumns(defaultUnit);

    var baseParams = {
        locales: "zh-CN",
        url: "${ctx}/report/deptexam/dataList.z",
        queryParamsType: "limit",
        dataType: "json",
        pageSize: 10,
        sidePagination: "client",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        striped: true,
        cache: false,
        columns:defaultColumns,
        clickToSelect: true,
        leftFixedColumns:true,
        leftFixedNumber:2,
        rightFixedColumns:true,
        rightFixedNumber:3,
        query:{unitname:defaultUnit},
        onClickRow: function (row, $element) {
            $("#message-deptexam-table .table-selected").removeClass("table-selected");
            $($element).addClass("table-selected");
            report = row;
            // messageUserList.seletedNode = row;
            // alert(row.innerid);
        },
        onDblClickRow: function (row, $element) {
            $("#message-deptexam-table .table-selected").removeClass("table-selected");
            $($element).addClass("table-selected");
            // messageUserList.seletedNode = row;
            $("#userListModal").modal("hide");
            // alert(row.innerid);
        },
        onLoadSuccess: function (aa, bb, cc) {
            console.log($("#table_row").height());
            // createStaticTHead();
            $("#message-deptexam-table a").editable({
                url: function (params) {
                    var sName = $(this).attr("name");
                    console.log(report["orderno"] + "->" + sName + ":" + report[sName]);
                    if (report) {
                        report[sName] = params.value;
                        $.ajax({
                            type: 'post',
                            url: '${ctx}/report/deptexam/editData.z',
                            data: report,
                            contentType: "application/x-www-form-urlencoded",
                            dataType: 'json',
                            success: function (data) {
                                alert('八成成功');
                            }
                        })
                    }
                },
                type: 'text'
            });
        }
    }

    function createStaticTHead(){
        $('.shelter').remove();
        var sourceTable = $("#message-deptexam-table");//table id
        var sourceTableHeight = sourceTable.height();

        var sourceTableHead = $("#message-deptexam-table thead tr");//table thead tr id
        var headHeight = sourceTableHead.height();//table thead tr height
        var sourceTableWidth = sourceTable.outerWidth(); //get source table width
        var testDiv = $("#test_width"); //測試獲取內容寬度
        var wrapDiv = $("#scroll_wrap"); //滾動條外層div，用於獲取div距離頭部的距離
        var tempTop = wrapDiv.offset().top - $(window).scrollTop(); //滾動頭部距離網頁頂部的距離
        //copy table and thead html tag from source table,
        $('body').append('<div id="shelter" class="shelter"><div id="fixed_table_wrap"><table id="fixed_table"  class="table table-bordered table-hover"><thead></thead></table></div></div>');
        var fixTopDiv = $("#shelter");
        fixTopDiv.hide();
        //only set top and left,beacuse i need the top bar can scroll left
        fixTopDiv.css({
            'height':headHeight,
            'position':'fixed',
            'top':tempTop+"px",
            'left':'0',
            'width':testDiv.width()+"px",
            'overflow':'hidden'
        });
        //set target table width equal source table width
        $("#fixed_table_wrap,#fixed_table").css({'width':sourceTableWidth+"px"});

        //only clone tr html and change thead tr id attr
        var targetHead = sourceTableHead.clone().attr('id','fix_head');
        targetHead.appendTo('#fixed_table thead');
        //mark target table thead td width,height equal source table thead td width,height
        $("#message-deptexam-table thead tr th").each(function(index,value){
            var tempWidth = $(value).outerWidth();
            var tempHeight = $(value).outerHeight();
            $("#fixed_table th").eq(index).css({'width':tempWidth+"px",'height':tempHeight+"px"});
        });
        //上下滚屏不计算宽度
        $(window).on('scroll',function(){
            tempTop = wrapDiv.offset().top - $(window).scrollTop();
            var scroll_top = wrapDiv.scrollTop() - headHeight + $(window).scrollTop();
            if(tempTop <= 0){
                tempTop = 0;
            }
            fixTopDiv.css({'top':tempTop +"px"});
            //control  show or hide
            if (scroll_top >= 0) {
                if(!fixTopDiv.is(':visible')){
                    fixTopDiv.show();
                }
            }else {
                if(fixTopDiv.is(':visible')){
                    fixTopDiv.hide();
                }
            }
        });
        //左右不计算高度
        var hDiv =$(".fixed-table-body");
        hDiv.on('scroll',function(){
            var sl=-Math.max(hDiv.scrollLeft(),$('document').scrollLeft());
            if(isNaN(sl)){
                sl = - hDiv.scrollLeft();
            }
            var baseWidth = testDiv.width() + hDiv.scrollLeft();
            fixTopDiv.css({"left":sl+'px','width':baseWidth+"px"});
        });
    }

    // $("#scroll_wrap").height($(window).height()-$("#title_row").height()-20);
    $("#message-deptexam-table").bootstrapTable(baseParams);

</script>
<script>

</script>
</body>
</html>