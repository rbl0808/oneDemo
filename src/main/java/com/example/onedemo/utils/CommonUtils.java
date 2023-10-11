package com.example.onedemo.utils;

import com.example.onedemo.model.Express;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zxd
 * @date 2021/2/1
 */
@Component
@Slf4j
public class CommonUtils {
    /**
     * list根据时间排序
     *
     * @param list
     */
    public void listSortAllType(List<Express> list) {
        Collections.sort(list, new Comparator<Express>() {
            @Override
            public int compare(Express o1, Express o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date dt1 = format.parse(o1.getStateDate());
                    Date dt2 = format.parse(o2.getStateDate());
                    if (dt1.getTime() < dt2.getTime()) {
                        return 1;//大的放前面
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }


    /**
     * 抽取list任意多元素 返回不重复的元素
     *
     * @param source
     * @param maxSize
     * @return
     */
    public List<String> randomDraw(List<String> source, int maxSize) {
        source = source.stream().distinct().collect(Collectors.toList());
        if (source.size() < 2 || source.size() <= maxSize) {
            return source;
        }
        List<String> result = new ArrayList<>();

        Random random = new Random();
        int randIndex = 0;
        for (int i = 0; i < maxSize; i++) {
            randIndex = random.nextInt(source.size());
            if (result.contains(source.get(randIndex))) {
                i--;
                continue;
            } else {
                result.add(source.get(randIndex));
            }
        }
        return result;
    }


    /**
     * 替换字符串中数字 第一种
     * @param str
     * @return
     */
    public String change(String str) {
        StringBuilder sb = new StringBuilder();
        String regex = "__\\d+__";
        String[] res = str.split(regex);
        int l = 1;
        for (int m = 0; m < res.length; m++) {
            if (m == res.length - 1) {
                sb.append(res[m]);
            } else {
                sb.append(res[m] + "__" + l++ + "__");
            }
        }
        return sb.toString();
    }

    /**
     * 替换字符串中数字 第二种
     * @param str
     * @return
     */
//    public String convert(String str) {
//        Pattern pattern = Pattern.compile("__\\d+__");
//        Matcher matcher = pattern.matcher(examInfo);
//        StringBuffer sb = new StringBuffer();
//        int i = 1;
//        while (matcher.find()) {
//            matcher.appendReplacement(sb, "__" + i++ + "__");
//        }
//        matcher.appendTail(sb);
//        return sb.toString();
//    }

    /**
     * list去重 保持插入顺序
     * @param source
     * @return
     */
    public List<String> distinct(List<String> source) {
        List<String> result = new ArrayList<>(new LinkedHashSet<>(source));
        return result;
    }

    /**
     * 列表分页
     */
    public List sub(List source, int pageNumber) {
        int size = source.size();
        int pageMax = size / 10 + 1;
        if (pageMax == 1) {
            return source;
        } else if (pageNumber < pageMax) {
            return source.subList((pageNumber - 1) * 10, pageNumber * 10);
        } else {
            return source.subList((pageMax - 1) * 10, size);
        }
    }

    /**
     * 截取list集合，返回list集合
     *
     * @param list   (需要截取的集合)
     * @param subNum (每次截取的数量)
     * @return
     */
    public static <T> List<List<T>> subList(List<T> list, Integer subNum) {
        // 新的截取到的list集合
        List<List<T>> newList = new ArrayList<List<T>>();
        // 要截取的下标上限
        Integer priIndex = 0;
        // 要截取的下标下限
        Integer lastIndex = 0;
        // 查询出来list的总数目
        Integer totalNum = list.size();
        // 总共需要插入的次数
        Integer insertTimes = totalNum / subNum;
        List<T> subNewList = new ArrayList<T>();
        for (int i = 0; i <= insertTimes; i++) {
            // [0--20) [20 --40) [40---60) [60---80) [80---100)
            priIndex = subNum * i;
            lastIndex = priIndex + subNum;
            // 判断是否是最后一次
            if (i == insertTimes) {
                log.info("最后一次截取：" + priIndex + "," + lastIndex);
                subNewList = list.subList(priIndex, list.size());
            } else {
                // 非最后一次
                subNewList = list.subList(priIndex, lastIndex);
            }
            if (subNewList.size() > 0) {
                newList.add(subNewList);
            }
        }
        return newList;
    }


    /**
     * 初始化类填充默认值
     * @param obj
     */
    public void filling(Object obj){
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Object o = field.get(obj);
                if (null == o) {
                    String type = field.getType().getName();
                    if ("java.util.List".equals(type)) {
                        field.set(obj, new ArrayList<>());
                    }else if("java.util.Map".equals(type)){
                        field.set(obj, new HashMap<>(1));
                    }else if("java.lang.Integer".equals(type)){
                        field.set(obj,0);
                    }else if("java.lang.Boolean".equals(type)){
                        field.set(obj,true);
                    }else if("java.lang.Set".equals(type)){
                        field.set(obj,new HashSet<>());
                    }else {
                        Object tmp = null;
                        try {
                            tmp = field.getType().newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        field.set(obj, tmp);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 格式化Date类型时间
     * @param date
     * @return
     */
    public String dateFormatMin(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

    /**
     * 获取2个Date中较大的值
     * @param first
     * @param second
     * @return
     */
    public Date maxDate(Date first,Date second){
        if(first == second){
            return first;
        }
        Boolean flag = first.before(second);
        if(flag){
            return second;
        }else {
            return first;
        }
    }

    /**
     * 脱敏字符串
     *
     * @param source        原字符串
     * @param beginOverlook 前几位不脱敏
     * @param endOverlook   后几位不脱敏
     * @return
     */
    public static String blurIdNormal(String source, int beginOverlook, int endOverlook) {
        if (StringUtils.isBlank(source)) {
            return "";
        }
        if (source.length() < beginOverlook + endOverlook) {
            return source;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length() - beginOverlook - endOverlook; i++) {
            sb.append("*");
        }

        return StringUtils.overlay(source, sb.toString(), beginOverlook, source.length() - endOverlook);
    }
}
