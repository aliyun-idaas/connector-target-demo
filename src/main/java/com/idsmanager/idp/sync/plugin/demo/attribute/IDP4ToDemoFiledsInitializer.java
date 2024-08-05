package com.idsmanager.idp.sync.plugin.demo.attribute;

import com.idsmanager.idp.sync.MajorType;
import com.idsmanager.idp.sync.MinorType;
import com.idsmanager.idp.sync.SyncObjectType;
import com.idsmanager.idp.sync.core.dto.template.SyncFieldMappingObject;
import com.idsmanager.idp.sync.core.fields.FieldMappingTemplateInitializer;
import com.idsmanager.idp.sync.plugin.demo.business.MetaBaseConstant;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 选择该同步类型后，默认上下游匹配映射的字段配置
 * ganyu
 */
@Component
public class IDP4ToDemoFiledsInitializer extends FieldMappingTemplateInitializer {

    public IDP4ToDemoFiledsInitializer() {
        super(MajorType.SCIM.name(), MinorType.IDP4_SCHEMA.name(),
                MajorType.APP_STANDARD.name(), MetaBaseConstant.MINORTYPE_NAME);
    }

    @Override
    public String getName() {
        return MetaBaseConstant.MINORTYPE_INITIALIZER_NAME;
    }

    @Override
    public String getDescription() {
        return MetaBaseConstant.MINORTYPE_INITIALIZER_DESCRITION;
    }

    @Override
    public void handleFieldMappings(List<SyncFieldMappingObject> mappings) {
        //人员
        mappings.add(createFieldMappingObject("人员唯一标识", "人员唯一标识", "externalId", "IDP人员外部id", "userCode", "账户唯一标识id", SyncObjectType.USER));
        mappings.add(createFieldMappingObject("显示名称", "显示名称", "displayName", "IDP人员显示名称", "userName", "账户名称", SyncObjectType.USER));
        mappings.add(createFieldMappingObject("账号", "账号", "userName", "IDP人员账号", "accountId", "账户账号", SyncObjectType.USER));
        mappings.add(createFieldMappingObject("描述信息", "描述信息", "description", "IDP描述信息", "description", "描述信息", SyncObjectType.USER));
        mappings.add(createFieldMappingObject("手机", "手机", "phoneNumber", "IDP人员手机号", "mobile", "手机号", SyncObjectType.USER));

        //组织部门
        mappings.add(createFieldMappingObject("部门名称", "部门名称", "organizationName", "IDP部门名称", "name", "部门名称", SyncObjectType.ORGANIZATION));
        mappings.add(createFieldMappingObject("部门唯一标识", "部门唯一标识", "externalId", "IDP部门唯一标识", "deptId", "部门id", SyncObjectType.ORGANIZATION));
        mappings.add(createFieldMappingObject("上级部门唯一标识", "上级部门唯一标识", "parentExternalId", "IDP上级部门唯一标识", "parentId", "父部门ID", SyncObjectType.ORGANIZATION));
        mappings.add(createFieldMappingObject("排序号", "排序号", "sortNumber", "排序号","order", "排序号",SyncObjectType.ORGANIZATION));

        //组
        mappings.add(createFieldMappingObject("组外部ID", "组外部ID", "externalId", "组外部ID", "roleId", "组唯一ID", SyncObjectType.GROUP));
        mappings.add(createFieldMappingObject("组名称", "组名称", "displayName", "组名称", "roleName", "组名称", SyncObjectType.GROUP));
        mappings.add(createFieldMappingObject("所属组织机构ID", "所属组织机构ID", "ouExternalId", "所属组织机构ID", "groupId", "组所属组织唯一ID", SyncObjectType.GROUP));
        mappings.add(createFieldMappingObject("存量成员列表", "存量成员列表", "members", "存量成员列表", "members", "组存量成员列表", SyncObjectType.GROUP));
        mappings.add(createFieldMappingObject("添加成员列表", "添加成员列表", "addMembers", "添加成员列表", "addMembers", "组添加成员列表", SyncObjectType.GROUP));
        mappings.add(createFieldMappingObject("移除成员列表", "移除成员列表", "deleteMembers", "移除成员列表", "deleteMembers", "组移除成员列表", SyncObjectType.GROUP));
    }


}
