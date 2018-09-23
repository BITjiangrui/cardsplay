package com.cardsplay.core.models;

 
/**
 * <pre>
 * 每张扑克牌的牌面数字
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
     * 获取 num
     * 
     * @return 返回 num
     */
    private char getNum()
    {
        return num;
    }
    
    /**
     * <pre>
     * 根据牌面数字找到扑克牌对应的牌面枚举对象
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
