package com.maxrocky.vesta.utility;

/**
 * Created by Yu on 2015/9/27.
 */
public class PageInfo {

    private int size;//每页数目
    private int number;//当前页数
    private int count;//从几条开始取
    private Integer dataTotal;//总数量
    private Integer maxPageNum;//总页数

    public PageInfo(){
        this.size = 10;
        this.number = 1;
        this.dataTotal = 0;
    }

    public PageInfo(int size, int number){
        this.size = size;
        this.number = number;
        this.dataTotal = 0;
    }

    public Integer getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(Integer dataTotal) {
        this.dataTotal = dataTotal;
    }

    public Integer getMaxPageNum() {
        return (dataTotal + size - 1) / size;
    }

    public int getCount() {
        return (number - 1 ) * size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
