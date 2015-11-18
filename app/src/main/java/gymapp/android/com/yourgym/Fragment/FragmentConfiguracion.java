package gymapp.android.com.yourgym.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 08/11/2015.
 */
public class FragmentConfiguracion extends Fragment{

    Spinner spinnerLanguage;
    Spinner spinnerColor;
    Button btnConfiguration;

    int colorFondo;

    OnChangePage listener;

    public FragmentConfiguracion(OnChangePage listener) {
     this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_configuracion, container, false);
        //container = (ViewGroup)inflater.inflate(R.layout.fragment_configuracion, container, false);

        spinnerLanguage = (Spinner) rootView.findViewById(R.id.spinnerLanguage);
        spinnerColor = (Spinner) rootView.findViewById(R.id.spinnerColor);
        btnConfiguration    = (Button) rootView.findViewById(R.id.btnConfig);

        loadConfig();

        if(colorFondo == 0){
            rootView.getRootView().setBackgroundColor(Color.WHITE);

        }else if(colorFondo == 1){
            rootView.getRootView().setBackgroundColor(Color.BLUE);

        }else if(colorFondo == 2){
            rootView.getRootView().setBackgroundColor(Color.RED);

        }else if(colorFondo == 3){
            rootView.getRootView().setBackgroundColor(Color.YELLOW);

        }else{
            rootView.getRootView().setBackgroundColor(Color.WHITE);
        }

        btnConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int indexLanguage = spinnerLanguage.getSelectedItemPosition();
                int indexColor = spinnerColor.getSelectedItemPosition();

                if (indexColor == 0) {
                    rootView.getRootView().setBackgroundColor(Color.WHITE);

                } else if (indexColor == 1) {
                    rootView.getRootView().setBackgroundColor(Color.BLUE);

                } else if (indexColor == 2) {
                    rootView.getRootView().setBackgroundColor(Color.RED);

                } else if (indexColor == 3) {
                    rootView.getRootView().setBackgroundColor(Color.YELLOW);

                } else {
                    rootView.getRootView().setBackgroundColor(Color.WHITE);
                }

                saveConfig(indexLanguage, indexColor);
                getActivity().finish();
                startActivity(new Intent(getActivity().getIntent()));
            }
        });

        return rootView;
    }

    public void saveConfig(int lenguajeId, int colorId){
        Context context = getActivity();
        SharedPreferences prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("language", lenguajeId);
        editor.putInt("color", colorId);
        editor.commit();
    }

    private void loadConfig(){
        Context context = getActivity();
        SharedPreferences prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int lang = prefs.getInt("language", 0);
        int color = prefs.getInt("color", 0);
        colorFondo = prefs.getInt("color", 0);

        spinnerLanguage.setSelection(lang);
        spinnerColor.setSelection(color);
    }
}
