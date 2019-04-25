package ch.heia.mobiledev.weatherForecast.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.heia.mobiledev.weatherForecast.R;
import ch.heia.mobiledev.weatherForecast.data.database.Entry;
import ch.heia.mobiledev.weatherForecast.data.network.Response;

/**
 * Exposes a list of Entries (through a Response object) to a RecyclerView
 */
class AdapterForRecyclerView extends RecyclerView.Adapter<AdapterForRecyclerView.AdapterViewHolder> {
    private final Response.EntryFilter filterEntry;

    enum ListType {HOURS, DAYS}
    private final ListType listType;

    // The context we use to utility methods, app resources and layout inflaters
    private final Context mContext;

    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter. In the
     * constructor of our Adapter, we receive an instance of a class that has implemented
     * said interface. We store that instance in this variable to call the onItemClick method whenever
     * an item is clicked in the list.
     */
    private final AdapterOnItemClickHandler mClickHandler;

    // Response instance containing the list of entries
    private Response mResponse;

    /**
     * Creates anAdapter.
     *
     * @param context      Used to talk to the UI and app resources
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    AdapterForRecyclerView(@NonNull Context context, AdapterOnItemClickHandler clickHandler, Response.EntryFilter filterEntry, ListType listType) {
        mContext = context;
        mClickHandler = clickHandler;
        this.filterEntry = filterEntry;
        this.listType = listType;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (like ours does) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link androidx.recyclerview.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new AdapterViewHolder that holds the View for each list item
     */
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item, viewGroup, false);
        view.setFocusable(true);
        return new AdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the entry
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param adapterViewHolder The ViewHolder which should be updated to represent the
     *                          contents of the item at the given position in the data set.
     * @param position          The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder adapterViewHolder, int position) {
        Entry entry = mResponse.getEntry(position, filterEntry);

        // Display title
        if (listType == ListType.HOURS) {
            adapterViewHolder.titleView.setText(entry.getTime());
        } else {
            adapterViewHolder.titleView.setText(entry.getDate());
        }
        adapterViewHolder.temp.setText(String.format(mContext.getString(R.string.info_temperature),  entry.getTemp()));
        StartFragment.setWeatherPicture(entry.getIcon(), adapterViewHolder.imageView, mContext);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mResponse) {
            return 0;
        }
        return mResponse.getEntries(filterEntry).length;
    }

    /**
     * Swaps the list used by the Adapter for its data. This method is called by
     * the ui component after a load has finished. When this method is called, we assume we have
     * a new set of data, so we call notifyDataSetChanged to tell the RecyclerView to update.
     *
     * @param newResponse the new list of entries to use as Adapter's data source
     */
    void swapResponse(final Response newResponse) {
        // If there was no forecast data, then recreate all of the list
        mResponse = newResponse;
        notifyDataSetChanged();
    }

    /**
     * The interface that receives onItemClick messages.
     */
    public interface AdapterOnItemClickHandler {
        // here you may add a parameter specifying which entry was clicked
        void onItemClick(int position);
    }

    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView titleView;
        final ImageView imageView;
        final TextView temp;

        AdapterViewHolder(View view) {
            super(view);

            titleView = view.findViewById(R.id.item_title);
            imageView = view.findViewById(R.id.item_image);
            temp = view.findViewById(R.id.item_temp);

            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click. We fetch the date that has been
         * selected, and then call the onItemClick handler registered with this adapter, passing that
         * date.
         *
         * @param v the View that was clicked
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            // get information on which item was clicked based on position
            mClickHandler.onItemClick(adapterPosition);
        }
    }
}