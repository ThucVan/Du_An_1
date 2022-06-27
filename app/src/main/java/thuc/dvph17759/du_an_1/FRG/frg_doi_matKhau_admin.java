package thuc.dvph17759.du_an_1.FRG;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import thuc.dvph17759.du_an_1.R;

public class frg_doi_matKhau_admin extends Fragment {
    TextInputEditText ed_user, ed_oldPassm, ed_newPass, ed_newPass2;
    Button btn_yes, btn_no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_admin_doi_mat_khau, container, false);

        ed_user = v.findViewById(R.id.ed_tai_khoan);
        ed_oldPassm = v.findViewById(R.id.ed_passWord);
        ed_newPass = v.findViewById(R.id.ed_new_passWord);
        ed_newPass2 = v.findViewById(R.id.ed_new_passWord_xacNhan);

        btn_yes = v.findViewById(R.id.btn_yes);
        btn_no = v.findViewById(R.id.btn_huy);

        btn_no.setOnClickListener(v1 -> {
            ed_user.setText("");
            ed_newPass.setText("");
            ed_oldPassm.setText("");
            ed_newPass2.setText("");
        });

        btn_yes.setOnClickListener(v1 -> {
            //doi mat khau
        });
        return v;
    }
}
