package com.ym.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource druidDataSource = new DruidDataSource();

    {

        druidDataSource.setUrl("jdbc:mysql://localhost:3306/et");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("etoak");
    }
    @Test
    public void shiroT(){

        //内置ini Realm
       JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        //设置权限开关
        jdbcRealm.setPermissionsLookupEnabled(true);
        String sql ="select roles from user_";
        jdbcRealm.setUserRolesQuery(sql);
        //构建sevurity环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        defaultSecurityManager.setRealm(jdbcRealm);
        //
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming","123456");
        subject.login(token);

        System.out.println(subject.isAuthenticated());


    }
}
