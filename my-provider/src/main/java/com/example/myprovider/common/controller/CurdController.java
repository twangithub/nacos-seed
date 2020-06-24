package com.example.myprovider.common.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myprovider.common.config.Result;
import com.example.myprovider.common.config.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * provide the basic curd method
 * include list/insert/view/update/delete
 */
@Slf4j
public class CurdController<T> {

    @Autowired
    private BaseMapper<T> mapper;

    /**
     * 在搜索的时候，去除这几个 map 参数
     */
    private String[] pageParams = {"size", "current", "orders"};

    /**
     * mp自带的分页,也可以使用pageHelper的分页 {@link #list}
     * example:
     * {
     *     "size": 15,            默认：10
     *     "current": 1,          当前页码
     *     "id": 1162,            SQL查询条件
     *     "orders": ["id desc"]  排序条件，可以设置多个
     * }
     * @param map
     * @return
     */
    @PostMapping( value = {"/listByPage", "/index", "/list"})
    @ResponseBody
    public Result listByPage(@RequestBody Map<String, Object> map) {
        return ResultGenerator.genSuccessResult(
                mapper.selectPage(
                        extractPageFromRequestMap(map),
                        extractWrapperFromRequestMap(map)
                )
        );
    }

    @PostMapping( value = {"/save", "/insert"} )
    @ResponseBody
    public Result save(@RequestBody T map) {
        return ResultGenerator.genSuccessResult(mapper.insert(map));
    }

    @GetMapping( value = {"/getById", "/get"} )
    @ResponseBody
    public Result getById(@RequestParam Long id) {
        return ResultGenerator.genSuccessResult(mapper.selectById(id));
    }

    @PostMapping( value = {"/updateById", "/update"} )
    @ResponseBody
    public Result updateById(@RequestBody T map) {
        return ResultGenerator.genSuccessResult(mapper.updateById(map));
    }

    @PostMapping( value = {"/deleteById", "/delete"} )
    @ResponseBody
    public Result deleteById(@RequestParam Long id) {
        return ResultGenerator.genSuccessResult(mapper.deleteById(id));
    }

    //采用pageHelper物理分页
    @PostMapping("/list2")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Wrapper wrapper = new QueryWrapper();
        List<T> list = mapper.selectList(wrapper);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /**
     * 从请求体中提取查询参数
     * @param map
     * @return
     */
    private QueryWrapper<T> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        for (String pageParam : pageParams) {
            map.remove(pageParam);
        }
        queryWrapper.allEq(map, false);
        return queryWrapper;
    }

    /**
     * 从请求体中提取分页参数
     * @param map
     * @return
     */
    private Page<T> extractPageFromRequestMap(Map<String, Object> map) {

        Page<T> page = new Page<>();

        String key = pageParams[0];
        if (map.containsKey(key) && map.get(key) instanceof Integer) {
            page.setSize((Integer)map.get(key));
        }

        key = pageParams[1];
        if (map.containsKey(key) && map.get(key) instanceof Integer) {
            page.setCurrent((Integer)map.get(key));
        }

        // 排序
        key = pageParams[2];
        if (map.containsKey(key) && map.get(key) instanceof List) {
            List<OrderItem> orderItemList = new ArrayList<>();
            for (String orderArrStr : (List<String>)map.get(key)) {
                if (StringUtils.isBlank(orderArrStr) || !orderArrStr.contains(" ")) {
                    continue;
                }
                String[] orderArr = orderArrStr.split(" ");
                if ("desc".equals(orderArr[1])) {
                    orderItemList.add(OrderItem.desc(orderArr[0]));
                } else {
                    orderItemList.add(OrderItem.asc(orderArr[0]));
                }
            }
            page.setOrders(orderItemList);
        }

        return page;
    }
}
