package com.xpsoft.xpxDroid.aaa.di.component;

import dagger.Component;

import com.xpsoft.xpxDroid.core.base.IBaseView;
import com.xpsoft.xpxDroid.lib.base.di.component.AppComponent;
import com.xpsoft.xpxDroid.lib.base.di.module.DialogModule;
import com.xpsoft.xpxDroid.lib.base.di.module.UiModule;
import com.xpsoft.xpxDroid.lib.base.di.scope.RetrofitScope;
import com.xpsoft.xpxDroid.lib.base.di.scope.UIScope;
import com.xpsoft.xpxDroid.aaa.di.module.AaaModule;

import com.xpsoft.xpxDroid.aaa.ui.activity.AaaActivity;



/**
 * ************************************************************
 * Author: XPSoft
 * CreateTime: 2020-04-01 23:49:43
 * PackageName: com.xpsoft.xpxDroid.aaa.di.component
 * Description:
 * ************************************************************
 */
@RetrofitScope
@UIScope
@Component(dependencies = AppComponent.class, modules = {UiModule.class, AaaModule.class, DialogModule.class})
public interface AaaComponent {
        void inject(AaaActivity aaaActivity);
    
    }
