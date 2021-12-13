package com.idsmanager.idp.sync.plugin.demo.client;

import com.idsmanager.idp.sync.SCIMException;
import com.idsmanager.idp.sync.SyncErrorCode;
import com.idsmanager.idp.sync.SyncObjectType;
import com.idsmanager.idp.sync.SyncOperationType;
import com.idsmanager.idp.sync.core.infrastructure.ApiResult;
import com.idsmanager.idp.sync.core.infrastructure.DataTransformContext;
import com.idsmanager.idp.sync.core.infrastructure.mapping.AttributeDescriptor;
import com.idsmanager.idp.sync.core.infrastructure.relation.DataRelation;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetDataItem;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetDataPushClient;
import com.idsmanager.idp.sync.plugin.demo.business.target.DemoTargetClientConfiguration;
import com.idsmanager.idp.sync.plugin.demo.entity.target.DemoTargetAccountEntity;
import com.idsmanager.idp.sync.plugin.demo.entity.target.DemoTargetBaseEntity;
import com.idsmanager.idp.sync.plugin.demo.entity.target.DemoTargetOrganizationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 2021/12/10 14:13
 * <p>推送数据实现
 *
 * @author xbj
 * @class DemoTargetDataPushClient
 * @since
 */
public class DemoTargetDataPushClient implements TargetDataPushClient {

    private static Logger LOG = LoggerFactory.getLogger(DemoTargetDataPushClient.class);

    private DemoTargetClientConfiguration configuration;

    public DemoTargetDataPushClient(DemoTargetClientConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * TODO 该接口用于实现真正的向目标推送数据
     *
     * @param item
     * @param context 数据推送上下文对象，可以获取当前正在推送的任务、来源、目标、任务批次ID等信息，也能获取当前的同步引擎核心对象的实例（DataTransformEngine）
     * @param relation 数据映射对象，用于维护映射关系
     * @return com.idsmanager.idp.sync.core.infrastructure.ApiResult
     * @date 2021/12/12 14:51
     **/
    @Override
    public ApiResult push(TargetDataItem item, DataTransformContext context, DataRelation relation) throws SCIMException {
        //转换实例对象
        if (!(item instanceof DemoTargetBaseEntity)) {
            throw new SCIMException(SyncErrorCode.SCIM_ERR_UNSUPPORTED_OBJECT_IMPL, "不支持的对象实例[" + item.getClass() + "]");
        }
        DemoTargetBaseEntity entity = (DemoTargetBaseEntity) item;
        //实例对象的类型，是用户、组织、组
        String objectType = item.objectType().name().toLowerCase();

        //当前操作类型
        SyncOperationType syncOperationType = item.getSyncOperationType();

        //可选 同步数据前，检查对象是否存在
        boolean checkObjectExistBeforeSync = context.getTask().isCheckModel();

        //TODO 具体的推送实现，可另外使用handler

        return null;
    }

    /**
     * 初始化目标对象
     * 不用修改
     **/
    @Override
    public TargetDataItem newTargetDataItem(SyncObjectType syncObjectType) throws SCIMException {
        if (!support(syncObjectType)) {
            throw new SCIMException(SyncErrorCode.SCIM_ERR_UNSUPPORTED_OBJECT_TYPE, "不支持的同步对象类型[" + syncObjectType + "]");
        }
        return SyncObjectType.ORGANIZATION == syncObjectType ? new DemoTargetOrganizationEntity() : new DemoTargetAccountEntity();
    }

    @Override
    public Map<String, Object> getTargetDataItemByCondition(String attributeValue) {
        return null;
    }

    /**
     * //TODO 批量推送，不是必须实现
     *
     * @param items
     * @param context
     * @return com.idsmanager.idp.sync.core.infrastructure.ApiResult
     * @date 2021/12/10 18:59
     **/
    @Override
    public ApiResult batchPush(List<TargetDataItem> items, DataTransformContext context) throws SCIMException {
        return null;
    }

    /**
     * 支持组织和用户同步，不需要修改
     */
    @Override
    public boolean support(SyncObjectType type) {
        return type == SyncObjectType.ORGANIZATION || type == SyncObjectType.USER;
    }

    /**
     * 执行对 TargetDataPushClient 对象的初始化
     * 里边比如可以初始化HttpClient连接或者LDAP连接信息等（具体取决于不同的插件）。
     * 当目标插件的配置发生变更后，引擎会重新构建 TargetDataPushClient  对象，构建之前会调用旧对象的 destroy方法 ，保证内存等资源的释放
     * <p>
     * 按需实现
     *
     * @param
     * @return void
     * @date 2021/12/10 19:05
     **/
    @Override
    public void initialize() throws SCIMException {
    }

    /**
     * 执行对 TargetDataPushClient 对象的销毁
     * <p>
     * 按需实现
     *
     * @param
     * @return void
     * @date 2021/12/10 19:05
     **/
    @Override
    public void destroy() {
    }

    /**
     * 所支持的属性描述
     *
     * @param syncObjectType 实体类型(User或Organization)
     * @return List<AttributeDescriptor> 属性描述列表
     */
    private DemoTargetDefaultAttributeDefiner attribute = new DemoTargetDefaultAttributeDefiner();

    @Override
    public Collection<AttributeDescriptor> listSupportedAttributes(SyncObjectType syncObjectType) {
        return attribute.define(syncObjectType);
    }
}
