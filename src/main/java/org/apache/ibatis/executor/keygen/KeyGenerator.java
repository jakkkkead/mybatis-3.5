/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor.keygen;

import java.sql.Statement;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Mybatis提供了主键生成器接口KeyGenerator，insert语句默认是不返回记录的主键值，而是返回插入的记录条数；如果业务层需要得到记录的主键时，
 * 可以通过配置的方式来完成这个功能 。
 * KeyGenerator的作用，是返回数据库生成的自增主键值，而不是生成数据库的自增主键值。返回的主键值放到哪儿呢？
 * 放到parameter object的主键属性上。
 *
 * 由于不同的数据库对主键的生成不一样：
 *
 * （1）针对Sequence主键而言，在执行insert sql前必须指定一个主键值给要插入的记录，如Oracle、DB2，KeyGenerator提供了processBefore（）方法。
 *
 * （2）针对自增主键的表，在插入时不需要主键，而是在插入过程自动获取一个自增的主键，比如MySQL，Postgresql，KeyGenerator提供了processAfter（）方法。
 * @author Clinton Begin
 */
public interface KeyGenerator {

  /**
   *  在执行insert之前，设置属性order="BEFORE"
   * @param executor
   * @param ms
   * @param stmt
   * @param parameter
   */
  void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

  /**
   * //在执行insert之后，设置属性order="AFTER"
   * @param executor
   * @param ms
   * @param stmt
   * @param parameter
   */
  void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

}
