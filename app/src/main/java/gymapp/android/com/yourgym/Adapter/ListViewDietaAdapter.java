package gymapp.android.com.yourgym.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import gymapp.android.com.yourgym.Model.Ejercicio;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 08/11/2015.
 */
public class ListViewDietaAdapter extends BaseAdapter {
    private LinkedList<Noticia> listNoticia = new LinkedList<Noticia>();
    private Context _context;

    public ListViewDietaAdapter(LinkedList<Noticia> listNoticia, Context context)
    {
        this.listNoticia = listNoticia;
        this._context = context;
    }

    @Override
    public int getCount() {
        if(listNoticia!=null) {
            if (listNoticia.size() > 0) {
                return listNoticia.size();
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listNoticia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.adapter_listview_ejercicio,null);
        TextView textConsejo = (TextView)convertView.findViewById(R.id.txtTitulo);
        //      TextView txtTipoContenido = (TextView)convertView.findViewById(R.id.txtTipoContenido);
        SharedPreferences prefs = _context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int lang = prefs.getInt("language", 0);
        if(lang == 0) {
            textConsejo.setText(listNoticia.get(position).get_titulo());
        }
        else if(lang == 1) {
            textConsejo.setText(listNoticia.get(position).get_tituloIng());
        }
        //  txtTipoContenido.setText("Consejo");
        return convertView;
    }
}
