<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>部门信息</title>
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
    <#--bootstrap treegird-->
    <script type="text/javascript" src="${ctx}/ftl/common/table/extensions/tree-column/bootstrap-table-tree-column.css"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/extensions/tree-column/bootstrap-table-tree-column.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>
    <script>
        $(function () {
            var $table = $("#table");
            $table.bootstrapTable({
                url:'${ctx}/report/kaoqin/departmentList.z',
                striped:true,
                sidePagenation:'server',
                idField:'id',
                columns:[
                   {
                        field:'Base_GroupName',
                        title:'部门名称'
                    },{
                        field: 'ck',
                        title:'选择',
                        checkbox: true
                    }
                ],
                treeShowField: 'Base_GroupName',
                parentIdField: 'Base_AuthID',
                onLoadSuccess: function(data) {
                    $table.treegrid({
                        initialState: 'collapsed',//收缩
                        treeColumn: 1,//指明第几列数据改为树形
                        expanderExpandedClass: 'glyphicon glyphicon-triangle-bottom',
                        expanderCollapsedClass: 'glyphicon glyphicon-triangle-right',
                        onChange: function() {
                            $table.bootstrapTable('resetWidth');
                        }
                    });
                }
            });
        })
    </script>
</head>
<body id="wrapper" style="overflow: hidden">
<table id="table"></table>
</body>
</html>