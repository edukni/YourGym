package gymapp.android.com.yourgym;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gyms);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng gym1 = new LatLng(25.675567839158344, -100.31281471252441);
        LatLng gym2 = new LatLng(25.697921604613754, -100.33937931060791);
        LatLng gym3 = new LatLng(25.69552399672519, -100.35336971282959);
        LatLng gym4 = new LatLng(25.673479210854946, -100.34718990325928);
        LatLng gym5 = new LatLng(25.725065344388177, -100.30985355377197);
        LatLng gym6 = new LatLng(25.675103702698426, -100.25753974914551);
        LatLng gym7 = new LatLng(25.650385826059416, -100.2896511554718);

        mMap.addMarker(new MarkerOptions().position(gym1).title("YourGym Centro") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));
        mMap.addMarker(new MarkerOptions().position(gym2).title("YourGym Mitras Centro") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));
        mMap.addMarker(new MarkerOptions().position(gym3).title("YourGym Leones") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));
        mMap.addMarker(new MarkerOptions().position(gym4).title("YourGym Obispado") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));
        mMap.addMarker(new MarkerOptions().position(gym5).title("YourGym UANL") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));
        mMap.addMarker(new MarkerOptions().position(gym6).title("YourGym Guadalupe") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));
        mMap.addMarker(new MarkerOptions().position(gym7).title("YourGym TEC") .icon(BitmapDescriptorFactory.fromResource(R.mipmap.yourlogo)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((gym1), 12.0f));
    }
}
