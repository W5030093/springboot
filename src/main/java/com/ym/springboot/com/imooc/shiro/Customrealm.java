package com.ym.springboot.com.imooc.shiro;

import com.mysql.jdbc.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Customrealm extends AuthorizingRealm {

    Map<String,String> usermap = new HashMap<>();

    {
        usermap.put("xiaoming","123456");
        super.setName("Customrealm");
    }
    //做授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从用户获取的信息 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //从数据库获取密码
        Set<String> roles = getRolesByName(username);
        Set<String> permissions = getPermissons();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo ;
    }

    //做认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从用户获取的信息 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //从数据库获取密码
        String password = getUserPasswordByName(username);
        if ("".equals(password)){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("xiaoming","123456","Customrealm");

        //加严操作
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark"));
        return simpleAuthenticationInfo;
    }

    private String getUserPasswordByName(String username) {

        String password = usermap.get(username);
        return password;
    }

    private Set<String> getRolesByName(String username){
        Set<String> rolesSet = new HashSet<>();
        rolesSet.add("user");
        return rolesSet;
    }
    public Set<String> getPermissons(){
        Set<String> permissonsSet = new HashSet<>();
        permissonsSet.add("user:add");
        permissonsSet.add("user:delete");
        return permissonsSet;
    }
}
