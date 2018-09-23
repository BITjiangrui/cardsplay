package org.playcards.core.models;

/**
 * <pre>
 * 扑克牌花色
 * 
 * </pre>
 */
public enum PorkColor
{
    F('F'), M('M'), X('X'), H('H');
    
    /**
     * 牌的花色
     */
    private char color;
    
    private PorkColor(char color)
    {
        this.color = color;
    }
    
    /**
     * <pre>
     * 根据花色字符查找扑克牌的花色枚举对象
     * 
     * @param color
     * @return
     * </pre>
     */
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
