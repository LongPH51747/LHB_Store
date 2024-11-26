package fpoly.longlt.duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.dao.UserDAO;
import fpoly.longlt.duan1.model.User;
import fpoly.longlt.duan1.screen.EditProfileScreen;
import fpoly.longlt.duan1.screen.LoginScreen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    ImageView imgLogOut, imgAvatar;
    TextView txtNameAccount;
    Button btn_update_profile;

    public int id;
    public String nameUser;
    UserDAO userDAO;
    public AccountFragment() {

    }


    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Phan Anh Xa
        imgLogOut = view.findViewById(R.id.imgLogOut);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        txtNameAccount = view.findViewById(R.id.txtNameAccount);
        btn_update_profile = view.findViewById(R.id.btn_update_profile);
        userDAO = new UserDAO(getContext());
        // Lay ra phan id cua nguoi duoi day
//        Bundle bundle = requireActivity().getIntent().getExtras();
//        if (bundle != null){
//            id = bundle.getInt("id");
//            nameUser = userDAO.getNameUserByID(id);
//        }
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user_id");
        if (user != null){
            id = user.getId_user();
            nameUser = userDAO.getNameUserByID(id);
        }else {
            Log.e("Loi", "Khong lay dc username tu id" + id );
        }
        // LogOut: Ben tai khoan User
        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hen Gap Lai Quy Khach...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginScreen.class));
            }
        });
        // Phan chinh sua thong tin ==> do user tu chinh sua
        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "....Updating....soon", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), EditProfileScreen.class));
            }
        });

        txtNameAccount.setText(nameUser);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}