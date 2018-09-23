package org.playcards.core.models;

/**
 * <pre>
 * �˿��ƻ�ɫ
 * 
 * </pre>
 */
public enum PorkColor
{
    F('F'), M('M'), X('X'), H('H');
    
    /**
     * �ƵĻ�ɫ
     */
    private char color;
    
    private PorkColor(char color)
    {
        this.color = color;
    }
    
    /**
     * <pre>
     * ���ݻ�ɫ�ַ������˿��ƵĻ�ɫö�ٶ���
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
