var Columns = {
    baseBeginColumns:[
        {title: '序号',
            formatter: function (value, row, index) {return index + 1;}
            }
      /*  ,{field: 'orderno', title: '合同号',formatter:function (value, row, index) {
               console.log("::::::"+value);
                // return '<button type="button" class="btn btn-success">'+value+'</button>';
                return '<button type="button" class="btn btn-link btn-toDetail" data-orderno="'+value+'">'+value+'</button>';
            }
    }*/
    ],
    baseEndColumns:[
          {
            field: 'orderno',    //用任务超时时长字段名称代替
            title: '任务超时时长'
           }
                    , 
           {
            field: 'reason',
            title: '原因',
            width : 300,
            editable:{
                type:"textarea",
                title:'原因',
                mode : 'inline',
                emptytext:'请填写'
            }
        }
        , {
            field: 'isExam',
            title: '考核情况',
            editable:{
                type:"select",
                source:[{value:"考核",text:"考核"},{value:"不考核",text:"不考核"},{value:"暂不考核",text:"暂不考核"}],
                title:'考核情况',
                mode : 'inline',
                emptytext:'请选择'
            }
        }
        , {
            field: 'appeal',
            title: '申诉情况',
            editable:{
                type:"textarea",
                title:'申诉',
                mode : 'inline',
                emptytext:'请填写'
            },

        }
    ],
    columns1 : [
                {field: 'orderno', title: '份合同号'},
                {field:'ordernoname',title:'合同号/任务'},
                {field: 'name', title: '任务名称'},
                {field: 'ownername', title: '部门名称'},
                {field: 'planstarttime', title: '计划开始时间'},
                {field: 'planfinishtime', title: '计划结束时间'},
                {field: 'duration', title: '任务时长'},
                {field: 'actualstarttime', title: '实际开始时间'},
                {field: 'actualfinishtime', title: '实际结束时间'},
                {field: 'actualduration', title: '实际时长'},
                {field:'overtime',title:'超时' },
                {field:'acttimeout',title:'实际超时'}                
            ],
   
       getColumns:function (unit) {
    	   var middleColumns ;
    	   middleColumns = Columns.columns1;
           var columns = [];
           columns=columns.concat(Columns.baseBeginColumns).concat(middleColumns);
           // columns=columns.concat(Columns.baseBeginColumns).concat(middleColumns).concat(Columns.baseEndColumns);
           return columns;
    }
}
