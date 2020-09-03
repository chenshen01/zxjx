package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description 选课表
 *
 * @author liuzhixiang 2020/04/04 12:04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_select_course")
public class SelectCourse implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 班级id
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 课程id
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 选课人
     */
    @Column(name = "selected_by")
    private Long selectedBy;
}
