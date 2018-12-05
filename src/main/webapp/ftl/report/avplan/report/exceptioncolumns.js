var Columns = {
    columns1 : [
        [
            {
                title: "序号",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2,
                formatter: function (value, row, index) {return index + 1;}
            },
            {
                field: 'basepername',
                title: "姓名",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2
            },
            {
                field: 'dept',
                title: "部门",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2
            },
            {
                field: 'baseperid',
                title: "员工工号",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2
            },
            {
                field: 'baserolename',
                title: "员工类别",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2
            },
            {
                field: 'cdate',
                title: "考勤时间",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2
            },
            {
                title: "异常出入次数",
                valign:"middle",
                align:"center",
                colspan: 2,
                rowspan: 1
            },
            {
                title: "异常出入时段",
                valign:"middle",
                align:"center",
                colspan: 2,
                rowspan: 1
            },
            {
                field: 'halftime',
                title: "异常出入时长(分钟)",
                valign:"middle",
                align:"center",
                colspan: 1,
                rowspan: 2
            }
        ],
        [
            {field: 'outcnt', title: '出'},
            {field: 'incnt', title: '入'},
            {field: 'halfouttime', title: '出去时间'},
            {field: 'halfintime', title: '返回时间'}
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


