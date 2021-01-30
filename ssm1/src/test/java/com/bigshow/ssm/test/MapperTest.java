package com.bigshow.ssm.test;

import com.bigshow.ssm.dao.DepartmentMapper;
import com.bigshow.ssm.dao.EmployeeMapper;
import com.bigshow.ssm.entity.Department;
import com.bigshow.ssm.entity.Employee;
import com.bigshow.ssm.service.EmployeeService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;



    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);


//        插入部门试试

//        departmentMapper.insertSelective(new Department(null, "开发部"));
//        departmentMapper.insertSelective(new Department(null, "测试部"));
//        插入员工试试
//        employeeMapper.insertSelective(new Employee(null, "老王", "M", "stupid@qq.com",1));
//        批量插入多个员工
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for(int i = 0; i < 1000; i++){
//            String name = UUID.randomUUID().toString().substring(0, 5) + i;
//            mapper.insertSelective(new Employee(null, name, "M", name + "@bupt.com",1));
//        }
//        System.out.println("task is completed");

    }
}
