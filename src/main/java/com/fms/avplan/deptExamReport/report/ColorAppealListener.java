package com.fms.avplan.deptExamReport.report;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
public class ColorAppealListener implements ValueListener
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
     * @author      dirful.zou
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
    public String getValue(RTemplate i_RTemplate ,Cell i_TemplateCell ,Cell i_DataCell ,RWorkbook i_DataWorkbook ,RSystemValue i_RSystemValue ,Object i_Datas ,Object i_Value) {
        CellStyle v_NewCellStyle;


        if(i_Datas==null)return "";
        DeptReport report = (DeptReport)i_Datas;
        if ("暂不考核".equals(report.getIsExam())) {
            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy(i_RTemplate.getName() + "1", i_DataCell, i_RTemplate);
            v_NewCellStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        } else if ("考核".equals(report.getIsExam())) {
            v_NewCellStyle = i_DataWorkbook.getCellStyleByCopy(i_RTemplate.getName() + "2", i_DataCell, i_RTemplate);
            //生成一个字体
            XSSFFont font = (XSSFFont) i_DataWorkbook.getWorkbook().createFont();
            font.setColor(Font.COLOR_RED);//HSSFColor.VIOLET.index //字体颜色
            //把字体应用到当前的样式
            v_NewCellStyle.setFont(font);
            v_NewCellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        } else {
            if (i_Value == null || "null".equalsIgnoreCase((String)i_Value)) {
                return "";
            }
            return i_Value.toString();
        }

        v_NewCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        i_DataCell.setCellStyle(v_NewCellStyle);
        for (int i = 0; i <= i_DataCell.getColumnIndex(); i++) {
            i_DataCell.getRow().getCell(i).setCellStyle(v_NewCellStyle);
        }
        if (i_Value == null || "null".equalsIgnoreCase((String)i_Value)) {
            return "";
        }
//        i_DataCell.getRow().getCell(i_DataCell.getColumnIndex()).setCellStyle(v_NewCellStyle);
        return i_Value.toString();
    }

}
