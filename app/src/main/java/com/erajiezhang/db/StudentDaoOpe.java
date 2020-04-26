package com.erajiezhang.db;

/**
 * @author EraJieZhang
 * @data 2020/3/29
 * 学生表工具类
 */

import android.content.Context;

import com.erajiezhang.db.dbhelp.StudentDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.Collection;
import java.util.List;

public class StudentDaoOpe {


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<Student> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getStudentDao().insertInTx(list);
    }
    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param student
     */
    public static void saveData(Context context, Student student) {
        DbManager.getDaoSession(context).getStudentDao().save(student);
    }
    /**
     * 删除数据至数据库
     *
     * @param context
     * @param student 删除具体内容（根据bean删除数据）
     */
    public static void deleteData(Context context, Student student) {
        DbManager.getDaoSession(context).getStudentDao().delete(student);
    }
    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容的id
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getStudentDao().deleteByKey(id);
    }
    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getStudentDao().deleteAll();
    }

    /**
     根据参数删除对应姓名的数据
     @param context
     @param w    删除的条件封装{WhereCondition w = StudentDao.Properties.Name.like("EraJieZhnang");}
     */
    public static void deleteDataName(Context context, WhereCondition w) {
        DbManager.getDaoSession(context).getStudentDao().queryBuilder().where(w).
                buildDelete().
                executeDeleteWithoutDetachingEntities();
    }
    /**
     * 更新数据库
     *
     * @param context
     * @param student
     */
    public static void updateData(Context context, Student student) {
        DbManager.getDaoSession(context).getStudentDao().update(student);
    }
    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<Student> queryAll(Context context) {
        QueryBuilder<Student> builder = DbManager.getDaoSession(context).getStudentDao().queryBuilder();

        return builder.build().list();
    }

    /**
     查询指定数据
     @param context
     @param where   whera条件{@"where _id = ?"}
     @param selectionArg    条件里“？”对应的参数 arraylist就行
     @return
     */
    public static List<Student> queryAppoint(Context context, String where, Collection<Object> selectionArg) {

        Query<Student> builder =  DbManager.getDaoSession(context).getStudentDao().
                queryRawCreateListArgs(where,selectionArg);

        return builder.list();
    }



    /**
     *  分页加载
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public static List<Student> queryPaging(int pageSize, int pageNum, Context context){
        StudentDao studentDao = DbManager.getDaoSession(context).getStudentDao();
        List<Student> listMsg = studentDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }
}
