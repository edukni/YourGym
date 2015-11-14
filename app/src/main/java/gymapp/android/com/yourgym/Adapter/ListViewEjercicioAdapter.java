package gymapp.android.com.yourgym.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import gymapp.android.com.yourgym.Model.Ejercicio;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 06/11/2015.
 */
public class ListViewEjercicioAdapter extends BaseAdapter {
    private LinkedList<Ejercicio> listNoticia = new LinkedList<Ejercicio>();
    private Context _context;

    public ListViewEjercicioAdapter(LinkedList<Ejercicio> listNoticia, Context context)
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
        textConsejo.setText(listNoticia.get(position).getNombreEjercicio());
        //  txtTipoContenido.setText("Consejo");
        return convertView;
    }
}
