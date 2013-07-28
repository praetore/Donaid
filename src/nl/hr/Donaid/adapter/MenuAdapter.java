package nl.hr.Donaid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.cloud.backend.android.R;

/**
 * Created with IntelliJ IDEA.
 * User: Darryl
 * Date: 26-6-13
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class MenuAdapter extends BaseAdapter {
    private final MenuItem[] items;

    public MenuAdapter(MenuItem... items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return items[i];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    // Vormgeving van menu-onderdelen
    @Override
    public View getView(int i, View reuse, ViewGroup parent) {
        ViewGroup view = getViewGroup(reuse, parent);
        MenuItem item = items[i];
        TextView textView = (TextView) view.findViewById(R.id.text);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        textView.setText(item.name);
        imageView.setImageResource(item.image);
        return view;
    }

    //  Inflater voor gebruik in getView()
    public ViewGroup getViewGroup(View reuse, ViewGroup parent) {
        if (reuse instanceof ViewGroup) return (ViewGroup) reuse;
        Context context = parent.getContext();
        LayoutInflater inflated = LayoutInflater.from(context);
        ViewGroup item = (ViewGroup) inflated.inflate(R.layout.menu_item, null);
        return item;
    }
}
