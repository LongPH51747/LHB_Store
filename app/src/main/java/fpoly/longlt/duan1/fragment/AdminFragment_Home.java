package fpoly.longlt.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.longlt.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment_Home extends Fragment {
    public AdminFragment_Home() {
        // Required empty public constructor
    }

    public static AdminFragment_Home newInstance() {
        AdminFragment_Home fragment = new AdminFragment_Home();
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
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }
}