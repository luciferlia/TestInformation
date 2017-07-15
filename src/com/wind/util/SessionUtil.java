package com.wind.util;

import java.util.ArrayList;

import com.wind.entity.UserList;

public class SessionUtil {
	//����sessionId�жϵ�ǰ�û��Ƿ�����ڼ�����  ������� ���ص�ǰ�û�  ���򷵻�null  
    public static UserList getUserBySessionId(ArrayList<UserList> userList,String sessionId) {  
        for (UserList user : userList) {  
            if(sessionId.equals(user.getSessionId())){  
                return user;  
            }  
        }  
        return null;  
    }  
}
