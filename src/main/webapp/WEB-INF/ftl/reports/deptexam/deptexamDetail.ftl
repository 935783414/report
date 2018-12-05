<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html xmlns="http://java.sun.com/jsf/facelets">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>日考核报表详情</title>

    <script type="text/javascript" src="${ctx}/ftl/common/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/ftl/common/jquery.form.js"></script>
<#--bootstrap-->
<#--<link rel="stylesheet" href="${ctx}/ftl/common/basic.css"/>-->
    <link rel="stylesheet" href="${ctx}/css/basic.css"/>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.css">
    <script type="text/javascript" src="${ctx}/ftl/common/bootstrap.js"></script>
<#--bootstrap table-->
    <script type="text/javascript" src="${ctx}/ftl/common/table/bootstrap-table.js"></script>
    <link href="${ctx}/ftl/common/table/bootstrap-table.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/ftl/common/table/locale/bootstrap-table-zh-CN.js"></script>


    <include></include>
    <style type="text/css">
        .btn-anchor{
            margin: 2px;
            padding:3px;
        }
        .btn-icon-img{
            width: 32px;
            height: 32px;
        }
    </style>
</head>
<body>

<div id="page-wrapper" style="padding-top: 10px;">

    <div id="page-inner">
    <#--toolbar-->
        <div id="toolbar"  class="navbar-fixed-bottom " style="margin-bottom: 10%;margin-left: 80%;width: 55px">
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="总体情况" id="total">
                <#--<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/total.svg" class="btn-icon-img" />
            </a>
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="设计/工艺" id="design">
                <#--<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/design.svg" class="btn-icon-img" />
            </a>
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="采购情况" id="purchase">
                <#--<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/purchase.svg" class="btn-icon-img" />
            </a>
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="铸造情况" id="casting">
                <#--<span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/casting.svg" class="btn-icon-img" />
            </a>
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="零件精加工" id="maching">
                <#--<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/maching.svg" class="btn-icon-img" />
            </a>
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="成套/附件连接" id="packing">
                <#--<span class="glyphicon glyphicon-compressed" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/packaging.svg" class="btn-icon-img" />
            </a>
            <a role="button" type="button"  class="btn btn-default btn-lg btn-anchor" data-toggle="tooltip" data-placement="right" title="退出详情" id="backBtn">
                <#--<span style="transform: scale(-1,1);" class="glyphicon glyphicon-log-out" aria-hidden="true"></span>-->
                <img src="${ctx}/ftl/icon/exitBtn.svg" class="btn-icon-img" />
            </a>
        </div>
    <#--end toolbar-->

    <#--<div style="margin-left: 500px;margin-right: 500px;padding-top: 10px;">-->
        <div class="center-block" style="width: 50%;margin-top: 20px">
        <#if (result?size<=0) >
            <div align="center" style="margin-top: 20%">
                <label>日考核报表详情为空</label>
            </div>
        </#if>
            <table role="table">
                <div class="row">
                <#assign mapIndex = 0>
                <#list result?keys as panel>
                    <#assign mapIndex = mapIndex +1 >
                    <#if result[panel]?? && (result[panel]?size>0) >
                    <#--<div class="col-md-6 col-sm-6 col-xs-12">-->
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 style="font-weight: bold;margin: 3px;margin-left: 10px;" id="panel_${mapIndex}" >${panel}<input type="hidden" name="panelName" class="panelName" value="${panel}"></h3>

                            </div>
                            <div class="panel-body">
                                <#assign  itemCount = 1/>
                            <#list result[panel]?keys as item>
                                <#if result[panel][item]?? >
                                    <#assign  map=result[panel][item]/>
                                    <div class="row" style="background-color: #90C9FF;margin-top: -15px;margin-bottom: 10px">
                                        <h4 style="font-weight: bold;padding-left: 30px;">${itemCount}：${item}</h4>
                                        <#assign  itemCount = itemCount+1 />
                                    </div>
                                    <#list map?keys as field>
                                        <#if field == '_partStatus'  >
                                            <div class="row">
                                                <div class="col-md-3 text-right"><span style="font-size: 14px" >进度：</span></div>
                                                <div class="col-md-9">
                                                    <#if map[field]?? >
                                                        <#assign  statusList=map[field]/>
                                                        <#list  statusList as status>

                                                            <button class="btn btn-${status.type!}" style="padding: 2px 20px;cursor: default;font-size: 75%;" >${status.title!}</button>
                                                        </#list>
                                                    </#if>
                                                </div>
                                            </div>
                                        <#else >
                                            <div class="row">
                                                <#-- Default panel contents -->
                                                <div class="col-md-3 text-right"><span style="font-size: 14px" >${field}：</span></div>
                                                <div class="col-md-9">
                                                ${map[field]!}
                                                </div>
                                            </div>
                                        </#if>
                                    </#list>
                                    <hr style="border-top: 1px solid #cbcbcb; "/>
                                </#if>
                            </#list>
                            </div>
                        </div>
                    <#--</div>-->
                    </#if>
                </#list>
                </div>
            </table>
        </div>

    </div>
</div>
<#--<script type="text/javascript" src="${ctx}/ftl/report/avplan/deptexamreport/exam/columns.js"></script>-->
<script type="text/javascript">

    $("#backBtn").click(function () {
        // window.history.go(-1);
        window.close();
    });
    $(".btn-anchor").hide();

        var panelInput = $(".panelName").toArray();
        for(var i=0;i<panelInput.length;i++){
            var panel_name = $(panelInput[i]).val();
            var panel_id = $(".panelName[value='"+panel_name+"']").parent().attr("id");
            initBtn(panel_name,panel_id,i);
            console.log(panel_name + "->"+panel_id);

        }
    function initBtn(panel_name,panel_id,i){
        if(panel_name.indexOf("总体情况")>-1){
            $("#total").show();
            console.log("init:"+i);
            $("#total").click(function (event) {
                var t = $('#' + panel_id).offset().top-25;
                $(window).scrollTop(t);
            })
        };
        if(panel_name.indexOf("设计")>-1){
            $("#design").show();
            console.log("init:"+i);
            $("#design").click(function (event) {
                var t = $('#' + panel_id).offset().top-25;
                $(window).scrollTop(t);
            })
        };
        if(panel_name.indexOf("采购")>-1){
            $("#purchase").show();
            console.log("init:"+i);
            $("#purchase").click(function (event) {
                var t = $('#' + panel_id).offset().top-25;
                $(window).scrollTop(t);
            })
        };
        if(panel_name.indexOf("铸造")>-1 ){
            $("#casting").show();
            console.log("init:"+i);
            $("#casting").click(function (event) {
                var t = $('#' + panel_id).offset().top-25;
                $(window).scrollTop(t);
            })
        };
        if(panel_name.indexOf("零件精加工")>-1 ){
            $("#maching").show();
            console.log("init:"+i);
            $("#maching").click(function (event) {
                var t = $('#' + panel_id).offset().top-25;
                $(window).scrollTop(t);
            })
        };
        if(panel_name.indexOf("成套")>-1 || panel_name.valueOf("附件连接")>-1 ){
            $("#packing").show();
            $("#packing").click(function (event) {
                var t = $('#' + panel_id).offset().top-25;
                $(window).scrollTop(t);
            })
        };
    }

    $("#backBtn").show();
    $(function () { $("[data-toggle='tooltip']").tooltip(); });

</script>
</body>
</html>