package com.wind.listener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;

import com.wind.entity.SessionAndUser;
import com.wind.entity.User;
import com.wind.entity.UserList;
import com.wind.util.SessionUtil;

/**
* @ClassName: SessionScanerListener
* @Description: 自定义session扫描器
* @author: 
* @date: 2014-9-10 下午10:16:42
* 
*/ 
public class SessionScanerListener implements HttpSessionListener,ServletRequestListener,HttpSessionAttributeListener {

    /**
    * @Field: list
    *          定义一个集合存储服务器创建的HttpSession
    *        LinkedList不是一个线程安全的集合
    */ 
    /**
     * private List<HttpSession> list = new LinkedList<HttpSession>();
     * 这样写涉及到线程安全问题,SessionScanerListener对象在内存中只有一个
     * sessionCreated可能会被多个人同时调用，
     * 当有多个人并发访问站点时，服务器同时为这些并发访问的人创建session
     * 那么sessionCreated方法在某一时刻内会被几个线程同时调用，几个线程并发调用sessionCreated方法
     * sessionCreated方法的内部处理是往一个集合中添加创建好的session，那么在加session的时候就会
     * 涉及到几个Session同时抢夺集合中一个位置的情况，所以往集合中添加session时，一定要保证集合是线程安全的才行
     * 如何把一个集合做成线程安全的集合呢？
     * 可以使用使用 Collections.synchronizedList(List<T> list)方法将不是线程安全的list集合包装线程安全的list集合
     */
    //使用 Collections.synchronizedList(List<T> list)方法将LinkedList包装成一个线程安全的集合
    private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
    //定义一个对象，让这个对象充当一把锁，用这把锁来保证往list集合添加的新的session和遍历list集合中的session这两个操作达到同步
    private Object lock = new Object();
    //当前用户数  
    private int userCounts=0;
  //用户集合  
    private ArrayList<UserList> userList;  
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	//sessionCreated  用户数+1  
    	//if(se.getSession().getAttribute("user")!=null){
    		 	userCounts++;  
    	        //重新在servletContext中保存userCounts  
    	        se.getSession().getServletContext().setAttribute("onLineCount", userCounts); 
    	       /* UserList user=new UserList();
    	        user.setSessionId(se.getSession().getId());
    	        user.setIp(((User) se.getSession().getAttribute("user")).getName());
    	        userList.add(user);
    	        se.getSession().setAttribute("userList", userList);*/
    	//}
        
    	/*ServletContext context = se.getSession().getServletContext();
        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
        if(onLineCount==null){
            context.setAttribute("onLineCount", 1);
        }else{
            onLineCount++;
            context.setAttribute("onLineCount", onLineCount);
        }
        System.out.println("session被创建了!!");
        HttpSession session = se.getSession();*/
        
       /* synchronized (lock){
            *//**
             *将该操作加锁进行锁定，当有一个thread-1(线程1)在调用这段代码时，会先拿到lock这把锁，然后往集合中添加session，
             *在添加session的这个过程中假设有另外一个thread-2(线程2)来访问了，thread-2可能是执行定时器任务的，
             *当thread-2要调用run方法遍历list集合中的session时，结果发现遍历list集合中的session的那段代码被锁住了，
             *而这把锁正在被往集合中添加session的那个thread-1占用着，因此thread-2只能等待thread-1操作完成之后才能够进行操作
             *当thread-1添加完session之后，就把lock放开了，此时thread-2拿到lock，就可以执行遍历list集合中的session的那段代码了
             *通过这把锁就保证了往集合中添加session和变量集合中的session这两步操作不能同时进行，必须按照先来后到的顺序来进行。
             *//*
            //list.add(session);
        }*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	 //sessionDestroyed  用户数-1  
        userCounts--;  
        //重新在servletContext中保存userCounts  
        se.getSession().getServletContext().setAttribute("onLineCount", userCounts);  
          
        @SuppressWarnings("unchecked")  
        ArrayList<UserList> userList=(ArrayList<UserList>) se.getSession().getServletContext().getAttribute("userList");  
        String sessionId=se.getSession().getId();  
        //如果当前用户在userList中  在session销毁时  将当前用户移出userList  
        if(SessionUtil.getUserBySessionId(userList, sessionId)!=null){  
            userList.remove(SessionUtil.getUserBySessionId(userList, sessionId));  
        }  
        //将userList集合  重新保存到servletContext  
        se.getSession().getServletContext().setAttribute("userList", userList); 
    	
    	
    	/*User u=(User) se.getSession().getAttribute("user");
        System.out.println("session被销毁了了!!");*/
    }

    /* Web应用启动时触发这个事件
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @SuppressWarnings("unchecked")
	/*@Override
    public void contextInitialized(ServletContextEvent sce) {
    	
    	
    	
        System.out.println("web应用初始化");
        //创建定时器
        Timer timer = new Timer();
        //每隔30秒就定时执行任务
        timer.schedule(new MyTask(list,lock), 0, 1000*30);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("web应用关闭");
    }*/

	

	@Override
	public void requestInitialized(ServletRequestEvent sce) {
		//从servletContext中获的userList  
        userList=(ArrayList<UserList>) sce.getServletContext().getAttribute("userList");  
        //如果servletContext中没有userList对象  初始化userList  
        if(userList==null){  
            userList=new ArrayList<UserList>();  
        }  
        HttpServletRequest request=(HttpServletRequest) sce.getServletRequest();  
          
        //sessionId  
        String sessionId=request.getSession().getId();  
        //如果当前sessionId不存在集合中  创建当前user对象  
        if(SessionUtil.getUserBySessionId(userList,sessionId)==null){  
            UserList user=new UserList();  
            user.setSessionId(sessionId);  
            user.setIp(request.getRemoteAddr());  
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");  
            user.setFirstTime(sdf.format(new Date()));  
            userList.add(user);  
        }  
        //将userList集合 保存到ServletContext  
        sce.getServletContext().setAttribute("userList", userList);
        //System.out.println("web应用初始化");
        /*//创建定时器
        Timer timer = new Timer();
        //每隔30秒就定时执行任务
        timer.schedule(new MyTask(list,lock,userCounts,userList), 0, 1000*30);*/
	}

	
    
    private static List<SessionAndUser> sessions;
    public static String                loginFlag = "user";
    
    static {
      if (sessions == null) {
        sessions = Collections.synchronizedList(new ArrayList<SessionAndUser>());
      }
    }
	@Override
	public void attributeAdded(HttpSessionBindingEvent e) {
		HttpSession session=e.getSession();
		 System.out.println("-------------*start added*-----------------------");
		 String attrName = e.getName();
//		 System.out.println("dsa"+attrName);
		 
		 //login
		 if (attrName.equals(loginFlag)) {
		      User nowUser = (User) e.getValue();
		      User sUser = (User)session.getAttribute(loginFlag);
		            // 遍历所有session
		      for (int i = sessions.size()-1; i >= 0; i--) {
		        SessionAndUser tem = sessions.get(i);
		        System.out.println(tem.getUserID());
		        System.out.println(nowUser.getName());
		        if (tem.getUserID().equals(nowUser.getName())) {
		          tem.getSession().invalidate();//自动调用remove
		          break;
		        }
		      }
		      
		      SessionAndUser sau = new SessionAndUser();
		      sau.setUserID(nowUser.getName());
		      sau.setSession(session);
		      sau.setSid(session.getId());
		      sessions.add(sau);
		 }
		      
//		  for (int i = 0; i < sessions.size(); i++) {
//		        SessionAndUser tem = sessions.get(i);
//		        System.out.println(i + ":" + tem.getUserID());
//		      }
//		    }
//		    
//		    System.out.println("Added:SID:" + session.getId() + "(" + e.getName() + ","
//		        + e.getValue() + ")" + sessions.size());
		  }

		

	@Override
	public void attributeRemoved(HttpSessionBindingEvent e) {
		 HttpSession session = e.getSession();
		    System.out.println("-------------*start Removed*-----------------------");
		    String attrName = e.getName();
		    // 登录
		    if (attrName.equals(loginFlag)) {
		      User nowUser = (User) e.getValue();
		      //User sUser = (User)session.getAttribute(loginFlag);
		      //System.out.println("nowUser:" + nowUser );
		      // 遍历所有session
		      for (int i = sessions.size()-1; i >= 0; i--) {
		        SessionAndUser tem = sessions.get(i);
		        //System.out.println("tem.getUserID()"+tem.getUserID()+"nowUser.getName()"+nowUser.getName());
		        if (tem.getUserID().equals(nowUser.getName())) {
		          System.out.println("Remove:invalidate 1!" + tem.getSid());
		          sessions.remove(i);
		          break;
		        }
		      }
		    }
		    
//		      for (int i = 0; i < sessions.size(); i++) {
//		        SessionAndUser tem = sessions.get(i);
//		        System.out.println(i + ":" + tem.getUserID());
//		      }
//		    }
//		    System.out.println("Removed:SID:" + session.getId() + "(" + e.getName()
//		        + "," + e.getValue() + ")" + sessions.size());
		  }
		  
		  public void attributeReplaced(HttpSessionBindingEvent e) {
		    HttpSession session = e.getSession();
		    System.out.println("-------------*start replace*-----------------------");
		    String attrName = e.getName();
		    int delS=-1;
		    // 登录
		    if (attrName.equals(loginFlag)) {
		     // User nowUser = (User) e.getValue();//old value
		      User nowUser = (User)session.getAttribute(loginFlag);//当前session中的user
		    //  System.out.println("nowUser:" + nowUser );
		   // 遍历所有session
		      for (int i = sessions.size()-1; i >= 0; i--) {
		        SessionAndUser tem = sessions.get(i);
		        if (tem.getUserID().equals(nowUser.getName())&&!tem.getSid().equals(session.getId())) {
		          System.out.println("Remove:invalidate 1!");
		          delS=i;
		        }else if(tem.getSid().equals(session.getId())){
		          tem.setUserID(nowUser.getName());
		        }
		      }
		      
		      if (delS!=-1) {
		        sessions.get(delS).getSession().invalidate();//失效时自动调用了remove方法。也就会把它从sessions中移除了
		      }
		      
//		      for (int i = 0; i < sessions.size(); i++) {
//		        SessionAndUser tem = sessions.get(i);
//		        System.out.println(i + ":" + tem.getUserID());
//		      }
		    }
//		    System.out.println("Replaced:SID:" + session.getId() + "(" + e.getName()
//		        + "," + e.getValue() + ")" + sessions.size());
		  }
		  
		  public void sessionCreated1(HttpSessionEvent e) {
		    System.out.println("Created:SID:" + e.getSession().getId());
		  }
		  
		  public void sessionDestroyed1(HttpSessionEvent e) {
		    System.out.println("Destroyed:SID:" + e.getSession().getId());
		  }

		@Override
		public void requestDestroyed(ServletRequestEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		  

		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session = request.getSession();
//		List<HttpSession> list=new ArrayList<HttpSession>();
//		list.add(session);
//        System.out.println("-------------*start Removed*-----------------------");
//        
//        for (int i = list.size()-1; i >= 0; i--){
//    		HttpSession tem = list.get(i);
//    		if (tem.getId().equals(session.getId())) {
//    			
//    			//list.set(list.get(i), null);
//    		
//    			System.out.println(list.size()+"aaa"); 
//    	          list.remove(i); 
//    	          System.out.println(list.size()+"bbb");
//    	          System.out.println(session.getId()+"hhhhhhh"+tem.getId());
//    	          session.invalidate();
//    	          break;
//    	         }
//    		} 
//	}

      
    
	

	
    
}

/**
* @ClassName: MyTask
* @Description:定时器要定时执行的任务
* @author:
* @date: 2014-9-11 上午12:02:36
*
*/ 
class MyTask extends TimerTask {
        
    //存储HttpSession的list集合
    private List<HttpSession> list;
    //存储传递过来的锁
    private Object lock;
    private int count;
    private ArrayList<UserList> uList;
    public MyTask(List<HttpSession> list,Object lock,int count,ArrayList<UserList> u){
        this.list = list;
        this.lock = lock;
        this.count=count;
        this.uList=u;
    }
    /* run方法指明了任务要做的事情
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
            //将该操作加锁进行锁定
        synchronized (lock) {
            System.out.println("定时器执行！！");
            ListIterator<HttpSession> it = list.listIterator();
            System.out.println("当前在线人数："+count);
            for(UserList uu:uList){
            	System.out.println("在线人员："+uu.getIp());
            }
            /**
             * 迭代list集合中的session，在迭代list集合中的session的过程中可能有别的用户来访问，
             * 用户一访问，服务器就会为该用户创建一个session,此时就会调用sessionCreated往list集合中添加新的session，
             * 然而定时器在定时执行扫描遍历list集合中的session时是无法知道正在遍历的list集合又添加的新的session进来了，
             * 这样就导致了往list集合添加的新的session和遍历list集合中的session这两个操作无法达到同步
             * 那么解决的办法就是把"list.add(session)和while(it.hasNext()){//迭代list集合}"这两段代码做成同步，
             * 保证当有一个线程在访问"list.add(session)"这段代码时，另一个线程就不能访问"while(it.hasNext()){//迭代list集合}"这段代码
             * 为了能够将这两段不相干的代码做成同步，只能定义一把锁(Object lock)，然后给这两步操作加上同一把锁，
             * 用这把锁来保证往list集合添加的新的session和遍历list集合中的session这两个操作达到同步
             * 当在执行往list集合添加的新的session操作时，就必须等添加完成之后才能够对list集合进行迭代操作，
             * 当在执行对list集合进行迭代操作时，那么必须等到迭代操作结束之后才能够往往list集合添加的新的session
             */
            while(it.hasNext()){
                HttpSession session = (HttpSession) it.next();
                /**
                 * 如果当前时间-session的最后访问时间>1000*15(15秒)
                 * session.getLastAccessedTime()获取session的最后访问时间
                 */
                if(System.currentTimeMillis()-session.getLastAccessedTime()>1000*30*30){
                    //手动销毁session
                    session.invalidate();
                    //移除集合中已经被销毁的session
                    it.remove();
                }
            }
        }
    }
}
