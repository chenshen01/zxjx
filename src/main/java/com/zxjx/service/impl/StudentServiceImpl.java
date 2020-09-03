package com.zxjx.service.impl;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.*;
import com.zxjx.service.*;
import com.zxjx.component.CodeUtil;
import com.zxjx.utils.DateUtil;
import com.zxjx.component.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 19:54
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl extends BaseServiceImpl<Student> implements IStudentService {

    @Autowired
    private CodeUtil codeUtil;

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICoursesService coursesService;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IAdminService adminService;

    /**
     * <p>
     *购买课程
     * </p>
     *
     * @param courseIds 购买的课程id
     * @param studentId 学生id
     * @param amount  总金额
     * @param payType 支付方式
     * @return message
     * @author liuzhixiang 2020/05/04 18:39
     */
    @Override
    public String buyCourses(Long studentId, BigDecimal amount, List<Long> courseIds,String payType){
        // 1、支付

        // 2、生成订单
        Student student = studentService.selectById(Student.builder().id(studentId).build());
        String orderNumber = codeUtil.getOrderNumber();
        ordersService.insert(Orders.builder().buyerAddress(student.getSchool()).moneyAccount(amount).buyerId(studentId)
             .buyerName(student.getName()).buyerPhone(student.getPhone()).updateTime(DateUtil.getCurrentDate())
             .paymentStatus(Orders.PAY_SUCCESS).orderStatus(Orders.PAY_SUCCESS).orderNumber(orderNumber)
             .build());
        List<Orders> orders = ordersService.selectByCondition(Orders.builder().orderNumber(orderNumber).build());
        Orders curOrder = orders.get(0);
        if (courseIds != null && !courseIds.isEmpty()) {
            courseIds.forEach( courseId -> {
                Courses courses = coursesService.selectById(Courses.builder().id(courseId).build());
                // 3、生成订单细节
                orderDetailService.insert(OrderDetail.builder().coursePrice(courses.getPrice()).courseId(courseId)
                    .courseImage(courses.getCourseImage()).orderId(curOrder.getId()).orderNumber(curOrder.getOrderNumber())
                    .build());
                // 4、修改购买课程人数
                Integer studiedAccount = courses.getStudiedAccount() == null ? 0 : courses.getStudiedAccount();
                courses.setStudiedAccount(studiedAccount + 1);
                coursesService.updateByPrimaryKey(courses);
                // 5、如果购买课程人数大于当前班级容量通知管理员新建班级
                Courses newCourses = coursesService.selectById(courses);
                List<Classes> classes = classesService.selectByCondition(Classes.builder().courseId(newCourses.getId())
                    .build());
                if (classes != null && !classes.isEmpty()) {
                    int account = 0;
                    int newClassCapacity = 0;
                    for (Classes classes1 : classes) {
                        account += classes1.getClassCapacity();
                    }
                    if (newCourses.getStudiedAccount() > account) {
                        newClassCapacity = newCourses.getStudiedAccount() - account;
                        String value = String.valueOf(newClassCapacity);
                        List<Admin> admins = adminService.selectAll(Admin.builder().build());
                        if (admins != null && !admins.isEmpty()) {
                            admins.forEach(admin -> {
                                mailUtil.sendMail(admin.getEmail(),BaseMassage.SUBJECT,"课程id为" + courses.getId() +
                                    " 课程名字为" + courses.getCourseName() + " 的购买人数已经大于当前平台的班级容量，具体超过：" + value +
                                    " 请尽快创建班级！");
                            });
                        }
                    }
                }
            });
        }
        return BaseMassage.BUY_SUCCESS;
    }
}
