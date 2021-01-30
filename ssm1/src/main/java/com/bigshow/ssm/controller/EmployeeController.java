package com.bigshow.ssm.controller;


import com.bigshow.ssm.entity.Employee;
import com.bigshow.ssm.entity.Msg;
import com.bigshow.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//处理员工crud请求
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /*
    单独删除和全部删除结合
     */
    @ResponseBody
    @RequestMapping(value="/emp/{ids}", method=RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            for(String str : str_ids){
                del_ids.add(Integer.parseInt(str));
            }
            employeeService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }

    @RequestMapping(value="/emp/{empId}", method=RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @RequestMapping(value = "/emp/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value="pn", defaultValue = "1") Integer pn){
        PageHelper.startPage(pn, 5);
//        startpage后面紧跟分页查询
        List<Employee> list = employeeService.getAll();
//        只需要将PageInfo交给页面即可,传入连续显示的页数为5
        PageInfo page = new PageInfo(list, 5);
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping(value = "/emp", method= RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError fieldError : errors){
                System.out.println("错误字段名:" + fieldError.getField());
                System.out.println("错误信息: " + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }
//    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value="pn", defaultValue = "1") Integer pn, Model model){
//        PageHelper.startPage(pn, 5);
////        startpage后面紧跟分页查询
//        List<Employee> list = employeeService.getAll();
////        只需要将PageInfo交给页面即可,传入连续显示的页数为5
//        PageInfo page = new PageInfo(list, 5);
//        model.addAttribute("pageInfo", page);
//        return "list";
//    }
    /*
    检查用户名是否可用
     */
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("empName") String empName){
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        boolean notExist = employeeService.checkUser(empName);
        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg", "用户名字必须是6-16位和字母或者2-6位中文");
        }
        if(notExist){
            return Msg.success();
        }else{
            return Msg.fail().add("va_msg", "用户名已经存在");
        }
    }

}
