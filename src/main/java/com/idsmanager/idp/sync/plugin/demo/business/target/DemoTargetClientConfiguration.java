package com.idsmanager.idp.sync.plugin.demo.business.target;

import com.idsmanager.idp.sync.core.infrastructure.source.SourceConnectionConfiguration;
import com.idsmanager.idp.sync.plugin.demo.business.DemoConfiguration;

/**
 * 2021/12/10 10:49
 * <p> 目标管理中，特殊的字段定义
 *
 * @author xbj
 * @class DemoTargetConfiguration
 * @since
 */
public class DemoTargetClientConfiguration extends DemoConfiguration implements SourceConnectionConfiguration {
    /**
     * 是否把来源的根节点，作为目标的一级子节点同步，直接挂靠在目标根节点下
     * @return
     */
    @Override
    public boolean syncSourceRootToTargetRoot() {
        return false;
    }
}
