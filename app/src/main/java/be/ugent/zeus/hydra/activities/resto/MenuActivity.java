package be.ugent.zeus.hydra.activities.resto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.View;
import be.ugent.zeus.hydra.R;
import be.ugent.zeus.hydra.activities.resto.common.RestoWebsiteActivity;
import be.ugent.zeus.hydra.adapters.resto.MenuPageAdapter;
import be.ugent.zeus.hydra.loader.ThrowableEither;
import be.ugent.zeus.hydra.loader.cache.Request;
import be.ugent.zeus.hydra.models.resto.RestoMenu;
import be.ugent.zeus.hydra.models.resto.RestoOverview;
import be.ugent.zeus.hydra.requests.RestoMenuOverviewRequest;

import java.util.ArrayList;

/**
 * Display the menu of the resto in a separate view, similar to the old app.
 *
 * @author Niko Strijbol
 */
public class MenuActivity extends RestoWebsiteActivity<RestoOverview> {

    private static final String URL = "http://www.ugent.be/student/nl/meer-dan-studeren/resto";

    private MenuPageAdapter pageAdapter;
    private ViewPager mViewPager;

    protected ArrayList<RestoMenu> data = null;

    public RestoMenu getMenu(int position) {
        return data.get(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_resto);
        super.onCreate(savedInstanceState);
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        pageAdapter = new MenuPageAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = $(R.id.resto_tabs_content);
        mViewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = $(R.id.resto_tabs_slider);
        tabLayout.setupWithViewPager(mViewPager);

        startLoader();
    }

    /**
     * @return The URL for the overflow button to display a website link.
     */
    @Override
    protected String getUrl() {
        return URL;
    }

    /**
     * This method is used to receive new data.
     *
     * @param data The new data.
     */
    @Override
    public void receiveData(@NonNull RestoOverview data) {
        super.receiveData(data);
        this.data = data;
        pageAdapter.setNumber(data.size());
    }

    /**
     * Called when a previously created loader is being reset, and thus making its data unavailable.  The application
     * should at this point remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<ThrowableEither<RestoOverview>> loader) {
        super.onLoaderReset(loader);
        this.data = new ArrayList<>();
        pageAdapter.setNumber(data.size());
    }

    /**
     * @return The main view of this activity. Currently this is used for snackbars, but that may change.
     */
    @Override
    protected View getMainView() {
        return mViewPager;
    }

    @Override
    public Request<RestoOverview> getRequest() {
        return new RestoMenuOverviewRequest();
    }
}