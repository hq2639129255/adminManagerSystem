package com.my.dao.imple;

import com.my.bean.Course;
import com.my.dao.BaseDao;
import com.my.dao.CourseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoImple extends BaseDao<Course> implements CourseDao {
    @Override
    public List<Course> findAllCourse(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
String sql="SELECT `id`,`c_name` cName,`price`  FROM `course`";
        List<Course> data = this.getListInstence(con, sql);
        return data;
    }
}
