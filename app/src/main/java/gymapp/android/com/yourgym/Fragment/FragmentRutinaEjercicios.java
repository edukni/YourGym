package gymapp.android.com.yourgym.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import gymapp.android.com.yourgym.ADHttpConnection;
import gymapp.android.com.yourgym.Adapter.ListViewEjercicioAdapter;
import gymapp.android.com.yourgym.Adapter.ListViewRutinaAdapter;
import gymapp.android.com.yourgym.Model.Ejercicio;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.Model.Rutina;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.OnConnectionFinished;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 01/11/2015.
 */
public class FragmentRutinaEjercicios extends Fragment implements OnConnectionFinished {

    ToggleButton btnRutina;
    ToggleButton btnEjercicio;
    OnChangePage listener;
    LinkedList<String> urlLista = new LinkedList<>();
    LinkedList<Rutina> _listRutina = new LinkedList<>();
    LinkedList<Ejercicio> _listEjercicio = new LinkedList<>();
    ListView listRutina;
    ListView listEjercicio;
    Context context;

    public  FragmentRutinaEjercicios(OnChangePage listener, Object urlLista) {
        this.listener = listener;
        this.urlLista = (LinkedList)urlLista;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup)inflater.inflate(R.layout.fragment_rutina, container, false);
        listRutina = (ListView)container.findViewById(R.id.listRutina);
        listEjercicio = (ListView)container.findViewById(R.id.listEjercicio);
        btnRutina = (ToggleButton)container.findViewById(R.id.btnRutina);
        btnEjercicio = (ToggleButton)container.findViewById(R.id.btnEjercicio);
        context = container.getContext();
        ADHttpConnection download = new ADHttpConnection(this);
        try {
            download.execute(new URI(urlLista.get(0)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        btnRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnEjercicio.isChecked()) {
                    btnEjercicio.setChecked(false);
                    listEjercicio.setVisibility(View.GONE);
                    listRutina.setVisibility(View.VISIBLE);
                } else {
                    btnRutina.setChecked(true);
                    listEjercicio.setVisibility(View.GONE);
                    listRutina.setVisibility(View.VISIBLE);
                }

            }
        });
        btnEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnRutina.isChecked()) {
                    btnRutina.setChecked(false);
                    listRutina.setVisibility(View.GONE);
                    listEjercicio.setVisibility(View.VISIBLE);
                    if(_listEjercicio.size()==0) {
                        ADHttpConnection downloadEjercicio = new ADHttpConnection(new OnConnectionFinished() {
                            @Override
                            public void onDownlandFinished(String cadena) {
                                try {
                                    JSONArray array = new JSONArray(cadena);
                                    for (int i=0;i<array.length();i++) {
                                        JSONObject jsonObject = array.getJSONObject(i);
                                        _listEjercicio.add(new Ejercicio(jsonObject));
                                    }
                                    listEjercicio.setAdapter(new ListViewEjercicioAdapter(_listEjercicio, context));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        try {
                            downloadEjercicio.execute(new URI(urlLista.get(1)));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    btnEjercicio.setChecked(true);
                    listEjercicio.setVisibility(View.VISIBLE);
                    listRutina.setVisibility(View.GONE);
                }
            }
        });
        final LinkedList<Noticia> list = new LinkedList<>();
        for(int i=0; i<10; i++) {
            list.add(new Noticia());
        }
        listEjercicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.changePage("detalle_noticia", listEjercicio.getItemAtPosition(position));
            }
        });
        return container;
    }


    @Override
    public void onDownlandFinished(String cadena) {
        try {
            JSONArray array = new JSONArray(cadena);
            _listRutina.clear();
            for(int i=0; i<array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                _listRutina.add(new Rutina(jsonObject));
            }
            listRutina.setAdapter(new ListViewRutinaAdapter(_listRutina, context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
