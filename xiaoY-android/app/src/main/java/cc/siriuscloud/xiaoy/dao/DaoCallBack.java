package cc.siriuscloud.xiaoy.dao;


/**
 * dao层回调接口
 * @param <T> 回调数据的类型
 */
public  interface DaoCallBack<T> {

    void onSuccess(int status,String msg,T data);
    void onError(int status,String msg,T data);
}
