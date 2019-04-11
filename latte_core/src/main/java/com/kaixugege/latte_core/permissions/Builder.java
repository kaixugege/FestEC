package com.kaixugege.latte_core.permissions;

/**
 * @Author: KaixuGege
 * Time:           2019/4/9
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public interface Builder {
    public PerProduct perProduct = new PerProduct();
    public PerProduct create();

    public void addPer(Per per);
    public void delPer(Per per);
    public void clearPer(Per per);//清除权限list

}
