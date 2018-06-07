package cc.siriuscloud.xiaoy.service.impl;


import cc.siriuscloud.xiaoy.dao.UserLocationMapper;
import cc.siriuscloud.xiaoy.domain.UserLocation;
import cc.siriuscloud.xiaoy.service.UserLocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLocationServiceImpl  implements UserLocationService {

    @Resource
    UserLocationMapper userLocationMapper;


    @Override
    public int addLocation(UserLocation location) {

        return userLocationMapper.insertSelective(location);
    }
}
