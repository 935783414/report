package com.fms.platform.common;





/**
 * 基础数据模型类
 * 
 * @author      Zdf
 * @createDate  2015-2-18
 * @version     v1.0  
 */
public class BaseOutDto extends BaseEntity
{
    
    private static final long serialVersionUID = -3258227868581112779L;
    

    /**  每页显示的条数 */
    private Integer limit;
    
    /**  总共的条数 */
    private Integer recordCount;
    
    /**  当前页 */
    private Integer currentPage;
    
    /**  从第几条开始（索引） */
    private Integer start;
    
    /**  总页数 */
    private Integer pageCount;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
    
    
    
   
    
}
