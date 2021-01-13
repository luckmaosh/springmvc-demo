package com.niux.spring.log;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class FindSubstring {

    public List<Integer> findSubstring(String s, String[] words) {

        if (StringUtils.isBlank(s) || words == null || words.length == 0) {
            return Lists.newArrayList();
        }
        for (String word : words) {
            if (!s.contains(word)) {
                return Lists.newArrayList();
            }
        }

        return null;

    }


}
