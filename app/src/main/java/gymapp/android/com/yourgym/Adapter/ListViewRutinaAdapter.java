package gymapp.android.com.yourgym.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.Model.Rutina;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 06/11/2015.
 */
public class ListViewRutinaAdapter extends BaseAdapter {
    private LinkedList<Rutina> listNoticia = new LinkedList<Rutina>();
    private Context _context;

    public ListViewRutinaAdapter(LinkedList<Rutina> listNoticia, Context context)
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
        if(convertView==null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_listview_rutina, null);
        }
        TextView textConsejo = (TextView)convertView.findViewById(R.id.nombre);
        TextView txtTipoContenido = (TextView)convertView.findViewById(R.id.contenido);
        TextView descripcionRutina = (TextView)convertView.findViewById(R.id.descripcionRutina);
        textConsejo.setText(listNoticia.get(position).getNombre());
        descripcionRutina.setText(listNoticia.get(position).getDescripcion());
        if(listNoticia.get(position).getEstado()==0) {
            txtTipoContenido.setText("En Progreso");
            txtTipoContenido.setTextColor(Color.parseColor("#ff0000"));
        } else {
            txtTipoContenido.setText("Completado");
            txtTipoContenido.setTextColor(Color.parseColor("#00ff00"));
        }
        return convertView;
    }
}
