package gymapp.android.com.yourgym.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import gymapp.android.com.yourgym.ADHttpConnection;
import gymapp.android.com.yourgym.Adapter.ListViewInstructorAdapter;
import gymapp.android.com.yourgym.Adapter.ListViewServicioAdapter;
import gymapp.android.com.yourgym.Model.Menu;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.Model.Servicio;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.OnConnectionFinished;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 07/11/2015.
 */
public class FragmentServicios extends Fragment {

    OnChangePage listener;
    Menu menu;
    LinkedList<Servicio> servicios = new LinkedList<>();

    public FragmentServicios(OnChangePage listener, Object objectItem) {
        this.listener = listener;
        menu = (Menu)objectItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_servicios, container, false);
        final ListView listEjercicioSelecc = (ListView)container.findViewById(R.id.listEjercicioSelecc);
        final ViewGroup finalContainer = container;
        ADHttpConnection download = new ADHttpConnection(new OnConnectionFinished() {
            @Override
            public void onDownlandFinished(String cadena) {
                try {
                    JSONArray array = new JSONArray(cadena);
                    servicios.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        servicios.add(new Servicio(jsonObject));
                    }
                    listEjercicioSelecc.setAdapter(new ListViewServicioAdapter(servicios, finalContainer.getContext()));
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
        return container;
    }

}
