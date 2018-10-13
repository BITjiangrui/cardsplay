package com.cardsplay.core.models;


public enum PorkColor
{
    F('F'), M('M'), X('X'), H('H');
    

    private char color;
    
    private PorkColor(char color)
    {
        this.color = color;
    }
    

    public static PorkColor getPorkColor(char color)
    {
        for (PorkColor porkColor : PorkColor.values())
        {
            if (porkColor.color == color)
            {
                return porkColor;
            }
        }
        return null;
    }
}
