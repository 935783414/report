package com.fms.platform.msg.aliyun;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20150827.PushRequest;
import com.aliyuncs.push.model.v20150827.PushResponse;

public class AliyunPush {
	// 默认杭州 网关地域
	private static final String REGION_HANGZHOU = "cn-hangzhou";

	// 移动推送阿里巴巴注册平台的appKey
	private static Long appKey = Long.valueOf(24543420);
	// Access Key管理 accessKeyId
	private static String accessKeyId = "LTAIsQCbNHG013Gn";
	// Access Key管理 accessKeySecret
	private static String accessKeySecret = "0T1190nMFtQOBzRLFvjbPvDgenbhdq" ;

	/**
	 * 利用阿里巴巴移动推送平台推送消息给andriod手机
	 * @param params 推送所需的各种参数
	 */
	public static void PushNoticeToAndroid(AliyunPushParams params) {
		// 初始化阿里云推送客户端
		IClientProfile profile = DefaultProfile.getProfile(REGION_HANGZHOU, accessKeyId, accessKeySecret);
		DefaultAcsClient client  = new DefaultAcsClient(profile);
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        final String date = dateFormat.format(new Date());
        PushRequest pushRequest = new PushRequest();

        // 推送目标
        pushRequest.setAppKey(appKey);
        pushRequest.setTarget(params.getTarget()); //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
        // "acb7481040724d5db53bcc73a81d858b,9aa005f25f9342df83f8022f5d023670"
        pushRequest.setTargetValue(params.getTargetValue()); //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        pushRequest.setDeviceType(1); // 设备类型deviceType 取值范围为:0~3. iOS设备: 0; Android设备: 1; 全部: 3, 这是默认值.


        // 推送配置
        pushRequest.setType(1); // 0:表示消息(默认为0), 1:表示通知
        pushRequest.setTitle(params.getTitle()); // 消息的标题
        pushRequest.setBody(params.getContent()); // 消息的内容
        pushRequest.setSummary(params.getSummary()); // 通知的摘要
//        // 推送配置: iOS
//        pushRequest.setiOSBadge("5"); // iOS应用图标右上角角标
//        pushRequest.setiOSMusic("default"); // iOS通知声音
//        pushRequest.setiOSExtParameters("{\"k1\":\"ios\",\"k2\":\"v2\"}"); //自定义的kv结构,开发者扩展用 针对iOS设备
//        pushRequest.setApnsEnv("DEV");
        //pushRequest.setRemind(true); // 推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次(发送通知时,Summary为通知的内容,Message不起作用)。注意：离线消息转通知仅适用于生产环境
        // 推送配置: Android
        //设置该参数后启动小米托管弹窗功能，此处指定通知点击后跳转的Activity（托管弹窗的前提条件：1. 继承小米辅助通道；2. storeOffLine设为true
        //pushRequest.setXiaomiActivity("_Your_XiaoMi_Activity_");
        pushRequest.setAndroidOpenType(params.getAndroidOpenType()); // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url 4 : 无跳转逻辑
        if(params.getAndroidOpenType().equals("2")) {
        	 pushRequest.setAndroidActivity(params.getUrlOrActivity());
        } else if(params.getAndroidOpenType().equals("3")){
          pushRequest.setAndroidOpenUrl(params.getUrlOrActivity()); // Android收到推送后打开对应的url,仅仅当androidOpenType=3有效
           
        }
        pushRequest.setAndroidExtParameters(params.getJsonParms()); // 设定android类型设备通知的扩展属性

        // 推送控制
        //final Date pushDate = new Date(System.currentTimeMillis() + 30 * 1000); // 30秒之间的时间点, 也可以设置成你指定固定时间
        //final String pushTime = ParameterHelper.getISO8601Time(pushDate);
        // pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        //pushRequest.setStoreOffline(false); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        //final String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
        //pushRequest.setExpireTime(expireTime);
        //pushRequest.setBatchNumber("100010"); // 批次编号,用于活动效果统计. 设置成业务可以记录的字符串

        PushResponse pushResponse;
		try {
			pushResponse = client.getAcsResponse(pushRequest);
			System.out.printf("RequestId: %s, ResponseId: %s\n",
	                pushResponse.getRequestId(), pushResponse.getResponseId());
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
//		AliyunPushParams params = new AliyunPushParams();
//		params.setAndroidOpenType("3");
//		params.setUrlOrActivity("http://www.baidu.com");// com.wzyb.aliyun.SecActivity
//		params.setTitle("人生如梦易如华");
//		params.setContent("人生如梦易如华tyyuuuu");
//		params.setSummary("人生如梦易如华。。。");
//		params.setJsonParms("{\"k1\":\"说好了\",\"k2\":\"在一起\"}");
//		params.setTarget("all");
//		params.setTargetValue("all");
//		PushNoticeToAndroid(params);
		AliyunPushParams params = new AliyunPushParams();
		params.setAndroidOpenType("2");
		params.setUrlOrActivity("com.wzyb.mobileoa.rolesupport.activity.RolesupActivity");// com.wzyb.aliyun.SecActivity
		params.setTitle("决策支持系统待办任务");
		params.setContent("您有15项待办任务");
		params.setSummary("人生如梦易如华。。。");
		params.setJsonParms("{\"k1\":\"说好ww了\",\"k2\":\"在一起\"}");
		params.setTarget("device");
		// 9aa005f25f9342df83f8022f5d023670 be1362c160f04cbd808773be68b6b67c
		params.setTargetValue("be1362c160f04cbd808773be68b6b67c,9aa005f25f9342df83f8022f5d023670");// "8816b9bfb0c74959b6cb4f2640d3faaa"
		PushNoticeToAndroid(params);
	}

}
