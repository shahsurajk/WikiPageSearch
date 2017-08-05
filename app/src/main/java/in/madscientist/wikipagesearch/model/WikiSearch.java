package in.madscientist.wikipagesearch.model;

/**
 * Created by frapp on 5/8/17.
 */

public class WikiSearch
{
    private Query query;


    private String batchcomplete;

    public Query getQuery ()
    {
        return query;
    }

    public void setQuery (Query query)
    {
        this.query = query;
    }

    public String getBatchcomplete ()
    {
        return batchcomplete;
    }

    public void setBatchcomplete (String batchcomplete)
    {
        this.batchcomplete = batchcomplete;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [query = "+query+", continue = , batchcomplete = "+batchcomplete+"]";
    }
}
