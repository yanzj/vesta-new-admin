package com.maxrocky.vesta.utility;

import java.util.Comparator;

/**
 * @author WeiYangDong
 * @date 2018/1/31 17:06
 * @deprecated Map比较器类
 */
public class MapKeyComparator implements Comparator<String> {

    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
