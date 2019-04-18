package ch.heia.mobiledev.thierry.ui;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.heia.mobiledev.thierry.R;
import ch.heia.mobiledev.thierry.data.network.Response;
import ch.heia.mobiledev.thierry.data.database.Entry;

public class MainListAdapter extends ArrayAdapter<String> {
    // data members
    private Response mResponse = null;

    // View lookup cache
    private static class ViewHolder {
        TextView temp;
        TextView time;
        TextView hum;
        TextView pres;
        TextView wind;
    }

    // constructor
    public MainListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    // used for swapping news
    public void swapResponse(Response response) {
        mResponse = response;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mResponse == null) {
            return 0;
        }
        return mResponse.getEntries().length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.body_layout, parent, false);

            viewHolder.temp = convertView.findViewById(R.id.body_temp);
            viewHolder.hum = convertView.findViewById(R.id.body_hum);
            viewHolder.pres = convertView.findViewById(R.id.body_pres);
            viewHolder.wind = convertView.findViewById(R.id.body_wind);
            viewHolder.time = convertView.findViewById(R.id.body_time);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.temp.setText(mResponse.getEntry(position).getTemp());
        viewHolder.hum.setText(mResponse.getEntry(position).getHum());
        viewHolder.pres.setText(mResponse.getEntry(position).getPres());
        viewHolder.wind.setText(mResponse.getEntry(position).getWind());
        viewHolder.time.setText(mResponse.getEntry(position).getTime());

        return convertView;
    }
}
