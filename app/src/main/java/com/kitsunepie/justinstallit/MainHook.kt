package com.kitsunepie.justinstallit

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.getMethodBySig
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName.contains("com.miui.packageinstaller")) {
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag("JustInstallIt")
            hook()
        }
    }

    private fun hook() {
        getMethodBySig("Lcom/xiaomi/market/data/b\$b;->u()I").also {
            it.hookAfter { param ->
                param.result = 1
            }
        }
    }
}