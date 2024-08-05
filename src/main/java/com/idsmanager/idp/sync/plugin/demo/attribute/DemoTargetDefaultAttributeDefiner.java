package com.idsmanager.idp.sync.plugin.demo.attribute;

import com.idsmanager.idp.sync.SyncObjectType;
import com.idsmanager.idp.sync.core.infrastructure.mapping.AttributeDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021/12/10 14:16
 * <p>预设的同步字段
 * attributeName为目标系统的具体字段名称，根据实际情况增删
 *
 * @author xbj
 * @class DemoTargetDefaultAttributeDefiner
 * @since
 */
public class DemoTargetDefaultAttributeDefiner {

    /**
     * 支持的属性列表
     *
     * @param syncObjectType 同步类型
     * @return list返回默认支持的属性列表
     */
    public List<AttributeDescriptor> define(SyncObjectType syncObjectType) {
        List<AttributeDescriptor> attributeDescriptors = new ArrayList<>();

        //人员
        if (SyncObjectType.USER.equals(syncObjectType)) {
            //TODO: 编写完全，以下两个为示例
            //用户编码
            AttributeDescriptor userCode = new AttributeDescriptor("userCode", "用户编码", "用户编码");
            attributeDescriptors.add(userCode);

            //用户名称
            AttributeDescriptor userName = new AttributeDescriptor("userName", "用户名称", "用户名称");
            attributeDescriptors.add(userName);

            //描述
            AttributeDescriptor description = new AttributeDescriptor("description", "描述", "描述");
            attributeDescriptors.add(description);

            //账号
            AttributeDescriptor accountId = new AttributeDescriptor("accountId", "账号", "账号");
            attributeDescriptors.add(accountId);

            //描述
            AttributeDescriptor mobile = new AttributeDescriptor("mobile", "手机号码", "手机号码");
            attributeDescriptors.add(mobile);


        }

        //组织(即部门)
        if (SyncObjectType.ORGANIZATION.equals(syncObjectType)) {
            //TODO: 组织同上
            //部门名称
            AttributeDescriptor pkDept = new AttributeDescriptor("name", "部门名称", "部门名称");
            attributeDescriptors.add(pkDept);

            //组织、部门唯一主键
            AttributeDescriptor deptId = new AttributeDescriptor("deptId", "组织、部门唯一主键", "组织、部门唯一主键");
            attributeDescriptors.add(deptId);

            //部门名称
            AttributeDescriptor parentId = new AttributeDescriptor("parentId", "部门所属上级id", "部门所属上级id");
            attributeDescriptors.add(parentId);

            //部门名称
            AttributeDescriptor order = new AttributeDescriptor("order", "排序号", "排序号");
            attributeDescriptors.add(order);


        }

        //组群
        if (SyncObjectType.GROUP.equals(syncObjectType)) {
            //TODO: 组群同上
            //组唯一ID
            AttributeDescriptor roleId = new AttributeDescriptor("roleId", "组唯一ID", "组唯一ID");
            attributeDescriptors.add(roleId);

            //组名称
            AttributeDescriptor roleName = new AttributeDescriptor("roleName", "组名称", "组名称");
            attributeDescriptors.add(roleName);

            //组所属组织唯一ID
            AttributeDescriptor groupId = new AttributeDescriptor("groupId", "组所属组织唯一ID", "组所属组织唯一ID");
            attributeDescriptors.add(groupId);

            //组存量成员列表
            AttributeDescriptor members = new AttributeDescriptor("members", "组存量成员列表", "组存量成员列表");
            attributeDescriptors.add(members);

            //组添加成员列表
            AttributeDescriptor addMembers = new AttributeDescriptor("addMembers", "组添加成员列表", "组添加成员列表");
            attributeDescriptors.add(addMembers);

            //组移除成员列表
            AttributeDescriptor deleteMembers = new AttributeDescriptor("deleteMembers", "组移除成员列表", "组移除成员列表");
            attributeDescriptors.add(deleteMembers);


        }

        return attributeDescriptors;
    }
}
