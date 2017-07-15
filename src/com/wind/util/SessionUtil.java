package com.wind.util;

import java.util.ArrayList;

import com.wind.entity.UserList;

public class SessionUtil {
	//根据sessionId判断当前用户是否存在在集合中  如果存在 返回当前用户  否则返回null  
    public static UserList getUserBySessionId(ArrayList<UserList> userList,String sessionId) {  
        for (UserList user : userList) {  
            if(sessionId.equals(user.getSessionId())){  
                return user;  
            }  
        }  
        return null;  
    }  
}
