package com.mici.eduservice.service;

import com.mici.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mici.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-02
 */
public interface EduSubjectService extends IService<EduSubject> {


    void saveSubject(MultipartFile file , EduSubjectService EduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
