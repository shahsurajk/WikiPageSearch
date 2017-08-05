package in.madscientist.wikipagesearch.model;

import java.util.List;

/**
 * Created by frapp on 5/8/17.
 */

public class Query
{
    private List<Pages> pages;

    public List<Pages> getPages ()
    {
        return pages;
    }

    public void setPages (List<Pages> pages)
    {
        this.pages = pages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pages = "+pages+"]";
    }
}