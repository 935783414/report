<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign ctx = request.contextPath />
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>项目管理报表平台</title>

    <script type="text/javascript" src="${ctx}/ftl/common/jquery.min.js"></script>
<#--bootstrap-->
    <link rel="stylesheet" href="${ctx}/css/bootstrap.css">

    <script type="text/javascript" src="${ctx}/ftl/common/bootstrap.js"></script>

<#--<script type="text/javascript" src="${ctx}/ftl/common/jquery.metisMenu.js"></script>-->

    <link rel="stylesheet" href="${ctx}/ftl/common/pricing.css">
    <link rel="stylesheet" href="${ctx}/ftl/common/basic.css">

    <style type="text/css">
        .enter-panel{
           margin: 0px 20px;
        }
        .enter-panel .price-simple{
            font-size: 35px !important;
            line-height: 1;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <!-- /. NAV SIDE  -->
    <div id="page-inner" style="min-height: 600px;padding-left: 20px;padding-right: 20px;">
        <div id="logobar"  style="position: absolute;right: 400px;top: 12px;width: 100px;height: 100px">
            <img src="${ctx}/ftl/images/logo.png" height="80%" >
        </div>
        <div class="row">
            <div class="col-md-12">
                <h1 class="page-head-line" style="color: #FF6A00">&nbsp;&nbsp;项目跟踪反馈分析平台&nbsp;&nbsp;<small>V1.5</small></h1>
                <h1 class="page-subhead-line" style="font-size: 18px;">欲善其事，先利其器；始于数据，善于分析；强化管理，补齐短板。 </h1>
            </div>
        </div>
        <div class="row text-center pad-row">
            <div class="col-md-3">
                <div class="panel-success simple-table enter-panel" >
                    <div class="panel-heading">
                        <h4>项目管理系统</h4>
                    </div>
                    <div class="alert alert-success">

                        <ul class="plan">
                            <li class="price-simple" style="border-bottom:1px solid #c6c6c6;">异常反馈</li>
                            <li style="border-bottom:1px solid #c6c6c6;"><strong>原因分析</strong></li>
                            <li style="border-bottom:1px solid #c6c6c6;"><strong>异常申诉</strong></li>
                            <li style="border-bottom:1px solid #c6c6c6;"><strong>异常考核</strong></li>
                        </ul>
                        <a href="javascript:;" data-type="ftl" data-data="exam.deptexam.guess"class="btn btn-success enter-btn">点击进入</a>
                    </div>

                </div>
            </div>

            <div class="col-md-3">
                <div class="panel-info simple-table enter-panel">
                    <div class="panel-heading">
                        <h4>项目管理系统</h4>
                    </div>
                    <div class=" alert alert-info">

                        <ul class="plan">
                            <li class="price-simple"><strong>预警报告</strong></li>
                            <li><strong>进度跟踪</strong></li>
                            <li><strong>进度预警</strong></li>
                            <li><strong>异常反馈</strong></li>
                        </ul>
                        <a href="javascript:;" data-type="ftl" data-data="warn.deptWarn.guess" class="btn btn-info enter-btn">点击进入</a>
                    </div>

                </div>
            </div>

            <div class="col-md-3">
                <div class="panel-info simple-table enter-panel">
                    <div class="panel-heading">
                        <h4>制造执行系统</h4>
                    </div>
                    <div class="alert alert-info">

                        <ul class="plan">
                            <li class="price-simple" style="font-size: 35px !important;"><strong>零件完工检测异常数据</strong></li>
                            <li><strong>&nbsp;</strong></li>
                            <li><strong>异常分析</strong></li>
                            <li><strong>查看异常</strong></li>
                        </ul>
                        <a href="#" data-type="ftl" data-data="mes.partsChecked.list" class="btn btn-warning enter-btn">点击进入</a>
                    </div>
                </div>
            </div>

           <div class="col-md-3">
                <div class="panel-warning simple-table enter-panel">
                    <div class="panel-heading">
                        <h4>项目管理系统</h4>
                    </div>
                    <div class="alert alert-warning">

                        <ul class="plan">
                            <li class="price-simple"><strong>准时率分析</strong></li>
                            <li><strong>完成情况</strong></li>
                            <li><strong>阶段分析</strong></li>
                            <li><strong>工期查看</strong></li>
                        </ul>
                        <a href="javascript:;" data-type="ftl" data-data="report.task.project" class="btn btn-warning enter-btn">点击进入</a>
                    </div>

                </div>
            </div>

        </div>
    </div>
    <!-- /. PAGE INNER  -->
</div>
</body>
<script type="text/javascript">
    $(".enter-panel").bind('click', function () {
        var type = $(this).find(".enter-btn").attr("data-type");
        var data = $(this).find(".enter-btn").attr("data-data");
        var url = '${ctx}' + "/"+data.replace(/\./g,"/");
        if (type === 'ftl'){
            url += ".z";
        }
        window.location.href = url;
    });

</script>
</html>
