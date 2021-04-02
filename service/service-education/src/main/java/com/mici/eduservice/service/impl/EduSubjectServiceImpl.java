package com.mici.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mici.eduservice.entity.EduSubject;
import com.mici.eduservice.entity.excel.SubjectData;
import com.mici.eduservice.entity.subject.OneSubject;
import com.mici.eduservice.entity.subject.TwoSubject;
import com.mici.eduservice.listener.SubjectExcelListener;
import com.mici.eduservice.mapper.EduSubjectMapper;
import com.mici.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try (InputStream in = file.getInputStream()) {
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        QueryWrapper<EduSubject> wrapperone = new QueryWrapper<>();
        wrapperone.eq("parent_id","0");
        List<EduSubject> onesEduSubjects = baseMapper.selectList(wrapperone);

        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoEduSubjects = baseMapper.selectList(wrapperTwo);

        List<OneSubject> finalSubjectList = new ArrayList<>();

        for (int i = 0; i < onesEduSubjects.size(); i++) {
            EduSubject eduSubject = onesEduSubjects.get(i);

            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());

            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);

            List<TwoSubject> twoSubjectsFinalList = new ArrayList<>();

            for (int j = 0; j <twoEduSubjects.size() ; j++) {
                EduSubject eduSubject2 = twoEduSubjects.get(j);

                if(eduSubject2.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    twoSubjectsFinalList.add(twoSubject);
                }

            }

            oneSubject.setChildren(twoSubjectsFinalList);
        }

        return finalSubjectList;
    }
}
