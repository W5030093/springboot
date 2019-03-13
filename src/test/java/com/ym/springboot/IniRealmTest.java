package com.ym.springboot;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class IniRealmTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();


    @Test
    public void shiroT(){

        //内置ini Realm
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //构建sevurity环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mark","123456");
        subject.login(token);

        System.out.println(subject.isAuthenticated());

        //具备角色
        subject.checkRole("admin");
        //具备删除的权限
        subject.checkPermission("delete");
    }
}
