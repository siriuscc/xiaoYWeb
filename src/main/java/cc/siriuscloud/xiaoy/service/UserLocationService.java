package cc.siriuscloud.xiaoy.service;

import cc.siriuscloud.xiaoy.domain.UserLocation;

import java.util.List;

public interface UserLocationService {

    int addLocation(UserLocation location);

    List<UserLocation> findAllLast();

}
