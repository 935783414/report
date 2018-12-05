var Columns = {
    baseBeginColumns:[
        {title: '序号',
            formatter: function (value, row, index) {return index + 1;}
            }
            
    ],
    columns1 : [                      
                  [                          
				   {

						  title: "序号",
						  valign:"middle",
						  align:"center",
						  colspan: 1,
						  rowspan: 1
					 },
                      {

                      	  title: "姓名",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {

                      	  title: "性别",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {

                      	  title: "年龄",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {

                      	  title: "员工工号",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {

                      	  title: "所属部门",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {
                          title: "卡类别",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {
                          title: "联系电话",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      },
                      {
                          title: "家庭住址",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 1
                      }
                  ],
              ],

         getColumns:function () {
    	   var middleColumns ;
    	   middleColumns = Columns.columns1;
           var columns = [];
           columns=columns.concat(middleColumns);
           return columns;
    }
}
