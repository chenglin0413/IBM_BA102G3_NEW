package serverEndpoint;

import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{myName}/{myRoom}") // myName:store_id , myRoom:
													// user_id
public class MyEchoServer {
	private static final Map<String, Set<Session>> connectedSessionsMap = Collections
			.synchronizedMap(new HashMap<String, Set<Session>>());

	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") int myRoom, Session userSession)
			throws IOException {
		System.out.println("myName:" + myName);
		System.out.println("myRoom:" + myRoom);

		if (connectedSessionsMap.containsKey(myName)) {
			connectedSessionsMap.get(myName).add(userSession);// 商家登入後的usersession加入myName這個房間。
			System.out.println("房間名:" + myName.toString() + "目前人數:" + connectedSessionsMap.get(myName).size());

		} else {
			Set<Session> sessions = new HashSet<Session>();
			sessions.add(userSession);// 把user_id加入這個聊天室(房號是由myName構成)
			connectedSessionsMap.put(myName.toString(), sessions);// 新成立的房間放入Map。
			System.out.println("房間名:" + myName.toString() + "目前人數:" + connectedSessionsMap.get(myName).size());
		}
		System.out.println("我是sessionID:" + userSession.getId() + " 已連線");
		System.out.println(myName + ": 已連線");
		System.out.println(myRoom + ": 已連線");
		System.out.println("========================================");
		// userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("36:" + message);
		System.out.println("37:" + userSession);
		Iterator<String> it = connectedSessionsMap.keySet().iterator();
		while (it.hasNext()) {
			String roomKey = it.next();//每個房間的roomKey
			Set<Session> roomSessions = connectedSessionsMap.get(roomKey);
			if (roomSessions.contains(userSession)) {// 如果這個房間包含這個userSession
				for (Session asession : roomSessions) {
					if (asession.isOpen())
						asession.getAsyncRemote().sendText(message);
				}
			}
		}

		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		// e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		// allSessions.remove(userSession);
		System.out.println("會員:" + userSession + "我要離開了");
		Set<String> set = connectedSessionsMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {// 每一個key裡面包含一個set<session>
			String roomKey = it.next();
			Set<Session> roomSessions = connectedSessionsMap.get(roomKey);
			for (Session asession : roomSessions) {
				if (asession.toString().equals(userSession.toString())) {
					roomSessions.remove(userSession);
				}
			}
		}
		System.out
				.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

}
