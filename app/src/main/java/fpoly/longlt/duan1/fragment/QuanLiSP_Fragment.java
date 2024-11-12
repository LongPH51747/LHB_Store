package fpoly.longlt.duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.screen.AddSP;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiSP_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiSP_Fragment extends Fragment {

    public QuanLiSP_Fragment() {
        // Required empty public constructor
    }

    public static QuanLiSP_Fragment newInstance() {
        QuanLiSP_Fragment fragment = new QuanLiSP_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_li_s_p_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView btn_add_sp = view.findViewById(R.id.btn_add_sp);
        btn_add_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(view.getContext(), AddSP.class));
            }
        });
    }
}