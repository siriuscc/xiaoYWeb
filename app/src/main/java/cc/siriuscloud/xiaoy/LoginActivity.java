package cc.siriuscloud.xiaoy;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.widget.FrameLayout;


import cc.siriuscloud.xiaoy.view.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private FrameLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //默认登录框
        replaceFragment(new LoginFragment());
    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_main_frame_layout,fragment);
        fragmentTransaction.commit();
    }



    public String getEmailCache(){

        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);

        String email = spf.getString("email","");


        return email;
    }

    public void setEmailCache(String email) {
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor edit = spf.edit();

        edit.putString("email",email);
        edit.apply();
        edit.commit();
    }
}
