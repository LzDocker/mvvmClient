package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.docker.moduleplayer.ui.index.PlayerMainActivity;
import java.lang.Override;
import java.lang.String;
import java.util.Map;

/**
 * DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY AROUTER. */
public class ARouter$$Group$$ModulePlayer implements IRouteGroup {
  @Override
  public void loadInto(Map<String, RouteMeta> atlas) {
    atlas.put("/ModulePlayer/ui/main", RouteMeta.build(RouteType.ACTIVITY, PlayerMainActivity.class, "/moduleplayer/ui/main", "moduleplayer", null, -1, -2147483648));
  }
}
