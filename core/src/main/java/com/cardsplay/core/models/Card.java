package com.cardsplay.core.models;

 

public class Card implements Comparable<Card>
{

    private PorkActor porkActor;
    

    private PorkColor porkColor;

    public Card(String porkAttr)
    {
        char porkActor = porkAttr.charAt(1);
        char porkColor = porkAttr.charAt(0);
        
        setPorkActor(PorkActor.getPorkActor(porkActor));
        setPorkColor(PorkColor.getPorkColor(porkColor));
    }
    

    public PorkActor getPorkActor()
    {
        return porkActor;
    }
    

    public void setPorkActor(PorkActor porkActor)
    {
        this.porkActor = porkActor;
    }
    

    public PorkColor getPorkColor()
    {
        return porkColor;
    }
    
    /**
     * set porkColor
     * 
     * @param  porkColor
     */
    public void setPorkColor(PorkColor porkColor)
    {
        this.porkColor = porkColor;
    }
    

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((porkActor == null) ? 0 : porkActor.hashCode());
        result = prime * result + ((porkColor == null) ? 0 : porkColor.hashCode());
        return result;
    }
    

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card)obj;
        if (porkActor != other.porkActor)
            return false;
        if (porkColor != other.porkColor)
            return false;
        return true;
    }
    

    

    @Override
    public String toString()
    {
        return "Pork [porkActor=" + porkActor + ", porkColor=" + porkColor + "]";
    }


    public int compareTo(Card o)
    {
        int compare = getPorkActor().compareTo(o.getPorkActor());
        if (compare == 0)
        {
            return getPorkColor().compareTo(o.getPorkColor());
        }
        return compare;
    }
}
