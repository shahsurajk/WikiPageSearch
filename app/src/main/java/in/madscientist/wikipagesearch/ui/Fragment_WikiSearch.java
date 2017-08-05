package in.madscientist.wikipagesearch.ui;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.madscientist.wikipagesearch.R;
import in.madscientist.wikipagesearch.adapter.Adapter_WikiSearch;
import in.madscientist.wikipagesearch.model.Pages;
import in.madscientist.wikipagesearch.model.WikiSearch;
import in.madscientist.wikipagesearch.network.APIManager;
import in.madscientist.wikipagesearch.network.RetrofitManager;
import in.madscientist.wikipagesearch.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frapp on 4/8/17.
 */

public class Fragment_WikiSearch extends Fragment {

    @BindView(R.id.searchEditText)TextInputEditText searchET;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    @BindView(R.id.searchProgress)ProgressBar searchProgressBar;

    private Adapter_WikiSearch adapter_wikiSearch;
    private List<Pages>pages = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        setEditTextListeners();
        adapter_wikiSearch = new Adapter_WikiSearch(pages,getActivity());

        recyclerView.setAdapter(adapter_wikiSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void makeSuggestionRequest(final String searchString){
        if (isAdded() && Utils.isOnline(getActivity())){
        searchProgressBar.setVisibility(View.VISIBLE);
        RetrofitManager.getInstance().create(APIManager.class)
                .getAutoCompleteSearch(searchString).enqueue(new Callback<WikiSearch>() {
            @Override
            public void onResponse(Call<WikiSearch> call, Response<WikiSearch> response) {
                pages.clear();
                pages.addAll(response.body().getQuery().getPages());
                searchProgressBar.setVisibility(View.GONE);
                adapter_wikiSearch.setSearchedString(searchString);
                adapter_wikiSearch.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<WikiSearch> call, Throwable t) {
                pages.clear();
                adapter_wikiSearch.notifyDataSetChanged();
                Utils.showToastMessage(getActivity(),"Oops! Something went wrong");
                searchProgressBar.setVisibility(View.GONE);
            }
        });
        }else{
//            todo show no internet dialog;
        }

    }
    private void setEditTextListeners(){
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>1){
                    try {
                        makeSuggestionRequest(URLEncoder.encode(charSequence.toString(),"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wiki_search,container,false);
    }
}
