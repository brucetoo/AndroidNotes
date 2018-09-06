package com.brucetoo.androidnotes.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by Bruce Too
 * On 06/09/2018.
 * At 11:47
 */
public class RebootAccessibility extends AccessibilityService {

    private static final String REBOOT_EN = "reboot";
    private static final String REBOOT_CN = "重启";
    private static final String TOUCH_TO_REBOOT_EN = "touch to reboot";
    private static final String TOUCH_TO_REBOOT_CN = "轻触重启";
    private static final String REBOOT_IMAGE_ID = "com.oneplus:id/zzz_entry_icon";
    private static final String ANDROID = "android";
    private static final String FRAME_LAYOUT = "android.widget.FrameLayout";
    private static final String TEXT_VIEW = "android.widget.TextView";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("RebootAccessibility", "onAccessibilityEvent -> "
                + AccessibilityEvent.eventTypeToString(event.getEventType())
                + "  " + event.getPackageName() + "  " + event.getClassName());
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                if (event.getPackageName().equals(ANDROID) && event.getClassName().equals(FRAME_LAYOUT)) {
                    performFirstClick();//第一次点击
                }
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                if (event.getPackageName().equals(ANDROID) && event.getClassName().equals(TEXT_VIEW)) {
                    performSecondClick();//第二次点击
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void performFirstClick() {
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(REBOOT_IMAGE_ID);
        List<AccessibilityNodeInfo> enReboot = getRootInActiveWindow().findAccessibilityNodeInfosByText(REBOOT_EN);
        List<AccessibilityNodeInfo> cnReboot = getRootInActiveWindow().findAccessibilityNodeInfosByText(REBOOT_CN);

        if (!isListEmpty(nodes)
                && (!isListEmpty(enReboot) || !isListEmpty(cnReboot))
                && nodes.size() == 2) {
            AccessibilityNodeInfo reboot = nodes.get(1);
            reboot.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void performSecondClick() {
        List<AccessibilityNodeInfo> enRebootAgain = getRootInActiveWindow().findAccessibilityNodeInfosByText(TOUCH_TO_REBOOT_EN);
        List<AccessibilityNodeInfo> cnRebootAgain = getRootInActiveWindow().findAccessibilityNodeInfosByText(TOUCH_TO_REBOOT_CN);

        if(!isListEmpty(enRebootAgain) && enRebootAgain.size() == 1){//英文版
            AccessibilityNodeInfo child = enRebootAgain.get(0).getParent().getChild(0);
            child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }else if(!isListEmpty(cnRebootAgain) && cnRebootAgain.size() == 1){//中文版
            AccessibilityNodeInfo child = cnRebootAgain.get(0).getParent().getChild(0);
            child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    private boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }


    @Override
    public void onInterrupt() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
