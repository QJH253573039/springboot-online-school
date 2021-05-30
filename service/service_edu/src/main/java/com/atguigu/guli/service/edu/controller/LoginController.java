package com.atguigu.guli.service.edu.controller;

import com.atguigu.guli.common.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("user")
public class LoginController {


    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }


    @GetMapping("info")
    public R info(String token) {
        return R.ok()
                .data("name", "admin")
                .data("roles", "[admin]")
                .data("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }


    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }


}
