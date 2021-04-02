package com.mici.eduservice.controller;


import com.mici.commonutils.R;
import com.mici.eduservice.entity.EduSubject;
import com.mici.eduservice.entity.subject.OneSubject;
import com.mici.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    //课程列表树形
    @GetMapping("getAllSubject")
    public R getAllSubject(){
       List<OneSubject> lists = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",lists);
    }

}

