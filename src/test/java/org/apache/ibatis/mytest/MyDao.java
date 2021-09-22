package org.apache.ibatis.mytest;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.domain.blog.Author;

import java.util.Map;

public interface MyDao {

  void insertAuthor(Author author);

  void updateAuthorIfNecessary(Author author);

  Map<String, Object> selectAll(@Param("id") String id, @Param("username")String username);

  Author selectFromAuthor(@Param("id") String id, @Param("username")String username);
}
