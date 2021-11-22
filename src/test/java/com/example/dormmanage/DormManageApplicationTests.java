package com.example.dormmanage;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DormManageApplicationTests {

    @Test
    void contextLoads() throws UnsupportedEncodingException {
//        String a = "666666";
//        String s = new String(Base64.encodeBase64(a.getBytes("UTF-8")));
//        System.out.println(s);

        Date date = new Date();
        date.setYear(2025);
        String data2 = "2021-11-19";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(dateFormat.format(date));
    }

}
