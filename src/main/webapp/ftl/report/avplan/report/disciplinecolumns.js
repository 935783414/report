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
                          rowspan: 2
                      },
                      {
                          title: "部门",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 2
                      },
                       {
                              title: "姓名",
                              valign:"middle",
                              align:"center",
                              colspan: 1,
                              rowspan: 2
                         },
                      {
                          title: "员工工号",
                          valign:"middle",
                          align:"center",
                          colspan: 1,
                          rowspan: 2
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
                      }
                  ],
                  [
                      {field: 'xmplanstarttime',align:"center", title: '次数'},
                      {field: 'xmplanfinishtime',align:"center", title: '分钟'},
                      {field: 'xmplanstarttime',align:"center", title: '次数'},
                      {field: 'xmplanfinishtime',align:"center", title: '分钟'},
                      {field: 'xmplanstarttime',align:"center", title: '次数'},
                      {field: 'xmplanfinishtime',align:"center", title: '天数'},
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
