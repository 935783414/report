var Columns = {

    columns1 : [
               {  field: 'rowtip', title: "行标签"},
		       {  field: 'ordercount', title: "份合同项数"},
		       {  field: 'shopcount', title: "订货台数" },
		       {  field: 'maxdates',title: "最大超期/提前天数" },
		       {  field: 'mindates', title: "最小超期/提前天数"},
		       {  field: 'avgdates',title: "平均超期/提前天数"},
               {  field: 'maxactdates', title: "最大实际工期(天)" },
               {  field: 'minactdates',title: "最小实际工期(天)"},
               {  field: 'avgactdates', title: "平均实际工期(天)"}
              ],
         getColumns:function(){
    	   var middleColumns ;
    	   middleColumns = Columns.columns1;
           var columns = [];
           columns=columns.concat(middleColumns);
           return columns;
    }
}
