	function getQueryString(name) 
	{
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	} 
	
	
	
	/**
	 * 保存参数  ZhengWei(HY) Add 2017-08-11
	 * 
	 * @param i_NameSpace
	 * @returns
	 */
	function saveParams(i_NameSpace)
	{
		$("input").each(function(i)
		{
			localStorage.setItem(i_NameSpace + $(this)[0].id ,$(this).val());
		});
		
		$("select").each(function(i)
		{
			localStorage.setItem(i_NameSpace + $(this)[0].id ,$(this).val());
		});
		
		localStorage.setItem(i_NameSpace ,"1");
	}
	
	
	
	/**
	 * 清空参数  ZhengWei(HY) Add 2017-08-11
	 * 
	 * @param i_NameSpace
	 * @returns
	 */
	function clearParams(i_NameSpace)
	{
		$("input").each(function(i)
		{
			localStorage.clear(i_NameSpace + $(this)[0].id);
		});
		
		$("select").each(function(i)
		{
			localStorage.clear(i_NameSpace + $(this)[0].id);
		});
		
		localStorage.clear(i_NameSpace ,"1");
	}
	
	
	
	/**
	 * 恢复参数  ZhengWei(HY) Add 2017-08-11
	 * 
	 * @param i_NameSpace
	 * @returns
	 */
	function renewParams(i_NameSpace)
	{
		$("input").each(function(i)
		{
			var v_Value = localStorage.getItem(i_NameSpace + $(this)[0].id);
			
			if ( v_Value != null )
			{
				$(this).val(v_Value);
			}
		});
		
		$("select").each(function(i)
		{
			var v_Value = localStorage.getItem(i_NameSpace + $(this)[0].id);
			
			if ( v_Value != null )
			{
				$(this).val(v_Value);
			}
		});
	}
	
	
	
	/**
	 * 恢复参数(必须是保存过才恢复，逻辑上:数据只有一次恢复机会)  ZhengWei(HY) Add 2017-08-11
	 * 
	 * @param i_NameSpace
	 * @returns
	 */
	function renewParamsBySave(i_NameSpace)
	{
		if ( localStorage.getItem(i_NameSpace) == "1" )
		{
			renewParams(i_NameSpace)
			
			localStorage.setItem(i_NameSpace ,"0");
		}
	}
	
	
	
	/**
	 * 得到焦点自动选择全部文字  ZhengWei(HY) Add 2017-09-22
	 * 
	 * @param i_NameSpace
	 * @returns
	 */
	$(".FocusAutoSelectAll").live("focus" ,function()
	{
		var v_Input = this;
		setTimeout(function() {
			 v_Input.select();
		} ,50);
	});
	
	
	
	$("input").each(function(i)
	{
		if ( $(this)[0].id != '' && ($(this)[0].name == '' || $(this)[0].name == null) )
		{
			$(this)[0].name = $(this)[0].id;
		}
	});
	
	
	
	$("select").each(function(i)
	{
		if ( $(this)[0].id != '' && ($(this)[0].name == '' || $(this)[0].name == null) )
		{
			$(this)[0].name = $(this)[0].id;
		}
	});
	

	
	if ( getQueryString("imei") == '' || getQueryString("imei") == null )
	{
		$("body").empty();
	}
