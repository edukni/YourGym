package gymapp.android.com.yourgym.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import gymapp.android.com.yourgym.ADHttpConnection;
import gymapp.android.com.yourgym.Adapter.ListViewInicioAdapter;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.OnConnectionFinished;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 03/11/2015.
 */
public class FragmentInicio extends Fragment {

    private OnChangePage _listener;
    private String identificador;
    LinkedList<Noticia> noticias = new LinkedList<>();
    String url;

    public FragmentInicio(OnChangePage listener, String identificador, String url)
    {
        this._listener = listener;
        this.identificador = identificador;
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        container = (ViewGroup)inflater.inflate(R.layout.fragment_inicio, container, false);
        final ListView listaInicio = (ListView)container.findViewById(R.id.listViewInicio);
        listaInicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _listener.changePage("detalle_noticia", listaInicio.getItemAtPosition(position));
            }
        });
        final ViewGroup finalContainer = container;
        ADHttpConnection connection = new ADHttpConnection(new OnConnectionFinished() {
            @Override
            public void onDownlandFinished(String cadena) {
                try {
                    JSONArray array = new JSONArray(cadena);
                    noticias.clear();
                    for (int i=0; i<array.length();i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        noticias.add(new Noticia(jsonObject));
                    }
                    listaInicio.setAdapter(new ListViewInicioAdapter(noticias, finalContainer.getContext()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            connection.execute(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return container;
    }
}