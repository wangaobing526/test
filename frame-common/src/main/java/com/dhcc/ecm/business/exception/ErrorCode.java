package com.dhcc.ecm.business.exception;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ErrorCode {
    //权限管理从100100开始
    //获得菜单树失败
    public  static  final String AUTHOR_GETAUTHORTREE_ERROR = "100100";
    
    public static final String NO_PERMISSION_ERROR = "100101";
    
    public static final String UNKNOW_ERROR = "UNKNOW_ERROR";
    
    //系统异常
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
    /**
     * 业务编码
     */
    //删除失败
    public static final String DELETE_ERROR = "200001";
    //保存失败
    public static final String SAVE_ERROR = "200002";
    //查询失败
    public static final String QUERY_ERROR = "200003";
    //数据输入错误
    public static final String DATA_INPUT_ERROR = "200004";
    //空值判断
    public static final String DATA_INPUT_NULL_ERROR = "200005";
    //赋值操作失败
    public static final String SETVALUE_ERROR = "200006";
    //远程调用异常
    public static final String REMOTE_ERROR = "200007";
}
