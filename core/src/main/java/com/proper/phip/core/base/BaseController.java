package com.proper.phip.core.base;

import com.github.pagehelper.Page;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ResultPage;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.restful.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Controller基类
 */
public class BaseController {

    // 默认分页记录数
    private static final String PAGE_SIZE = "10";

    /**
     * 自定义请求返回错误处理
     */
    @ExceptionHandler(ReturnException.class)
    public ResponseEntity<String> handleException(ReturnException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    /**
     * 取得当前登录用户ID
     */
    protected String getCurrentUserId() {
        String userId = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator iterator = authentication.getAuthorities().iterator();
        if (((Iterator) iterator).hasNext()) {
            userId = iterator.next().toString();
        }
        return userId;
    }

    /**
     * 取得分页信息
     */
    protected Pagination getPagination(HttpServletRequest request) {
        Pagination pagination = new Pagination();
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isEmpty(currentPage)) {
            currentPage = "1";
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = PAGE_SIZE;
        }
        pagination.setCurrent(Integer.parseInt(currentPage));
        pagination.setPageSize(Integer.parseInt(pageSize));
        return pagination;
    }

    /**
     * 设置分页返回值
     */
    protected <T> ResultPage<T> responsePage(List<T> list) {
        Page<T> page = (Page<T>) list;
        ResultPage<T> resultPage = new ResultPage<T>();
        resultPage.setList(page.getResult());
        Pagination pagination = new Pagination();
        pagination.setCurrent(page.getPageNum());
        pagination.setPageSize(page.getPageSize());
        pagination.setTotal(page.getTotal());
        resultPage.setPagination(pagination);
        return resultPage;
    }

    /**
     * 简单返回值
     */
    protected SimpleResponse responseSimple(String msg) {
        return new SimpleResponse(msg);
    }
}
