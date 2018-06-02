package cc.siriuscloud.xiaoy.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cc.siriuscloud.xiaoy.AppVessel;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.domain.User;

public class PersonalFragment extends Fragment {


    private Button submitBtn;
    private TextView nameText;
    private TextView phoneText;
    private TextView emailText;
    private EditText suggestEdit;


    private ProgressDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //提取数据
        User user=AppVessel.get("user");

        //绑定组件
        submitBtn=getActivity().findViewById(R.id.submit_btn);
        nameText=getActivity().findViewById(R.id.name_txt);
        phoneText=getActivity().findViewById(R.id.phont_txt);
        emailText=getActivity().findViewById(R.id.email_txt);
        suggestEdit=getActivity().findViewById(R.id.suggest_edt);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new ProgressDialog(getActivity());

                dialog.setCancelable(false);
                dialog.setTitle("提交建议");

                dialog.setMessage("提交中......请稍候！");
                dialog.show();

                //提交数据
                    //TODO 建议的提交逻辑


                //提交完成，清空输入框
                suggestEdit.setText("");
                dialog.cancel();
            }
        });


    }




}
