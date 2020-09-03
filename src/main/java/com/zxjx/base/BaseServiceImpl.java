package com.zxjx.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.base.basePage.PageResult;
import com.zxjx.base.basePage.PageUtil;
import com.zxjx.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 10:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl<T> implements IBaseService<T>{
    @Autowired
    private BaseMapper<T> baseMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public T selectById(T t) {
        return baseMapper.selectById(t);
    }

    // to-do 分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> selectAll(T t) {
        return baseMapper.selectAll(t);
    }

    @Override
    //@Transactional()
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return baseMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteByPrimaryKey(T t) {
        return baseMapper.deleteByPrimaryKey(t);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> selectByCondition(T t) {
        return baseMapper.selectByCondition(t);
    }

    /**
     * <p>
     * 根据传入的条件进行分页查询
     * </p>
     *
     * @author liuzhixiang 2020/04/10 22:12
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageResult selectPagesByCondition(PageRequest pageRequest, T t){
        return PageUtil.getPageResult(pageRequest,getPageInfo(pageRequest,t));
    }

    private PageInfo<T> getPageInfo(PageRequest pageRequest, T t){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<T> results = baseMapper.selectByCondition(t);
        return new PageInfo<T>(results);
    }

}
