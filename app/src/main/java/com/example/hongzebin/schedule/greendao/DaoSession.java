package com.example.hongzebin.schedule.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.hongzebin.schedule.bean.Course;

import com.example.hongzebin.schedule.greendao.CourseDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig courseDaoConfig;

    private final CourseDao courseDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        courseDaoConfig = daoConfigMap.get(CourseDao.class).clone();
        courseDaoConfig.initIdentityScope(type);

        courseDao = new CourseDao(courseDaoConfig, this);

        registerDao(Course.class, courseDao);
    }
    
    public void clear() {
        courseDaoConfig.clearIdentityScope();
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

}
