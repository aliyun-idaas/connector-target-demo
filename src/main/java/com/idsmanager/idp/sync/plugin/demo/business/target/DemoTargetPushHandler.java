package com.idsmanager.idp.sync.plugin.demo.business.target;

import com.idsmanager.idp.sync.SyncOperationType;
import com.idsmanager.idp.sync.core.infrastructure.DataTransformContext;
import com.idsmanager.idp.sync.core.infrastructure.relation.DataRelation;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetDataItem;
import com.idsmanager.idp.sync.plugin.demo.entity.target.DemoTargetAccountEntity;

/**
 * @author ganyu
 * @date 2024/7/30 21:29
 */
public class DemoTargetPushHandler {
    public void create(TargetDataItem item, DataTransformContext context, DataRelation relation) {
        if(item instanceof DemoTargetAccountEntity){
            DemoTargetAccountEntity user = (DemoTargetAccountEntity) item;
            // 执行具体操作逻辑
        }
    }
}
