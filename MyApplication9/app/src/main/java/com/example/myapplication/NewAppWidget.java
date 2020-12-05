package com.example.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
       // if ("RefreshImage"== intent.getAction())
        {
            AppWidgetManager appWidgetManager= AppWidgetManager.getInstance(context);
            RemoteViews remoteViews= new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
            ComponentName componentName = new ComponentName(context, NewAppWidget.class);
            remoteViews.setImageViewResource(R.id.imageButton,RandomImage());
            Toast.makeText(context,"!!!",Toast.LENGTH_LONG).show();
            appWidgetManager.updateAppWidget(componentName,remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        ComponentName componentName = new ComponentName(context, NewAppWidget.class);

        //remoteViews.setImageViewResource(R.id.imageButton, R.drawable.coin1);
        remoteViews.setOnClickPendingIntent(R.id.imageButton,
                getPendingSelfIntent(context, "RefreshImage"));
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }


    public int RandomImage()
    {
        Random rnd = new Random();
        int tmp =rnd.nextInt();
        if (tmp%2==0)
            return R.drawable.coin1;
        else
            return R.drawable.coin2;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}