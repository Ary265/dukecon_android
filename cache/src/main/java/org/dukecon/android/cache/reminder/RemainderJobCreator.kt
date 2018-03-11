package org.dukecon.android.cache.reminder

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator


class ReminderJobCreator(private val reminderEngine: ReminderEngine) : JobCreator {

    override fun create(tag: String): Job? {
        when (tag) {
            ReminderJob.TAG -> return ReminderJob(reminderEngine)

        /*          case SyncJob.TAG:
                return new SyncJob();
                */

            else -> return null
        }
    }
}
