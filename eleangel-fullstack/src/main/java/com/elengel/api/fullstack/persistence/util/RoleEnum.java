package com.elengel.api.fullstack.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.CREATE_ONE_PRODUCT,
            RolePermissionEnum.UPDATE_ONE_PRODUCT,
            RolePermissionEnum.DISABLE_ONE_PRODUCT,
            RolePermissionEnum.READ_ONE_PRODUCT,

            RolePermissionEnum.READ_ALL_CATEGORIES,
            RolePermissionEnum.CREATE_ONE_CATEGORY,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,
            RolePermissionEnum.READ_ONE_CATEGORY,
            RolePermissionEnum.DISABLE_ONE_CATEGORY,

            RolePermissionEnum.READ_MY_PROFILE

    )),
    ASSITANT(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.UPDATE_ONE_PRODUCT,
            RolePermissionEnum.READ_ONE_PRODUCT,

            RolePermissionEnum.READ_ALL_CATEGORIES,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,
            RolePermissionEnum.READ_ONE_CATEGORY,

            RolePermissionEnum.READ_MY_PROFILE
    )),
    CUSTOMER(Arrays.asList(
            RolePermissionEnum.READ_MY_PROFILE
    )),
    GUARDIA(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.READ_ALL_CATEGORIES,
            RolePermissionEnum.READ_MY_PROFILE

    ));

    public List<RolePermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }

    private List<RolePermissionEnum>  permissions;
    RoleEnum(List<RolePermissionEnum> permissions){
        this.permissions=permissions;
    }

}
