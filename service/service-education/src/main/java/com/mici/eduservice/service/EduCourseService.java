package com.mici.eduservice.service;

import com.mici.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mici.eduservice.entity.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfo courseInfo);
}
