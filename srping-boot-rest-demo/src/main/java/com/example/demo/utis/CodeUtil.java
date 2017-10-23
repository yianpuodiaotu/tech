/*
 * CodeUtil.java [V 1.0.0]
 * classes com.emcc.zkl.core.common.CodeUtil
 * 高炎  Creact 2015年2月25日 上午11:13:40
 */
package com.example.demo.utis;


/**
 * 
 * <p>
 * 编号生成器。
 * <p>
 *
 * 创建日期 2015年7月13日<br>
 * @author  $litong$<p>
 * @since  1.0.0
 */
public class CodeUtil {
    public static final String[] LETTERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    private final static int pixLen = LETTERS.length;
    private static volatile int pixOne = 0;
    private static volatile int pixTwo = 0;
    private static volatile int pixThree = 0;

    /**
     * 生成短时间内不会重复的长度为15位的字符串， 生成策略为获取自1970年1月1日零时零分零秒 并追加五位""的自增字符串.<br/>
     * 如果系统时间设置为大于<b>2304-6-27 7:00:26<b/>的时间，将会报错！<br/>
     * 
     * @return 15位短时间不会重复的字符串。<br/>
     * @since JDK1.6
     */
    final private synchronized static String generate() {
        StringBuilder sb = new StringBuilder();// 创建一个StringBuilder
        sb.append(DateformatUtils.getCurrentTimeMillis());


        pixThree++;
        if (pixThree == pixLen) {
            pixThree = 0;
            pixTwo++;
            if (pixTwo == pixLen) {
                pixTwo = 0;
                pixOne++;
                if (pixOne == pixLen) {
                    pixOne = 0;
                }
            }
        }
        return sb.append(LETTERS[pixOne]).append(LETTERS[pixTwo]).append(LETTERS[pixThree]).toString();
    }

    /**
     * 生成不重复的编码
     * 
     * @return
     */
    public static long getLongCode() {
        return Long.parseLong(generate());
    }
}
