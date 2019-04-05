package cc.siriuscloud.xiaoy;

import org.junit.Test;

import java.util.Date;

import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.HttpUtil;

public class HttpUtilTest {





    @Test
    public void testHttpUtil(){

        Task task = new Task(0, "标题"
                , new Date(), new Date(),
                0, "", 0, "");

        HttpUtil.mappingFormBody(task);

    }



}
