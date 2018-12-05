package com.fms.templateSys.service.impl;

import org.hy.common.Return;
import org.hy.common.xml.annotation.Xjava;

import com.fms.platform.common.BaseService;
import com.fms.platform.common.util.PageUtil;
import com.fms.templateSys.dao.ITemplateDAO;
import com.fms.templateSys.dto.TemplateInDto;
import com.fms.templateSys.dto.TemplateOutDto;
import com.fms.templateSys.service.ITemplateService;





/**
 * ${template}服务层接口
 *
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0  
 */
@Xjava(id="${system}TemplateService")
public class TemplateServiceImpl extends BaseService implements ITemplateService
{
    
    @Xjava
    private ITemplateDAO                           $systemTemplateDao;
    
    
    




	/**
     * ${template}业务列表
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       inDto
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<TemplateOutDto> queryTemplateList(TemplateInDto templateInDto)
    {
        // 对外接口返回消息主体的封装
    	TemplateOutDto templateOutDto = new TemplateOutDto(); // 实例化对外dto
    	templateOutDto.setDatas(this.$systemTemplateDao.queryTemplateList(templateInDto)); // 检索
    	//templateOutDto.setData(templateOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<TemplateOutDto> v_ExecRet = new Return<TemplateOutDto>(true);
        return v_ExecRet.paramObj(templateOutDto);
        
    }



    /**
     * 用户信息列表,带分页查询
     *
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return            Return.paramStr  异常时返回错误编号
     */
    public Return<TemplateOutDto> queryTemplateListByPage(TemplateInDto templateInDto)
    {
        // 对外接口返回消息主体的封装
    	TemplateOutDto templateOutDto = new TemplateOutDto(); // 实例化对外dto
    	PageUtil.PackInDto(templateInDto.getCurrentPage(), templateInDto.getLimit(), templateInDto);
    	Integer recordCount = this.$systemTemplateDao.queryTemplateListCount(templateInDto);
    	// 分页信息放入输出参数中
    	PageUtil.PackOutDto(templateOutDto, templateInDto,recordCount);
    	
    	templateOutDto.setDatas(this.$systemTemplateDao.queryTemplateListByPage(templateInDto)); // 列表结果集返回
    	
        Return<TemplateOutDto> v_ExecRet = new Return<TemplateOutDto>(true);
        return v_ExecRet.paramObj(templateOutDto);
        
    }


	/**
     * ${template}业务单个对象查询
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       inDto
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<TemplateOutDto> queryTemplate(TemplateInDto templateInDto)
    {
        // 对外接口返回消息主体的封装
    	TemplateOutDto templateOutDto = new TemplateOutDto(); // 实例化对外dto
    	templateOutDto.setData(this.$systemTemplateDao.queryTemplate(templateInDto)); // 检索
    	//templateOutDto.setData(templateOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<TemplateOutDto> v_ExecRet = new Return<TemplateOutDto>(true);
        return v_ExecRet.paramObj(templateOutDto);
        
    }
    
    
    /**
     * ${template}单表插入
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数
     */
	public Return<TemplateOutDto> saveTemplate(TemplateInDto templateInDto) {
		// 对外接口返回消息主体的封装
    	TemplateOutDto templateOutDto = new TemplateOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.$systemTemplateDao.saveTemplate(templateInDto);
    	//templateOutDto.setData(templateOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<TemplateOutDto> v_ExecRet = new Return<TemplateOutDto>(true);
        // 行数等于1的时候，插入成功
    	v_ExecRet.set(lineCount == 1);
        return v_ExecRet.paramObj(templateOutDto);
	};
	
	
	
    /**
     *  ${template}单表更新
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数 
     */
	public Return<TemplateOutDto> updateTemplate(TemplateInDto templateInDto) {
		// 对外接口返回消息主体的封装
    	TemplateOutDto templateOutDto = new TemplateOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.$systemTemplateDao.updateTemplate(templateInDto);
    	//templateOutDto.setData(templateOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<TemplateOutDto> v_ExecRet = new Return<TemplateOutDto>(true);
        // 行数>0的时候，更新成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(templateOutDto);
	};
	
	
    /**
     *  ${template}单表删除
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数   
     */
	public Return<TemplateOutDto> deleteTemplate(TemplateInDto templateInDto) {
		// 对外接口返回消息主体的封装
    	TemplateOutDto templateOutDto = new TemplateOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.$systemTemplateDao.deleteTemplate(templateInDto);
    	//templateOutDto.setData(templateOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<TemplateOutDto> v_ExecRet = new Return<TemplateOutDto>(true);
        // 行数>0的时候，删除成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(templateOutDto);
	};
	
	
	
	public ITemplateDAO get$systemTemplateDao() {
		return $systemTemplateDao;
	}



	public void set$systemTemplateDao(ITemplateDAO $systemTemplateDao) {
		this.$systemTemplateDao = $systemTemplateDao;
	}
    
    


}
