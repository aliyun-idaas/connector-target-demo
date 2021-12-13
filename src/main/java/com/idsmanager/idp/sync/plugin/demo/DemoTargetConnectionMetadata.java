package com.idsmanager.idp.sync.plugin.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idsmanager.idp.sync.InvalidConfigException;
import com.idsmanager.idp.sync.MajorType;
import com.idsmanager.idp.sync.MinorType;
import com.idsmanager.idp.sync.SyncObjectType;
import com.idsmanager.idp.sync.core.infrastructure.ConnectionMetadataImpl;
import com.idsmanager.idp.sync.core.infrastructure.ConnectionTestResult;
import com.idsmanager.idp.sync.core.infrastructure.mapping.AttributeDescriptor;
import com.idsmanager.idp.sync.core.infrastructure.mapping.AttributeSetter;
import com.idsmanager.idp.sync.core.infrastructure.source.SourceDataPullClient;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetConnectionConfiguration;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetConnectionMetadata;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetConnectionPlugin;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetDataPushClient;
import com.idsmanager.idp.sync.plugin.demo.attribute.DemoAttributeSetter;
import com.idsmanager.idp.sync.plugin.demo.attribute.DemoTargetDefaultAttributeDefiner;
import com.idsmanager.idp.sync.plugin.demo.business.source.DemoSourceClientConfiguration;
import com.idsmanager.idp.sync.plugin.demo.business.target.DemoTargetClientConfiguration;
import com.idsmanager.micro.commons.web.filter.RIDHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021/12/9 11:14
 * <p> 目标系统连接的基础方法类，同步引擎根据目标系统提供的文档实现各种查询方法。
 * 定义目标的类型，
 *
 * @author xbj
 * @class DemoTargetConnectionMetadata
 * @since
 */
@TargetConnectionPlugin
public class DemoTargetConnectionMetadata extends ConnectionMetadataImpl implements TargetConnectionMetadata {

    private static final Logger LOG = LoggerFactory.getLogger(DemoTargetConnectionMetadata.class);

    /**
     * 自定义主类型和子类型
     * 一般情况下，定义子类型即可，注意，子类型需要以 _SCHEMA 结尾，才能正常获取到表单json信息
     */
    public DemoTargetConnectionMetadata() {
        super(MajorType.APP_STANDARD.name(), MinorType.DEMO_SCHEMA.name());
    }

    @Override
    public TargetDataPushClient build(TargetConnectionConfiguration configuration) throws InvalidConfigException {
        return null;
    }

    @Override
    public AttributeSetter getAttributeSetter() {
        return new DemoAttributeSetter();
    }

    /**
     * 校验创建目标时，提交的表单json参数
     */
    @Override
    public void validate(String json) throws InvalidConfigException {
        doValidate(json);
    }


    private JSONObject doValidate(String json) throws InvalidConfigException {
        //第一步：非空校验
        if (StringUtils.isBlank(json)) {
            throw new InvalidConfigException("配置信息不能为空");
        }
        //第二步：key校验
        JSONObject config = (JSONObject) JSONObject.parse(json);

        //TODO: 修改具体的必填参数名
        String[] dbKey = {"baseUrl"};
        for (String key : dbKey) {
            //开发时打开校验
            ensureParamNotNull(key, config.getString(key));
        }
        return config;
    }

    /**
     * TODO 修改测试目标系统连接
     * 测试数据源接口或者数据库等是否能连通
     **/
    @Override
    public ConnectionTestResult testConnection(String json) {
        try {
            DemoTargetClientConfiguration configuration = parseJsonConfig(json);

            //编写测试连接的代码，
            return new ConnectionTestResult(true, "测试连接成功");

        } catch (Exception e) {
            LOG.warn("[{}]-Test Connect error", RIDHolder.id(), e);
            return new ConnectionTestResult(false, e.getMessage());
        }
    }

    /**
     * //TODO 解析前端传递的配置信息(前端将整个数据源配置对象转为了json字符串传到后端，后端需要自己解析得到具体参数值)
     *
     **/
    private DemoTargetClientConfiguration parseJsonConfig(String jsonConfig) throws InvalidConfigException {
        JSONObject json = doValidate(jsonConfig);
        DemoTargetClientConfiguration configuration = new DemoTargetClientConfiguration();
        configuration.setJsonConfig(jsonConfig);
        //TODO 具体的参数
        String baseUrl = json.getString("baseUrl");
        configuration.setBaseUrl(baseUrl);


        return configuration;
    }

    /**
     * TODO: 修改DemoTargetDefaultAttributeDefiner中预设的目标字段
     * 在配置源和目标的属性映射时，首先要获取源默认支持哪些属性，该方法用于获取源默认支持的属性集合。
     * 在实际配置时，一个源的具体配置实例，支持的属性是可能与默认支持的属性集合不同的，比如IDP中的用
     * 户如果不加的数据字典，默认只支持：账号、姓名、手机、邮箱、排序编码，但是扩展字典可以随意扩展属
     * 性。获取具体实例的支持的属性，请调用
     * {@link TargetDataPushClient#listSupportedAttributes(SyncObjectType)} 方法。
     *
     * @param syncObjectType 对象类型
     * @return 支持的属性对象集合
     */
    private DemoTargetDefaultAttributeDefiner attribute = new DemoTargetDefaultAttributeDefiner();

    @Override
    public Collection<AttributeDescriptor> listDefaultSupportedAttributes(SyncObjectType syncObjectType) {
        return attribute.define(syncObjectType);
    }
}
