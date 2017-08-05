package in.madscientist.wikipagesearch.model;

/**
 * Created by frapp on 5/8/17.
 */

public class Terms
{
    private String[] description;

    public String[] getDescription ()
    {
        return description;
    }

    public void setDescription (String[] description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [description = "+description+"]";
    }
}

