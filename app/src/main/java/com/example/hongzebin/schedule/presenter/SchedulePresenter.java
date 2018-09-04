package com.example.hongzebin.schedule.presenter;

import com.example.hongzebin.schedule.bean.Course;
import com.example.hongzebin.schedule.bean.TableModel;
import com.example.hongzebin.schedule.model.ScheduleModel;
import com.example.hongzebin.schedule.util.GsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 进行数据操作后返回给ScheduleActivity
 * Created By Mr.Bean
 */
public class SchedulePresenter {

    private List<Course> mCourseList;

    public interface schedulePresenterCallBack{
        void onLoading(TableModel tableModel);
        void onFinish(List<TableModel> list);
    }

    /**
     * 获取到课表数据
     * @param flag  数据库中是否已经有课程数据
     * @param json  课程表json数据
     * @param callBack  接口
     */
    public void loadData(boolean flag, String json, schedulePresenterCallBack callBack){
        ScheduleModel scheduleModel = new ScheduleModel();
        List<TableModel> tableModelList = new ArrayList<>();
        if (!flag) {
            //从数据库获取数据
            scheduleModel.getData(new ScheduleModel.GreenDaoCallBack() {
                @Override
                public void onFinish(List<Course> list) {
                    mCourseList = list;
                }
            });
        } else {
            //解析json数据
            GsonUtil gson = new GsonUtil();
            mCourseList = gson.parseJSONWithGson(json);
            scheduleModel.saveData(mCourseList);
        }
        for (Course course : mCourseList) {
            TableModel tableModel = turnToTableModel(course);
            tableModelList.add(tableModel);
            callBack.onLoading(tableModel);
        }
        callBack.onFinish(tableModelList);
    }

    /**
     * 把解析的得到的对象，转换成要放到课程表上的对象
     *
     * @param course 把gson解析得到的对象，进行转换
     * @return 转换后的对象
     */
    private TableModel turnToTableModel(Course course) {
        List<String> timeList = Arrays.asList(course.getTime().split(","));
        List<String> weekList = Arrays.asList(course.getWeek().split(","));
        return new TableModel(course.getName(), course.getNumber(), course.getMajor()
                , course.getFigureNumber(), timeList, weekList, course.getWeekday()
                , course.getPlace(), course.getTeacher());
    }

}
