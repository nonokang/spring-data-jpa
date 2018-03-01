package com.spring.data.jpa.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageUtils {

    /**
     * 封装分页数据到Map中。
     */
    /*public static Map<String, Object> getPageMap(Page<?> objPage) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(Constants.PAGE_RESULT_LIST, objPage.getContent()); // 数据集合
        resultMap.put(Constants.PAGE_TOTAL_NUM, objPage.getTotalElements()); // 总记录数
        resultMap.put(Constants.PAGE_TOTAL_PAGE, objPage.getTotalPages()); // 总页数
        resultMap.put(Constants.PAGE_NUM, objPage.getNumber()); // 当前页码
        resultMap.put(Constants.PAGE_SIZE, objPage.getSize()); // 每页显示数量

        return resultMap;
    }*/

    /**
     * 创建分页请求。
     * 
     * @author YangZhenghua
     * @date 2014-7-14
     * 
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param sortType 排序字段
     * @param direction 排序方向
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortType, String direction) {
        Sort sort = null;

        if (null != sortType) {
//            return PageRequest.of(pageNum - 1, pageSize);
            return new PageRequest(pageNum - 1, pageSize);
        } else if (null != direction) {
            if (Direction.ASC.equals(direction)) {
                sort = new Sort(Direction.ASC, sortType);
            } else {
                sort = new Sort(Direction.DESC, sortType);
            }
//            return PageRequest.of(pageNum - 1, pageSize, sort);
            return new PageRequest(pageNum - 1, pageSize, sort);
        } else {
            sort = new Sort(Direction.ASC, sortType);
//            return PageRequest.of(pageNum - 1, pageSize, sort);
            return new PageRequest(pageNum - 1, pageSize, sort);
        }
    }

    /**
     * 创建分页请求(该方法可以放到util类中).
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortType) {
        return buildPageRequest(pageNum, pageSize, sortType, null);
    }
    
    /**
     * 创建分页请求
     * 
     * @author YangZhenghua
     * @date 2014-11-12
     * 
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, Sort sort) {
//        return PageRequest.of(pageNum - 1, pageSize, sort);
        return new PageRequest(pageNum - 1, pageSize, sort);
    }

    /**
     * 创建分页请求(该方法可以放到util类中).
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize) {
        return buildPageRequest(pageNum, pageSize, null, null);
    }
}
