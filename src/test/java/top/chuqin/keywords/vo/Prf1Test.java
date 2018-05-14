package top.chuqin.keywords.vo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Prf1Test {
    @Test
    public void test(){
        Prf1 prf1 = new Prf1(Arrays.asList(), Arrays.asList());
        List<String> expectList = Arrays.asList("推荐系统", "社会网络", "矩阵分解", "影响力", "排序学习");
        List<String> actuallyList = Arrays.asList("推荐", "系统", "社会", "复制", "选择");

        int k = prf1.softContains(expectList, actuallyList);
        System.out.println(k);
        Assert.assertEquals(k, 2);
    }
}