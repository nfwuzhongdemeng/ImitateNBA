package com.dyy.nba.commonlibs.manager;

import com.dyy.nba.commonlibs.util.ShareUtils;

/**
 * Created by 段钰莹 on 2017/10/7.
 */

public class MemoryManager {
    public static ShareUtils getMemory(){
        return ShareUtils.getInstance().resetShare("memory");
    }

    public static void deleteMemory(){
        ShareUtils.getInstance().resetShare("memory").clear();
    }
}
