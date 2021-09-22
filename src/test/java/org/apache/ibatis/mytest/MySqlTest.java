package org.apache.ibatis.mytest;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.domain.blog.Author;
import org.apache.ibatis.domain.blog.mappers.AuthorMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mytest.MyDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MySqlTest extends BaseDataTest {

  private static SqlSessionFactory sqlMapper;

  @Before
  public void setup() throws Exception {
    createBlogDataSource();
    final String resource = "org/apache/ibatis/mytest/MyMapperConfig.xml";
    final Reader reader = Resources.getResourceAsReader(resource);
    sqlMapper = new SqlSessionFactoryBuilder().build(reader);
  }

  public static DataSource createBlogDataSource() throws IOException, SQLException {
    DataSource ds = createUnpooledDataSource(BLOG_PROPERTIES);
    runScript(ds, BLOG_DDL);
    runScript(ds, BLOG_DATA);
    return ds;
  }

  @Test
  public void testSqlNode(){
    try (SqlSession session = sqlMapper.openSession()) {
      MyDao mapper = session.getMapper(MyDao.class);
      Author expected = new Author(500, "cbegin", "******", "cbegin@somewhere.com", "Something...", null);
      mapper.insertAuthor(expected);
      Map<String, Object> map = mapper.selectAll("500","cbegin");
      System.out.println(map);
    }
  }

  @Test
  public void update(){
    try (SqlSession session = sqlMapper.openSession()) {
      MyDao mapper = session.getMapper(MyDao.class);
      Author expected = new Author(500, "cbegin", "******", null, null, null);
      mapper.updateAuthorIfNecessary(expected);
    }
  }

  /**
   * 测试map结果封装
   */
  @Test
  public void testMapResult(){
    try (SqlSession session = sqlMapper.openSession()) {
      MyDao mapper = session.getMapper(MyDao.class);
      Map<String, Object> map = mapper.selectAll("500","cbegin");
      System.out.println(map);
    }
  }

  @Test
  public void testResultMap(){
    try (SqlSession session = sqlMapper.openSession()) {
      MyDao mapper = session.getMapper(MyDao.class);
      Map<String, Object> map = mapper.selectAll("500","cbegin");
      System.out.println(map);
    }
  }

}
