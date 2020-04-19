package com.imnu.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

public class SimpleBizservice {
    @Autowired
    private MapperClass mapper = null;

    public SimpleBizservice() {
    }

    public MapperClass getMapper() {
        return this.mapper;
    }

    public List getEntity() {
        return this.mapper.getAllEntity();
    }

    public <T> T save(T t) {
        Class[] funIntf = this.mapper.getClass().getInterfaces();
        Class<?> mapperClazz = null;
        if (funIntf != null && funIntf.length > 0) {
            mapperClazz = funIntf[0];
        }

        Class<?> clz = this.getEntityClass(mapperClazz);
        if (clz != null && !clz.isInstance(t)) {
            System.out.println("不是实体类");
            return null;
        } else {
            this.mapper.save(t);
            return t;
        }
    }

    public List getPageEntitys(String where, int pageIndex, int pageSize) {
        return this.mapper.getPageEntitys(where, pageIndex, pageSize);
    }

    public int getRecordCount(String where) {
        return this.mapper.getRecordCount(where);
    }

    public List getEntity(String where) {
        return this.mapper.getEntitys(where);
    }

    public <T> List<T> getTopEntity(String where, int topCount) {
        return this.mapper.getTopEntity(where, topCount);
    }

    private Class<?> getEntityClass(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        Class<?> entityClass = null;
        Type[] var7 = types;
        int var6 = types.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            Type type = var7[var5];
            if (type instanceof ParameterizedType) {
                ParameterizedType t = (ParameterizedType)type;
                if (t.getRawType() == SimpleMapper.class) {
                    entityClass = (Class)t.getActualTypeArguments()[0];
                    break;
                }
            }
        }

        return entityClass;
    }

    public <T> int update(T t) {
        return this.mapper.update(t);
    }

    public int delete(String where) {
        return this.mapper.delete(where);
    }

    public Boolean isExist(String where) {
        return this.getRecordCount(where) > 0;
    }

    public <T> T load(int id) {
        Class[] funIntf = this.mapper.getClass().getInterfaces();
        Class<?> mapperClazz = null;
        if (funIntf != null && funIntf.length > 0) {
            mapperClazz = funIntf[0];
        }

        Class<?> entityClzz = this.getEntityClass(mapperClazz);
        TableMetadata tm = TableMetadata.forClass(entityClzz);
        String where = MessageFormat.format("where  {0}={1}", tm.getPkColumn(), id);
        List list = this.getEntity(where);
        return list == null ? null : list.get(0);
    }

    public <T> T load(String where) {
        List<T> list = this.mapper.getEntitys(where);
        return list != null && list.size() != 0 ? list.get(0) : null;
    }

    public <T> List<T> query(String nativateSql) {
        return this.mapper.query(nativateSql);
    }

    public List<HashMap<String, Object>> queryToMap(String nativateSql) {
        return this.mapper.queryToMap(nativateSql);
    }

    public int executeUpdate(String sql) {
        return this.mapper.nativeUpdate(sql);
    }
}
