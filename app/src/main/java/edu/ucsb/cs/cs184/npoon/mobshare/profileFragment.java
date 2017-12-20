package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by nickkpoon on 12/20/17.
 */

public class profileFragment extends Fragment {

    private TextView name;
    private TextView phone_num;
    private TextView rides_given;
    private TextView rides_taken;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = getActivity().findViewById(R.id.profile_name);
        phone_num = getActivity().findViewById(R.id.profile_number);
        rides_given = getActivity().findViewById(R.id.profile_given);
        rides_taken = getActivity().findViewById(R.id.profile_taken);


        return view;
    }

    @Override
    public void onCreate(Bundle save)
    {
        super.onCreate(save);
        setRetainInstance(true);
    }


}
