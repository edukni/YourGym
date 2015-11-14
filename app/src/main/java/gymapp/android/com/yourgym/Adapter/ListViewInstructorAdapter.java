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
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 07/11/2015.
 */
public class ListViewInstructorAdapter extends BaseAdapter {

    private LinkedList<Noticia> listNoticia = new LinkedList<Noticia>();
    private Context _context;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public ListViewInstructorAdapter(LinkedList<Noticia> listNoticia, Context context)
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
        textConsejo.setText(listNoticia.get(position).get_titulo());
        imageLoader.displayImage(listNoticia.get(position).get_imagen(), image);
        return convertView;
    }
}
