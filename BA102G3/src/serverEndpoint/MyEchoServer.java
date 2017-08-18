package serverEndpoint;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{storename}/{users}")
public class MyEchoServer {
	private static final Map<String,Set<Session>>connectedSessionsMap = Collections.synchronizedMap(new HashMap<String,Set<Session>>());
	private static final String storename=null;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 日期格式化
	@OnOpen
	public void onOpen(@PathParam("storename") String storename, @PathParam("users") int users, Session userSession) throws IOException {
		if(connectedSessionsMap.containsKey(storename)){
			connectedSessionsMap.get(storename).add(userSession);//如果這個加入的商家name已存在map中，則把這位user加入Set<Session>中。
		}else{
			Set<Session> sessions=new HashSet<Session>();
			connectedSessionsMap.put("storename",sessions);//如果這個加入的商家name不在map中，則新增，並把這位user加入Ses<Session>中。
		}
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(storename + "商家名稱: 已連線");
		System.out.println(users + ":聊天室_活動中。");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message ) {
		Iterator<String> it=connectedSessionsMap.keySet().iterator();
		while(it.hasNext()){
			String storename=it.next();
			Set<Session> storeroom=connectedSessionsMap.get(storename);
			for(Session session:storeroom){
				if (session.isOpen())
				session.getAsyncRemote().sendText(message);
			}
		}
		
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
//		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
