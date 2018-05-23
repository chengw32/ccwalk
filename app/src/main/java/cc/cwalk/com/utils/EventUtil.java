package cc.cwalk.com.utils;


import org.greenrobot.eventbus.EventBus;

/**
 *  EventBus 封装工具
 * @author Administrator
 * 
 * EventBus是一款针对Android优化的发布/订阅事件总线。
 * 主要功能是替代Intent,Handler,BroadCast在Fragment，Activity，Service，
 * 线程之间传递消息.优点是开销小，代码更优雅。以及将发送者和接收者解耦。
 *
 */
public class EventUtil {

	public final static String ACT_LOGIN = "login";
	public final static String ACT_REFRESH = "refresh";
	public final static String IMAGE_VIDEO = "pickerimages";
	public final static String NOTICE_PUBLISH = "publish";
	public final static String REMOVE_MEMBER = "removemember";
	public final static String REMOVE_ATTENTION = "removeattention";
	/**
	 * 注册订阅者
	 * @param context
	 */
	public static void register(Object context){
		if (!EventBus.getDefault().isRegistered(context)) {
			EventBus.getDefault().register(context);
		}

		//注册：三个参数分别是，消息订阅者（接收者），接收方法名，事件类  
		//  EventBus.getDefault().register(this,"setTextA",SetTextAEvent.class);  
		//  EventBus.getDefault().register(this,"setTextB",SetTextBEvent.class);  
		//  EventBus.getDefault().register(this,"messageFromSecondActivity",SecondActivityEvent.class);  
		//  EventBus.getDefault().registerSticky(this, "messageFromSecondActivity", SecondActivityEvent.class);  
		//  EventBus.getDefault().register(this,"countDown",CountDownEvent.class);  
	}

	/**
	 * 解除注册
	 * @param context
	 */
	public static void unregister(Object context){
		if (EventBus.getDefault().isRegistered(context)) {
			EventBus.getDefault().unregister(context);
		}
	}

	/**
	 * 发送消息 数据
	 */
	public static void sendEvent(String action,Object data){
		EventBus.getDefault().post(new BaseEvent(action,data));
	}


	public static class BaseEvent{
		private  String actiong ;
		private  Object data ;
		public BaseEvent(String act,Object object) {
			this.actiong = act ;
			this.data = object ;
		}
		public String getAction() {
			return actiong;
		}

		public Object getData() {
			return data;
		}
	}

}
