package com.idsmanager.idp.sync.plugin.demo.entity.target;

import com.idsmanager.idp.sync.SyncObjectType;
import com.idsmanager.idp.sync.core.infrastructure.target.TargetDataItem;

/**
 * 2021/12/10 11:01
 * <p> 目标用户实体类
 *
 * @author xbj
 * @class DemoTargetAccountEntity
 * @since
 */
public class DemoTargetAccountEntity extends DemoTargetBaseEntity implements TargetDataItem {

    /**
     * 用户id
     **/
    private String userCode;

    private String parentOUId;

    private String userName;

    private String mobile;

    private String description;

    private String accountId;

    /**
     * TODO 唯一标识字段
     **/
    @Override
    public String uniqueId() {
//        return userId;
        return null;
    }

    /**
     * TODO 唯一标识的字段名
     **/
    @Override
    public String uniqueIdType() {
//        return "userId";
        return null;
    }

    /**
     * TODO 用户显示名称字段
     **/
    @Override
    public String displayName() {
        return null;
    }

    @Override
    public SyncObjectType objectType() {
        return SyncObjectType.USER;
    }

    /**
     * TODO 用户启用状态字段
     **/
    @Override
    public boolean isEnabled() {
//        return enabled;
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
    }

    /**
     * TODO 用户所属组织唯一标识字段
     **/
    @Override
    public String getParentUniqueId() {
        return null;
    }

    /**
     * 是否支持失败重推
     **/
    @Override
    public boolean isSupportRePush() {
        return false;
    }

    public String getParentOUId() {
        return parentOUId;
    }

    public void setParentOUId(String parentOUId) {
        this.parentOUId = parentOUId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
