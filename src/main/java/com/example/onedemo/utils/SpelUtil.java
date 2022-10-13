package com.example.onedemo.utils;

import org.apache.catalina.User;
import org.apache.commons.compress.utils.Lists;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxd
 * @Date 2022/10/10 15:23
 */
public class SpelUtil {

    public Object getExplValue(String expl){
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(expl);
        return expression.getValue();
    }

    public static void main(String[] args) {
        // eval 执行一个表达式   javascript
        String expl  = "1-1 != 0 ";
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(expl);
        // 获取表达式运行结果
        Object value = expression.getValue();
        System.out.println("value = " + value);

        spelBaseTest();
    }

    //  示例
    public static void spelBaseTest() {
        // 1.创建表达式解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 2.执行表达式,取得表达式结果
        String expressionStr = "'Hello, Spel' + #ending + '\n' + " +
                "'Fighting, ' + #mobileMap['huawei'] + #ending + '\n' + " +
                "'Fly, ' + #collectCar[0] + #ending + '\n' + " +
//                "'欧力给, ' + #user.name + #ending + '\n' + " +
                " new String(#number matches '\\d+')";
        Expression expression = parser.parseExpression(expressionStr);

        // 3.创建变量上下文,设置变量
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        // 变量1,普通字符串
        evaluationContext.setVariable("ending", "!");
        // 变量2,Map类型
        Map<String, String> mobileMap = new HashMap<>();
        mobileMap.put("huawei", "华为");
        mobileMap.put("xiaomi", "小米");
        evaluationContext.setVariable("mobileMap", mobileMap);
        // 变量3,Collection类型
        evaluationContext.setVariable("collectCar", new ArrayList<>(Arrays.asList("比亚迪", "红旗", "吉利")));
        // 变量4,POJO对象
//        evaluationContext.setVariable("user", new User("饱饱"));
        // 变量5,匹配正则表达式
        evaluationContext.setVariable("number", 142857);

        // 4.传入变量上下文,通过表达式对象取得结果
        String result = expression.getValue(evaluationContext, String.class);
        System.out.println(result);
    }
}
