package com.own.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.own.web.dao.dao.MUserInfoDao;
import com.own.web.dao.pojo.MUserInfo;
import com.own.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author liuChang
 * @date 2023/3/22 18:01
 * @describe
 */
@Service
public class UserServiceImpl extends ServiceImpl<MUserInfoDao, MUserInfo> implements UserService {
}
