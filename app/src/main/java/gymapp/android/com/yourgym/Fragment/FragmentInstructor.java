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
import gymapp.android.com.yourgym.Adapter.ListViewInstructorAdapter;
import gymapp.android.com.yourgym.Model.Menu;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.OnConnectionFinished;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 07/11/2015.
 */
public class FragmentInstructor extends Fragment {

    OnChangePage listener;
    Menu menu;
    LinkedList<Noticia> list = new LinkedList<>();

    public  FragmentInstructor(OnChangePage listener, Object objectItem) {
        this.listener = listener;
        this.menu = (Menu)objectItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        container = (ViewGroup)inflater.inflate(R.layout.fragment_instructor, container, false);
        final ListView listEjercicioSelecc = (ListView)container.findViewById(R.id.listEjercicioSelecc);
        final ViewGroup finalContainer = container;
        ADHttpConnection download = new ADHttpConnection(new OnConnectionFinished() {
            @Override
                public void onDownlandFinished(String cadena){
                    try {
                        JSONArray array = new JSONArray(cadena);
                        list.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            list.add(new Noticia(jsonObject));
                        }
                        listEjercicioSelecc.setAdapter(new ListViewInstructorAdapter(list, finalContainer.getContext()));
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
        listEjercicioSelecc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.changePage("detalle_noticia", listEjercicioSelecc.getItemAtPosition(position));
            }
        });
        return container;
    }
}
