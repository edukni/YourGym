package gymapp.android.com.yourgym.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gymapp.android.com.yourgym.Model.Consejo;
import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 05/11/2015.
 */
public class ListViewInicioAdapter extends BaseAdapter {

    private LinkedList<Noticia> listNoticia = new LinkedList<Noticia>();
    private Context _context;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public ListViewInicioAdapter(LinkedList<Noticia> listNoticia, Context context)
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_listview_inicio, null);
        }
        TextView textConsejo = (TextView)convertView.findViewById(R.id.textConsejo);
        TextView txtTipoContenido = (TextView)convertView.findViewById(R.id.txtTipoContenido);
        ImageView image = (ImageView)convertView.findViewById(R.id.imageConsejo);
        imageLoader.displayImage(listNoticia.get(position).get_imagen(), image);
        textConsejo.setText(listNoticia.get(position).get_titulo());


        if((listNoticia.get(position).get_tipoContenido()==0) ) {
            txtTipoContenido.setText("Consejo");
        } else if((listNoticia.get(position).get_tipoContenido()==1)) {
            txtTipoContenido.setText("Promocion");
        }
        return convertView;
    }
}
