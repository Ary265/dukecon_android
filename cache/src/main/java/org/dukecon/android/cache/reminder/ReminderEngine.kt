package org.dukecon.android.cache.reminder

interface ReminderEngine {
    fun triggerEvent(id: String)
    fun removeReminder(id: String)

}
