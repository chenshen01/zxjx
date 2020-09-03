package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description 课程种类
 *
 * @author liuzhixiang 2020/04/02 16:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_course_class")
public class CourseClass implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 课程种类
     */
    @Column(name = "course_class_name")
    private String courseClassName;

}
