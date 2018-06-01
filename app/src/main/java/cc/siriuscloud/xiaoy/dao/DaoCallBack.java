package cc.siriuscloud.xiaoy.dao;

public interface DaoCallBack {
    void onSuccess(int status,String msg,Object data);
    void onError(int status,String msg,Object data);
}
