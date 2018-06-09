package cc.siriuscloud.xiaoy.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import cc.siriuscloud.xiaoy.AppVessel;
import cc.siriuscloud.xiaoy.LoginActivity;
import cc.siriuscloud.xiaoy.MainActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.UserDao;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.MyStringUtils;


/**
 * 登录窗口
 */
public class LoginFragment extends Fragment {

    private static final String TAG=LoginFragment.class.getName();

    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private TextView registerBtn;



    private AppCompatButton loginBtn;

    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //绑定组件
        emailInput = getActivity().findViewById(R.id.email_ipt);
        passwordInput = getActivity().findViewById(R.id.password_ipt);
        loginBtn = getActivity().findViewById(R.id.login_btn);
        registerBtn=getActivity().findViewById(R.id.register_btn);

        //跳转到注册页面
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((LoginActivity)getActivity()).replaceFragment(new RegisterFragment());

            }
        });


        String emailCache = ((LoginActivity) getActivity()).getEmailCache();


        emailInput.setText(emailCache);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = LoginFragment.this.emailInput.getText().toString();
                String password = MyStringUtils.String2Md5(
                        LoginFragment.this.passwordInput.getText().toString());

                dialog = ProgressDialog.show(getActivity(), "加載中...", "正在验证請稍後！");


                UserDao userDao = new UserDao(new DaoCallBack<User>() {
                    @Override
                    public void onSuccess(int status, String msg, User user) {


                        if (dialog != null) {
                            dialog.dismiss();
                            dialog = null;
                        }
                        //保存数据
                        AppVessel.put("user",user);
                        Log.d(TAG,"...........AppVessel.put(\"user,\""+user+");");

                        Log.d(TAG,"login status:.........."+status);

                        ((LoginActivity)getActivity()).setEmailCache(email);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                            }
                        });


                        //提交地理位置数据
                        ((LoginActivity)getActivity()).requestLocation();


                        Intent intent = new Intent(getActivity(), MainActivity.class);

                        getActivity().startActivity(intent);

                    }

                    @Override
                    public void onError(int status, String msg, User data) {

                        if (dialog != null) {
                            dialog.dismiss();
                            dialog = null;
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                userDao.findUser(email, password);

            }
        });


    }
}
