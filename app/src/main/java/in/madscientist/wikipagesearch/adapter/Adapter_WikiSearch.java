package in.madscientist.wikipagesearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import in.madscientist.wikipagesearch.R;
import in.madscientist.wikipagesearch.model.Pages;
import in.madscientist.wikipagesearch.ui.Activity_WebView;
import in.madscientist.wikipagesearch.util.Utils;

/**
 * Created by frapp on 5/8/17.
 */

public class Adapter_WikiSearch extends RecyclerView.Adapter<Adapter_WikiSearch.ViewHolder>{

    private List<Pages>pages;
    private Context context;

    public Adapter_WikiSearch(List<Pages> pages, Context context) {
        this.pages = pages;
        this.context = context;
    }

    @Override
    public Adapter_WikiSearch.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_search_result,parent,false));
    }

    @Override
    public void onBindViewHolder(Adapter_WikiSearch.ViewHolder holder, int position) {
        final Pages page = pages.get(position);

        String url ="";
        final String title =page.getTitle();
//        todo add check and fill string
        if (page.getTerms()!=null && page.getTerms().getDescription().length!=0){
            holder.contentTV.setText(page.getTerms().getDescription()[0]);
            holder.contentTV.setVisibility(View.VISIBLE);
        }else{
            holder.contentTV.setVisibility(View.GONE);
        }

        holder.titleTV.setText(title);
        if (page.getThumbnail()!=null && !TextUtils.isEmpty(page.getThumbnail().getSource())) {
            Picasso.with(context).load(page.getThumbnail().getSource())
                    .placeholder(R.drawable.wiki_icon_logo)
                    .fit().centerInside().into(holder.circleImageView);
        }else{
            Picasso.with(context).load(R.drawable.wiki_icon_logo)
                    .placeholder(R.drawable.wiki_icon_logo)
                    .fit().centerInside().into(holder.circleImageView);
        }
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,Activity_WebView.class);
                intent.putExtra(Activity_WebView.KEY_URL, Utils.generateURLForPageID(page.getPageid()));
                intent.putExtra(Activity_WebView.KEY_TITLE,title);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rsr_rowLayout)LinearLayout rowLayout;
        @BindView(R.id.rsr_content_text)TextView contentTV;
        @BindView(R.id.rsr_title_text)TextView titleTV;
        @BindView(R.id.rsr_civ)CircleImageView circleImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
