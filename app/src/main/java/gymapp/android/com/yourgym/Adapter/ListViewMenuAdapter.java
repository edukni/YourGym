package gymapp.android.com.yourgym.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gymapp.android.com.yourgym.Model.Menu;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 02/11/2015.
 */
public class ListViewMenuAdapter extends BaseAdapter {
    private List<Menu> _menu = new ArrayList<>();
    private LinkedList<Menu> categoria = new LinkedList<Menu>();
    private Context _context;

    public ListViewMenuAdapter(List<Menu> menu, Context context)
    {
        this._menu = menu;
        this._context = context;
    }

    @Override
    public int getCount() {
        if(_menu!=null) {
            if (_menu.size() > 0) {
                return _menu.size();
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return _menu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.adapter_menu_lateral,null);
        TextView txtTitulo = (TextView)convertView.findViewById(R.id.txtTitulo);
        ImageView imageIcono = (ImageView)convertView.findViewById(R.id.imagenIcono);
      //  imageIcono.setImageResource(R.mipmap.pagos);

        txtTitulo.setText(_menu.get(position).getIdentificador());

        if(position==0) {
            imageIcono.setImageResource(R.mipmap.inicio2);

        }
        else if(position==1) {

            imageIcono.setImageResource(R.mipmap.rutina2);
        }
        else if(position==2) {
            imageIcono.setImageResource(R.mipmap.dieta2);
        }
        else if(position==3)     {
            imageIcono.setImageResource(R.mipmap.instructor2);

        }
        else if (position==4) {
            imageIcono.setImageResource(R.mipmap.rutina2);
        }
        else if(position==5) {
            imageIcono.setImageResource(R.mipmap.servicios2);
        }

        return convertView;




    }
}
