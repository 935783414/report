var Columns = {
    baseBeginColumns:[
        {title: '序号',
            formatter: function (value, row, index) {return index + 1;}
            }
            
    ],
    columns1 : [                      
                  [                          
               {
                      field: 'xmorderno',
                      title: "份合同号",  
                      valign:"middle",
                      align:"center",
                      colspan: 1,
                      rowspan: 2
                 },
                /* {
                     field: 'xmname',
                     title: "项目名称",
                     valign:"middle",
                     align:"center",
                     colspan: 1,
                     rowspan: 2
                }, */        
                      {
                          title: "项目",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "销售商务阶段",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "项目计划编制",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "产品设计",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "工艺设计",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "商品计划下达",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "零件计划下达",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "外购附件",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "标准件",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "砂铸",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "精铸",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "零件精加工",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "装配入库",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      },
                      {
                          title: "包装发运阶段",
                          valign:"middle",
                          align:"center",
                          colspan: 7,
                          rowspan: 1
                      }                    
                  ],
                  [ 
{field: 'xmplanstarttime', title: '计划开始时间'},
{field: 'xmplanfinishtime', title: '计划结束时间'},
{field: 'xmduration', title: '工期'},
{field: 'xmactualstarttime', title: '实际开始时间'},
{field: 'xmactualfinishtime', title: '实际结束时间'},
{field: 'xmactualduration', title: '实际工期'},
{field: 'xmoverdate', title: '提前 / 超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value < 0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }
},
{field: 'xsplanstarttime', title: '计划开始时间'},
{field: 'xsplanfinishtime', title: '计划结束时间'},
{field: 'xsduration', title: '工期'},
{field: 'xsactualstarttime', title: '实际开始时间'},
{field: 'xsactualfinishtime', title: '实际结束时间'},
{field: 'xsactualduration', title: '实际工期'},
{field: 'xsoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'xbplanstarttime', title: '计划开始时间'},
{field: 'xbplanfinishtime', title: '计划结束时间'},
{field: 'xbduration', title: '工期'},
{field: 'xbactualstarttime', title: '实际开始时间'},
{field: 'xbactualfinishtime', title: '实际结束时间'},
{field: 'xbactualduration', title: '实际工期'},
{field: 'xboverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'cpplanstarttime', title: '计划开始时间'},
{field: 'cpplanfinishtime', title: '计划结束时间'},
{field: 'cpduration', title: '工期'},
{field: 'cpactualstarttime', title: '实际开始时间'},
{field: 'cpactualfinishtime', title: '实际结束时间'},
{field: 'cpactualduration', title: '实际工期'},
{field: 'cpoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'gyplanstarttime', title: '计划开始时间'},
{field: 'gyplanfinishtime', title: '计划结束时间'},
{field: 'gyduration', title: '工期'},
{field: 'gyactualstarttime', title: '实际开始时间'},
{field: 'gyactualfinishtime', title: '实际结束时间'},
{field: 'gyactualduration', title: '实际工期'},
{field: 'gyoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'sxplanstarttime', title: '计划开始时间'},
{field: 'sxplanfinishtime', title: '计划结束时间'},
{field: 'sxduration', title: '工期'},
{field: 'sxactualstarttime', title: '实际开始时间'},
{field: 'sxactualfinishtime', title: '实际结束时间'},
{field: 'sxactualduration', title: '实际工期'},
{field: 'sxoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'lxplanstarttime', title: '计划开始时间'},
{field: 'lxplanfinishtime', title: '计划结束时间'},
{field: 'lxduration', title: '工期'},
{field: 'lxactualstarttime', title: '实际开始时间'},
{field: 'lxactualfinishtime', title: '实际结束时间'},
{field: 'lxactualduration', title: '实际工期'},
{field: 'lxoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'wjplanstarttime', title: '计划开始时间'},
{field: 'wjplanfinishtime', title: '计划结束时间'},
{field: 'wjduration', title: '工期'},
{field: 'wjactualstarttime', title: '实际开始时间'},
{field: 'wjactualfinishtime', title: '实际结束时间'},
{field: 'wjactualduration', title: '实际工期'},
{field: 'wjoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'bjplanstarttime', title: '计划开始时间'},
{field: 'bjplanfinishtime', title: '计划结束时间'},
{field: 'bjduration', title: '工期'},
{field: 'bjactualstarttime', title: '实际开始时间'},
{field: 'bjactualfinishtime', title: '实际结束时间'},
{field: 'bjactualduration', title: '实际工期'},
{field: 'bjoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'szplanstarttime', title: '计划开始时间'},
{field: 'szplanfinishtime', title: '计划结束时间'},
{field: 'szduration', title: '工期'},
{field: 'szactualstarttime', title: '实际开始时间'},
{field: 'szactualfinishtime', title: '实际结束时间'},
{field: 'szactualduration', title: '实际工期'},
{field: 'szoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'jzplanstarttime', title: '计划开始时间'},
{field: 'jzplanfinishtime', title: '计划结束时间'},
{field: 'jzduration', title: '工期'},
{field: 'jzactualstarttime', title: '实际开始时间'},
{field: 'jzactualfinishtime', title: '实际结束时间'},
{field: 'jzactualduration', title: '实际工期'},
{field: 'jzoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'ljplanstarttime', title: '计划开始时间'},
{field: 'ljplanfinishtime', title: '计划结束时间'},
{field: 'ljduration', title: '工期'},
{field: 'ljactualstarttime', title: '实际开始时间'},
{field: 'ljactualfinishtime', title: '实际结束时间'},
{field: 'ljactualduration', title: '实际工期'},
{field: 'ljoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'zpplanstarttime', title: '计划开始时间'},
{field: 'zpplanfinishtime', title: '计划结束时间'},
{field: 'zpduration', title: '工期'},
{field: 'zpactualstarttime', title: '实际开始时间'},
{field: 'zpactualfinishtime', title: '实际结束时间'},
{field: 'zpactualduration', title: '实际工期'},
{field: 'zpoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }},
{field: 'bgplanstarttime', title: '计划开始时间'},
{field: 'bgplanfinishtime', title: '计划结束时间'},
{field: 'bgduration', title: '工期'},
{field: 'bgactualstarttime', title: '实际开始时间'},
{field: 'bgactualfinishtime', title: '实际结束时间'},
{field: 'bgactualduration', title: '实际工期'},
{field: 'bgoverdate', title: '提前/超时',
	formatter: function(value,row,index) {
    	//通过判断单元格的值，来格式化单元格，返回的值即为格式化后包含的元素
    	var a = "";
        	if(value <0) {
        		var a = '<span class="label label-success">'+'提前'+value*(-1)+'天'+'</span>';
        	}else if(value==0){
        		var a = '<span class="label label-info">'+'正常'+'</span>';	
        	}
        	else{
        		var a = '<span class="label label-danger">'+'超时'+value+'天'+'</span>';
        	}
			return a;
    }}
                  ]
              ],
         getColumns:function () {
    	   var middleColumns ;
    	   middleColumns = Columns.columns1;
           var columns = [];
           columns=columns.concat(middleColumns);
           return columns;
    }
}
