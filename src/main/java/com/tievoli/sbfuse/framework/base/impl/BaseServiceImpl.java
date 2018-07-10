package com.tievoli.sbfuse.framework.base.impl;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.tievoli.sbfuse.framework.base.BaseService;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 基础服务的公共实现.
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private static final int INSERT_BATCH_SIZE = 100;

    private static final int UPDATE_BATCH_SIZE = 100;

    private static final int MAX_BATCH_SIZE = 200;


    @Autowired
    protected BaseMapper<T> mapper;

    /**
     * 根据Mapper类型获取Mapper
     *
     * @param m
     * @return
     */
    @SuppressWarnings("unchecked")
    public <M extends BaseMapper> M getMapper(Class<M> m) {
        return (M) mapper;
    }

    public <M extends BaseMapper> M getMapper() {
        return (M) mapper;
    }

    //获取泛型类型
    protected Class<T> getSuperClassGenricType() {
        return ReflectionKit.getSuperClassGenricType(getClass(), 0);
    }

    //获取SqlSession
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(getSuperClassGenricType());
    }

    //获取Statement
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(getSuperClassGenricType()).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    @Transactional
    public boolean insert(T t) {
        return SqlHelper.retBool(mapper.insert(t));
    }

    @Override
    @Transactional
    public boolean insertAllColumn(T t) {
        return SqlHelper.retBool(mapper.insertAllColumn(t));
    }

    @Override
    @Transactional
    public boolean insertBatch(List<T> list) {
        return insertBatch(list, INSERT_BATCH_SIZE);
    }

    @Override
    @Transactional
    public boolean insertBatch(List<T> list, final int batchSize) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("Error: list must not be empty.");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = list.size();

            //防止错误设置批处理批次条数
            int finalBatchSize = Math.min(batchSize, MAX_BATCH_SIZE);
            String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
            for (int i = 0; i < size; i++) {
                batchSqlSession.insert(sqlStatement, list.get(i));
                if (i >= 1 && i % finalBatchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new MybatisPlusException("Error: Cannot execute insertBatch Method. Cause", e);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean insertOrUpdateBatch(List<T> list) {
        return insertOrUpdateBatch(list, INSERT_BATCH_SIZE);
    }

    @Override
    @Transactional
    public boolean insertOrUpdateBatch(List<T> list, int batchSize) {
        return doInsertOrUpdateBatch(list, batchSize, false);
    }

    @Override
    @Transactional
    public boolean insertOrUpdateAllColumnBatch(List<T> list) {
        return insertOrUpdateAllColumnBatch(list, INSERT_BATCH_SIZE);
    }

    @Override
    @Transactional
    public boolean insertOrUpdateAllColumnBatch(List<T> list, int batchSize) {
        return doInsertOrUpdateBatch(list, batchSize, true);
    }

    /**
     * 插入或更新批处理操作.
     *
     * @param list
     * @param batchSize
     * @param allColumn 判断字段是插入或更新全部字段还是插入或更新部分字段.
     * @return
     */
    private boolean doInsertOrUpdateBatch(List<T> list, int batchSize, boolean allColumn) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("Error: list must not be empty");
        }

        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = list.size();
            //防止错误设置批处理批次条数
            int finalBatchSize = Math.min(batchSize, MAX_BATCH_SIZE);
            for (int i = 0; i < size; i++) {
                if (allColumn) {
                    insertOrUpdate(list.get(i));
                } else {
                    insertOrUpdateAllColumn(list.get(i));
                }

                if (i >= 1 && i % finalBatchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute doInsertOrUpdateBatch Method. Cause", e);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteById(Serializable serializable) {
        return SqlHelper.delBool(mapper.deleteById(serializable));
    }

    @Override
    @Transactional
    public boolean deleteByMap(Map<String, Object> map) {
        return SqlHelper.delBool(mapper.deleteByMap(map));
    }

    @Override
    @Transactional
    public boolean delete(Wrapper<T> wrapper) {
        return SqlHelper.delBool(mapper.delete(wrapper));
    }

    @Override
    @Transactional
    public boolean deleteBatchIds(Collection<? extends Serializable> collection) {
        return SqlHelper.delBool(mapper.deleteBatchIds(collection));
    }

    @Override
    @Transactional
    public boolean updateById(T t) {
        return SqlHelper.retBool(mapper.updateById(t));
    }

    @Override
    @Transactional
    public boolean updateAllColumnById(T t) {
        return SqlHelper.retBool(mapper.updateAllColumnById(t));
    }

    @Override
    @Transactional
    public boolean update(T t, Wrapper<T> wrapper) {
        return SqlHelper.retBool(mapper.update(t, wrapper));
    }

    @Override
    @Transactional
    public boolean updateForSet(String s, Wrapper<T> wrapper) {
        return SqlHelper.retBool(mapper.updateForSet(s, wrapper));
    }

    @Override
    @Transactional
    public boolean updateBatchById(List<T> list) {
        return updateBatchById(list, UPDATE_BATCH_SIZE);
    }

    @Override
    @Transactional
    public boolean updateBatchById(List<T> list, int i) {
        return doUpdateBatchById(list, i, false);
    }

    @Override
    @Transactional
    public boolean updateAllColumnBatchById(List<T> list) {
        return updateAllColumnBatchById(list, UPDATE_BATCH_SIZE);
    }

    @Override
    @Transactional
    public boolean updateAllColumnBatchById(List<T> list, int i) {
        return doUpdateBatchById(list, i, true);
    }

    /**
     * 根据ID批量更新数据.
     *
     * @param list
     * @param batchSize
     * @param allColumn 是否更新全部列
     * @return true, 表示成功,false 表示失败
     */
    private boolean doUpdateBatchById(List<T> list, int batchSize, boolean allColumn) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("Error: list must not be empty.");
        }

        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = list.size();

            //防止错误设置批处理批次条数
            int finalBatchSize = Math.min(batchSize, MAX_BATCH_SIZE);
            SqlMethod sqlMethod = allColumn ? SqlMethod.UPDATE_BY_ID : SqlMethod.UPDATE_ALL_COLUMN_BY_ID;
            String sqlStatement = sqlStatement(sqlMethod);

            for (int i = 0; i < size; i++) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put("et", list.get(i));
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % finalBatchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new MybatisPlusException("Error: Cannot execute doUpdateBatchById Method. Cause", e);
        }
        return true;
    }

    /**
     * 通过实体类反射的方式进行sql操作.
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public boolean insertOrUpdate(T entity) {
        if (Objects.nonNull(entity)) {
            Class<?> clazz = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
            //判断表不为空,并且存在主键属性
            if (Objects.nonNull(tableInfo) && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(clazz, entity, tableInfo.getKeyProperty());
                //如果主键值为null则插入
                if (Objects.isNull(idVal)) {
                    return insert(entity);
                } else {
                    return updateById(entity) || insert(entity);
                }
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean insertOrUpdateAllColumn(T entity) {
        if (Objects.nonNull(entity)) {
            Class<?> clazz = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
            //判断表不为空,并且存在主键属性
            if (Objects.nonNull(tableInfo) && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(clazz, entity, tableInfo.getKeyProperty());
                //如果主键值为null则插入
                if (Objects.isNull(idVal)) {
                    return insertAllColumn(entity);
                } else {
                    return updateAllColumnById(entity) || insertAllColumn(entity);
                }
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Override
    public T selectById(Serializable serializable) {
        return (T) mapper.selectById(serializable);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> collection) {
        return mapper.selectBatchIds(collection);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public T selectOne(Wrapper<T> wrapper) {
        List<T> list = mapper.selectList(wrapper);
        return SqlHelper.getObject(list);
    }

    @Override
    public Map<String, Object> selectMap(Wrapper<T> wrapper) {
        return SqlHelper.getObject((List<Map<String, Object>>) mapper.selectMaps(wrapper));
    }

    @Override
    public Object selectObj(Wrapper<T> wrapper) {
        return SqlHelper.getObject(mapper.selectObjs(wrapper));
    }

    @Override
    public int selectCount(Wrapper<T> wrapper) {
        return mapper.selectCount(wrapper);
    }

    @Override
    public List<T> selectList(Wrapper<T> wrapper) {
        return mapper.selectList(wrapper);
    }

    @Override
    public Page<T> selectPage(Page<T> page) {
        return selectPage(page, Condition.EMPTY);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> wrapper) {
        return mapper.selectMaps(wrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<T> wrapper) {
        return mapper.selectObjs(wrapper);
    }

    @Override
    public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<T> wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(mapper.selectMapsPage(page, wrapper));
        return page;
    }

    @Override
    public Page<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(mapper.selectPage(page, wrapper));
        return page;
    }
}
