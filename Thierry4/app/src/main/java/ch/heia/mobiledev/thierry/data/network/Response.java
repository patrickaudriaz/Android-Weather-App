package ch.heia.mobiledev.thierry.data.network;

import android.support.annotation.NonNull;
import ch.heia.mobiledev.thierry.database.Entry;

public class Response {
  // data members
  // array of entries
  @NonNull
  private final Entry[] mEntries;

  public Response(@NonNull final Entry[] entries) {
    mEntries = entries;
  }

  public Entry[] getEntries() {
    return mEntries;
  }
  public Entry getEntry(int index) {
    return mEntries[index];
  }
}
