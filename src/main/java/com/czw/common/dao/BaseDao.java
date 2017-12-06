package com.czw.common.dao;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.czw.common.entity.BaseEntity;
import com.czw.common.tools.ReflectionTools;
import com.czw.common.tools.Tools;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月5日
 * www.cuizhiwei.com
 */
@SuppressWarnings("unchecked")
public class BaseDao <T extends BaseEntity> extends HibernateDaoSupport{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<T> entityClass;
	private static final Map<String, String> entityClassIdName = new HashMap<String, String>();
	/**
	 * 构造方法
	 */
	public BaseDao()
	{
		this.entityClass = ReflectionTools.getSuperClassGenricType(getClass(),0);
	}
	
	@Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	public Session getXSession()
	{
		Session session = super.getSessionFactory().openSession();
		return session;
	}
	
	public String getEntityIdDefineName(){
		if(entityClass != null){
			String className = entityClass.getName();
			String define = entityClassIdName.get(className);
			if(define == null){
				synchronized (entityClassIdName) {
					try {
						Class c = entityClass;
						while(c != null && define == null){
							Field[] fs = c.getDeclaredFields();
							for(Field f : fs){
								try {
									f.setAccessible(true);
									if(f.getAnnotation(Id.class)!=null){
										define = f.getName();
										break;
									}
								} catch (Exception e) {
									
								}
							}
							if(define == null){
								c = c.getSuperclass();
							}
						}
					} catch (Exception e) {
						
					}
					if(define == null)
						define = "id";
					entityClassIdName.put(className, define);
				}
			}
			return define;
		}
		return "id";
	}
	
	public Long save(T entity){
		Assert.notNull(entity, "entity不能为空");	
		if(entity instanceof BaseEntity){
			Date date = new Date();
			((BaseEntity)entity).setCreateTime(date);//插入时间
			((BaseEntity)entity).setUpdateTime(date);//插入最后更新时间和插入时间一致
		}
		Long id = (Long) getHibernateTemplate().save(entity);
		return id;
	}
	
	public void saveOrUpdate(T entity){
		Assert.notNull(entity, "entity不能为空");	
		if(entity instanceof BaseEntity && ((BaseEntity)entity).getCreateTime() == null)
			((BaseEntity)entity).setCreateTime(new Date());//插入时间
		getHibernateTemplate().saveOrUpdate(entity);
		if(getXSession().isDirty()){
			if(entity instanceof BaseEntity){
				((BaseEntity)entity).setUpdateTime(new Date());
				getHibernateTemplate().update(entity);
			}
		}
	}
	
	public void update(T entity){
		Assert.notNull(entity, "entity不能为空");		
		getHibernateTemplate().update(entity);
		if(getXSession().isDirty()){
			if(entity instanceof BaseEntity){
				((BaseEntity)entity).setUpdateTime(new Date());
				getHibernateTemplate().update(entity);
			}
		}
	}
	
	public void delete(T entity){
		Assert.notNull(entity, "entity不能为空");		
		getHibernateTemplate().delete(entity);
	}
	
	public void delete(Long id){
		delete(find(id));
	}
	
	public void delete(String hql){
		Query query = getXSession().createQuery(hql);
		query.executeUpdate();
	}
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public T find(Long id){
		Assert.notNull(id, "id不能为空");
		T entity = null;
		entity = (T)getHibernateTemplate().load(entityClass, id);
		return entity;
	}
	/**
	 * 通过限定一系列条件进行唯一查询
	 * @param query
	 * @return
	 */
	public T find(Map<String , Object> query){
		T entity = null;
		try {
			StringBuffer hql = new StringBuffer("form ").append(entityClass.getSimpleName()).append(" a where");
			HashMap<String, Object> map = new HashMap<String, Object>();
			int i = 0;
			for(Map.Entry<String, Object> en:query.entrySet()){
				if(Tools.stringIsNotNull(en.getKey())){
					map.put(en.getKey(), en.getValue());
					if(i>0){
						hql.append(" and");
					}
					hql.append(" a.").append(en.getKey()).append("=:").append(en.getValue());
					i++;
				}
			}
			String idname = this.getEntityIdDefineName();
			try {
				if(entityClass.getField(idname) != null)
					hql.append(" order by a."+idname+" desc");
			} catch (Exception e) {
				// TODO: handle exception
			}
			List<T> list = this.findList(hql.toString(), map, 0, 1);
			if(list != null && list.size()>0){
				entity = list.get(0);
			}
			return entity;
		} catch (Exception e) {
			
		}
		return null;
		
	}
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T find(String fileName,Object fieldValue){
		Criterion criterion = Restrictions.eq(fileName, fieldValue);
		T t = (T) createCriteria(getXSession(), entityClass, criterion).setCacheable(isUseQueryListCache()).uniqueResult();
		return t;
	}
	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findList(String fileName,Object fieldValue){
		Criterion criterion = Restrictions.eq(fileName, fieldValue);
		List<T> l = createCriteria(getXSession(), entityClass, criterion).setCacheable(isUseQueryListCache()).list();
		return l;
	}
	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListByIds(List<Long> idList)
	{
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			List<T> l = createCriteria(getXSession(), entityClass, criterion).setCacheable(isUseQueryListCache()).list();
			return l;
		} else
		{
			return null;
		}
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> X find(String hql, Object... values)
	{
		X x = (X) createQuery(getXSession(), hql, values).setCacheable(isUseQueryListCache()).uniqueResult();
		return x;
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <X> X find(String hql, Map<String, ?> values)
	{
		X x = (X) createQuery(getXSession(), hql, values).setCacheable(isUseQueryListCache()).uniqueResult();
		return x;
	}
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findList(String hql, Object... values)
	{
		List<X> l = createQuery(getXSession(), hql, values).setCacheable(isUseQueryListCache()).list();
		return l;
	}
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	public <X> List<X> findList(String hql, Map<String, ?> values)
	{
		List<X> l = createQuery(getXSession(), hql, values).setCacheable(isUseQueryListCache()).list();
		return l;
	}
	/**
	 * 本地SQL进行CRUD操作.
	 * @return 更新记录数.
	 */
	public List getSql(String sql)
	{
		Assert.hasText(sql, "sql不能为空");
		List l = getXSession().createSQLQuery(sql).setCacheable(isUseQueryListCache()).list();
		return l;
	}
	
	
	public <X> List<X> findList(String hql, Map<String, ?> values, boolean cacheable)
	{
		List<X> l = createQuery(getXSession(), hql, values).setCacheable(cacheable).list();
		return l;
	}
	
	public <X> List<X> findList(String hql, Map<String, ?> values, int startPos, int pageSize)
	{
		Query query = getXSession().createQuery(hql);
		List<X> l = createQuery(getXSession(), hql, values).setFirstResult(startPos).setMaxResults(pageSize).setCacheable(isUseQueryListCache()).list();
		return l;
	}
	
	
	
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public Query createQuery(Session session, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		if (values != null)
		{
			query.setProperties(values);
		}
		return query;
	}
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public Query createQuery(Session session, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
		return query;
	}
	/**
	 * 根据Criterion条件创建Criteria.
	 */
	public Criteria createCriteria(Session session, Class<? extends BaseEntity> entityClass, Criterion... criterions)
	{
		Criteria criteria = session.createCriteria(entityClass);
		for (Criterion criterion : criterions)
		{
			criteria.add(criterion);
		}
		return criteria;
	}
	private boolean isUseQueryListCache()
	{
		return false;
	}
	
	
}
