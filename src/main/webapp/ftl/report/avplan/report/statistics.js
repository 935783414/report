var Columns = {
    baseBeginColumns:[
        {title: '序号',
            formatter: function (value, row, index) {return index + 1;}
            }
            
    ],
    columns1 : [                      
                  [
              
               {
                      field: 'Username',
                      title: "姓名",
                      valign:"middle",
                      align:"center",
                      colspan: 1,
                      rowspan: 2
                 },
                 {
                      field: 'data-time',
                      title: "时段",
                      valign:"middle",
                      align:"center",
                      colspan: 1,
                      rowspan: 2
                  },
                      {
                          field: 'xxxlist',
                          title: "全勤记录",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 2
                      },
                      {
                          title: "刷卡记录",
                          valign:"middle",
                          align:"center",
                          colspan: 31,
                          rowspan: 1
                      },
                      {
                          title: "迟到记录",
                          valign:"middle",
                          align:"center",
                          colspan: 2,
                          rowspan: 1
                      },
                      {
                          title: "早退记录",
                          valign:"middle",
                          align:"center",
                          colspan: 2,
                          rowspan: 1
                      },
                      {
                          title: "旷工记录",
                          valign:"middle",
                          align:"center",
                          colspan: 2,
                          rowspan: 1
                      },
                      {
                      	  field: 'QJcishu',
                          title: "请假记录（每天以8小时计）",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 2
                      }
                  ],
                  [
{field: 'time1', title: '1'},
{field: 'time2', title: '2'},
{field: 'time3', title: '3'},
{field: 'time4', title: '4'},
{field: 'time5', title: '5'},
{field: 'time6', title: '6'},
{field: 'time7', title: '7'},
{field: 'time1', title: '8'},
{field: 'time2', title: '9'},
{field: 'time3', title: '10'},
{field: 'time4', title: '11'},
{field: 'time5', title: '12'},
{field: 'time6', title: '13'},
{field: 'time7', title: '14'},
{field: 'time1', title: '15'},
{field: 'time2', title: '16'},
{field: 'time3', title: '17'},
{field: 'time4', title: '18'},
{field: 'time5', title: '19'},
{field: 'time6', title: '20'},
{field: 'time7', title: '21'},
{field: 'time1', title: '22'},
{field: 'time2', title: '23'},
{field: 'time3', title: '24'},
{field: 'time4', title: '25'},
{field: 'time5', title: '26'},
{field: 'time6', title: '27'},
{field: 'time7', title: '28'},
{field: 'time7', title: '29'},
{field: 'time7', title: '30'},
{field: 'time7', title: '31',
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
{field: 'mintime', title: '分钟'},
{field: 'cishu', title: '次数',
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
{field: 'mintime', title: '分钟'},
{field: 'cishu', title: '次数',
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
{field: 'mintime', title: '分钟'},
{field: 'cishu', title: '次数',
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


