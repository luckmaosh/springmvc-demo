/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package io.netty.http.websocketx.server;

import java.util.List;

/**
 * User: maso [maoshaohui@baidu.com]
 * Date: 15-1-8
 * Time: 下午2:46
 */
public interface OffLineService {
    List<OffLine> queryOffLineListByUserCode(String userCode);

    public void delOffLine(int id);
}
