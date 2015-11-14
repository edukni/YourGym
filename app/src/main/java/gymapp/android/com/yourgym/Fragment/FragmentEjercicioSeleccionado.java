package gymapp.android.com.yourgym.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import gymapp.android.com.yourgym.ADHttpConnection;
import gymapp.android.com.yourgym.Adapter.ListViewDietaAdapter;
import gymapp.android.com.yourgym.Adapter.ListViewEjercicioAdapter;
import gymapp.android.com.yourgym.Adapter.ListViewInicioAdapter;
import gymapp.android.com.yourgym.Model.Ejercicio;
import gymapp.android.com.yourgym.Model.Menu;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.Model.Rutina;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.OnConnectionFinished;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 06/11/2015.
 */
public class FragmentEjercicioSeleccionado extends Fragment {

    OnChangePage listener;
    String url;
    LinkedList<Noticia> noticias = new LinkedList<>();
    Menu menu;

    public  FragmentEjercicioSeleccionado(OnChangePage listener, Object item) {
        this.listener = listener;
        menu = (Menu)item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup)inflater.inflate(R.layout.fragment_ejercicio_seleccionado, container, false);
        final ListView listEjercicioSelecc = (ListView)container.findViewById(R.id.listEjercicioSelecc);
        final ViewGroup finalContainer = container;
        ADHttpConnection download = new ADHttpConnection(new OnConnectionFinished() {
            @Override
            public void onDownlandFinished(String cadena) {
                try {
                    JSONArray array = new JSONArray(cadena);
                    noticias.clear();
                    for (int i=0; i<array.length();i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        noticias.add(new Noticia(jsonObject));
                    }
                    listEjercicioSelecc.setAdapter(new ListViewDietaAdapter(noticias, finalContainer.getContext()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            download.execute(new URI(menu.getUrl()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //   listEjercicioSelecc.setAdapter(new ListViewEjercicioAdapter(list, container.getContext()));
        listEjercicioSelecc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.changePage("detalle_noticia", listEjercicioSelecc.getItemAtPosition(position));
            }
        });
        return container;
    }
}
