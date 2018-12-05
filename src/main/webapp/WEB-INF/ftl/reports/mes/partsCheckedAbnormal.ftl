<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>零件完工检测异常数据</title>

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

    <script type="text/javascript" src="${ctx}/ftl/common/bootStrapModalDrag.js"></script>

    <#--<script type="text/javascript" src="${ctx}/ftl/common/table/extensions/resizable/bootstrap-table-resizable.js "></script>-->
    <#--loading -->
    <#--<link rel="stylesheet" href="${ctx}/ftl/common/ladda/ladda-themeless.min.css">
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/spin.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/ladda.min.js"></script>-->

    <style>
        #toolbar input{
            display: inline;
        }
    </style>
</head>
<body id="wrapper" style="overflow-y: auto;margin: 10px;">

    <h3 style="margin: 0;text-align: center;">零件完工检测异常数据</h3>
    <hr style="margin: 8px 0 0 0"/>
    <table id="data-list" style="table-layout: fixed;"></table>

    <div id="toolbar" >
        <label for="selectKey" style="margin-left: 10px;">部门编号</label>
        <div class="btn-group">
            <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary" id="selectKey" data-departCode="">部门编号&nbsp; <span class="caret"></span></button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="menu1" style="width: 230px;">
                <#list departMap?keys as key>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="selectDept('${key}')">${key} | ${departMap[key]}</a></li>
                </#list>
            </ul>
        </div>
        <label for="planCode" style="margin-left: 10px;">路线单号</label>
        <input id="planCode" class="form-control" type="text" placeholder="请输入路线单号" style="width: 150px;">
        <#--<label for="planCode" style="margin-left: 10px;">部门编号</label>
        <input id="departCode" class="form-control" type="text" placeholder="请输入部门编号" style="width: 150px;">-->
        <label for="planCode" style="margin-left: 10px;">起止日期</label>
        <input id="startDate" class="form-control" type="text" placeholder="请选择起始日期" data-provide="datepicker" style="width: 150px;"/> -
        <input id="endDate" class="form-control" type="text" placeholder="请输终止日期" data-provide="datepicker" style="width: 150px;"/>
        <button id="search-btn" class="btn btn-md btn-success" style="margin-left: 10px;width: 70px;">查询</button>
        <button id="clear-btn" class="btn btn-danger" style="margin-left: 10px;width: 70px;">清空</button>
    </div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 1100px;margin-top: 150px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <div class="modal-body" style="height: 110px;">
                    <table id="detail-list" style="table-layout: fixed;"></table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

    <#--成功提示框-->
    <div class="modal fade bd-example-modal-sm"  id="successAlert" role="dialog" data-backdrop="false"  aria-hidden="true">
        <div class="modal-dialog modal-sm" style="margin-top: 240px;background-color: rgba(255,255,255,0.6)">
            <div class="modal-content">
                <div style="margin-right: 8px;">
                    <button onclick="clearAlert('#successMsg')" type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: rgb(255,0,0);">
                        &times;
                    </button>
                </div>
                <p id="successMsg" class="text-center mb-0" style="margin: 15px;height: 20px;">

                </p>
            </div>
        </div>
    </div>
    <#--失败提示框-->
    <div class="modal fade bd-example-modal-sm"  id="failAlert" role="dialog" data-backdrop="false"  aria-hidden="true">
        <div class="modal-dialog modal-sm" style="margin-top: 240px;background-color: rgba(255,255,255,0.6)">
            <div class="modal-content">
                <div style="margin-right: 8px;">
                    <button onclick="clearAlert('#failMsg')" type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: rgb(255,0,0);">
                        &times;
                    </button>
                </div>
                <p id="failMsg" class="text-center mb-0" style="margin: 15px;height: 20px;"></p>
            </div>
        </div>
    </div>


    <script type="text/javascript">
        var detailList = $('#detail-list');
        var dataList = $('#data-list');

        //成功弹窗方法
        function successAlert(info){
            var html = '<i class="glyphicon glyphicon-ok-circle" style="color: green;"></i>&nbsp;&nbsp;&nbsp;&nbsp;';
            $('#successMsg').html(html + info);
            $('#successAlert').modal('show');
        }
        //失败弹窗方法
        function failAlert(info){
            var html = '<i class="glyphicon glyphicon-remove-circle" style="color: red;"></i>&nbsp;&nbsp;&nbsp;&nbsp;';
            $('#failMsg').html(html + info);
            $('#failAlert').modal('show');
        }
        //清除提示框内容，避免内容叠加
        function clearAlert(selector) {
            $(selector).html('');
        }
        //部门编号选择
        function selectDept(departCode) {
            var html = '&nbsp; <span class="caret"></span>';
            $('#selectKey').attr('data-departCode',departCode);
            $('#selectKey').html(departCode + html);
        }

        //定义二级页面的table列
        var columns = [
            {field: 'planQTY', title: '	计划数量', width: 70,align:'right',halign:'center'},
            {field: 'receiveQTY', title: '领用数量', width: 70,align:'right',halign:'center'},
            {field: 'samplingQty', title: '	抽检数量', width: 70,align:'right',halign:'center'},
            {field: 'batchCheckQty', title: '分批数量', width: 70,align:'right',halign:'center'},
            {field: 'qualifiedQty', title: '合格数量', width: 70,align:'right',halign:'center'},
            {field: 'unqualifiedQty', title: '不合格数量  ', width: 90,align:'right',halign:'center'},
            {field: 'samplingQtySum', title: '抽检数量和  ', width: 90,align:'right',halign:'center'},
            {field: 'batchCheckQtySum', title: '分批数量和', width: 90,align:'right',halign:'center'},
            {field: 'qualifiedQtySum', title: '	合格数量和', width: 90,align:'right',halign:'center'},
            {field: 'unqualifiedQtySum', title: '不合格数量和', width: 100,align:'right',halign:'center'},
            /*{field: 'qtyDifferent', title: '数量不一致', width: 80},
            {field: 'receiveDifferentChecked', title: '	领用数量分批数量不一致', width: 100},
            {field: 'receiveDifferentPlan', title: '领用数量计划数量不一致', width: 100},*/
            {field: 'batchCheckFlag', title: '分批标识', width: 100,align:'right',halign:'center',
                formatter: function (value, row, index) {
                    if(row.batchCheckFlag == 0){
                        return "未分批检测";
                    }else {
                        return "分批检测";
                    }
                }
            },
            //{field: 'completeInfo', title: '完成情况', width: 80,align:'left',halign:'center'}
            {field: 'endFlag', title: '完成情况', width: 80,align:'left',halign:'center',
                formatter: function (value, row, index) {
                    if(row.endFlag == 1){
                        return "已完成";
                    }else if(row.endFlag == 2){
                        return "未完成";
                    }else {
                        return "未开始";
                    }
                }
            }
        ];

        //异常超链接
        function abnormalDetail(planQTY,receiveQTY,samplingQty,batchCheckQty,qualifiedQty,unqualifiedQty,samplingQtySum,batchCheckQtySum,qualifiedQtySum,
                unqualifiedQtySum,completeInfo,batchCheckFlag,endFlag,planCode,processNo) {
            var data = [];
            var obj = {
                planQTY:planQTY,
                receiveQTY:receiveQTY,
                samplingQty:samplingQty,
                batchCheckQty:batchCheckQty,
                qualifiedQty:qualifiedQty,
                unqualifiedQty:unqualifiedQty,
                samplingQtySum:samplingQtySum,
                batchCheckQtySum:batchCheckQtySum,
                qualifiedQtySum:qualifiedQtySum,
                unqualifiedQtySum:unqualifiedQtySum,
                completeInfo:completeInfo,
                batchCheckFlag:batchCheckFlag,
                endFlag:endFlag
            };
            data.push(obj);
            detailList.bootstrapTable('load',data);
            //如果不分批，将分批检测表中数据列隐藏
            if(batchCheckFlag == 0){
                detailList.bootstrapTable('hideColumn','samplingQtySum');
                detailList.bootstrapTable('hideColumn','batchCheckQtySum');
                detailList.bootstrapTable('hideColumn','qualifiedQtySum');
                detailList.bootstrapTable('hideColumn','unqualifiedQtySum');
            }else if(batchCheckFlag == 1){
                detailList.bootstrapTable('showColumn','samplingQtySum');
                detailList.bootstrapTable('showColumn','batchCheckQtySum');
                detailList.bootstrapTable('showColumn','qualifiedQtySum');
                detailList.bootstrapTable('showColumn','unqualifiedQtySum');
            }

            $('#myModalLabel').html('路线单号【' + planCode + '】第【' + processNo + '】序' + '异常数据明细');
            $('#detailModal').modal('show');
        }

        $(function () {
            var date = utils.getNowFormatDate();

            //给两个日期搜索框初始化当前日期
            $('#startDate').val(date);
            $('#endDate').val(date);

            //清空按钮 点击事件
            $('#clear-btn').click(function () {
                $('#toolbar input').val('');
                $('#selectKey').attr('data-departCode','');
                $('#selectKey').html('部门编号&nbsp; <span class="caret"></span>');
            });

            //查询按钮 点击事件
            $('#search-btn').click(function () {
                var planCode = $('#planCode').val();
                var departCode = $('#selectKey').attr('data-departCode');
                var startDate = $('#startDate').val();
                var endDate = $('#endDate').val();
                if(startDate == '' || startDate.length == 0){
                    failAlert("起始日期不能为空！");
                }else if(endDate != '' && startDate > endDate){
                    failAlert("起始日期不能大于终止日期！");
                }else if(startDate > date){
                    failAlert("起始日期不能大于当前日期！");
                }else{
                    var params = {planCode:planCode,departCode:departCode,startDate:startDate,endDate:endDate};
                    dataList.bootstrapTable('refresh',{query: params});
                }
            });

            //table init
            dataList.bootstrapTable({
                url: '${ctx}/mes/partsChecked/dataList.z',
                striped : true,
                pagination : true,
                sidePagination : 'client',
                search :true,
                searchOnEnterKey :true,
                trimOnSearch : true,
                idField : 'id',
                toolbar : "#toolbar",
                columns : [
                    {title: '序号', width: 50,align:'center',halign:'center',formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
                    {field: 'departCode', title: '部门编号', width: 65,align:'left',halign:'center'},
                    {field: 'planCode', title: '路线单号', width: 80,align:'left',halign:'center'},
                    {field: 'itemCode', title: '零件代号', width: 200,align:'left',halign:'center'},
                    {field: 'processNo', title: '工序号  ', width: 50,align:'right',halign:'center'},
                    {field: 'processName', title: '	工序名称', width: 80,align:'left',halign:'center'},
                    //{field: 'endFlag', title: '	完工标识', width: 60,align:'left',halign:'center'},
                    {field: 'saveTime', title: '保存时间', width: 120,align:'left',halign:'center'},
                    {field: 'checkPerson', title: '	检验员  ', width: 80,align:'left',halign:'center'},
                    {title: '异常情况', width: 250,align:'left',halign:'center',
                        formatter: function (value, row, index) {
                            var opt = row.planQTY + "," + row.receiveQTY + "," +row.samplingQty + "," +row.batchCheckQty + "," +row.qualifiedQty + ","
                                    +row.unqualifiedQty + "," +row.samplingQtySum + "," + row.batchCheckQtySum + "," + row.qualifiedQtySum + ","
                                    +row.unqualifiedQtySum + ",'" +row.completeInfo + "'," + row.batchCheckFlag + "," + row.endFlag + ",'"
                                    +row.planCode + "'," + row.processNo;
                            var html = '<div style="cursor: pointer;" onclick="abnormalDetail(' + opt + ')"><a>';
                            if(row.qtyDifferent == '异常'){
                                html += "合格数量加不合格数量不等于检测数量，";
                            }
                            if(row.receiveDifferentChecked == '异常'){
                                html += "领用数量不等于分批数量，";
                            }else if(row.completeInfo == '异常'){
                                html += "检测完成标识有误，";
                            }
                            if(row.receiveDifferentPlan == '异常'){
                                html += "领用数量不等于计划数量，";
                            }
                            html = html.substring(0,html.length - 1);
                            html += "</a></div>";
                            return html;
                        }
                    }
                ],
                onLoadSuccess : function (data) {
                    var a = 1;
                }
            });

            //detail table init
            detailList.bootstrapTable({
                data:[],
                idField : 'id',
                columns : columns,
                onLoadSuccess : function (data) {

                }
            });

        });

        //datepicker 中文及格式化
        $.fn.datepicker.dates['en'] = {
            days: ['星期天', "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            daysShort: ['星期天', "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            daysMin: ["日", "一", "二", "三", "四", "五", "六"],
            months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            today: "Today",
            clear: "Clear",
            format: "yyyy/mm/dd",
            titleFormat: "yyyy/MM",
            weekStart: 0
        };

        var utils = {
            getNowFormatDate :function() {
                var date = new Date();
                var seperator1 = "/";
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                var currentdate = year + seperator1 + month + seperator1 + strDate;
                return currentdate;
            }
        }

    </script>
</body>
</html>