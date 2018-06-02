package cc.siriuscloud.xiaoy.dao;




public  interface DaoCallBack<T> {

    void onSuccess(int status,String msg,T data);
    void onError(int status,String msg,T data);
}
