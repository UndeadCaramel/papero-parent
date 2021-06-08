package com.papero.serviceedu.controller;

import com.papero.commonutils.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        Map user=new HashMap();
        user.put("name","tom");
        user.put("roles","[admin]");
        user.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return R.ok().data(user);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
