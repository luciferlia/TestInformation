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
* @Description: �Զ���sessionɨ����
* @author: 
* @date: 2014-9-10 ����10:16:42
* 
*/ 
public class SessionScanerListener implements HttpSessionListener,ServletRequestListener,HttpSessionAttributeListener {

    /**
    * @Field: list
    *          ����һ�����ϴ洢������������HttpSession
    *        LinkedList����һ���̰߳�ȫ�ļ���
    */ 
    /**
     * private List<HttpSession> list = new LinkedList<HttpSession>();
     * ����д�漰���̰߳�ȫ����,SessionScanerListener�������ڴ���ֻ��һ��
     * sessionCreated���ܻᱻ�����ͬʱ���ã�
     * ���ж���˲�������վ��ʱ��������ͬʱΪ��Щ�������ʵ��˴���session
     * ��ôsessionCreated������ĳһʱ���ڻᱻ�����߳�ͬʱ���ã������̲߳�������sessionCreated����
     * sessionCreated�������ڲ���������һ����������Ӵ����õ�session����ô�ڼ�session��ʱ��ͻ�
     * �漰������Sessionͬʱ���Ἧ����һ��λ�õ���������������������sessionʱ��һ��Ҫ��֤�������̰߳�ȫ�Ĳ���
     * ��ΰ�һ�����������̰߳�ȫ�ļ����أ�
     * ����ʹ��ʹ�� Collections.synchronizedList(List<T> list)�����������̰߳�ȫ��list���ϰ�װ�̰߳�ȫ��list����
     */
    //ʹ�� Collections.synchronizedList(List<T> list)������LinkedList��װ��һ���̰߳�ȫ�ļ���
    private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
    //����һ���������������䵱һ�����������������֤��list������ӵ��µ�session�ͱ���list�����е�session�����������ﵽͬ��
    private Object lock = new Object();
    //��ǰ�û���  
    private int userCounts=0;
  //�û�����  
    private ArrayList<UserList> userList;  
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	//sessionCreated  �û���+1  
    	//if(se.getSession().getAttribute("user")!=null){
    		 	userCounts++;  
    	        //������servletContext�б���userCounts  
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
        System.out.println("session��������!!");
        HttpSession session = se.getSession();*/
        
       /* synchronized (lock){
            *//**
             *���ò���������������������һ��thread-1(�߳�1)�ڵ�����δ���ʱ�������õ�lock�������Ȼ�������������session��
             *�����session����������м���������һ��thread-2(�߳�2)�������ˣ�thread-2������ִ�ж�ʱ������ģ�
             *��thread-2Ҫ����run��������list�����е�sessionʱ��������ֱ���list�����е�session���Ƕδ��뱻��ס�ˣ�
             *����������ڱ������������session���Ǹ�thread-1ռ���ţ����thread-2ֻ�ܵȴ�thread-1�������֮����ܹ����в���
             *��thread-1�����session֮�󣬾Ͱ�lock�ſ��ˣ���ʱthread-2�õ�lock���Ϳ���ִ�б���list�����е�session���Ƕδ�����
             *ͨ��������ͱ�֤�������������session�ͱ��������е�session��������������ͬʱ���У����밴�������󵽵�˳�������С�
             *//*
            //list.add(session);
        }*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	 //sessionDestroyed  �û���-1  
        userCounts--;  
        //������servletContext�б���userCounts  
        se.getSession().getServletContext().setAttribute("onLineCount", userCounts);  
          
        @SuppressWarnings("unchecked")  
        ArrayList<UserList> userList=(ArrayList<UserList>) se.getSession().getServletContext().getAttribute("userList");  
        String sessionId=se.getSession().getId();  
        //�����ǰ�û���userList��  ��session����ʱ  ����ǰ�û��Ƴ�userList  
        if(SessionUtil.getUserBySessionId(userList, sessionId)!=null){  
            userList.remove(SessionUtil.getUserBySessionId(userList, sessionId));  
        }  
        //��userList����  ���±��浽servletContext  
        se.getSession().getServletContext().setAttribute("userList", userList); 
    	
    	
    	/*User u=(User) se.getSession().getAttribute("user");
        System.out.println("session����������!!");*/
    }

    /* WebӦ������ʱ��������¼�
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @SuppressWarnings("unchecked")
	/*@Override
    public void contextInitialized(ServletContextEvent sce) {
    	
    	
    	
        System.out.println("webӦ�ó�ʼ��");
        //������ʱ��
        Timer timer = new Timer();
        //ÿ��30��Ͷ�ʱִ������
        timer.schedule(new MyTask(list,lock), 0, 1000*30);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("webӦ�ùر�");
    }*/

	

	@Override
	public void requestInitialized(ServletRequestEvent sce) {
		//��servletContext�л��userList  
        userList=(ArrayList<UserList>) sce.getServletContext().getAttribute("userList");  
        //���servletContext��û��userList����  ��ʼ��userList  
        if(userList==null){  
            userList=new ArrayList<UserList>();  
        }  
        HttpServletRequest request=(HttpServletRequest) sce.getServletRequest();  
          
        //sessionId  
        String sessionId=request.getSession().getId();  
        //�����ǰsessionId�����ڼ�����  ������ǰuser����  
        if(SessionUtil.getUserBySessionId(userList,sessionId)==null){  
            UserList user=new UserList();  
            user.setSessionId(sessionId);  
            user.setIp(request.getRemoteAddr());  
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");  
            user.setFirstTime(sdf.format(new Date()));  
            userList.add(user);  
        }  
        //��userList���� ���浽ServletContext  
        sce.getServletContext().setAttribute("userList", userList);
        //System.out.println("webӦ�ó�ʼ��");
        /*//������ʱ��
        Timer timer = new Timer();
        //ÿ��30��Ͷ�ʱִ������
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
		            // ��������session
		      for (int i = sessions.size()-1; i >= 0; i--) {
		        SessionAndUser tem = sessions.get(i);
		        System.out.println(tem.getUserID());
		        System.out.println(nowUser.getName());
		        if (tem.getUserID().equals(nowUser.getName())) {
		          tem.getSession().invalidate();//�Զ�����remove
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
		    // ��¼
		    if (attrName.equals(loginFlag)) {
		      User nowUser = (User) e.getValue();
		      //User sUser = (User)session.getAttribute(loginFlag);
		      //System.out.println("nowUser:" + nowUser );
		      // ��������session
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
		    // ��¼
		    if (attrName.equals(loginFlag)) {
		     // User nowUser = (User) e.getValue();//old value
		      User nowUser = (User)session.getAttribute(loginFlag);//��ǰsession�е�user
		    //  System.out.println("nowUser:" + nowUser );
		   // ��������session
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
		        sessions.get(delS).getSession().invalidate();//ʧЧʱ�Զ�������remove������Ҳ�ͻ������sessions���Ƴ���
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
* @Description:��ʱ��Ҫ��ʱִ�е�����
* @author:
* @date: 2014-9-11 ����12:02:36
*
*/ 
class MyTask extends TimerTask {
        
    //�洢HttpSession��list����
    private List<HttpSession> list;
    //�洢���ݹ�������
    private Object lock;
    private int count;
    private ArrayList<UserList> uList;
    public MyTask(List<HttpSession> list,Object lock,int count,ArrayList<UserList> u){
        this.list = list;
        this.lock = lock;
        this.count=count;
        this.uList=u;
    }
    /* run����ָ��������Ҫ��������
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
            //���ò���������������
        synchronized (lock) {
            System.out.println("��ʱ��ִ�У���");
            ListIterator<HttpSession> it = list.listIterator();
            System.out.println("��ǰ����������"+count);
            for(UserList uu:uList){
            	System.out.println("������Ա��"+uu.getIp());
            }
            /**
             * ����list�����е�session���ڵ���list�����е�session�Ĺ����п����б���û������ʣ�
             * �û�һ���ʣ��������ͻ�Ϊ���û�����һ��session,��ʱ�ͻ����sessionCreated��list����������µ�session��
             * Ȼ����ʱ���ڶ�ʱִ��ɨ�����list�����е�sessionʱ���޷�֪�����ڱ�����list��������ӵ��µ�session�����ˣ�
             * �����͵�������list������ӵ��µ�session�ͱ���list�����е�session�����������޷��ﵽͬ��
             * ��ô����İ취���ǰ�"list.add(session)��while(it.hasNext()){//����list����}"�����δ�������ͬ����
             * ��֤����һ���߳��ڷ���"list.add(session)"��δ���ʱ����һ���߳̾Ͳ��ܷ���"while(it.hasNext()){//����list����}"��δ���
             * Ϊ���ܹ��������β���ɵĴ�������ͬ����ֻ�ܶ���һ����(Object lock)��Ȼ�����������������ͬһ������
             * �����������֤��list������ӵ��µ�session�ͱ���list�����е�session�����������ﵽͬ��
             * ����ִ����list������ӵ��µ�session����ʱ���ͱ����������֮����ܹ���list���Ͻ��е���������
             * ����ִ�ж�list���Ͻ��е�������ʱ����ô����ȵ�������������֮����ܹ�����list������ӵ��µ�session
             */
            while(it.hasNext()){
                HttpSession session = (HttpSession) it.next();
                /**
                 * �����ǰʱ��-session��������ʱ��>1000*15(15��)
                 * session.getLastAccessedTime()��ȡsession��������ʱ��
                 */
                if(System.currentTimeMillis()-session.getLastAccessedTime()>1000*30*30){
                    //�ֶ�����session
                    session.invalidate();
                    //�Ƴ��������Ѿ������ٵ�session
                    it.remove();
                }
            }
        }
    }
}
