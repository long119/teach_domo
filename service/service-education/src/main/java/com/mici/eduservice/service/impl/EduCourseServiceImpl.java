package com.mici.eduservice.service.impl;

import com.mici.eduservice.entity.EduCourse;
import com.mici.eduservice.entity.EduCourseDescription;
import com.mici.eduservice.entity.vo.CourseInfo;
import com.mici.eduservice.mapper.EduCourseMapper;
import com.mici.eduservice.service.EduCourseDescriptionService;
import com.mici.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mici.servicebase.config.exceptionhandler.MiciException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0){
          throw new MiciException(20001,"添加信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        courseDescriptionService.save(eduCourseDescription);

        return eduCourse.getId();
    }
}
