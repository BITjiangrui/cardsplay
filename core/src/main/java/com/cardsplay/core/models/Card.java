package com.cardsplay.core.models;

 
/**
 * <pre>
 * 一张扑克牌对象
 * 1.实现Comparable接口，通过compareTo方法进行比较大小
 * 2.比较规则：
 * 1)先看牌面数字，数字大的就大;
 * 2)牌面数字相同时，花色大的就大;
 * 
 * </pre>
 */
public class Card implements Comparable<Card>
{
    /**
     * 扑克牌的牌面数字
     */
    private PorkActor porkActor;
    
    /**
     * 扑克牌的花色
     */
    private PorkColor porkColor;
    
    /**
     * 长度为2的字符串，接收扑克牌的数字和花色：第0位为数字，第1位为花色 <默认构造函数>
     */
    public Card(String porkAttr)
    {
        char porkActor = porkAttr.charAt(1);
        char porkColor = porkAttr.charAt(0);
        
        setPorkActor(PorkActor.getPorkActor(porkActor));
        setPorkColor(PorkColor.getPorkColor(porkColor));
    }
    
    /**
     * 获取 porkActor
     * 
     * @return 返回 porkActor
     */
    public PorkActor getPorkActor()
    {
        return porkActor;
    }
    
    /**
     * 设置 porkActor
     * 
     * @param porkActor
     */
    public void setPorkActor(PorkActor porkActor)
    {
        this.porkActor = porkActor;
    }
    
    /**
     * 获取 porkColor
     * 
     * @return 返回 porkColor
     */
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
    
    /**
     * 重载方法
     * 
     * @return
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((porkActor == null) ? 0 : porkActor.hashCode());
        result = prime * result + ((porkColor == null) ? 0 : porkColor.hashCode());
        return result;
    }
    
    /**
     * 重载方法
     * 
     * @param obj
     * @return
     */
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
    

    
    /**
     * 重载方法
     * 
     * @return
     */
    @Override
    public String toString()
    {
        return "Pork [porkActor=" + porkActor + ", porkColor=" + porkColor + "]";
    }

    /**
     * 重载方法
     * 
     * @param o
     * @return
     */
    public int compareTo(Card o)
    {
        // 先去比较牌面大小
        int compare = getPorkActor().compareTo(o.getPorkActor());
        // 牌面相同时
        if (compare == 0)
        {
            // 比较花色
            return getPorkColor().compareTo(o.getPorkColor());
        }
        return compare;
    }
}
