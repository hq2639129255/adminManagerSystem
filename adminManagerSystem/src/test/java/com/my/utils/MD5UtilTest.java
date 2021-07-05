package com.my.utils;

import org.junit.Test;

public class MD5UtilTest {
    @Test
    public void testmd5(){
        MD5Util md5Util=new MD5Util();
        String b = md5Util.md5("88888888");
        System.out.println(b);
    }
}
