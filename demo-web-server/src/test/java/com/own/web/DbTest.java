package com.own.web;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.own.web.dao.dao.MUserInfoDao;
import com.own.web.dao.dao.RoleInfoDao;
import com.own.web.dao.pojo.MUserInfo;
import com.own.web.dao.pojo.RoleInfo;
import com.own.web.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liuChang
 * @date 2023/2/14 16:25
 * @describe
 */

@SpringBootTest
public class DbTest {

    @Autowired
    private MUserInfoDao userInfoDao;

    @Autowired
    private RoleInfoDao roleInfoDao;

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void pageTest() {
        PageHelper.startPage(1, 2);
        List<MUserInfo> MUserInfos = userInfoDao.selectList(new QueryWrapper<>());
        PageInfo<MUserInfo> info = new PageInfo<>(MUserInfos);
        System.out.println(MUserInfos);
        System.out.println("----------");
        System.out.println(info);
        System.out.println("----------");
        PageHelper.startPage(1, 1);
        List<RoleInfo> roleInfos = roleInfoDao.selectList(new QueryWrapper<>());
        System.out.println(roleInfos);
    }

    private static final String[] LAST_NAMES = {"云", "飞", "虹", "琪", "诗", "琳", "婷", "梦", "欣", "雨", "思", "欢", "丽", "菲", "娜", "莉", "倩", "婕", "媛", "琴", "静", "茜", "妍", "瑶", "娅", "秀", "雯", "娜", "晶", "妮", "蕾", "靓", "洁", "晨", "蓓", "宁", "萍", "霞", "翠", "玲", "慧", "红", "妤", "希", "娟", "鑫", "美", "阳", "璐", "佳", "欢", "琼", "嫣", "妹", "宇", "涵", "娣", "露", "瑾", "晨", "瑛", "菁", "舒", "薇", "岚", "凤", "琳", "文", "妙", "梅", "香", "莲", "妍", "怡"};
    private static final String[] FIRST_NAMES = {"张", "李", "王", "赵", "钱", "孙", "周", "吴", "郑", "冯", "陈", "楚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "谢", "周", "邓", "苏", "潘", "董", "于", "任", "傅", "汪", "范", "金", "石", "廖", "陆", "蓝", "魏", "邹", "孔", "翟", "秋", "何", "伍", "祁", "江", "闵", "羊", "山", "臧", "师", "储", "雷", "田", "龙", "熊", "尹", "黄", "穆", "萧", "欧阳", "诸葛", "上官", "司马", "南宫", "夏侯", "东方", "皇甫", "公孙", "慕容", "轩辕", "司徒", "狄仁杰", "李白", "杜甫", "白居易", "王维", "苏轼", "辛弃疾"};

    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "qq.com", "icloud.com"};
    private static final String[] PHONE_PREFIXES = {"301", "240", "202", "703", "410", "571", "202", "301", "703"};

    private static final int[] genders = {1, 0};

    private static final Random random = new Random();

    @Test
    public void generateMUserInfo() {

        List<MUserInfo> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            MUserInfo userInfo = new MUserInfo();
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            int gender = genders[random.nextInt(genders.length)];
            int age = random.nextInt(100); // 20 ~ 69 岁
            String phone = generatePhoneNumber();

            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];

            userInfo.setAge(age);
            userInfo.setGender(gender);
            userInfo.setEmail(email);
            userInfo.setPhone(phone);
            userInfo.setName(firstName + lastName);
            list.add(userInfo);
        }

        /*
         总共耗时: 11605 秒
         */
        long start = System.currentTimeMillis();
        List<List<MUserInfo>> partition = ListUtil.partition(list, 1000);
        for (List<MUserInfo> mUserInfos : partition) {
            userService.saveBatch(mUserInfos);
        }

        /*
          总共耗时: 11029 秒
         */
        ArrayList<MUserInfo> list1 = new ArrayList<>(1000);

        int c = 0;
        for (MUserInfo mUserInfo : list) {
            c++;
            list1.add(mUserInfo);
            if (c % 1000 == 0) {
                userService.saveBatch(list1);
                list1.clear();
            }
        }
        if (c % 1000 != 0) {
            userService.saveBatch(list1);
        }

        long end = System.currentTimeMillis();
        System.out.println("总共耗时: " + (end - start) / 10 + " 秒");
    }

    public static String generatePhoneNumber() {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
