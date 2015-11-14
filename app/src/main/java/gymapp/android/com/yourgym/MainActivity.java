package gymapp.android.com.yourgym;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;
import java.util.LinkedList;

import gymapp.android.com.yourgym.Adapter.ListViewMenuAdapter;
import gymapp.android.com.yourgym.Fragment.FragmentConfiguracion;
import gymapp.android.com.yourgym.Fragment.FragmentDetalleNoticia;
import gymapp.android.com.yourgym.Fragment.FragmentEjercicioSeleccionado;
import gymapp.android.com.yourgym.Fragment.FragmentInicio;
import gymapp.android.com.yourgym.Fragment.FragmentInstructor;
import gymapp.android.com.yourgym.Fragment.FragmentLogin;
import gymapp.android.com.yourgym.Fragment.FragmentRegister;
import gymapp.android.com.yourgym.Fragment.FragmentRutinaEjercicios;
import gymapp.android.com.yourgym.Fragment.FragmentServicios;
import gymapp.android.com.yourgym.Model.Menu;


public class MainActivity extends ActionBarActivity implements OnChangePage {
    private FrameLayout _fragmentContenido;
    DrawerLayout mDrawerLayout;
    ImageView image;
    TextView nombre;
    Button txtLogout;
    private ActionBarDrawerToggle mDrawerToggle;
    private double latitude;
    private double longitude;
    private GoogleMap mapa;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView listMenu = (ListView)findViewById(R.id.menuPrincipal);
        image = (ImageView)findViewById(R.id.image);
        nombre = (TextView)findViewById(R.id.nombreUsuario);
        txtLogout=(Button) findViewById(R.id.txtLogout);

        txtLogout.setOnClickListener(new View.OnClickListener(){
                                        public void onClick(View v){

                                            SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
                                      SharedPreferences.Editor editor=prefe.edit();
                                            editor.clear();
                                            editor.commit();

                                            changePage("Login",null);

                                        }
                                     });


        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d("MyApp", "No SDCARD");
        } else {
            File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"YourGym");
            Log.e("",""+directory.getPath());
            directory.mkdirs();
        }
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                 //la actividad
                mDrawerLayout,         //el drawerLayout que desplegar  �
                R.string.app_name,  //descripci�n al abrir
                R.string.app_name  //descripci�n al cerrar
        );

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

        final LinkedList<Menu> menuItems = new LinkedList<>();
        LinkedList<String> listRutinaEjercicio = new LinkedList<>();
        listRutinaEjercicio.add("http://yourgym.site88.net/loadRutina.php");
        listRutinaEjercicio.add("http://yourgym.site88.net/loadEjercicio.php");

        menuItems.add(new Menu("Inicio","http://yourgym.site88.net/loadInicio.php"));
        menuItems.add(new Menu("Rutina", listRutinaEjercicio));
        menuItems.add(new Menu("Dieta", "http://yourgym.site88.net/loadDieta.php"));
        menuItems.add(new Menu("Instructor", "http://yourgym.site88.net/loadInstructor.php"));
        menuItems.add(new Menu("Servicios", "http://yourgym.site88.net/loadServicios.php"));
        menuItems.add(new Menu("Configuracion", ""));
        ListViewMenuAdapter adapter = new ListViewMenuAdapter(menuItems,this);
        listMenu.setAdapter(adapter);
        _fragmentContenido = (FrameLayout) findViewById(R.id.fragmentContenido);
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View clickView, int position, long id) {
                changePage(menuItems.get(position).getIdentificador(), menuItems.get(position));
            }
        });

        final SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);

        //prefe.edit().clear().commit();

        if(prefe.getString("nombre","").equals(""))
        {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentRegister(this)).commit();
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            image.setImageURI(Uri.parse(prefe.getString("imagen", "")));
            nombre.setText(prefe.getString("nombre", ""));
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentLogin(this)).addToBackStack(null).commit();
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void changePage(String id, Object itemSelect) {

        if(id.equals("Login")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentLogin(this)).addToBackStack(null).commit();
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }
        if(id.equals("Register")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentRegister(this)).addToBackStack(null).commit();
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }

        if(id.equals("Rutina")) {

            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentRutinaEjercicios(this, ((Menu) itemSelect).getLista())).addToBackStack(null).commit();
        }
        if(id.equals("Dieta")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentEjercicioSeleccionado(this, itemSelect)).addToBackStack(null).commit();
        }
        if(id.equals("Instructor")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentInstructor(this, itemSelect)).addToBackStack(null).commit();
        }
        if(id.equals("Servicios")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentServicios(this,itemSelect)).addToBackStack(null).commit();
        }
        if(id.equals("Inicio")) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            FragmentManager fm = getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            if(itemSelect instanceof String) {
                getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentInicio(this, id, itemSelect.toString())).addToBackStack(null).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentInicio(this, id, ((Menu) (itemSelect)).getUrl())).addToBackStack(null).commit();
            }

            final SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
            image.setImageURI(Uri.parse(prefe.getString("imagen", "")));
            nombre.setText(prefe.getString("nombre", ""));
        }
        if(id.equals("detalle_noticia")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentDetalleNoticia(this, itemSelect)).addToBackStack(null).commit();
        }
        if(id.equals("listaEjercicioSeleccionado")) {
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentEjercicioSeleccionado(this, itemSelect)).addToBackStack(null).commit();
        }
        if(id.equals("Configuracion")){
            getSupportFragmentManager().beginTransaction().replace(_fragmentContenido.getId(), new FragmentConfiguracion(this)).addToBackStack(null).commit();
        }
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            Log.e("", "numero de elementos" + getSupportFragmentManager().getBackStackEntryCount());
            getFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
    private void popupLocaGPSCliente1() {
        try {
            AlertDialog.Builder builder;
            AlertDialog alertDialog;

            LayoutInflater vi = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = vi.inflate(R.layout.cliente_local, null);


            LatLng cordGoogleSp = new LatLng(latitude, longitude);
            mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment1)).getMap();



            mapa.addMarker(new MarkerOptions().position(cordGoogleSp));
            CameraPosition update = new CameraPosition(cordGoogleSp, 15, 0, 0);
            mapa.animateCamera(CameraUpdateFactory.newCameraPosition(update), 5000, null);
            mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            builder = new AlertDialog.Builder(this);
            builder.setView(view);
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog = builder.create();
            alertDialog.setIcon(R.drawable.inicio);
            alertDialog.setTitle("Localiza��o do Cliente:");
            alertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
