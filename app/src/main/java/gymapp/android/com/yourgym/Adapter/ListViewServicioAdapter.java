package gymapp.android.com.yourgym.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.LinkedList;

import gymapp.android.com.yourgym.Model.Noticia;
import gymapp.android.com.yourgym.Model.Servicio;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 08/11/2015.
 */
public class ListViewServicioAdapter extends BaseAdapter {
    private LinkedList<Servicio> listNoticia = new LinkedList<Servicio>();
    private Context _context;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public ListViewServicioAdapter(LinkedList<Servicio> listNoticia, Context context)
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_listview_instructor, null);
        }
        ImageView image = (ImageView)convertView.findViewById(R.id.image);
        TextView textConsejo = (TextView)convertView.findViewById(R.id.nombre);
        textConsejo.setText(listNoticia.get(position).getNombreServicio());
        imageLoader.displayImage(listNoticia.get(position).getUrl(), image);
        return convertView;
    }
}
