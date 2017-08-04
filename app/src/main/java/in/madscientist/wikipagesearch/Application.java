package in.madscientist.wikipagesearch;

import in.madscientist.wikipagesearch.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by frapp on 4/8/17.
 */

public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
