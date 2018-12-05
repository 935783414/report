<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.hy.common.Help" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>智能选型</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="shortcut icon" href="/favicon.ico">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    
    
	<style type="text/css">
	.icon {
	   width: 1em; height: 1em;
	   vertical-align: -0.15em;
	   fill: currentColor;
	   overflow: hidden;
	}
	</style>
	
	
	<script type="text/javascript">
	
	</script>
	
  </head>
  <body>
  
	<!-- page集合的容器，里面放多个平行的.page，其他.page作为内联页面由路由控制展示 -->
    <div class="page-group">
        <!-- 单个page ,第一个.page默认被展示-->
        <div class="page page-current">
			
			<div class="content">
			    <div class="list-block">
			    	<ul>
			    	
				    	<li>
				        <div class="item-content">
				          <div class="item-media"></div>
				          <div class="item-inner">
				            <div class="item-title label"><a href="#">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</a></div>
				            <div class="item-input">
				              <input id="loginName" type="text" placeholder="_" class="FocusAutoSelectAll">
				            </div>
				          </div>
				        </div>
				      	</li>
				      	
				      	<!-- 
				      	<li>
				        <div class="item-content">
				          <div class="item-media"></div>
				          <div class="item-inner">
				            <div class="item-title label"><a href="#">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</a></div>
				            <div class="item-input">
				              <input id="loginPassword" type="password" class="FocusAutoSelectAll">
				            </div>
				          </div>
				        </div>
				      	</li>
			    	 	-->
			    	 	
			    	</ul>
			    </div>
			    
			    <div class="content-block">
				    <div class="row">
				      <div class="col-100"><a id="login" href="#" class="button button-big button-fill button-success">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</a></div>
				    </div>
			  	</div>
			  
			</div>
            
        </div>
        
    </div>


    
    <!-- popup, panel 等放在这里 -->
    <div class="popup"></div>
    <div class="panel-overlay"></div>
    <!-- Left Panel with Reveal effect -->
    <div class="panel panel-left panel-reveal">
        <div class="content-block">
            <p>这是一个侧栏</p>
            <p></p>
            <!-- Click on link with "close-panel" class will close panel -->
            <p><a href="#" class="close-panel">关闭</a></p>
        </div>
    </div>
    
    	
    	
    <script type='text/javascript' charset='utf-8' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js'></script>
    <script type='text/javascript' charset='utf-8' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js'></script>
    <script type='text/javascript' charset='utf-8' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js'></script>
    
    <script type='text/javascript' charset='utf-8' src='js/appCommon.js'></script>
    
    
    <script type="text/javascript" charset='utf-8'>
		
		/**
		 * 登录  ZhengWei(HY) Add 2017-10-23
		 */
		$("#login").live('click' ,function()
		{
			device.login("mes" ,$("#loginName").val() ,"");
		});
    
	</script>
    
	
	<!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>

  </body>
</html>