package com.company.project.configurer.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.company.project.model.User;
import com.company.project.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	
    @Resource
    private UserService userService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
//        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        // 用户的角色集合
//        Set<String> roles = new HashSet<>();
//        roles.add(user.getRoleList().get(0).getRole());
//        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
//        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.findByUserName(username);
        if (userInfo == null) {
            return null;
        }
        if (!userInfo.getEnable()) { //账户冻结
            throw new LockedAccountException();
        }
        String credentials = userInfo.getPassword();
        System.out.println("credentials="+credentials);
        //ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUsername(), //用户名
                credentials,//密码,
                getName() 
//                credentialsSalt,
//                getName()  //realm name
        );
//        Session session = SecurityUtils.getSubject().getSession();
//        session.setAttribute("USER_SESSION", userInfo);
        return authenticationInfo;
    }
}
