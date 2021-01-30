package com.bigshow.ssm.controller;

import com.bigshow.ssm.entity.Department;
import com.bigshow.ssm.entity.Msg;
import com.bigshow.ssm.service.DepartMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartMentService departMentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list = departMentService.getDepts();
        return Msg.success().add("depts", list);
    }


}
