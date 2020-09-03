package com.zxjx.controller;

import com.zxjx.entity.Comment;
import com.zxjx.entity.CourseClass;
import com.zxjx.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:10
 */
@RestController
public class CommentController {

    @Autowired
    private ICommentService service;

    @GetMapping( value = "/zxjx/comment/selectById/{id}")
    public Comment selectById(@PathVariable("id") Long id){

        return service.selectById(Comment.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/comment/insert")
    public int insert(Comment comment){
        return service.insert(comment);
    }

    @PostMapping( value = "/zxjx/comment/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(Comment.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/comment/update")
    public int update(@RequestBody Comment comment){
        return service.updateByPrimaryKey(comment);
    }
}
