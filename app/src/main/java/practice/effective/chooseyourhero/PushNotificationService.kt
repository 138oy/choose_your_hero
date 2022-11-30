package practice.effective.chooseyourhero

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class PushNotificationService : FirebaseMessagingService() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            sendNotification()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "NOTIFICATIONS_CHANNEL"

        createChannel(channelId, notificationManager)

        val deepLinkIntent = Intent(
            Intent.ACTION_VIEW,
            "https://app.com/1011334".toUri(),
        )

        val deepLinkPendingIntent: PendingIntent? =
            TaskStackBuilder.create(this).run {
                addNextIntentWithParentStack(deepLinkIntent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

        val title = getString(R.string.notification_title)
        val text = getString(R.string.notification_body)
        val smallIcon = R.drawable.ic_launcher_foreground

        val notification: Notification.Builder = Notification.Builder(this, channelId)
            .setContentIntent(deepLinkPendingIntent)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(smallIcon)
            .setAutoCancel(true)


        notificationManager.notify(1, notification.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(channelId: String, notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            channelId,
            "NotificationsChannel",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channel)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}