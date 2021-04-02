package com.mici.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mici.commonutils.R;
import com.mici.eduservice.entity.EduTeacher;
import com.mici.eduservice.entity.vo.TeacherQuery;
import com.mici.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-19
 */
@RestController
@Api(description = "讲师管理")
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    @ApiOperation(value = "所有的讲师列表")
    public R findAllTeacher(){
        return R.ok().data("items",eduTeacherService.list(null));
    }


    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "查询对应ID讲师")
    public R getTeacherById(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        EduTeacher teacherServiceById = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacherServiceById);
    }

    @PostMapping("updateTeacher")
    @ApiOperation(value = "修改讲师")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "删除讲师列表传入讲师ID")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b)
            return R.ok();
        else
            return R.error();
    }

    @ApiOperation(value = "讲师分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value ="当前页数" ,required = true) @PathVariable long current,
                             @ApiParam(name = "limit",value ="每页分页数量" ,required = true) @PathVariable long limit){

        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        eduTeacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }


    @ApiOperation(value = "讲师分页查询带查询的条件")
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value ="当前页数" ,required = true) @PathVariable long current,
                             @ApiParam(name = "limit",value ="每页分页数量" ,required = true) @PathVariable long limit,
                             @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> teacherPage = new Page<>(current,limit);

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();


        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        wrapper.orderByDesc("gmt_create");

        eduTeacherService.page(teacherPage,wrapper);

        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }

    }

