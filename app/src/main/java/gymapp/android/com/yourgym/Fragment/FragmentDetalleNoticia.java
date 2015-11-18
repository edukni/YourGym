package gymapp.android.com.yourgym.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import gymapp.android.com.yourgym.MainActivity;
import gymapp.android.com.yourgym.Model.Ejercicio;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.R;


/**
 * Created by Cesar on 05/11/2015.
 */
public class FragmentDetalleNoticia extends Fragment {
    private OnChangePage _listener;
    private Noticia noticia;
    Context context;
    ImageLoader imageLoader = ImageLoader.getInstance();
    int lenguaje;

    public FragmentDetalleNoticia(OnChangePage listener, Object itemSelect)
    {
        context = (MainActivity)listener;
        if(itemSelect instanceof Noticia) {
            this._listener = listener;
            this.noticia = (Noticia) itemSelect;
        } else if(itemSelect instanceof Ejercicio) {
            this._listener = listener;
            this.noticia = new Noticia();
            this.noticia.set_titulo(((Ejercicio)itemSelect).getNombreEjercicio());
            this.noticia.set_imagen(((Ejercicio) itemSelect).getUrlFoto());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup)inflater.inflate(R.layout.fragment_detalle_noticia, container, false);

        TextView textNoticia = (TextView)container.findViewById(R.id.textConsejo);
        TextView descripcion = (TextView)container.findViewById(R.id.descripcion);
        Button btnShare = (Button)container.findViewById(R.id.btnShare);
        ImageView image = (ImageView)container.findViewById(R.id.imageConsejo);

        loadConfig();

        imageLoader.displayImage(noticia.get_imagen(), image);
        context = container.getContext();
        textNoticia.setText(noticia.get_titulo());
        if(!noticia.get_descripion().equals("")) {
            if (lenguaje == 0)
                descripcion.setText(noticia.get_descripion());
            else if (lenguaje == 1)
                descripcion.setText(noticia.get_descripionIng());
        } else {
            descripcion.setVisibility(View.GONE);
        }
        final ViewGroup finalContainer = container;
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(noticia.get_titulo()));
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, noticia.get_descripion());

                PackageManager pm = getActivity().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(sharingIntent, 0);

                for (final ResolveInfo app : activityList)
                {
                    if ((app.activityInfo.name).contains("facebook"))
                    {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        sharingIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        sharingIntent.setComponent(name);
                    }
                }
               startActivityForResult(Intent.createChooser(sharingIntent, "Share via"),1);
            }
        });
        return container;
    }

    private void loadConfig(){
        Context context = getActivity();
        SharedPreferences prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        lenguaje = prefs.getInt("language", 0);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        String codigo = String.valueOf(requestCode);
                Log.e("Este es el codigo de: ", codigo);
    }
}
