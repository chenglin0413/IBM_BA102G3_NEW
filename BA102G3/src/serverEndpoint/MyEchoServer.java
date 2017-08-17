package serverEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/MyEchoServer/{store_name}/{store_id}")
public class MyEchoServer {
private static  String roomkey=null;
private static final Map<String,Set<Session>>connectedSessionsMap = Collections.synchronizedMap(new HashMap<String,Set<Session>>());
	
	@OnOpen
	public void onOpen(@PathParam("store_name") String store_name, @PathParam("store_id") Integer store_id, Session userSession) throws IOException {
		roomkey=store_name+"store_id";
		if(connectedSessionsMap.containsKey(roomkey)){
			Set<Session> roomValue=connectedSessionsMap.get(roomkey);
			roomValue.add(userSession);//把開啟的連線加入set
			connectedSessionsMap.put(roomkey,roomValue);
			System.out.println(userSession.getId() + ": 已連線");
			System.out.println(store_name + "商家名稱: 已連線");//取商店STORE_name
			System.out.println(store_id + ":聊天室_活動中。");//取商店STORE_ID
			System.out.println(roomValue.size());//印出目前聊天室人數
//			userSession.getBasicRemote().sendText("WebSocket 連線成功");
		}else{
			Set<Session> roomValue=Collections.synchronizedSet(new HashSet<Session>());
			roomValue.add(userSession);
			System.out.println(roomValue.size());//印出新聊天室的人數
			connectedSessionsMap.put(roomkey, roomValue);
			
			
		}
		
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message,String roomkey) {
		Set<Session> oneSessionSet=connectedSessionsMap.get(roomkey);
		for (Session session : oneSessionSet) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason,String roomkey) {
		Set<Session> oneSessionSet=connectedSessionsMap.get(roomkey);
		oneSessionSet.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
