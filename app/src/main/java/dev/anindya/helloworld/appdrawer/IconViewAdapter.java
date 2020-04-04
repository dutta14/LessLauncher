package dev.anindya.helloworld.appdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.anindya.helloworld.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IconViewAdapter extends RecyclerView.Adapter<IconViewAdapter.ViewHolder> {

    private final List<AppInfo> mAppsList;

    IconViewAdapter(@NonNull final Context context) {
        mAppsList = new ArrayList<>();

        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        final PackageManager pm = context.getPackageManager();
        for (final ResolveInfo resolveInfo: pm.queryIntentActivities(intent, 0)) {
            final AppInfo app = new AppInfo(resolveInfo.loadLabel(pm),
                    resolveInfo.activityInfo.packageName,
                    resolveInfo.activityInfo.loadIcon(pm));
            mAppsList.add(app);
        }
        Collections.sort(mAppsList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        //This is what adds the code we've written in here to our target view
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.app_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //Here we use the information in the list we created to define the views
        viewHolder.setImage(mAppsList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mAppsList.size();
    }

    /**
     * The {@link RecyclerView.ViewHolder} for the icon.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView mImageView;

        private ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //Finds the views from our row.xml
            mImageView = itemView.findViewById(R.id.iconImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(@NonNull final View view) {
            final int position = getAdapterPosition();
            final Context context = view.getContext();

            final Intent launchIntent = context.getPackageManager()
                    .getLaunchIntentForPackage(mAppsList.get(position).getPackageName().toString());
            context.startActivity(launchIntent);
        }

        void setImage(@NonNull final Drawable drawable) {
            mImageView.setImageDrawable(drawable);
        }
    }
}
