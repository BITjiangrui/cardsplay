package com.cardsplay.core.models;

 
/**
 * <pre>
 * ÿ���˿��Ƶ���������
 * 
 * </pre>
 */
public enum PorkActor
{
    TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NIME('9'), TEN('T'), J('J'), Q('Q'), K(
        'K'), A('A');
    
    private char num;
    
    private PorkActor(char num)
    {
        this.num = num;
    }
    
    /**
     * ��ȡ num
     * 
     * @return ���� num
     */
    private char getNum()
    {
        return num;
    }
    
    /**
     * <pre>
     * �������������ҵ��˿��ƶ�Ӧ������ö�ٶ���
     * 
     * @param num
     * @return
     * </pre>
     */
    public static PorkActor getPorkActor(char num)
    {
        for (PorkActor porkActor : PorkActor.values())
        {
            if (porkActor.getNum() == num)
            {
                return porkActor;
            }
        }
        return null;

    }
}
