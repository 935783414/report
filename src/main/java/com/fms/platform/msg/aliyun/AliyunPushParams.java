package com.fms.platform.msg.aliyun;

public class AliyunPushParams {
	private String title; // 消息推送标题
	private String content; // 消息推送内容
	private String summary; // 通知推送摘要
	private String jsonParms;  // 设定android类型设备通知的扩展属性"{\"k1\":\"andriod\",\"k2\":\"v2\"}"
	private String target; //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
	private String targetValue;  //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
	private String androidOpenType; // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url 4 : 无跳转逻辑
	private String urlOrActivity;// Android收到推送后打开对应的url,仅仅当androidOpenType=3有效 activity 从jar包开始
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getJsonParms() {
		return jsonParms;
	}
	public void setJsonParms(String jsonParms) {
		this.jsonParms = jsonParms;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	public String getAndroidOpenType() {
		return androidOpenType;
	}
	public void setAndroidOpenType(String androidOpenType) {
		this.androidOpenType = androidOpenType;
	}
	public String getUrlOrActivity() {
		return urlOrActivity;
	}
	public void setUrlOrActivity(String urlOrActivity) {
		this.urlOrActivity = urlOrActivity;
	}

	
}
