<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>吴忠仪表部门信息</title>
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
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/table/zTreeStyle.css">
    <link rel="stylesheet" href="${ctx}/ftl/common/ladda/ladda-themeless.min.css">
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/spin.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/ladda/ladda.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-treeview.js"></script>
    <style type="text/css">
               *{
                     margin: 0;
                     padding: 0;
                     /*background-color: #EAEAEA;*/
                    }
                div{
                          width: auto;
                          height: auto;
                          background-color: #fbfbfb;
                      }
                .center-in-center{
                          position: absolute;
                         top: 50%;
                         left: 50%;
                          -webkit-transform: translate(-50%, -50%);
                          -moz-transform: translate(-50%, -50%);
                        -ms-transform: translate(-50%, -50%);
                          -o-transform: translate(-50%, -50%);
                          transform: translate(-50%, -50%);
                      }
            </style>



</head>
<body id="wrapper" style="overflow: hidden">
<div class="panel panel-default center-in-center" data-options="fit:true">
    <div class="panel-heading " data-options="fit:true" style="background: #006699">
        <span style="color: #fbfbfb">部门选择</span>
    </div>
    <form action="${ctx}/reports/task/statistics.z" method="post" id="form1">
    <div class="panel-body "style="width: 350px;height:450px;overflow:scroll" data-options="fit:true">
        <div  id="tree" data-options="fit:true"></div>
    </div>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-primary " style="width: 120px" onclick="submit()">提&nbsp;交</button>
    </form>
</div>
<script type="text/javascript">
    //选中/取消父节点时选中/取消所有子节点
     function getChildNodeIdArr(node) {
             var ts = [];
             if (node.nodes) {
                     for (x in node.nodes) {
                             ts.push(node.nodes[x].nodeId);
                             if (node.nodes[x].nodes) {
                                     var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                                     for (j in getNodeDieDai) {
                                             ts.push(getNodeDieDai[j]);
                                         }
                                 }
                         }
                 } else {
                     ts.push(node.nodeId);
                 }
             return ts;
         }
     //选中所有子节点时选中父节点
     function setParentNodeCheck(node) {
             var parentNode = $("#tree").treeview("getNode", node.parentId);
             if (parentNode.nodes) {
                     var checkedCount = 0;
                     for (x in parentNode.nodes) {
                             if (parentNode.nodes[x].state.checked) {
                                     checkedCount ++;
                                 } else {
                                     break;
                                 }
                         }
                     if (checkedCount === parentNode.nodes.length) {
                             $("#tree").treeview("checkNode", parentNode.nodeId);
                             setParentNodeCheck(parentNode);
                         }
                 }
         }

     $(function () {
             $.ajax({
                     type: "Post",
                         url: '${ctx}/response/menu',
                         dataType: "json",
                         success: function (result) {
                         $('#tree').treeview({
                                     data: result.list,         // 数据源
                                     showCheckbox: true,   //是否显示复选框
                                     highlightSelected: true,    //是否高亮选中
                                     multiSelect: true,    //多选
                                     levels : 2,
                                     enableLinks : true,//必须在节点属性给出href属性
                                     color: "#010A0E",
                                     onNodeChecked : function (event,node) {
                                     var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                                     if (selectNodes) { //子节点不为空，则选中所有子节点
                                             $('#tree').treeview('checkNode', [selectNodes, { silent: true }]);
                                         }
                                 },
                             onNodeUnchecked : function(event, node) { //取消选中节点
                                     var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                                     if (selectNodes) { //子节点不为空，则取消选中所有子节点
                                             $('#tree').treeview('uncheckNode', [selectNodes, { silent: true }]);
                                         }
                                 },

                             onNodeExpanded : function(event, data) {

                                },

                            onNodeSelected: function (event, data) {
                                    //alert(data.nodeId);
                                }

                         });
                     },
                     error: function () {
                        alert("菜单加载失败！")
                     }
            });
         })

    /**
     * 提交form表单内容
     */
    function submit(){
        var result = document.getElementById("tree").value;

        if(result == ""){
            alert("部门不能为空");
            return false;
        }

        document.getElementById("form1").submit();
    }

</script>
</body>
</html>