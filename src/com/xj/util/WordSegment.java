package com.xj.util;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;

import java.util.List;

/**
 * Created by zhengguo on 2017/7/3.
 */
public class WordSegment {
    public static String []  split(String s) {
        List<Term> term = ToAnalysis.parse(s);
        String [] result = new String[term.size()];
        for(int i = 0; i< term.size(); i++) {
            result[i] = term.get(i).getName();// 获取单词  
        }
        return result;
    }

}
