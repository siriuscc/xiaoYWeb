package cc.siriuscloud.xiaoy.service.impl;

import cc.siriuscloud.xiaoy.dao.StaffMapper;
import cc.siriuscloud.xiaoy.domain.Staff;
import cc.siriuscloud.xiaoy.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StaffServiceImpl implements StaffService {


    @Resource
    private StaffMapper staffMapper;


    @Override
    public Staff login(Staff staff) {


        return staffMapper.selectByNamePasswd(staff);
    }
}
