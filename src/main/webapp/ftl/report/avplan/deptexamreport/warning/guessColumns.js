var Columns = {
    baseBeginColumns: [
        {
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }
        , {
            field: 'orderno', title: '份合同号', formatter: function (value, row, index) {
                // console.log("::::::" + value);
                // return '<button type="button" class="btn btn-success">'+value+'</button>';
                return '<button type="button" class="btn btn-link btn-toDetail" data-innerid="' + row.innerid + '">' + value + '</button>';
            }
        }
    ],
    baseEndColumns: [
        {
            field: 'finish_percent',
            title: '任务完成度'
        }
        ,
        {
            class : 'longColumns ellipsis',
            field: 'reason',
            title: '情况'
        }
        , {
            field: 'warn_level',
            title: '预警等级'
        }
        , {
            class: 'longColumns',
            field: 'appeal',
            title: '异常反馈',
            width: 300

        }
    ],
    columns1: [
        {field: 'specailproductcode', title: '特品单号'},
        {field: 'suborderno', title: '合同号'},
        {field: 'productclass', title: '特品型号'},
        {field: 'producttype', title: '产品型号'},
        {field: 'departname', title: '分配部门'},
        {field: 'planstarttime', title: '计划开始时间'},
        {field: 'planfinishtime', title: '计划结束时间'},
        {field: 'flag', title: '是否超期'},
        {field: 'status', title: '任务状态'}
    ],
    columns2: [
        {field: 'gytype', title: '工艺分类'},
        {field: 'planno', title: '计划月次'},
        {field: 'part_plan_no', title: '路线单号'},

        {field: 'itemcode', title: '图号'},
        {field: 'itemname', title: '铸造名称'},
        {field: 'cl', title: '材质'},
        {field: 'planqty', title: '计划数'},
        {field: 'zjtcs', title: '投产数'},

        {field: 'zjwgs', title: '完工数'},
        {field: 'finishqty', title: '入库数'},
        {field: 'planstarttime', title: '计划开始时间'},
        {field: 'planfinishtime', title: '计划结束时间'},

        {field: 'zstime', title: '铸件计划首次下达日期'},
        {field: 'zftime', title: '铸件计划最后下达日期'},
        {field: 'znum', title: '铸件下达数量'},
        {field: 'single', title: '单重'},

        {field: 'status', title: '任务状态'}
    ],
    columns3: [
        {field: 'putype', title: '采购类型'},
        {field: 'planstarttime', title: '计划开始时间'},
        {field: 'planfinishtime', title: '计划结束时间'},
        {field: 'planpercent', title: '计划（%）'},
        {field: 'orderpercent', title: '订单（%）'},
        {field: 'receivepercent', title: '到货（%）'},
        {field: 'checkpercent', title: '检验（%）'},
        {field: 'inpercent', title: '入库（%）'},
        {field: 'status', title: '任务状态'}
    ],
    columns4: [
        {field: 'suborderno', title: '合同号'},
        {field: 'stime', title: '机加计划开始时间'},
        {field: 'ftime', title: '机加计划结束时间'},
        {field: 'planstarttime', title: '装配计划开始时间'},
        {field: 'planfinishtime', title: '装配计划结束时间'},
        {field: 'productclass', title: '产品型号'},
        {field: 'planno', title: '计划月次'},
        {field: 'zxjg', title: '执行机构'},
        {field: 'fj', title: '附件'},
        {field: 'required_qty', title: '需求数量'},
        {field: 'in_inv_count', title: '入库数量'},
        {field: 'pt_count', title: '配套数量'},
        {field: 'last_pt_date', title: '配套时间'},
        {field: 'flag', title: '任务状态'}
    ],
    getColumns: function (unit) {
        var middleColumns;
        if (unit === "研发部" || unit === "工艺部") {
            middleColumns = Columns.columns1;
        } else if (unit === "砂铸部" || unit === "精铸部") {
            middleColumns = Columns.columns2;
        } else if (unit === "采购部") {
            middleColumns = Columns.columns3;
        } else if (unit.indexOf("事业部") > -1) {
            middleColumns = Columns.columns4;
        }
        var columns = [];
        columns = columns.concat(Columns.baseBeginColumns).concat(middleColumns).concat(Columns.baseEndColumns);
        return columns;
    },
    getFixedColumnNumber: function (unit) {
        var fixNumber =2;
        if (unit === "研发部" || unit === "工艺部") {
            fixNumber = 5;
        } else if (unit === "砂铸部" || unit === "精铸部") {
            fixNumber = 5;
        } else if (unit === "采购部") {
            fixNumber = 3;
        } else if (unit.indexOf("事业部") > -1) {
            fixNumber = 3
        }
        return fixNumber;
    }
}