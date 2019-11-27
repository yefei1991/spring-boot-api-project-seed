package com.company.project.configurer;

import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.company.project.core.AbstractModel;

@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class SqlStatementInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
//        Subject subject = SecurityUtils.getSubject();
//        subject.getPrincipal();
        if(object instanceof AbstractModel) {
        	AbstractModel obj=(AbstractModel)object;
        	Subject subject = SecurityUtils.getSubject();
        	Object principal=subject.getPrincipal();
        	if(principal!=null) {
        		obj.setLastUpdateBy(principal.toString());
        	}
        	if (SqlCommandType.INSERT.equals(sqlCommandType)) {
        		//插入操作时，自动插入env
        		obj.setCreatetime(new Date());
        		obj.setUpdatetime(new Date());
        	}else{
        		if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
        			//update时，自动更新updated_at
        			obj.setUpdatetime(new Date());
        		}
        	}
        }
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
