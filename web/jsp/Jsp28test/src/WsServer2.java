

import java.io.IOException;
import java.util.StringTokenizer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/*
 * sessions가 각 호출 시 마다 생성되므로 static으로 정해 줘야 한다.
 * 브라우져가 websocket을 지원해야 한다.
 * 웹 소켓 연결 후 별도 close 동작 없이 브라우져를 닫을 경우 자동으로 onClose가 호출 된다.
 * 
 */
@ServerEndpoint("/websocketendpoint2")
public class WsServer2 {
	
	private static final java.util.Set<Session> sessions =
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	private static final java.util.Map<String, Session> clientMap =
			java.util.Collections.synchronizedMap(new java.util.HashMap<String, Session>());
	
//	private static final java.util.Map<String, Session> clienMap =
//			java.util.Collections.synchronizedMap(new java.util.HashMap<String, Session>());
	
	
	/*
	 * @OnOpen allows us to intercept the creation of a new session.
	 * The session class allows us to send data to the user.
	 * In the method onOpen, we'll let the user know that the handshake was
	 * successful
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id: " + session.getId());
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("11Connection Established");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId()+" has ended");
		sessions.remove(session);
	}
	
	/*
	 * When a use sends a message to the server,
	 * this method will intercept the message and allow us to react to it.
	 * For now the message is read as a String.
	 */
	
	@OnMessage
	public void onMessage(String message, Session session) {
		StringTokenizer t1 = new StringTokenizer(message);
		t1.nextToken();
		String test = t1.nextToken();
		System.out.println("Message from " + session.getId() +": "+message+test);
		System.out.println("test"+test);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to1 : " + message+test);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		sendAllSessionToMessage(session, message);
	}
	
	/*
	 *  모든 사용자에게 메시지를 전달한다.
	 */
	
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for( Session session : WsServer2.sessions) {
				if( ! self.getId().equals(session.getId()))
					session.getBasicRemote().sendText("All : " + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@OnError
	public void onError(Throwable e, Session session) {
	}
      
}