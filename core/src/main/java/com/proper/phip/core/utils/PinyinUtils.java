package com.proper.phip.core.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;

/**
 * 拼音工具类
 */
public class PinyinUtils {

    public static String getPinYinHeadChar(String str) {
        StringBuilder headChar = new StringBuilder("");
        if (StringUtils.isNotEmpty(str)) {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);     // 大写
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  // 无声调
            format.setVCharType(HanyuPinyinVCharType.WITH_V);      // lü -> lv

            try {
                char[] chars = str.trim().replaceAll("\\p{P}","").toCharArray();
                for (char word : chars) {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        headChar.append(pinyinArray[0].substring(0, 1));
                    } else {
                        headChar.append(String.valueOf(word).toUpperCase());
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        return headChar.toString();
    }
}
