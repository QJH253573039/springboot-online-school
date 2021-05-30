package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author QJH
 * @since 2020-08-12
 */
@Api(description = "讲师管理")
@CrossOrigin // 允许跨域
@RestController
@RequestMapping("admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @ApiOperation("获取讲师列表")
    @GetMapping("list")
    public R list() {
        return R.ok().data("list", teacherService.list());
    }


    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("remove/{id}")
    public R remove(@ApiParam("讲师ID") @PathVariable String id) {
        boolean boo = teacherService.removeById(id);
        if (true == boo) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }


    @ApiOperation("分页列表")
    @GetMapping("page/{pageNo}/{pageSize}")
    public R page(@ApiParam("页码") @PathVariable("pageNo") Integer pageNo,
                  @ApiParam("单页数量") @PathVariable("pageSize") Integer pageSize,
                  @ApiParam("老师对象") Teacher param) {
        Page<Teacher> page = teacherService.page(
                new Page<>(pageNo, pageSize),
                null == param ? null : new QueryWrapper<Teacher>()
                        .like(StringUtils.isNotBlank(param.getName()), "name", param.getName())
                        .eq(null != param.getLevel(), "level", param.getLevel())
        );
        return R.ok().data("page", page);
    }


    @ApiOperation("分页列表")
    @GetMapping("save")
    public R save(@ApiParam("老师对象") @RequestBody Teacher param) {
        boolean boo = teacherService.save(param);
        if (boo == true){
            return R.ok().message("保存成功");
        }else {
            return R.error().message("保存失败");
        }
    }



    @ApiOperation("分页列表")
    @GetMapping("update")
    public R update(@ApiParam("老师对象") @RequestBody Teacher param) {
        boolean boo = teacherService.updateById(param);
        if (boo == true){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }

}

