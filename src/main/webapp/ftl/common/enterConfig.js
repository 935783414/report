var EnterConfig = {
    getLocation : function (data) {
        var ary =data.split(".");
        var data = _enterConfig;
        for(var i=0;i<ary.length;i++){
            data = data[ary[i]];
        }
        console.log(data);
        return data;
    }
}
var _enterConfig = {
    report:{
        deptexam : '/report/deptexam.z'
    },
    analyses : "/analyses"
}