package dev.anindya.helloworld.appdrawer;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

/**
 * The {@link AppInfo} contains details about an application.
 */
class AppInfo implements Comparable<AppInfo> {

    private final CharSequence mLabel;
    private final CharSequence mPackageName;
    private final Drawable mIcon;

    /**
     * Create an {@link AppInfo}.
     *
     * @param resolveInfo    A {@link ResolveInfo} to get the {@link AppInfo}.
     * @param packageManager A {@link PackageManager} to resolve the information.
     */
    AppInfo(@NonNull final ResolveInfo resolveInfo,
            @NonNull final PackageManager packageManager) {
        this(resolveInfo.loadLabel(packageManager),
                resolveInfo.activityInfo.packageName,
                resolveInfo.activityInfo.loadIcon(packageManager));
    }

    /**
     * Create an {@link AppInfo}.
     *
     * @param label       The label of the app.
     * @param packageName The package name of the app.
     * @param icon        The icon for the app.
     */
    @VisibleForTesting
    AppInfo(@NonNull final CharSequence label,
            @NonNull final CharSequence packageName,
            @NonNull final Drawable icon) {
        mLabel = label;
        mPackageName = packageName;
        mIcon = icon;
    }

    /**
     * Returns the icon for the {@link AppInfo}.
     *
     * @return the icon {@link Drawable} for the {@link AppInfo}.
     */
    @NonNull
    Drawable getIcon() {
        return mIcon;
    }

    /**
     * Returns the package name for the {@link AppInfo}.
     *
     * @return the package name for the {@link AppInfo}.
     */
    @NonNull
    CharSequence getPackageName() {
        return mPackageName;
    }

    @Override
    public int compareTo(@NonNull final AppInfo appInfo) {
        return mLabel.toString().toLowerCase().compareTo(appInfo.mLabel.toString().toLowerCase());
    }
}
