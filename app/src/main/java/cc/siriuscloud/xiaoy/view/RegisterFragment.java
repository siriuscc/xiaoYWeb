package cc.siriuscloud.xiaoy.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cc.siriuscloud.xiaoy.LoginActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.UserDao;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.Message;
import cc.siriuscloud.xiaoy.utils.MyStringUtils;

public class RegisterFragment extends Fragment {


    private TextInputEditText nameText;
    private TextInputEditText emailText;
    private TextInputEditText passwdText;
    private TextInputEditText passwdConfirmText;
    private TextInputEditText phoneText;

    private AppCompatButton registerBtn;


    private ProgressDialog dialog;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //绑定组件
        nameText = getActivity().findViewById(R.id.name_txt);
        emailText = getActivity().findViewById(R.id.email_txt);
        passwdText = getActivity().findViewById(R.id.passwd_txt);
        passwdConfirmText = getActivity().findViewById(R.id.passwd_confirm_txt);
        phoneText = getActivity().findViewById(R.id.phone_txt);

        registerBtn = getActivity().findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new ProgressDialog(getActivity());

                dialog.setTitle("请稍候，注册中");
                dialog.setMessage("世界上有10种人，看懂这句话的和看不懂的......!");

                dialog.setCancelable(false);
                dialog.show();

                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                String passwd = passwdText.getText().toString();
                String passwdConfirm = passwdConfirmText.getText().toString();
                String phone = phoneText.getText().toString();


                if (!TextUtils.equals(passwdConfirm, passwd)) {

                    passwdConfirmText.setError("两次输入不匹配");

                    dialog.cancel();

                    Toast.makeText(getActivity(), "注册失败，请检查后重试", Toast.LENGTH_SHORT).show();
                    return;
                }


                User user = new User();
                user.setUsername(name);
                user.setEmail(email);
                user.setPasswd(MyStringUtils.String2Md5(passwd));
                user.setPhone(phone);


                new UserDao(new DaoCallBack<User>() {

                    @Override
                    public void onSuccess(int status, String msg, User data) {

                        if (status == Message.STATUS_SUCCESS) {

                            if (null != dialog) {
                                //取消显示
                                dialog.cancel();
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                                    ((LoginActivity) getActivity()).replaceFragment(new LoginFragment());
                                }
                            });
                            return;
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onError(int status, String msg, User data) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).register(user);

            }
        });


    }
}
