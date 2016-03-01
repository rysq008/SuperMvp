package com.ly.supermvp.model.entity;

import java.util.List;

/**
 * <Pre>
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:28
 */
public class NewsResponse {
    public String showapi_res_code;
    public String showapi_res_error;
    public ShowApiResBody showapi_res_body;

    public class ShowApiResBody {
        public PageBean pagebean;
        public String ret_code;
    }
    public class PageBean{
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<NewsBody> contentlist;
    }
}
