package com.busycount.rvadapter;

import java.util.List;


/**
 * Util
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class IList {

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    public static int getSize(List list) {
        return isEmpty(list) ? 0 : list.size();
    }
}
