package com.maxrocky.vesta.utility;

/**
 * Created by liuwei on 2016/1/26.
 */
public class Page {

    private Integer defaultPageNumber = 0;  //从第0页开始 limit 10   从第一页开始 limit 1,10
    private Integer defaultPageSize = 10;
    private Integer pageNumber;
    private Integer maxResult;   //query.getMaxResult(page.maxResult)

    private Integer p;
    private Integer c;
    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        pageNumber = p == null?this.defaultPageNumber:p;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        maxResult = c == null?this.defaultPageSize:c;

    }

    public Integer getMaxResult(){
        return  maxResult == null ? this.defaultPageSize : maxResult;
    }

    public Integer getFirstResult() {
        pageNumber = pageNumber == null? this.defaultPageNumber : pageNumber;
        maxResult = maxResult == null ? this.defaultPageSize : maxResult;
        return pageNumber * maxResult;
    }
}
