package com.hao.gomall_core.delegates;

public abstract class MallDelegate extends PermissionCheckedDelegate {

    @SuppressWarnings("unchecked")
    public <T extends MallDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
