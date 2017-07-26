package be.ugent.zeus.hydra.ui.common.recyclerview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;

import be.ugent.zeus.hydra.ui.common.recyclerview.viewholders.DataViewHolder;
import be.ugent.zeus.hydra.ui.main.minerva.SearchHelper;
import be.ugent.zeus.hydra.ui.main.minerva.SearchStateListener;
import java8.lang.Iterables;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Searchable adapter.
 *
 * @author Niko Strijbol
 */
public abstract class DiffSearchableItemAdapter<D, V extends DataViewHolder<D>> extends DiffAdapter<D, V> implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener, SearchHelper {

    private List<D> allData;
    private final Function<D, String> stringifier;

    private boolean isSearching;

    private final Set<SearchStateListener> listeners = Collections.newSetFromMap(new WeakHashMap<>());

    protected DiffSearchableItemAdapter(Function<D, String> stringifier) {
        super();
        this.stringifier = stringifier;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void setItems(List<D> items) {
        this.allData = items;
        super.setItems(items);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!isSearching) {
            Iterables.forEach(listeners, listener -> listener.onSearchStateChange(true));
        }
        this.isSearching = true;
        List<D> filtered = StreamSupport.stream(allData)
                .filter(s -> stringifier.apply(s).contains(newText.toLowerCase()))
                .collect(Collectors.toList());
        setUpdate(filtered);
        return true;
    }

    private void setUpdate(List<D> items) {
        synchronized (updateLock) {
            if (isDiffing) {
                scheduledUpdate = items;
            } else {
                updateItemInternal(items);
                isDiffing = true;
            }
        }
    }

    @Override
    public boolean onClose() {
        if (isSearching) {
            Iterables.forEach(listeners, listener -> listener.onSearchStateChange(false));
        }
        this.isSearching = false;
        return false;
    }

    /**
     * Can be called when the search view opens.
     */
    public void onOpen() {
        if (!isSearching) {
            Iterables.forEach(listeners, listener -> listener.onSearchStateChange(true));
        }
        this.isSearching = true;
        Iterables.forEach(listeners, listener -> listener.onSearchStateChange(isSearching));
    }

    @Override
    public boolean isSearching() {
        return isSearching;
    }

    @Override
    public void registerSearchListener(@NonNull SearchStateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterSearchListener(@NonNull SearchStateListener listener) {
        listeners.remove(listener);
    }
}