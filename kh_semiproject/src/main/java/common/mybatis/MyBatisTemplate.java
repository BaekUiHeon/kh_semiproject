package common.mybatis;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisTemplate {
	public SqlSession getSession() {
		SqlSession session = null;
		try {
			session= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
