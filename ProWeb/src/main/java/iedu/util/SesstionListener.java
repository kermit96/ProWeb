package iedu.util;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SesstionListener
 *
 */
public class SesstionListener implements HttpSessionListener {

	private static Hashtable<String, HttpSession> sessionmap =  new Hashtable<String, HttpSession>();
	   
	public    SesstionListener()
	{		
		 
		
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
		sessionmap.put(arg0.getSession().getId(),arg0.getSession() );
	}
  
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		sessionmap.remove(arg0.getSession().getId());				
	}
	
	public static HttpSession getSession(String sesstion) 
	{						
		return sessionmap.get(sesstion);
	}
     
}
