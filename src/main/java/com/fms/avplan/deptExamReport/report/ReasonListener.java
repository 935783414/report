package com.fms.avplan.deptExamReport.report;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.hy.common.report.bean.RSystemValue;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.event.ValueListener;


/**
 * 单元格颜色的监听器
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-09-20
 * @version     v1.0
 */
public class ReasonListener implements ValueListener
{

    private String  valueName;



    /**
     * 变量名称
     *
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @return
     */
    public String getValueName()
    {
        return this.valueName;
    }



    public void setValueName(String i_ValueName)
    {
        this.valueName = i_ValueName;
    }



    /**
     * 对变量名称反射出来的值进行加工处理
     *
     * @author      ZhengWei(HY)
     * @createDate  2017-06-29
     * @version     v1.0
     *
     * @param i_RTemplate     模板
     * @param i_TemplateCell  模板单元格对象
     * @param i_DataCell      数据单元格对象
     * @param i_DataWorkbook  数据工作薄对象
     * @param i_RSystemValue  系统变量信息
     * @param i_Datas         本行对应的数据
     * @param i_Value         反射出来的变量名称对应的值
     * @return
     */
    public String getValue(RTemplate i_RTemplate ,Cell i_TemplateCell ,Cell i_DataCell ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Object i_Datas ,Object i_Value)
    {
//        CellStyle v_NewCellStyle = i_DataCell.getCellStyle();
//
//        if ( "无".equals(i_Value.toString()) )
//        {
//            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy("normalVale" ,i_DataCell ,i_RTemplate);
//            v_NewCellStyle.setFillForegroundColor(IndexedColors.LIME.index);
//        }
//        else
//        {
//            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy("excepionValue" ,i_DataCell ,i_RTemplate);
//            v_NewCellStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
//        }
//
//        v_NewCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//        i_DataCell.setCellStyle(v_NewCellStyle);
//        i_DataCell.getRow().getCell(0).setCellStyle(v_NewCellStyle);
//        i_DataCell.getRow().getCell(1).setCellStyle(v_NewCellStyle);
//
        if ( i_Value == null )
        {
            return "";
        }
        String[] tags = {"<b>","</b>","<strong>","</strong>","</i>","</i>","<hr/>","<br/>"};
        String[] replaces = {"","","","","","","\n","\n"};
        i_Value = StringUtils.replaceEachRepeatedly(i_Value.toString(),tags,replaces);
        return i_Value.toString();
    }

}
