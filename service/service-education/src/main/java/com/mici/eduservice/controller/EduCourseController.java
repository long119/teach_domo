package com.mici.eduservice.controller;


import com.mici.commonutils.R;
import com.mici.eduservice.entity.vo.CourseInfo;
import com.mici.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {


    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo){
     String id = courseService.saveCourseInfo(courseInfo);

        return R.ok().data("courseId",id);
    }


}

