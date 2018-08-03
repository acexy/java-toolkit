package com.thankjava.toolkit.math;

import java.math.BigDecimal;

/**
 * 提供相对精确不损失精度的算法
 */
public class MathUtils {

    private MathUtils() {
    }

    private static BigDecimal toBigDecimal(double doubleValue) {
        return new BigDecimal(String.valueOf(doubleValue));
    }

    /**
     * 相对精确的加法
     *
     * @param doubleA
     * @param doubleB
     * @return
     */
    public static double add(double doubleA, double doubleB) {
        return toBigDecimal(doubleA).add(toBigDecimal(doubleB)).doubleValue();
    }

    /**
     * 相对精确的乘法
     *
     * @param doubleA
     * @param doubleB
     * @return
     */
    public static double multiply(double doubleA, double doubleB) {
        return toBigDecimal(doubleA).multiply(toBigDecimal(doubleB)).doubleValue();
    }

    /**
     * 相对精确的减法
     *
     * @param doubleA
     * @param doubleB
     * @return
     */
    public static double subtract(double doubleA, double doubleB) {
        return toBigDecimal(doubleA).subtract(toBigDecimal(doubleB)).doubleValue();
    }

    /**
     * 相对精确的除法
     *
     * @param doubleA
     * @param doubleB
     * @return
     */
    public static double divide(double doubleA, double doubleB) {
        return toBigDecimal(doubleA).divide(toBigDecimal(doubleB)).doubleValue();
    }

    /**
     * 提供相对精确的除法 (超出精度的数字默认使用BigDecimal.ROUND_DOWN策略处理)
     * @param doubleA
     * @param doubleB
     * @param scale 精度
     * @return
     */
    public static double divide(double doubleA, double doubleB, int scale) {
        return toBigDecimal(doubleA).divide(toBigDecimal(doubleB), scale, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
