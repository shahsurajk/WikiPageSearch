package in.madscientist.wikipagesearch.network;

import in.madscientist.wikipagesearch.model.WikiSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by frapp on 5/8/17.
 */

public interface APIManager {

    @GET(URL.API_END_POINT+"?action=query&format=json&prop=pageimages|pageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=200&pilimit=10&wbptterms=description&gpslimit=10")
    Call<WikiSearch>getAutoCompleteSearch(@Query("gpssearch")String search);
}
