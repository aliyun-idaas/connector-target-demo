package com.idsmanager.idp.sync.plugin.demo.attribute;

import com.idsmanager.idp.sync.SCIMException;
import com.idsmanager.idp.sync.SyncErrorCode;
import com.idsmanager.idp.sync.core.infrastructure.mapping.AttributeSetter;
import com.idsmanager.idp.sync.core.infrastructure.mapping.FieldAttributeGetter;
import com.idsmanager.idp.sync.core.infrastructure.mapping.FieldAttributeSetter;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetDataItem;
import com.idsmanager.idp.sync.plugin.demo.entity.target.DemoTargetAccountEntity;
import com.idsmanager.idp.sync.plugin.demo.entity.target.DemoTargetOrganizationEntity;
import com.idsmanager.micro.commons.web.filter.RIDHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021/12/10 15:48
 * <p> 属性的设置  一般不需要修改
 * 注释掉的部分为扩展字段相关的set，有需要时，按需启用并修改
 *
 * @author xbj
 * @class DemoAttributeSetter
 * @since
 */
public class DemoAttributeSetter implements AttributeSetter {

    private static Logger LOG = LoggerFactory.getLogger(DemoAttributeSetter.class);

    public static final String extendAttributePrefix = "extendField.";

    private FieldAttributeSetter setter = new FieldAttributeSetter();

    /**
     * 某具体属性值获取
     *
     * @param item          源数据条目对象
     * @param attributeName 属性名
     * @return Object 属性值
     */
    @Override
    public TargetDataItem setAttribute(TargetDataItem item, String attributeName, Object... attributeValue) throws SCIMException {
        if (item == null || StringUtils.isBlank(attributeName)) {
            throw new SCIMException(SyncErrorCode.SCIM_ERR_MAPPING_GET_ATTRIBUTE, "属性映射错误，目标对象为空或者属性名为空");
        }
        if (!(item instanceof DemoTargetAccountEntity) && !(item instanceof DemoTargetOrganizationEntity)) {
            throw new SCIMException(SyncErrorCode.SCIM_ERR_MAPPING_GET_ATTRIBUTE, "属性映射错误，设置属性失败，不支持的对象实例[" + item.getClass() + "]");
        }
        handleCommonAttribute(item, attributeName, attributeValue);
        return item;
    }

    private void handleCommonAttribute(TargetDataItem entity, String attributeName, Object... attributeValues) {
//        if (!putExtendAttributes(entity, attributeName, attributeValues)) {
            setter.set(entity, attributeName, attributeValues[0]);
//        }
    }

    /*public boolean putExtendAttributes(TargetDataItem entity, String attributeName, Object[] attributeValues) {
        if (attributeName.startsWith(extendAttributePrefix)) {
            attributeName = attributeName.substring(extendAttributePrefix.length());
            Map<String, Object> extendFields = createIfNullAndGetExtendField(entity);
            for (Object object : attributeValues) {
                extendFields.put(attributeName, object);
            }
            return true;
        }
        return false;
    }*/

    /*private Map<String, Object> createIfNullAndGetExtendField(TargetDataItem entity) {
        Map<String, Object> extendFields = null;
        if (entity instanceof DemoTargetOrganizationEntity) {
            DemoTargetOrganizationEntity deptEntity = (DemoTargetOrganizationEntity) entity;
            extendFields = deptEntity.getExt();
            if (extendFields == null) {
                extendFields = new HashMap<>();
                deptEntity.setExt(extendFields);
            }
        } else {
            DemoTargetAccountEntity userEntity = (DemoTargetAccountEntity) entity;
            extendFields = userEntity.getExtattr();
            if (extendFields == null) {
                extendFields = new HashMap<>();
                userEntity.setExtattr(extendFields);
            }
        }

        return extendFields;
    }*/
}
