package ch.heia.mobiledev.weatherForecast.data.network;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import ch.heia.mobiledev.weatherForecast.data.database.Entry;

public class Response {
    // data members
    // array of entries
    @NonNull
    private final Entry[] mEntries;

    public Response(@NonNull final Entry[] entries) {
        mEntries = entries;
    }

    public Entry[] getEntries(EntryFilter filter) {
        return getFiltredData(filter);
    }
    public Entry getEntry(int index, EntryFilter filter) {
        return getFiltredData(filter)[index];
    }
    private Entry[] getFiltredData(EntryFilter filter) {
        LinkedList<Entry> result = new LinkedList<>();
        for (int i = 0; i < mEntries.length; i++) {
            if (filter.filter(mEntries[i], i == 0, i == mEntries.length-1)) {
                result.add(mEntries[i]);
            }
        }
        return result.toArray(new Entry[0]);
    }
    public interface EntryFilter {
        // here you may add a parameter specifying which entry was clicked
        boolean filter(Entry entry, boolean isFirst, boolean isLast);
    }
}
