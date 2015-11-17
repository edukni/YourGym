package gymapp.android.com.yourgym.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 08/11/2015.
 */
public class FragmentGimnasios extends Fragment{

    OnChangePage listener;

    public FragmentGimnasios(OnChangePage listener) {
     this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup)inflater.inflate(R.layout.fragment_gimnasios, container, false);

        return container;
    }
}
