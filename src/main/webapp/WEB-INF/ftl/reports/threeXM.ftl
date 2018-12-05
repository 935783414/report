<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="robots" content="all,follow">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>吴忠仪表部门信息</title>
    <script type="text/javascript" src="${ctx}/ftl/common/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/jquery.form.js"></script>
    <#--bootstrap-->
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
    <#--model-->
    <link href="${ctx}/css/model/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/model/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/css/model/font-icon-style.css" rel="stylesheet">
    <link href="${ctx}/css/model/style.default.css" rel="stylesheet">
    <link href="${ctx}/css/model/card.css" rel="stylesheet">
    <link href="${ctx}/css/model/style.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/chart.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/front.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/mychart.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/popper.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/model/tether.min.js"></script>
</head>
<body>
<header class="header">
    <nav class="navbar navbar-expand-lg ">

        <div class="container-fluid ">
            <div class="navbar-holder d-flex align-items-center justify-content-between">
                <div class="navbar-header">
                    <a href="javascrip:;" class="navbar-brand">
                        人员选择
                    </a>
                </div>
            </div>
        </div>
    </nav>
</header>
<div class="page-content d-flex align-items-stretch" style="width:250px; height:500px;overflow:scroll">
    <!--***** SIDE NAVBAR *****-->
    <nav class="side-navbar">
        <hr>
        <!-- Sidebar Navidation Menus-->
        <ul class="list-unstyled">
            <li class="active"> <a href="javascript:;"><i class="icon-home"></i>吴忠仪表部门</a></li>
            <li><a href="#apps" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>部门</a>
                <ul id="apps" class="collapse list-unstyled">
                    <li><a href="javascript:;">数控部</a></li>
                    <li><a href="javascript:;">加工部</a></li>
                    <li><a href="javascript:;">安装部</a></li>
                    <li><a href="javascript:;">检验部</a></li>
                </ul>
            </li>
            <li> <a href="javascript:;"id="bid"> <i class="fa fa-bar-chart"></i>电子信息部</a></li>
            <li><a href="#forms" aria-expanded="false" data-toggle="collapse"> <i class="fa fa-building-o"></i>技术 </a>
                <ul id="forms" class="collapse list-unstyled">
                    <li><a href="javascript:;">开发</a></li>
                    <li><a href="javascript:;">测试</a></li>
                </ul>
            </li>
            <li> <a href="javascript:;"> <i class="fa fa-map-o"></i>财务部 </a></li>
            <li><a href="#pages" aria-expanded="false" data-toggle="collapse"> <i class="fa fa-file-o"></i>后勤部</a>
                <ul id="pages" class="collapse list-unstyled">
                    <li><a href="javascript:;">食堂</a></li>
                    <li><a href="javascript:;">门卫</a></li>
                    <li><a href="javascript:;">消防</a></li>
                    <li><a href="javascript:;">安检</a></li>
                    <li><a href="javascript:;">办公室</a></li>
                    <li><a href="javascript:;">保洁</a></li>
                    <li><a href="javascript:;">安防</a></li>
                </ul>
            </li>
    </nav>
    <div class="content-inner">
     </div>
    <script type="text/javascript">
        /**
         * ajax局部刷新页面
         */

    </script>
</div>
</body>
</html>