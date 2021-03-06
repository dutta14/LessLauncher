package dev.anindya.helloworld.appdrawer;

import static android.content.Intent.ACTION_PACKAGE_ADDED;
import static android.content.Intent.ACTION_PACKAGE_REMOVED;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
        new AppsLoader(context).execute();
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        //Here we use the information in the list we created to define the views
        viewHolder.setImage(mAppsList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mAppsList.size();
    }

    private void updateList() {
        notifyDataSetChanged();
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

            //Set a grayscale filter for the icon.
            final ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            mImageView.setColorFilter(new ColorMatrixColorFilter(matrix));

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

    private class AppsLoader extends AsyncTask<Void, Void, Void> {

        private final Context mContext;

        private AppsLoader(@NonNull final Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(@NonNull final Void... voids) {
            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            final PackageManager pm = mContext.getPackageManager();
            for (final ResolveInfo resolveInfo: pm.queryIntentActivities(intent, 0)) {
                mAppsList.add(new AppInfo(resolveInfo, pm));
                publishProgress();
            }
            Collections.sort(mAppsList);
            return null;
        }

        @Override
        protected void onProgressUpdate(@NonNull final Void... values) {
            super.onProgressUpdate(values);
            updateList();
        }

        @Override
        protected void onPostExecute(@NonNull final Void ignored) {
            super.onPostExecute(ignored);
            updateList();
        }
    }

    class AppListenerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(@NonNull final Context context, @NonNull final Intent intent) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case ACTION_PACKAGE_ADDED:
                    case ACTION_PACKAGE_REMOVED:
                        trackPackageChanged(context);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void trackPackageChanged(@NonNull final Context context) {
        new AppsLoader(context).execute();
        updateList();
    }
}
