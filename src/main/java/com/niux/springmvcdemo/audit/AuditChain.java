package com.niux.springmvcdemo.audit;

/**
 * Created by shaohui.mao
 * on 16/4/22.下午1:36
 */
public abstract class AuditChain {

    private AuditChain auditChain;

    public abstract void doFilter();

}
