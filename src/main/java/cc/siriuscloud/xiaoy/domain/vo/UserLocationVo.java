package cc.siriuscloud.xiaoy.domain.vo;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.UserLocation;

public class UserLocationVo {


    private User user;
    private UserLocation userLocation;


    public UserLocationVo() {
    }

    public UserLocationVo(User user, UserLocation userLocation) {
        this.user = user;
        this.userLocation = userLocation;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserLocation getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(UserLocation userLocation) {
        this.userLocation = userLocation;
    }




}
