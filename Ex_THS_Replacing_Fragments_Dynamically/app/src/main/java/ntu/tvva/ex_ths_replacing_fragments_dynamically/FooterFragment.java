package ntu.tvva.ex_ths_replacing_fragments_dynamically;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FooterFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_footer, container, false);
        Button b1 =v.findViewById(R.id.button);
        Button b2 =v.findViewById(R.id.button2);
        Button b3 =v.findViewById(R.id.button3);

        FragmentManager fragmentManager = getParentFragmentManager();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content, new Fragment1()).commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView_Content, new Fragment2()).commit();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView_Content, new Fragment3()).commit();
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
}