package org.dukecon.android.cache.reminder


import com.evernote.android.job.Job
import com.evernote.android.job.JobRequest
import com.evernote.android.job.util.support.PersistableBundleCompat
import org.dukecon.data.model.EventEntity

class ReminderJob(private val reminderEngine: ReminderEngine) : Job() {

    override fun onRunJob(params: Job.Params): Job.Result {
        val id = params.extras.getString(EXTRA_ID, "")
        if (id.isEmpty()) {
            return Job.Result.FAILURE
        }

        reminderEngine.triggerEvent(id)
        reminderEngine.removeReminder(id)

        return Job.Result.SUCCESS
    }

    companion object {

        val TAG = "ReminderJob"

        private val EXTRA_ID = "EXTRA_ID"

        /*package*/
        internal fun schedule(reminder: EventEntity): Int {
            val extras = PersistableBundleCompat()
            extras.putString(EXTRA_ID, reminder.id)

            val time = Math.max(1L, reminder.startTime.millis - System.currentTimeMillis())

            return JobRequest.Builder(TAG)
                    .setExact(time)
                    .setExtras(extras)
                    .setUpdateCurrent(false)
                    .build()
                    .schedule()
        }
    }
}

