package com.bwei.fangjingdong.net;

/**
 * 作者：王建勋
 * 时间：2017-12-08 16:40
 * 类的用途：
 */

public class API {
    public static boolean isOnline = false;
    public static final String DEV = "http://120.27.23.105/";
    public static final String WROK = "";
    public static final String HOST = isOnline ? WROK : DEV;

    public static final String LOGIN = "user/login";//登陆
    public static final String REGISTER = "user/reg";//注册
    public static final String USERINFO = "user/getUserInfo";//获取用户信息
    public static final String HOMEAD = "ad/getAd";//首页广告、轮播
    public static final String SEARCH = "product/searchProducts?source=android&page=1";//根据关键词搜索商品
    public static final String CLASS = "product/getCatagory";//分类
    public static final String PRODUCT_CATAGORY = "product/getProductCatagory";//商品子分类接口
    public static final String ZIPRODUCT = "product/getProducts";//当前子分类下的商品列表（分页）
    public static final String PRODUCTDETAIL = "product/getProductDetail?source=android";//商品详情
    public static final String GETCARTS = "product/getCarts?source=android";//查询购物车
    public static final String ADDCART = "product/addCart?source=android";//添加购物车
    public static final String GETORGERS = "product/getOrders";//订单列表
}
