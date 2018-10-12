package com.cardsplay.core.models;

 
/**
 * <pre>
 * һ���˿��ƶ���
 * 1.ʵ��Comparable�ӿڣ�ͨ��compareTo�������бȽϴ�С
 * 2.�ȽϹ���
 * 1)�ȿ��������֣����ִ�ľʹ�;
 * 2)����������ͬʱ����ɫ��ľʹ�;
 * 
 * </pre>
 */
public class Card implements Comparable<Card>
{
    /**
     * �˿��Ƶ���������
     */
    private PorkActor porkActor;
    
    /**
     * �˿��ƵĻ�ɫ
     */
    private PorkColor porkColor;
    
    /**
     * ����Ϊ2���ַ����������˿��Ƶ����ֺͻ�ɫ����0λΪ���֣���1λΪ��ɫ <Ĭ�Ϲ��캯��>
     */
    public Card(String porkAttr)
    {
        char porkActor = porkAttr.charAt(1);
        char porkColor = porkAttr.charAt(0);
        
        setPorkActor(PorkActor.getPorkActor(porkActor));
        setPorkColor(PorkColor.getPorkColor(porkColor));
    }
    
    /**
     * ��ȡ porkActor
     * 
     * @return ���� porkActor
     */
    public PorkActor getPorkActor()
    {
        return porkActor;
    }
    
    /**
     * ���� porkActor
     * 
     * @param porkActor
     */
    public void setPorkActor(PorkActor porkActor)
    {
        this.porkActor = porkActor;
    }
    
    /**
     * ��ȡ porkColor
     * 
     * @return ���� porkColor
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
     * ���ط���
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
     * ���ط���
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
     * ���ط���
     * 
     * @return
     */
    @Override
    public String toString()
    {
        return "Pork [porkActor=" + porkActor + ", porkColor=" + porkColor + "]";
    }

    /**
     * ���ط���
     * 
     * @param o
     * @return
     */
    public int compareTo(Card o)
    {
        // ��ȥ�Ƚ������С
        int compare = getPorkActor().compareTo(o.getPorkActor());
        // ������ͬʱ
        if (compare == 0)
        {
            // �Ƚϻ�ɫ
            return getPorkColor().compareTo(o.getPorkColor());
        }
        return compare;
    }
}
